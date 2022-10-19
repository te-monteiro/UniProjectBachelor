package sd1920.trab2.server;

import java.net.URI;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class ComparatorByTime implements Comparator<Map.Entry<URI, Long>> {

	@Override
	public int compare(Entry<URI, Long> o1, Entry<URI, Long> o2) {
		return -o1.getValue().compareTo(o2.getValue());
	}
}
