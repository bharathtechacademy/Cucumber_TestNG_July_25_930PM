package com.creatio.crm.framework.db.commons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creatio.crm.framework.utilities.DBUtil;

public class DBCommons {
	
	//This class will contain all the common methods to process the data collected from the database.
	
	// Common method to convert the raw data into proper collections-related data structure.
		public static List<Map<String,String>> readData(String query) throws SQLException{
			List<Map<String,String>> data = new ArrayList<Map<String,String>>();
			
			//Get the raw data from the database
			ResultSet dataSet =DBUtil.getData(query);
			
			//Convert the data into a list of maps.
			while (dataSet.next()) { //if next row available-go to next row . else-end the iterations
				
				//Create an empty map to store each raw data.
				Map<String,String> rowData = new HashMap<String,String>(); 
				
				//By using for loop, collect each and every cell data within the row.
				for(int c=1; c<=dataSet.getMetaData().getColumnCount(); c++) {				
					String columnName = dataSet.getMetaData().getColumnName(c);
					String columnValue = dataSet.getString(c);
					rowData.put(columnName, columnValue);				
				}
				
				//Add each row data into the main list (data).
				data.add(rowData);
				
			}
			
			return data;		
		}

}
