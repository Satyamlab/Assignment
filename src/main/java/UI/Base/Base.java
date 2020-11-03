package UI.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import Constant.Data;

public class Base {

	public static ThreadLocal<String> browser = new ThreadLocal<String>();
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public RemoteWebDriver remoteWebDriver;
	
	public static void CreateDriver() {
		if(browser.get().equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Binaries//chromedriver.exe");
			driver.set(new ChromeDriver());
		}
		else if(browser.get().equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Binaries//geckodriver.exe");
			driver.set(new FirefoxDriver());
		}
		else {
			
		}
		driver.get().get(Data.url);
		driver.get().manage().window().maximize();
	}	
}