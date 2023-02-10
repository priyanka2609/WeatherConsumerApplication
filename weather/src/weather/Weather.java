package weather;

import java.util.logging.Logger;

public class Weather {
	
	public static void main(String[] args) {

		 Logger logger = Logger.getLogger(ShowResponse.class.getName());
		 
		if (args.length == 0) {
			logger.info("The data is empty");
			return;
		}
		
		Response res = new Response();
		
		if(args[0].equalsIgnoreCase("report"))
			res.getAllReports();
		
		
		else if(args.length == 1) {
				 if(args[0].contains(",")) 
					res.cities(args);
				 
				 else{
					 res.showSingle(args[0]);
				 }
			}
			 else {
				 res.cityAndDate(args);
			 }
		}	 
	}
	
	

