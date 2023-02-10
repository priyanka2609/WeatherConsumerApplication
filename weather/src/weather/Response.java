package weather;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

//import org.apache.log4j.BasicConfigurator;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;


public class Response {

	 HttpResponse<JsonNode> response = null;
	 ShowResponse showRes = new ShowResponse();
	 OutputInTableForm form = new OutputInTableForm();
	 
	 private static final Logger LOGGER = Logger.getLogger(Response.class.getName());
//	 private static final Logger LOGGER = LogManager.getLogger(Response.class);
	 //   private static final Logger logger = LogManager.getLogger(Response.class);
//	 private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(Response.class);

	public void showSingle(String city){
			response = showRes.showResponse(city);
			
			if(response == null)
				return;
			
			if(response.getBody().toString().equals("{}")) {
				System.out.println("Input not valid");
				return;
			}
			
			LOGGER.info("The temperature when the city is passed....");
			form.upper();
			form.show(response);
	}
	
	
	public void cities(String[] args) {
		 String[] cities = args[0].split(",");
		 LOGGER.info("The temperature when the multiple cities are passed....");
		 form.upper();
		 for (String city : cities) {
			response = showRes.showResponse(city);
			if(response != null)
				form.show(response);
			else 
				System.out.println("-------------------------------------------------------------------------");
		} 
	}
	
	public void cityAndDate(String[] args) {
		HttpResponse<JsonNode> response = showRes.showResponse(args[0], args[1]);
		if(response == null || response.getBody().toString().equals("{}")) {
			LOGGER.info("Did not get any valid result from the api.");
			return;
		}
		
		 LOGGER.info("The temperature when the city and date are passed....");
		form.upper();
		form.show(response);
	}
	
	public void getAllReports() {
		List<CurrentLocation> list = new ArrayList<>();
		HttpResponse<JsonNode> showResponse = showRes.showResponse();
		
		if (showResponse == null) {
			return;
		}
		
		JSONArray array = showResponse.getBody().getArray();
		array.forEach(e -> {
			Gson gson = new Gson();
			CurrentLocation o = gson.fromJson(e.toString(),CurrentLocation.class);
			list.add(o);
		});
		
		OutputInTableForm obj = new OutputInTableForm();
		
		obj.createReport(list);
	}
	
}

