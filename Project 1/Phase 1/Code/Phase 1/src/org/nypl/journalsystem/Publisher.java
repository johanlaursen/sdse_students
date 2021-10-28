package org.nypl.journalsystem;

public final class Publisher {
	String PublisherName, Location;
	
	Publisher(String PublisherName, String Location){
	this.PublisherName = PublisherName;
	this.Location = Location;
	}

	public String toString(){ 
		
		return "Publisher: " + this.PublisherName + "Location: " + this.Location + "\n";
	
}
}
