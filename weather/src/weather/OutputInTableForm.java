package weather;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OutputInTableForm {

	public void upper() {
		System.out.println("-------------------------------------------------------------------------");
		System.out.printf("%5s %15s %16s %15s %15s", "id", "city" , "date", "temp_c", "temp_f\n");
		System.out.println("-------------------------------------------------------------------------");
	}
	
	public void show(HttpResponse<JsonNode> response) {
		
		int id = response.getBody().getObject().getInt("id");
		String city = response.getBody().getObject().getString("city");
		String date = response.getBody().getObject().getString("date");
		double temp_c = response.getBody().getObject().getDouble("temp_c");
		double temp_f = response.getBody().getObject().getDouble("temp_f");
		
		System.out.format("%5s %15s %16s %15s %15s", id, city, date, temp_c, temp_f);
		System.out.println("\n-------------------------------------------------------------------------");
	}
	
	public void createReport(List<CurrentLocation> data) {
	
	 try (XSSFWorkbook workbook = new XSSFWorkbook()) {
		 
            XSSFSheet sheet = workbook.createSheet("Weather Data");

            int rowNum = 0;
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;
            Cell cell = row.createCell(cellNum++);
            cell.setCellValue("Id");
            cell = row.createCell(cellNum++);
            cell.setCellValue("City");
            cell = row.createCell(cellNum++);
            cell.setCellValue("Date (dd-MM-yyyy)");
            cell = row.createCell(cellNum++);
            cell.setCellValue("Temp (C)");
            cell = row.createCell(cellNum++);
            cell.setCellValue("Temp (F)");
            

            for (CurrentLocation response : data) {
            	
            	int id = response.getId();
        		String city = response.getCity();
        		String date = response.getDate();
        		double temp_c = response.getTemp_c();
        		double temp_f = response.getTemp_f();
            	
        		
        		createRow(sheet, rowNum++,id, city, date, temp_c, temp_f);
        		
            }

            try (FileOutputStream outputStream = new FileOutputStream("weather-data.xlsx")) {
                workbook.write(outputStream);
                System.out.println("Excel file written successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void createRow(XSSFSheet sheet,int rowNum,int id, String city, String date, double temp_c, double temp_f) {
	   if(rowNum > 3) {
		   return;
	   }
	   
	   Row row = sheet.createRow(rowNum++);
       int cellNum = 0;
       Cell cell = row.createCell(cellNum++);
       cell.setCellValue(id);
       cell = row.createCell(cellNum++);
       cell.setCellValue(city);
       cell = row.createCell(cellNum++);
       cell.setCellValue(date);
       cell = row.createCell(cellNum++);
       cell.setCellValue(temp_c);
       cell = row.createCell(cellNum++);
       cell.setCellValue(temp_f);
   }
}
