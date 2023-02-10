package weather;

import java.util.logging.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ShowResponse {

	public HttpResponse<JsonNode> showResponse(String city) {

		 Logger logger = Logger.getLogger(ShowResponse.class.getName());
		 
		try {
			 HttpResponse<JsonNode> asJson = Unirest.get("http://localhost:9095/"+city)    
			.asJson();
			 
//			 System.out.println(asJson.getBody());
			 
			 if(asJson.getBody().toString().equals("{}")) {
				 logger.info("The city entered " +city+ " is not valid");
				 return null;
			 }
			 
			 return asJson;
		} 
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	public HttpResponse<JsonNode> showResponse(String city,String date){
		try {
			return Unirest.get("http://localhost:9095/temperature?city=" + city + "&date=" + date)
					.header("accept", "application/json").asJson();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HttpResponse<JsonNode> showResponse(){
		try {
			HttpResponse<JsonNode> asJson = Unirest.get("http://localhost:9095/getAll")    
					.asJson();
			return asJson;
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;
	}
}
