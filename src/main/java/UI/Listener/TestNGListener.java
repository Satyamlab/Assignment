package UI.Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import UI.Base.Base;

public class TestNGListener extends Base implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		browser.set(context.getCurrentXmlTest().getParameter("browser"));
		Base.CreateDriver();
	}

	@Override
	public void onFinish(ITestContext context) {
		Base.driver.get().close();
		Base.driver.get().quit();
	}

}
