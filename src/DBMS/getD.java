package DBMS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
class Results {
	String id;
	String title;
	String year;
	String audrating;
	String audnumrating;
	String country;
	String genre;
	
}
public class getD {
	
	private static Connection connect;
	private static DBconnection dbconnection;
	private static java.sql.Statement statement1; 
	ArrayList<Integer> movieids = new ArrayList<Integer>();
	ArrayList<String> countries = new ArrayList<String>();
	ArrayList<String> actors= new ArrayList<>();
	ArrayList<String> directors= new ArrayList<>();
	ArrayList<Tags> tags= new ArrayList<>();
	String iq="", cq="", aq="", dq="", tq="",finalquery="";
	String userquery="";
//	String actorarray[]=null;
//	String directorarray[]=null;
	boolean actorselection=false;
	boolean movieselecton=false;
	boolean directorselection=false;
	getD(){
		try{
			dbconnection=new DBconnection();
			connect=dbconnection.getConnection();
			statement1 =  connect.createStatement();
			}
			catch(SQLException e){
				
			}
	}
	public ArrayList<String> useridresults( ArrayList<Integer> tagid,  ArrayList<String> movieid ){
		ArrayList<String> useridlist= new ArrayList<>();
		userquery= "(SELECT DISTINCT USER_ID FROM USER_TAGEDMOVIES U WHERE U.MID='"+movieid.get(0)+"' ";
		for(int i=1;i<movieid.size();i++){
			userquery+=" UNION SELECT DISTINCT USER_ID FROM USER_TAGEDMOVIES U WHERE U.MID='"+movieid.get(i)+"' ";
		}
		userquery+=") ";
		
		userquery+= " INTERSECT ";
				userquery+= "(SELECT DISTINCT USER_ID FROM USER_TAGEDMOVIES U WHERE U.TAG_ID='"+tagid.get(0)+"' ";
		for(int i=1;i<tagid.size();i++){
			userquery+=" UNION SELECT DISTINCT USER_ID FROM USER_TAGEDMOVIES U WHERE U.TAG_ID='"+tagid.get(i)+"' ";
		}
		userquery+=") ";		
		try{
			
			System.out.println("Selecting userid");
			ResultSet result=  statement1.executeQuery(userquery);
			while(result.next()){
				String user = result.getString("USER_ID");
				useridlist.add(user);	
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return useridlist;
		
	}
	public String getUserquery(){
		return userquery;
	}
	public ArrayList<String> getGenre(){
		ArrayList<String> genres = new ArrayList<String>();
		try{
			
			System.out.println("Selecting genre from the table");
			String genre;
			ResultSet result=  statement1.executeQuery("SELECT DISTINCT GENRE FROM MOVIE_GENRE ORDER BY GENRE");
			while(result.next()){
				genre = result.getString("GENRE");
				genres.add(genre);	
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return genres;
	}
	public void genreClicked(ArrayList<String> genre, String attr, int fyear, int tyear){
		iq="";
		cq=""; 
		aq=""; 
		dq=""; 
		tq="";
		 String option;
		    if(attr.equals("AND"))
		    	option="INTERSECT";
		    else
		    	option="UNION";
		
		System.out.println("Selecting Countries from the table");
		String country;
		iq=" (SELECT DISTINCT G.MID FROM MOVIE_GENRE G, MOVIE M WHERE G.MID=M.MID AND G.GENRE='"+genre.get(0)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
		for(int i=1;i<genre.size();i++){
			iq+=" "+option+" SELECT DISTINCT G.MID FROM MOVIE_GENRE G, MOVIE M WHERE G.MID=M.MID AND G.GENRE='"+genre.get(i)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";
		}
		iq+=")";
		updateActors(iq);
		updateTags(iq);
		updateDirectors(iq);
		updateCountries(iq);
	}
	public void countryClicked(ArrayList<String> country, String attr, int fyear, int tyear){
		cq="";
		aq=""; 
		dq=""; 
		tq="";
		String option;
	    if(attr.equals("AND"))
	    	option="INTERSECT";
	    else
	    	option="UNION";
		if(country!=null && country.size()>0){
			cq+=" INTERSECT ";
			cq+=" (SELECT DISTINCT C.MID FROM MOVIE_COUNTRY C, MOVIE M WHERE C.MID=M.MID AND C.COUNTRY='"+country.get(0)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
			for(int i=1;i<country.size();i++){
				cq+=" "+option+" SELECT DISTINCT C.MID FROM MOVIE_COUNTRY C, MOVIE M WHERE C.MID=M.MID AND C.COUNTRY='"+country.get(i)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";
			}
			cq+=") ";
			}
		String query= iq+cq;
		updateActors(query);
		updateTags(query);
		updateDirectors(query);	
	}
	public void ActorClicked(ArrayList<String> actorlist, int fyear,int tyear, String attr){
		aq=""; 
		dq=""; 
		tq="";
		String option;
	    if(attr.equals("AND"))
	    	option="INTERSECT";
	    else
	    	option="UNION";
		if(actorlist!=null && actorlist.size()>0){
			aq+=" INTERSECT ";
			aq+=" (SELECT DISTINCT A.MID FROM MOVIE_ACTOR A, MOVIE M WHERE A.MID=M.MID AND A.ACTOR_NAME='"+actorlist.get(0)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
			for(int i=1;i<actorlist.size();i++){
				aq+=" "+option+" SELECT DISTINCT A.MID FROM MOVIE_ACTOR A, MOVIE M WHERE A.MID=M.MID AND A.ACTOR_NAME='"+actorlist.get(i)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";
			}
			aq+=") ";
		}
		String query= iq+cq+aq;
		updateTags(query);
		updateDirectors(query);
	}
	
	public void DirectorClicked(String directorname, int fyear,int tyear){
		dq=""; 
		tq="";
		if(!directorname.equals("")){
			dq+=" INTERSECT ";
			dq+=" (SELECT DISTINCT D.MID FROM MOVIE_DIRECTOR D, MOVIE M WHERE D.MID=M.MID AND D.DIRECTOR_NAME='"+directorname+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
			dq+=") ";
		}
		String query= iq+cq+aq+dq;
		updateTags(query);
	}
	public void TagsClicked(ArrayList<Integer> tagids, String sign, int weight, int fyear,int tyear,String attr ){
		tq="";
		System.out.println(weight);
		 if(tagids.size()!=0){
			String option;
		    if(attr.equals("AND"))
		    	option="INTERSECT";
		    else
		    	option="UNION";
					
			tq+=" INTERSECT ";
			tq+=" (SELECT DISTINCT D.MID FROM MOVIE_TAGS D, MOVIE M WHERE D.MID=M.MID AND D.TAG_ID='"+tagids.get(0)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
			if(weight>-1){
				tq+=" AND D.WEIGHT "+sign+ weight;
			}
			for(int i=1;i<tagids.size();i++){
				tq+=" "+option+" SELECT DISTINCT D.MID FROM MOVIE_TAGS D, MOVIE M WHERE D.MID=M.MID AND D.TAG_ID='"+tagids.get(i)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";
				if(weight>-1){
					tq+=" AND D.WEIGHT "+sign+ weight;
				}
			}
			tq+=") ";
		 }
	}
	public ArrayList<Results> getResult(){
		ArrayList<Results> result= new ArrayList<>();
		try{
		String f= iq+" "+cq+" "+aq+" "+dq+" "+tq;
		
		 finalquery=" SELECT DISTINCT M.MID,M.TITLE,M.YEAR,M.RTAUDRATING,M.RTAUDNUMRATING,C.COUNTRY, G.GENRE FROM MOVIE M,MOVIE_COUNTRY C,MOVIE_GENRE G WHERE M.MID=C.MID AND C.MID=G.MID AND M.MID IN ("+f+ ")";
			
		System.out.println(finalquery);
		ResultSet rs = statement1.executeQuery(finalquery);
		while(rs.next()){
			Results tmp= new Results();
			tmp.id=""+rs.getInt("MID");
			System.out.println(tmp.id);
			tmp.title=""+rs.getString("TITLE");
			tmp.year=""+rs.getInt("YEAR");
			tmp.audrating=""+rs.getInt("RTAUDRATING");
			tmp.audnumrating=""+rs.getFloat("RTAUDNUMRATING");
			tmp.country=""+rs.getString("COUNTRY");
			tmp.genre=""+rs.getString("GENRE");
			result.add(tmp);
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	 return result;
	} 
	public void updateCountries(String q){
		countries.clear();
		String query= "SELECT DISTINCT C.COUNTRY FROM MOVIE_COUNTRY C WHERE C.MID IN "+q;
		try{
		System.out.println(query);
		ResultSet result = statement1.executeQuery(query);
		while(result.next()){
			String country = result.getString("COUNTRY");
			countries.add(country);	
		}
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public ArrayList<String> getCountries(){
		return countries;
	}
	public void updateActors(String q){
		actors.clear();
		try{ 
			String query= "SELECT DISTINCT A.ACTOR_NAME FROM MOVIE_ACTOR A WHERE A.MID IN (" +q+ ") ORDER BY A.ACTOR_NAME"; 
			System.out.println(query);
			ResultSet result = statement1.executeQuery(query);
			int i=0;
			while(result.next()&& i<100){
				String actor = result.getString("ACTOR_NAME");
				actors.add(actor);
				i++;
			}
			
			}
	    
			catch (SQLException e) {
			e.printStackTrace();
			}
	}
	public String[]  getActors(){
		String[] actorarray= new String[actors.size()];
		for(int j=0; j<actors.size(); j++){
			actorarray[j]= actors.get(j);
		}
		return actorarray;
	}
	public void updateDirectors(String q){
		
		directors.clear();
		try{
			String query= "SELECT DISTINCT D.DIRECTOR_NAME FROM MOVIE_DIRECTOR D WHERE D.MID IN (" +q+ ") ORDER BY D.DIRECTOR_NAME"; 
			System.out.println(query);
			ResultSet result = statement1.executeQuery(query);
			int i=0;
			while(result.next()&& i<100){
				String director = result.getString("DIRECTOR_NAME");
				directors.add(director);
				i++;
			}
			
		}
		

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String[]  getDirectors(){
		String[] directorarray= new String[directors.size()];
		for(int j=0; j<directors.size(); j++){
			directorarray[j]= directors.get(j);
		}
		return directorarray;
	}
	
	public void updateTags(String q){
		tags.clear();
		try{
			String query= "SELECT DISTINCT T.TAG_ID, T.VALUE FROM TAGS T, MOVIE_TAGS M WHERE T.TAG_ID=M.TAG_ID AND M.MID IN ("+q+ ")";
			System.out.println(query);
			ResultSet result = statement1.executeQuery(query);
			while(result.next()){
				int id = result.getInt("TAG_ID");
				String val=result.getString("VALUE");
				Tags tag= new Tags(id, val);
				tags.add(tag);
			}}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Tags> getTags(){
		return tags;
	}
	
	public String getmovieQuery(){
		String f= finalquery; 
		return f;
	}
	
}
