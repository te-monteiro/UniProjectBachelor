
public class MainTwitterHappy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwitterHappy user = new TwitterHappy();
		 System.out.println(user.getNumberOfMessages(TwitterHappy.MORNING));
		 System.out.println(user.isHappy());
		 System.out.println(user.isFullyHappy());
		 user.sendMessage(TwitterHappy.MORNING);
		 user.sendMessage(TwitterHappy.MORNING);
		 user.sendMessage(TwitterHappy.MORNING);
		 user.sendMessage(TwitterHappy.MORNING);
		 user.sendMessage(TwitterHappy.MORNING);
		 user.sendMessage(TwitterHappy.MORNING);
		 user.sendMessage(TwitterHappy.MORNING);
		 user.sendMessage(TwitterHappy.AFTERNOON);
		 user.sendMessage(TwitterHappy.AFTERNOON);
		 System.out.println(user.getMessagesMorning());
		 System.out.println(user.getMessagesAfternoon());
		 System.out.println(user.getMessagesNight());
		 System.out.println(user.isHappy());
		 System.out.println(user.isFullyHappy());
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 user.sendMessage(TwitterHappy.NIGHT);
		 System.out.println(user.isHappy());
		 System.out.println(user.isFullyHappy());
		 System.out.println(user.getMessagesMorning());
		 System.out.println(user.getMessagesAfternoon());
		 System.out.println(user.getMessagesNight());
		 user.reset();
		 user.sendMessage(TwitterHappy.AFTERNOON);
		 System.out.println(user.getMessagesMorning());
		 System.out.println(user.getMessagesAfternoon());
		 System.out.println(user.getMessagesNight());
	}

}
