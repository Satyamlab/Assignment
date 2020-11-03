package API.EndPoints;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class RestResponse<T> {

	private T data;
	private Response response;
	private Exception e;
	
	public RestResponse(Class<T> data, Response response) {
		 this.response = response;
		 try {
			this.data = data.newInstance();
		} catch (Exception e) {
			 throw new RuntimeException("There should be a default constructor in the Response");
		}
	}

	public boolean isSuccessful() {
	
		int code = response.getStatusCode();
	 	
		if( code == 200 || code == 201 || code == 202 || code == 203 || code == 204 || code == 205) return true;
	 	return false;
	 }

	@SuppressWarnings("unchecked")
	public T getBody() {
	 try {
	 data = (T) response.getBody().as(data.getClass(),ObjectMapperType.JACKSON_2);
	 }catch (Exception e) {
	 this.e=e;
	 }
	 return data;
	 }

	public String GetStringBody() {
		return response.getBody().asString();
	 }

	 public Exception getException() {
	 return e;
	 } 

}