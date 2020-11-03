package Test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import API.EndPoints.EndPoints;
import API.EndPoints.RestResponse;
import API.Model.Response.WeatherResponse;
import UI.Base.Base;
import UI.Pages.HomePage;
import UI.Pages.WeatherPage;
import Utility.Util;

public class APIUITest extends Base{
	
	@Parameters({"city"})
	@Test
	public void ValidateUIandAPIResult(String city) throws InterruptedException {
		HomePage home = new HomePage();
		home.SelectMenuItem("weather");
		WeatherPage weather = new WeatherPage();
		weather.searchCity(city);
		int value = weather.getCityTempInFahrenheit(city);
		float UIkelvinValue = Util.FahrenheitToKelvin(value);
		
		RestResponse<WeatherResponse> wr= EndPoints.getWeather(city);
		if(wr.isSuccessful()) {
			float APIkelvinValue = wr.getBody().main.temp;
			if(!Util.TempCompare(UIkelvinValue, APIkelvinValue)) {
				throw new RuntimeException("Temperature is not with in variance range. UI value: "+UIkelvinValue+" | API value :"+APIkelvinValue);
			}
		}
		else {
			throw new RuntimeException("weather response is not success");
		}	
	}
}