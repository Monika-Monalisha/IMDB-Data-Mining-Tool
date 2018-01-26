package DBMS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Result {
	int id;
	String title;
	int year;
	String country;
	int audrating;
	float audnumrating;
	
}


public class Getdata {
	private static Connection connect;
	private static DBconnection dbconnection;
	private static java.sql.Statement statement1; 
	List<Integer> movieids = new ArrayList<Integer>();
	ArrayList<String> genres = new ArrayList<String>();
	ArrayList<String> countries = new ArrayList<String>();
	ArrayList<String> actors= new ArrayList<>();
	String iq="", gq="", cq="", aq="", dq="", tq="";
	String actorarray[]=null;
	String option;
	boolean actorselection=false;
	boolean movieselecton=false;
	boolean directorselection=false;
	Getdata(){
		try{
			dbconnection=new DBconnection();
			connect=dbconnection.getConnection();
			statement1 =  connect.createStatement();
			}
			catch(SQLException e){
				
			}
//			finally{
//				if(statement1!=null|| statement2!=null||statement3!=null){
//					dbconnection.closeConnection();
//				}
//			}
	}
	
	public ArrayList<String> getGenre(){
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
		gq=""; 
		cq=""; 
		aq=""; 
		dq=""; 
		tq="";
	    String option;
	    if(attr.equals("AND"))
	    	option="INTERSECT";
	    else
	    	option="UNION";
		try{
			System.out.println("Selecting Countries from the table");
			String country;
			gq=" (SELECT DISTINCT G.MID FROM MOVIE_GENRE G, MOVIE M WHERE G.MID=M.MID AND G.GENRE='"+genre.get(0)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
			for(int i=1;i<genre.size();i++){
				gq+=" "+option+" SELECT DISTINCT G.MID FROM MOVIE_GENRE G, MOVIE M WHERE G.MID=M.MID AND G.GENRE='"+genre.get(i)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";
			}
			gq+=")";
			String query= "SELECT DISTINCT C.COUNTRY FROM MOVIE_COUNTRY C WHERE C.MID IN "+gq;
			System.out.println(query);
			ResultSet result = statement1.executeQuery(query);
			while(result.next()){
				country = result.getString("COUNTRY");
				countries.add(country);	
			}
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public String[] getActor(ArrayList<String> country, int tyear,int fyear, String attr){
		cq=""; 
		aq=""; 
		dq=""; 
		tq="";
	    if(attr.equals("AND"))
	    	option="INTERSECT";
	    else
	    	option="UNION";
	      
		try{ 
			if(country!=null && country.size()>0){
				cq+=" INTERSECT ";
				cq+=" (SELECT DISTINCT C.MID FROM MOVIE_COUNTRY C, MOVIE M WHERE C.MID=M.MID AND C.COUNTRY='"+country.get(0)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
				for(int i=1;i<country.size();i++){
					cq+=" "+option+" SELECT DISTINCT C.MID FROM MOVIE_COUNTRY C, MOVIE M WHERE C.MID=M.MID AND C.COUNTRY='"+country.get(i)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";
				}
				cq+=") ";
				}
			String query= "SELECT DISTINCT A.ACTOR_NAME FROM MOVIE_ACTOR A WHERE A.MID IN (" +gq+cq+ ") ORDER BY A.ACTOR_NAME"; 
			System.out.println(query);
			ResultSet result = statement1.executeQuery(query);
			int i=0;
			while(result.next()&& i<100){
				String actor = result.getString("ACTOR_NAME");
				actors.add(actor);
				i++;
			}
			actorarray= new String[actors.size()];
			for(int j=0; j<actors.size(); j++){
				actorarray[j]= actors.get(j);
			}
		}
	    
			catch (SQLException e) {
			e.printStackTrace();
				}
	    
		return actorarray;
	}
	
//	public String[] getActorgenre(ArrayList<String> genre, int tyear,int fyear, String attr){
//			if(attr.equals("AND"))
//				option="INTERSECT";
//			else
//				option="UNION";	
//		
//	    	try {
//	    	iq+=" INTERSECT ";
//	    	iq="(SELECT DISTINCT G.MID FROM MOVIE_GENRE G, MOVIE M WHERE G.MID=M.MID AND G.GENRE='"+genre.get(0)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
//			for(int i=1;i<genre.size();i++){
//				iq+=" "+option+" SELECT DISTINCT G.MID FROM MOVIE_GENRE G, MOVIE M WHERE G.MID=M.MID AND G.GENRE='"+genre.get(i)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";
//			}
//			iq+=")";
//			String query= "SELECT DISTINCT A.ACTOR_NAME FROM MOVIE_ACTOR A WHERE A.MID IN (" +iq+ ") ORDER BY A.ACTOR_NAME";	
//			System.out.println(query);
//			ResultSet result = statement1.executeQuery(query);
//			int i=0;
//			while(result.next()&& i<100){
//				String actor = result.getString("ACTOR_NAME");
//				actors.add(actor);
//				i++;
//			}
//			actorarray= new String[actors.size()];
//			for(int j=0; j<actors.size(); j++){
//				actorarray[j]= actors.get(j);
//			}
//			} 
//			catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		return actorarray;
//		}
		

	
	public String [] getDirector()
	{
		ArrayList<String> directors= new ArrayList<>();
		String directorarray[]=null;
		try{
			String query= "SELECT DISTINCT D.DIRECTOR_NAME FROM MOVIE_DIRECTOR D WHERE D.MID IN (" +iq+ ") ORDER BY D.DIRECTOR_NAME"; 
			System.out.println(query);
			ResultSet result = statement1.executeQuery(query);
			int i=0;
			while(result.next()&& i<100){
				String director = result.getString("DIRECTOR_NAME");
				directors.add(director);
				i++;
			}
			directorarray= new String[directors.size()];
			for(int j=0; j<directors.size(); j++){
				directorarray[j]= directors.get(j);
			}
		}
		//SELECT DISTINCT ACTOR_NAME FROM MOVIE_ACTOR A,MOVIE_COUNTRY C, MOVIE_GENRE G, MOVIE M WHERE M.YEAR>='1988' AND M.YEAR <='2000' AND G.GENRE='Adventure' AND C.COUNTRY='USA'

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return directorarray;
	}
	
	//selecting tags based on genres, countries, actors, directors.
	public ArrayList<Tags> getTag(String directorname, ArrayList<String> actorlist, int fyear,int tyear, String attr){
		ArrayList<Tags> tags= new ArrayList<>();
		try{
			String option;
		    if(attr.equals("AND"))
		    	option="INTERSECT";
		    else
		    	option="UNION";
		    
			String query = "";
			
			if(actorlist!=null && actorlist.size()>0){
				iq+=" INTERSECT ";
				iq+=" (SELECT DISTINCT A.MID FROM MOVIE_ACTOR A, MOVIE M WHERE A.MID=M.MID AND A.ACTOR_NAME='"+actorlist.get(0)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
				for(int i=1;i<actorlist.size();i++){
					iq+=" "+option+" SELECT DISTINCT A.MID FROM MOVIE_ACTOR A, MOVIE M WHERE A.MID=M.MID AND A.ACTOR_NAME='"+actorlist.get(i)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";
				}
				iq+=") ";
			}
			if(!directorname.equals("")){
				iq+=" INTERSECT ";
				iq+=" (SELECT DISTINCT D.MID FROM MOVIE_DIRECTOR D, MOVIE M WHERE D.MID=M.MID AND D.DIRECTOR_NAME='"+directorname+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
				iq+=") ";
			}
			query+= "SELECT DISTINCT T.TAG_ID, T.VALUE FROM TAGS T, MOVIE_TAGS M WHERE T.TAG_ID=M.TAG_ID AND M.MID IN ("+iq+ ")";
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
		
		return tags;
	}
	public ArrayList<Result> getResult(ArrayList<Integer> tagids, String sign, int weight, int fyear,int tyear,String attr ){
			 ArrayList<Result> result= new ArrayList<>();
			 String query="";
			 try{
				 if(tagids.size()!=0){
					String option;
				    if(attr.equals("AND"))
				    	option="INTERSECT";
				    else
				    	option="UNION";
							
					iq+=" INTERSECT ";
					iq+=" (SELECT DISTINCT D.MID FROM MOVIE_TAGS D, MOVIE M WHERE D.MID=M.MID AND D.TAG_ID='"+tagids.get(0)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";	
					if(weight>-1){
						iq+=" AND TAG_WEIGHT "+sign+ weight;
					}
					for(int i=1;i<tagids.size();i++){
						iq+=" "+option+" SELECT DISTINCT D.MID FROM MOVIE_TAGS D, MOVIE M WHERE D.MID=M.MID AND D.TAG_ID='"+tagids.get(i)+"' AND M.YEAR>='"+ fyear+"' AND M.YEAR <='"+tyear+"' ";
						if(weight>-1){
							iq+=" AND TAG_WEIGHT "+sign+ weight;
						}
					}
					iq+=") ";
				 }
			 query+=" SELECT DISTINCT M.MID,M.TITLE,M.YEAR,M.RTAUDRATING,M.AUDNUMRATING FROM MOVIE M WHERE M.MID IN ("+iq+ ")";
		
			 System.out.println(query);
			ResultSet rs = statement1.executeQuery(query);
			while(rs.next()){
				Result tmp= new Result();
				tmp.id=rs.getInt("MID");
				System.out.println(tmp.id);
				tmp.title=rs.getString("TITLE");
				tmp.year=rs.getInt("YEAR");
				tmp.country=rs.getString("COUNTRY");
				tmp.audrating=rs.getInt("RTAUDRATING");
				tmp.audnumrating=rs.getFloat("RTAUDNUMRATING");
			}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		 return result;
	}


}


