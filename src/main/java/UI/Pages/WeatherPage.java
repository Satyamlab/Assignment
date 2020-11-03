package UI.Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import UI.Base.Base;
import Utility.Util;

public class WeatherPage extends Base{
	
	@FindBy(id="searchBox")
	WebElement searchBox;

	@FindBy(xpath="//*[@id='messages']")	
	WebElement CityList;

	By CityTempFah = new By.ByClassName("tempWhiteText");
	
	@FindBy(className="leaflet-popup-content-wrapper")
	WebElement CityLeafletPopUp;

	@FindBy(xpath="//*[@class='leaflet-popup-content']//b")
	List<WebElement> LeafletPopUpContent;
	
	@FindBy(className="leaflet-popup-close-button")
	WebElement CityLeafletClose;
	
	public WeatherPage() {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver.get(),5),this);
		while(!Util.waitForPageLoad()) {
		}
	}

	public void searchCity(String city) {
		assertEquals(true, Util.waitForElementVisibility(searchBox));
		String CityXPath = "//*[@id='"+city+"']";
		Util.SendText(searchBox,city);
		WebElement cityCheckBox = CityList.findElement(By.xpath(CityXPath));
		Util.waitForElementVisibility(cityCheckBox);
		if(!cityCheckBox.isSelected()) {
			Util.Click(cityCheckBox);			
		}
	}

	public void clickCityOnMap(String cityName) {
		String CityOnMapXpath = "//*[@title='"+cityName+"']";
		WebElement element = driver.get().findElement(By.xpath(CityOnMapXpath));
		assertEquals(true, Util.waitForElementVisibility(element));		
		Util.Click(element);
		String temperature = Util.getText(element.findElement(CityTempFah));
		int value = Integer.parseInt(temperature.substring(0,2));
		assertTrue(value>0 && value<100);
	}
	
	
	public void verifyCityPopUp() {
		assertEquals(true, Util.waitForElementVisibility(CityLeafletPopUp));	
		assertTrue(Util.getText(LeafletPopUpContent.get(0)).trim().contains("Condition"));
		assertTrue(Util.getText(LeafletPopUpContent.get(1)).trim().contains("Wind"));
		assertTrue(Util.getText(LeafletPopUpContent.get(2)).trim().contains("Humidity"));
		assertTrue(Util.getText(LeafletPopUpContent.get(3)).trim().contains("Temp in Degrees"));
		assertTrue(Util.getText(LeafletPopUpContent.get(4)).trim().contains("Temp in Fahrenheit"));
	}
	
	public int getCityTempInFahrenheit(String cityName) {
		String CityOnMapXpath = "//*[@title='"+cityName+"']";
		WebElement element = driver.get().findElement(By.xpath(CityOnMapXpath));
		assertEquals(true, Util.waitForElementVisibility(element));
		String temperature = Util.getText(element.findElement(CityTempFah));
		int value = Integer.parseInt(temperature.substring(0,2));
		return value;
	}
	
	public void closeLeafletPopUp() {
		Util.Click(CityLeafletClose);		
	}
}