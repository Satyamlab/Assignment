package Listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import UI.Base.Base;
import Utility.ExtentReport;

public class TestNGListener extends Base implements ITestListener, ISuiteListener {

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(LogStatus.PASS, result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(LogStatus.FAIL, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(LogStatus.SKIP, result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		test.get().log(LogStatus.FAIL, result.getThrowable());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		test.get().log(LogStatus.FAIL, result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		test.set(ExtentReport.report.startTest(context.getName()));
		try {
			browser.set(context.getCurrentXmlTest().getParameter("browser"));
			Base.CreateDriver();
		} catch (Exception e) {
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentReport.report.flush();
		ExtentReport.report.endTest(test.get());
		try {
			Base.driver.get().close();
			Base.driver.get().quit();			
		}
		catch (Exception e) {
		}
	}

	@Override
	public void onStart(ISuite suite) {
		ExtentReport.Extent();
	}

	@Override
	public void onFinish(ISuite suite) {
	}
}