package org.nypl.journalsystem;

import java.util.ArrayList;
import java.util.List;


public final class Journal {
	String JournalName, ISSN;
	List<Article> Articles = new ArrayList<Article>();
	Publisher publisher;

	
	
	Journal(String JournalName, String ISSN){
        this.JournalName = JournalName; 
        this.ISSN = ISSN;
	}
	
	public void AddArticle(Article art){
		this.Articles.add(art);
		
	}
	public void SetPublisher(Publisher pub){
		this.publisher = pub;
		
	}
	
	public String toString(){ 
		String output = "Journal Name:" + this.JournalName + " \n";
		output += this.publisher.toString();
		output += "ISSN: " + this.ISSN + " \n";
//		System.out.println(String.valueOf(this.Articles.size()));
		for (Article art : this.Articles) 
		{ 
			output += art.toString();
		}
		return output + "\n";
		
		
	}
}
