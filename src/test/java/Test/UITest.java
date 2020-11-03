package Test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import UI.Pages.HomePage;
import UI.Pages.WeatherPage;

public class UITest {

	@Parameters({"city"})
	@Test
	public void Validate_Weather_Functionality(String city) throws InterruptedException {
		HomePage home = new HomePage();
		home.SelectMenuItem("weather");
		WeatherPage weather = new WeatherPage();
		weather.searchCity(city);
		weather.clickCityOnMap(city);
		weather.verifyCityPopUp();
		weather.closeLeafletPopUp();
	}
}