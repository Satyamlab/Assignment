package Test;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import API.EndPoints.EndPoints;
import API.EndPoints.RestResponse;
import API.Model.Response.WeatherResponse;

public class APITest {

	@Parameters({"city"})
	@Test
	public void Validate_Weather_API(String city) {
		RestResponse<WeatherResponse> wr = EndPoints.getWeather(city);
		assertTrue(wr.isSuccessful());
	}	
}