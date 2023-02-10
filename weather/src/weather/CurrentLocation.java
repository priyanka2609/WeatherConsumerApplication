package weather;

import lombok.Data;

@Data
public class CurrentLocation {

	private int id;
	private String city;
	private String date;
	private double temp_c;
	private double temp_f;
	
}
