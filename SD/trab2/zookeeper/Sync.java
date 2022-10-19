package sd1920.trab2.zookeeper;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import sd1920.trab2.api.restRep.MessageService;
import sd1920.trab2.api.restRep.UserService;
import sd1920.trab2.clients.MessagesEmailClientRep;
import sd1920.trab2.clients.UsersEmailClientRep;
import sd1920.trab2.clients.rest.EmailClientRest;
import sd1920.trab2.clients.rest.MessageClientRest;
import sd1920.trab2.clients.rest.UserClientRest;

public class Sync {

	private ZookeeperProcessor zk;
	private String domain;
	private String primaryURI;
	private String serverURI;
	private String secret;
	private List<Operation> history;
	private UserService users;
	private MessageService messages;
	private Map<String, EmailClientRest[]> reps;

	private long version;

	public Sync(ZookeeperProcessor zk, String domain, String serverURI, String secret) {
		version = -1L;
		this.zk = zk;
		this.domain = domain;
		this.serverURI = serverURI;
		history = new LinkedList<>();
		reps = new HashMap<>();
		this.secret = secret;
		runZookeeper();
	}

	private void runZookeeper() {

		String path = "/" + domain;
		String newPath = zk.write(path, CreateMode.PERSISTENT);

		zk.getChildren(path, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				List<String> lst = zk.getChildren(path, this);
				Collections.sort(lst);
				String leader = zk.getURI(path + "/" + lst.get(0));
				if (!leader.equals(getLeader()))
					updateLeader(leader);

				Map<String, EmailClientRest[]> aux = new HashMap<>();
				for (int i = 0; i < lst.size(); i++) {
					String uri = zk.getURI(path + "/" + lst.get(i));
					if (!uri.equals(serverURI))
						synchronized (reps) {
							if (!reps.containsKey(uri)) {
								EmailClientRest[] lista = new EmailClientRest[2];
								lista[0] = new MessageClientRest(URI.create(uri), 1, 0);
								lista[1] = new UserClientRest(URI.create(uri), 1, 0);
								aux.put(uri, lista);
							} else
								aux.put(uri, reps.get(uri));
						}
				}
				synchronized (reps) {
					reps = aux;
				}
			}
		});

		if (newPath != null)
			System.out.println("Created znode: " + newPath);

		newPath = zk.write(path + "/rep_", serverURI, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("Created child znode: " + newPath);
	}

	public synchronized long getVersion() {
		return version;
	}

	public synchronized void done() {
		version++;
	}

	public synchronized void waitForVersion(long version) {
		if (this.getVersion() < version) {
			List<Operation> ops = this.getOperations(this.getVersion(), version, this.getLeader());
			for (int i = 0; i < ops.size(); i++)
				try {
					ops.get(i).execute(messages, users, this.version);
				} catch (WebApplicationException wae) {
				}
		}
	}

	private List<Operation> getOperations(long min, long max, String uri) {
		return ((UsersEmailClientRep) reps.get(uri)[1]).getOperations(min, max, secret).getEntity();

	}

	public synchronized void updateLeader(String primary) {
		primaryURI = primary;
		if (serverURI.equals(primaryURI))
			this.getLatestVersion();
	}

	private synchronized void getLatestVersion() {
		long latest = version;
		String latestURI = "";
		Set<String> uris = reps.keySet();
		for (String uri : uris) {
			long aux = ((UsersEmailClientRep) reps.get(uri)[1]).getVersion(secret).getEntity();
			if (aux > latest) {
				latest = aux;
				latestURI = uri;
			}
		}

		if (latest > version) {
			List<Operation> ops = this.getOperations(this.version, version, latestURI);
			for (int i = 0; i < ops.size(); i++)
				ops.get(i).execute(messages, users, this.version);
		}
	}

	public synchronized String getServerURI() {
		return serverURI;
	}

	public synchronized String getLeader() {
		return primaryURI;
	}

	public synchronized boolean isLeader() {
		return primaryURI.equals(serverURI);
	}

	public synchronized List<Operation> getHistory() {
		return history;
	}

	public synchronized void addHistory(Operation op) {
		history.add(op);
	}

	public synchronized void setResources(UserService users, MessageService messages) {
		this.users = users;
		this.messages = messages;
	}

	public void replicate(Operation op) {
		List<String> done = new LinkedList<>();
		Set<String> running = reps.keySet();
		long version = this.version + 1;
		Thread t = new Thread(() -> {
			int retries = 0;
			synchronized (done) {
				while (retries < 3 && !done.containsAll(running)) {
					for (String uri : running) {
						if (!done.contains(uri) && op.replicate((MessagesEmailClientRep) reps.get(uri)[0],
								(UsersEmailClientRep) reps.get(uri)[1], version)) {
							done.add(uri);
							done.notify();
						}
					}
					retries++;
				}
			}
		});
		t.start();

		synchronized (done) {
			while (done.size() < 1 && t.isAlive())
				try {
					done.wait();
				} catch (InterruptedException e) {
				}
		}

		if (done.size() < 1)
			throw new WebApplicationException(Status.NOT_IMPLEMENTED);
	}

}
