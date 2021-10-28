package org.nypl.journalsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;



public class LibrarySystem {
	Map<Integer,String> IDAuthorMap = new HashMap<Integer,String>();
	Map<String,Publisher> PublisherMap = new HashMap<String,Publisher>();
	Map<String,String> JournaltoPublisherMap = new HashMap<String,String>();
    List<Journal> Journals = new ArrayList<Journal>();



	public LibrarySystem() {

		
		
	}
	
	public void load() throws FileNotFoundException, IOException {
		PublisherMap.put("Springer", new Publisher("Springer","Germany"));
		PublisherMap.put("Elsevier", new Publisher("Elsevier","Netherlands"));
		PublisherMap.put("Nature Research", new Publisher("Nature Research","Great Britain"));
		this.Journals.add(new Journal("Higher Education","0018-4687"));
		this.Journals.get(Journals.size()-1).SetPublisher(PublisherMap.get("Springer"));
		this.Journals.add(new Journal("System","0346-2511"));
		this.Journals.get(Journals.size()-1).SetPublisher(PublisherMap.get("Elsevier"));
		this.Journals.add(new Journal("Chem","2451-9294"));
		this.Journals.get(Journals.size()-1).SetPublisher(PublisherMap.get("Elsevier"));
		this.Journals.add(new Journal("Nature","1476-4687"));
		this.Journals.get(Journals.size()-1).SetPublisher(PublisherMap.get("Nature Research"));
		this.Journals.add(new Journal("Society","0147-2011"));
		this.Journals.get(Journals.size()-1).SetPublisher(PublisherMap.get("Springer"));


		loadAuthors();
		loadArticles();
	}
	
	protected void loadAuthors() throws FileNotFoundException, IOException {
		Reader in = new FileReader("data/Authors.csv");
		Iterable<CSVRecord> records = CSVFormat.TDF.withFirstRecordAsHeader().withDelimiter(',').parse(in);

		for (CSVRecord record : records) {
		    String AuthorID = record.get("ID");
		    String Name = record.get("Name");
		    this.IDAuthorMap.put(Integer.parseInt(AuthorID), Name);
		    
		    //System.out.println(AuthorID + " " + Name);
		}
		        
		// Currently loading all ID Author pairs and storing it in a hashmap
	}
	
	protected void loadArticles() throws FileNotFoundException, IOException {
		//File file = new File("data/Articles.csv");
		Reader in = new FileReader("data/Articles.csv");
		Iterable<CSVRecord> records = CSVFormat.TDF
				.withFirstRecordAsHeader()
				.withDelimiter(',')
				.parse(in);
		
		for (CSVRecord record : records) {
		    String ArticleID = record.get("ID");
		    String Title = record.get("Title");
		    String ISSN = record.get("ISSN");
		    String AuthorIDsfucked = record.get("AuthorIDs");
		    
		    AuthorIDsfucked = AuthorIDsfucked.replaceAll("[\\[\\]]", "");
		    AuthorIDsfucked = AuthorIDsfucked.replaceAll(" ", "");

		    int[] AuthorIDs = Arrays.stream(AuthorIDsfucked.split(";"))
		    	    .mapToInt(Integer::parseInt)
		    	    .toArray();
		    
		    List<String> AuthorIDsString = new ArrayList<String>();
		    		for (int AuthorID : AuthorIDs) {
		    			AuthorIDsString.add(this.IDAuthorMap.get(AuthorID));
		    		   
		    		}
		    
		    
		    Map<Integer,Article> IDAuthorMap = new HashMap<Integer,Article>();
		    Article art = new Article(Integer.parseInt(ArticleID), Title, AuthorIDsString, ISSN);
		    IDAuthorMap.put(Integer.parseInt(ArticleID), art);
		    
    		for (Journal journ : this.Journals) {
    			//System.out.println(journ.ISSN + "   " + ISSN);
    			if (journ.ISSN.equals( ISSN)) {journ.AddArticle(art);}} 

//		    System.out.println(ArticleID + " " + Title + " "+AuthorIDsfucked +" " + ISSN);
//		    System.out.println(Arrays.toString(AuthorIDs));


		}

		//TODO: Load articles from file and assign them to appropriate journal
	}
	
	
	public void listContents() {
		for (Journal journ : this.Journals) {System.out.println(journ.toString());} 
		
	}
	
	public static final void main(String[] args) throws Exception {
		LibrarySystem librarySystem = new LibrarySystem();
		
		librarySystem.load();
		librarySystem.listContents();
	}
}
