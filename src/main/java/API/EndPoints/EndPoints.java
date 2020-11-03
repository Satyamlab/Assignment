package API.EndPoints;

import API.Model.Response.WeatherResponse;
import Constant.Data;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndPoints {
	
	/**
	 * Method for Weather Response API
	 * 
	 * @param city
	 * @return
	 */
	public static RestResponse<WeatherResponse> getWeather(String city) {
		RequestSpecification request;
		RestAssured.baseURI = Data.APIurl;
		request=RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.queryParam("q", city)
				.queryParam("appid", Data.apiKey)
				.get(routes.getWeather());
        return new RestResponse<WeatherResponse>(WeatherResponse.class,response);        
	}
}