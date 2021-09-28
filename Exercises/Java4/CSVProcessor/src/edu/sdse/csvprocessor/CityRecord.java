package edu.sdse.csvprocessor;

public class CityRecord {
	int id, year, population;
	String city;
	
	CityRecord(int id, int year, String city, int population){
        this.id = id;
        this.year = year;
        this.population = population;
        this.city = city;
        
	}
	public String toString(){
	return "id: " + this.id + ", year: " + this.year + ", city: " + this.city + ", population: " + this.population;
	}
}