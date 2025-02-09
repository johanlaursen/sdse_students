package edu.sdse.csvprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class CityCSVProcessor {
	
	public void readAndProcess(File file) {
		//Try with resource statement (as of Java 8)
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			//Discard header row
			br.readLine();
			
			String line;
			List<CityRecord> allRecords = new ArrayList<CityRecord>();
			Map<String,List<CityRecord>> log = new HashMap<String,List<CityRecord>>();
			while ((line = br.readLine()) != null) {
				// Parse each line
				String[] rawValues = line.split(",");
				
				int id = convertToInt(rawValues[0]);
				int year = convertToInt(rawValues[1]);
				String city = convertToString(rawValues[2]);
				int population = convertToInt(rawValues[3]);
				CityRecord cr = new CityRecord(id, year, city, population);
				allRecords.add(cr);
				
				if (log.get(city) == null){
					List<CityRecord> newcity = new ArrayList<CityRecord>();
					newcity.add(cr);
					log.put(city, newcity );
					
				}
				else {
					List<CityRecord> citydata = log.get(city);
					citydata.add(cr);
					log.put(city, citydata);
				}
				System.out.println(cr);
				
				//TODO: Extend the program to process entries!
			}
			cityprint(log);
			
		} catch (Exception e) {
			System.err.println("An error occurred:");
			e.printStackTrace();
		}
	}
	
	private void cityprint(Map<String,List<CityRecord>> log) {
		for (Entry<String, List<CityRecord>> entry : log.entrySet()) {
			int numEntries, minYear, maxYear, total;
			String city;
			numEntries = 0;
			total = 0;
			city = entry.getKey();
			minYear = entry.getValue().get(0).year;
			maxYear = entry.getValue().get(0).year;
			
			
			for (CityRecord record : log.get(city)) {
				numEntries += 1;
				total += record.population;
				if (record.year < minYear) {minYear = record.year;}
				else if (record.year > maxYear) {maxYear = record.year;}
				
				
			}
			System.out.println("Average population of " + city + "("+ minYear+"-"+maxYear+"; "+ numEntries+ " entries): " + total/numEntries);
		
	}
	}
	
	private String cleanRawValue(String rawValue) {
		return rawValue.trim();
	}
	
	private int convertToInt(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		return Integer.parseInt(rawValue);
	}
	
	private String convertToString(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		
		if (rawValue.startsWith("\"") && rawValue.endsWith("\"")) {
			return rawValue.substring(1, rawValue.length() - 1);
		}
		
		return rawValue;
	}
	
	public static final void main(String[] args) {
		CityCSVProcessor reader = new CityCSVProcessor();
		
		File dataDirectory = new File("data/");
		File csvFile = new File(dataDirectory, "Cities.csv");
		
		reader.readAndProcess(csvFile);
	}
}
