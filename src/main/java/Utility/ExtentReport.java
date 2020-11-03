package Utility;

import java.sql.Timestamp;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReport {

	public static ExtentReports report;
	
	public static void Extent() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		report = new ExtentReports(System.getProperty("user.dir")+"//Report//ExtentReportResults"+timestamp.getTime()+".html");
	}	
}
