package UI.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UI.Base.Base;
import Utility.Util;

public class HomePage extends Base{

	@FindBy(linkText="No Thanks")
	WebElement No_Thanks;
	
	@FindBy(id="h_sub_menu")
	WebElement SubMenu;

	@FindBy(linkText="WEATHER")
	WebElement Weather;
	
	public HomePage() {
		PageFactory.initElements(driver.get(),this);
	}
		
	/**
	 * Method for selecting item from ndtv menu list
	 * 
	 * @param value
	 */
	public void SelectMenuItem(String value) {
		if(Util.waitForElementVisibility(No_Thanks)) {
			Util.Click(No_Thanks);
		}
		Util.Click(SubMenu);
		switch (value.trim().toLowerCase()) {
		
		case "weather":
			Util.Click(Weather);
			break;
		}
	}
}