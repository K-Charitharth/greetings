package Assignment.greetings;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Greet implements Job {
	SendMail mail = new SendMail();
	Wish wish;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			String fileName = "G:\\java\\eclipse-workspace\\csvreader\\src\\main\\resources\\MyCsv.csv";
			FileReader fileReader = new FileReader(fileName, StandardCharsets.UTF_8);
			CSVReader csvReader = new CSVReader(fileReader);
			csvReader.skip(1);

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String today = formatter.format(date);
			String todayDate = today.substring(0, 5);
			String todayYear = today.substring(6, 10);

			String[] row;
			while ((row = csvReader.readNext()) != null) {
				if (row[6].trim().toLowerCase().equals("true")) {
					String eName = row[2].trim();
					String mailID = row[4].trim();
					String DOB = row[3].trim().substring(0, 5);
					String YOB = row[3].trim().substring(6, 10);
					String DOJ = row[5].trim().substring(0, 5);
					String YOJ = row[5].trim().substring(6, 10);

					if (DOB.equals(todayDate)) {
						int noOfYears = Integer.parseInt(todayYear) - Integer.parseInt(YOB);
						sendingMail(eName, mailID, "birthday", noOfYears);
					}
					if (DOJ.equals(todayDate)) {
						int noOfYears = Integer.parseInt(todayYear) - Integer.parseInt(YOJ);
						if (noOfYears > 0)
							sendingMail(eName, mailID, "anniversary", noOfYears);
					}
				}
			}
		} catch (CsvValidationException e) {
			System.out.println("Error1");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}

	public void sendingMail(String eName, String mailID, String greetType, int noOfYears) {
		wish = new Wish(eName, greetType, noOfYears);
		String subject = wish.getSubject();
		String text = wish.getText();

		if (mail.send(mailID, subject, text)) {
			System.out.println("Message sent successfully");
		} else {
			System.out.println("Message not sent");
		}
	}
}
