package Utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Data;
import UI.Base.Base;

public class Util extends Base{
	
	/**
	 * Method to click on element
	 * 
	 * @param element
	 */
	public static void Click(WebElement element) {
		element.click();
	}
	
	/**
	 * Method to wait for element visibility for element
	 * 
	 * @param element
	 * @return
	 */
	public static boolean waitForElementVisibility(WebElement element) {
		WebDriverWait wait;
		boolean flag;
		try {
			WebDriver driver = Base.driver.get();
			wait = new WebDriverWait(driver,Data.explicitWait);
			wait.until(ExpectedConditions.visibilityOf(element));
			flag = true;
		}
		catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * Method for sending input data in element
	 * 
	 * @param element
	 * @param city
	 */
	public static void SendText(WebElement element, String city) {
			element.click();
			element.clear();
			element.sendKeys(city);			
	}

	/**
	 * Methods return the inner text of element
	 * 
	 * @param element
	 * @return
	 */
	public static String getText(WebElement element) {
		return element.getText();
	}
	
	/**
	 * Method which check if the page is completely loaded or not
	 * 
	 * @return
	 */
	public static boolean waitForPageLoad() {
		String pageLoadStatus = null;
		JavascriptExecutor js;
		do {
			js = (JavascriptExecutor) driver.get();
			pageLoadStatus = (String)js.executeScript("return document.readyState");
			} while ( !pageLoadStatus.equals("complete") );
		return true;
	}
	
	/**
	 * Method to convert Fahrenheit temp to Kelvin
	 * @param F
	 * @return
	 */
	public static float FahrenheitToKelvin(float F) {
		return 273.5f + ((F - 32.0f) * (5.0f/9.0f)); 
	}

	/**
	 * Method to compare temperature with variance
	 * 
	 * @param UITemp
	 * @param APITemp
	 * @return
	 */
	public static boolean TempCompare(float UITemp, Float APITemp) {
		float variation = Data.TempVariance;
		float tempdiff;
		if(UITemp>APITemp) {
			tempdiff = UITemp-APITemp;			
		}
		else {
			tempdiff = APITemp-UITemp;
		}

		if(tempdiff <= variation) {
			return true;
		}
		return false;
	}
}