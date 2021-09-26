package Assignment.greetings;

import java.util.Random;
import java.util.ArrayList;

public class Wish {
	private String greetType;
	private String name;
	private int noOfYears;

	public Wish(String name, String greetType, int noOfYears) {
		this.name = name;
		this.greetType = greetType;
		this.noOfYears = noOfYears;
	}

	public String getSubject() {
		switch (this.greetType) {
		case "birthday":
			return ("Happy "+this.noOfYears+"th Birthday 😊, " + this.name + "!!");
		case "anniversary":
			return ("Happy "+this.noOfYears+"th Anniversary 😊, " + this.name + "!!");

		}
		return "";
	}

	public String getText() {
		ArrayList<String> birthday = new ArrayList<String>();
		birthday.add("Many more happy returns of the day");
		birthday.add("Wish you a happy birthday");
		birthday.add("Happy Birthday to you");
		birthday.add("Happy returns of the day");

		ArrayList<String> anniversary = new ArrayList<String>();
		anniversary.add("Happy Anniversary");
		anniversary.add("Wish you a happy anniversary");
		anniversary.add("Happy Anniversary to you");
		anniversary.add("Happy returns of the Anniversary");

		Random random = new Random();
		switch (this.greetType) {
		case "birthday":
			return "Hi " + this.name + "\n" + birthday.get(random.nextInt(birthday.size()));
		case "anniversary":
			return "Hi " + this.name + "\n" + anniversary.get(random.nextInt(anniversary.size()));

		}
		return "";

	}
}
