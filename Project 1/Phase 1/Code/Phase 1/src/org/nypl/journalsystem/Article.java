package org.nypl.journalsystem;
import java.util.List;

public final class Article {
	Integer ID;
	String Title, ISSN;
	List<String> AuthorIDsString;
	
	Article(Integer ID, String Title,List<String> AuthorIDsString, String ISSN){
        this.ID = ID;
        this.Title = Title;
        this.ISSN = ISSN;
        this.AuthorIDsString = AuthorIDsString;
        
	}
	
	public String toString(){ 
		String output = "\tArticle ID: " + String.valueOf(this.ID) + "\n \tArticle Title: " + this.Title + "\n \tISSN: " + ISSN + "\n" + "\tAuthors: \n";
		
			for (String Author : this.AuthorIDsString) 
			{ 
				output += "\t  " + Author + "\n"; 
			}
		
		return output + "\n";
		
		
	}

}
