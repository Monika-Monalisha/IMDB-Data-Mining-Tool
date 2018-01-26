package DBMS;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Movies {
	int id;
	String title;
	int year;
	float AUDRating;
	int AUDNumRating;
	
	Movies(int id, String title, int year, float AUDRating, int AUDNumRating){
		this.id=id;
		this.title=title;
		this.year=year;
		this.AUDRating=AUDRating;
		this.AUDNumRating=AUDNumRating;
		
		}
	}
class Movie_country{
int movieId;
String country;

Movie_country(int id, String country){
this.movieId= id;
this.country= country;
}
}

class Tags{
	int tagId;
	String tagValue;
	Tags(int tagid, String tagvalue){
		this.tagId= tagid;
		this.tagValue=tagvalue;
	}
}
class User_taggedmovie{
	int userId, movieId,tagId;
	
	User_taggedmovie(int uid, int mid, int tid){
		this.userId=uid;
		this.movieId=mid;
		this.tagId=tid;
	
	}
}

class Movie_tags{
	int movieId, tagId, tagWeight;
	Movie_tags(int mid, int tid, int tweight){
		this.movieId= mid;
		this.tagId= tid;
		this.tagWeight=tweight;
		
		
	}
}

class Movie_genre{
	int movieId;
	String genre;
	Movie_genre(int mid, String genre){
		this.movieId=mid;
		this.genre=genre;
	}
}
class Movie_director{
	int movieId;
	String directorId;
	String directorName;
	Movie_director(int mid, String dirId,String dirName){
		this.movieId=mid;
		this.directorId=dirId;
		this.directorName=dirName;
	}
}

class Movie_actor{
	int movieId;
	String actorId;
	String actorName;
	int ranking;
	Movie_actor(int mid, String actId,String actName, int rank){
		this.movieId=mid;
		this.actorId=actId;
		this.actorName=actName;
		this.ranking=rank;
	}
}

public class Populate {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println(args[0]);
		//System.out.println(args[1]);
		//System.out.println(args[2]);
		
		DBconnection db=new DBconnection();
		Connection connection= db.getConnection();
		String currentline;
		BufferedReader br= null;
		
		//reading data from movies.dat
		ArrayList<Movies> movies= new ArrayList<>();
		try {
			 br = new BufferedReader(new FileReader("src/DBMS/"+args[0]));
			} 
			catch (FileNotFoundException e) {
				System.out.println("file not found");
			e.printStackTrace();
			}
			try {
				while ((currentline = br.readLine()) != null)
				{
					float AudienceRating=0;
					int AudienceNumRating=0;
					String ar[]=null;
					int id=0; 
					try{
						 ar = currentline.split("\\t");
							if(ar!= null){
							  id =Integer.parseInt(ar[0].trim());//Integer.parseInt(ar[0]);
							String title =ar[1];
							int year =Integer.parseInt(ar[5]);
							
							try{
							AudienceRating =Float.parseFloat(ar[17]);
							}
							catch(NumberFormatException E){
								AudienceRating= 0;
								//System.out.println("id" +id+ " "+ar[17]);
							}
							try{
								AudienceNumRating =Integer.parseInt(ar[18]);
							}
							catch(NumberFormatException E)
							{
								AudienceNumRating =0;
								//System.out.println("id" +id+ " "+ ar[18]);
							}
							
							Movies temp= new Movies (id, title, year,AudienceRating, AudienceNumRating);
							movies.add(temp);
						}
					}
					catch(NumberFormatException E)
					{
						E.printStackTrace();
						System.err.println(E.getMessage());
						continue;
					}
				}	
			} 
				
				catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Statement stmt = connection.createStatement();
				String sql = "DELETE FROM MOVIE";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				//String sql =	"INSERT INTO MOVIE\n" +
						//"(MID, TITLE, YEAR)\n" +
						//"VALUES (?,?,?)";
				String sql =	"INSERT INTO MOVIE\n" +
								"(MID, TITLE, YEAR, RTAUDRATING, RTAUDNUMRATING)\n" +
								"VALUES (?,?,?,?,?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				for (Movies m : movies) {
					pstmt.setInt(1, m.id);
					pstmt.setString(2, m.title);
					pstmt.setInt(3, m.year);
					pstmt.setFloat(4, m.AUDRating);
					pstmt.setFloat(5, m.AUDNumRating);
					pstmt.executeUpdate();
				}
				System.out.println(" movies data inserted");

			} catch (SQLException e) {
				e.printStackTrace();	
		}
			
			
		// raeding data from tags.dat
			ArrayList<Tags> tags= new ArrayList<>();
			try {
				 br = new BufferedReader(new FileReader("src/DBMS/"+args[1]));
				} 
				catch (FileNotFoundException e) {
					
					System.out.println("file not found");
				e.printStackTrace();
				}
			
			try {
				while ((currentline = br.readLine()) != null)
				{
					try{
						String ar[] = currentline.split("\\t");
							if(ar.length>1){
							int id =Integer.parseInt(ar[0]);//Integer.parseInt(ar[0]);
							String value=null;
						for(int i=1; i<ar.length;i++){
							
							value = value+ar[i];
						}
							Tags temp= new Tags (id, value);
							tags.add(temp);
						}
					}
					catch(NumberFormatException E)
					{
						E.printStackTrace();
						System.err.println(E.getMessage());
						continue;
					}
				}	
			} 
				
				catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Statement stmt = connection.createStatement();
				String sql = "DELETE FROM TAGS";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				String sql =	"INSERT INTO TAGS\n" +
								"(TAG_ID,VALUE )\n" +
								"VALUES (?,?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				for (Tags t : tags) {
					pstmt.setInt(1, t.tagId);
					pstmt.setString(2, t.tagValue);
					pstmt.executeUpdate();
				}
				System.out.println(" Tags data inserted");

			} catch (SQLException e) {
				e.printStackTrace();	
		}
			
			
			// reading data from user_taggedmovies.dat
			ArrayList<User_taggedmovie> user_taggedmovie= new ArrayList<>();
			try {
				 br = new BufferedReader(new FileReader("src/DBMS/"+args[2]));
				} 
				catch (FileNotFoundException e) {
					
					System.out.println("file not found");
				e.printStackTrace();
				}
			
			try {
				while ((currentline = br.readLine()) != null)
				{
					try{
						String ar[] = currentline.split("\\t");
							if(ar.length>1){
							int userId =Integer.parseInt(ar[0]);//Integer.parseInt(ar[0]);
							int movieId= Integer.parseInt(ar[1]);
							int tagId= Integer.parseInt(ar[2]);
							User_taggedmovie temp= new User_taggedmovie (userId,movieId,tagId);
							user_taggedmovie.add(temp);
						}
					}
					catch(NumberFormatException E)
					{
						E.printStackTrace();
						System.err.println(E.getMessage());
						continue;
					}
				}	
			} 
				
				catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Statement stmt = connection.createStatement();
				String sql = "DELETE FROM USER_TAGEDMOVIES";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				//String sql =	"INSERT INTO MOVIE\n" +"(MID, TITLE, YEAR)\n" +"VALUES (?,?,?)";
				String sql =	"INSERT INTO USER_TAGEDMOVIES\n" +"(USER_ID,MID,TAG_ID )\n"+
								"VALUES (?,?,?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				for (User_taggedmovie u : user_taggedmovie) {
					pstmt.setInt(1, u.userId);
					pstmt.setInt(2, u.movieId);
					pstmt.setInt(3, u.tagId);
					
					pstmt.executeUpdate();
				}
				System.out.println(" user_taggedmovies data inserted");

			} catch (SQLException e) {
				System.err.println(e.getErrorCode());
				e.printStackTrace();	
		}
			
		
		// raeding data from movies_countries.dat
					ArrayList<Movie_country> moviecountry= new ArrayList<>();
					try {
						 br = new BufferedReader(new FileReader("src/DBMS/"+args[3]));
						} 
						catch (FileNotFoundException e) {
							
							System.out.println("file not found");
						e.printStackTrace();
						}
					
					try {
						while ((currentline = br.readLine()) != null)
						{
							try{
								String ar[] = currentline.trim().split("\\t");
									if(ar.length>1){
									int movieId =Integer.parseInt(ar[0]);//Integer.parseInt(ar[0]);
								    String country= ar[1];
									Movie_country temp= new Movie_country (movieId,country);
									moviecountry.add(temp);
								}
							}
							catch(NumberFormatException E)
							{
								E.printStackTrace();
								System.err.println(E.getMessage());
								continue;
							}
						}	
					} 
						
						catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						Statement stmt = connection.createStatement();
						String sql = "DELETE FROM MOVIE_COUNTRY";
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					try {
						String sql =	"INSERT INTO MOVIE_COUNTRY\n" +"(MID,COUNTRY )\n"+
										"VALUES (?,?)";
						PreparedStatement pstmt = connection.prepareStatement(sql);
						for (Movie_country m : moviecountry) {
							pstmt.setInt(1, m.movieId);
							pstmt.setString(2, m.country);
							pstmt.executeUpdate();
						}
						System.out.println(" movie_country data inserted");

					} catch (SQLException e) {
						System.err.println(e.getErrorCode());
						e.printStackTrace();	
				}
		
			
		// raeding data from movie_tags.dat
					ArrayList<Movie_tags> movie_tags= new ArrayList<>();
					try {
						 br = new BufferedReader(new FileReader("src/DBMS/"+args[4]));
						} 
						catch (FileNotFoundException e) {
							
							System.out.println("file not found");
						e.printStackTrace();
						}
					
					try {
						while ((currentline = br.readLine()) != null)
						{
							try{
								String ar[] = currentline.trim().split("\\t");
									if(ar.length>1){
									int mid =Integer.parseInt(ar[0]);//Integer.parseInt(ar[0]);
									int tagid=Integer.parseInt(ar[1]);
									int tweight=Integer.parseInt(ar[2]);
								
									Movie_tags temp= new Movie_tags (mid, tagid,tweight);
									movie_tags.add(temp);
								}
							}
							catch(NumberFormatException E)
							{
								E.printStackTrace();
								System.err.println(E.getMessage());
								continue;
							}
						}	
					} 
						
						catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						Statement stmt = connection.createStatement();
						String sql = "DELETE FROM MOVIE_TAGS";
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					try {
						String sql =	"INSERT INTO MOVIE_TAGS\n" +
										"(MID,TAG_ID,WEIGHT )\n" +
										"VALUES (?,?,?)";
						PreparedStatement pstmt = connection.prepareStatement(sql);
						for ( Movie_tags mt : movie_tags) {
							pstmt.setInt(1, mt.movieId);
							pstmt.setInt(2, mt.tagId);
							pstmt.setInt(3, mt.tagWeight);
							pstmt.executeUpdate();
						}
						System.out.println(" Movie_Tags data inserted");

					} catch (SQLException e) {
						e.printStackTrace();	
				}
					
		
		// raeding data from movie_genres.dat
		ArrayList<Movie_genre> moviegenre= new ArrayList<>();
		try {
			 br = new BufferedReader(new FileReader("src/DBMS/"+args[5]));
			} 
			catch (FileNotFoundException e) {
				
				System.out.println("file not found");
			e.printStackTrace();
			}
		
		try {
			while ((currentline = br.readLine()) != null)
			{
				try{
					String ar[] = currentline.trim().split("\\t");
						if(ar.length>1){
						int movieId =Integer.parseInt(ar[0]);//Integer.parseInt(ar[0]);
					    String genre= ar[1];
						Movie_genre temp= new Movie_genre (movieId,genre);
						moviegenre.add(temp);
					}
				}
				catch(NumberFormatException E)
				{
					E.printStackTrace();
					System.err.println(E.getMessage());
					continue;
				}
			}	
		} 
			
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Statement stmt = connection.createStatement();
			String sql = "DELETE FROM MOVIE_GENRE";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			String sql =	"INSERT INTO MOVIE_GENRE\n" +"(MID,GENRE )\n"+
							"VALUES (?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			for (Movie_genre mg : moviegenre) {
				pstmt.setInt(1, mg.movieId);
				pstmt.setString(2, mg.genre);
				pstmt.executeUpdate();
			}
			System.out.println(" movie_genre data inserted");

		} catch (SQLException e) {
			System.err.println(e.getErrorCode());
			e.printStackTrace();	
	}
		
		
		// raeding data from movie_directors.dat
				ArrayList<Movie_director> moviedirector= new ArrayList<>();
				try {
					 br = new BufferedReader(new FileReader("src/DBMS/"+args[6]));
					} 
					catch (FileNotFoundException e) {
						
						System.out.println("file not found");
					e.printStackTrace();
					}
				
				try {
					while ((currentline = br.readLine()) != null)
					{
						try{
							String ar[] = currentline.trim().split("\\t");
								if(ar.length>1){
								int movieId =Integer.parseInt(ar[0]);//Integer.parseInt(ar[0]);
							    String dirId= ar[1];
							    String dirName= ar[2];
								Movie_director temp= new Movie_director (movieId,dirId,dirName);
								moviedirector.add(temp);
							}
						}
						catch(NumberFormatException E)
						{
							E.printStackTrace();
							System.err.println(E.getMessage());
							continue;
						}
					}	
				} 
					
					catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Statement stmt = connection.createStatement();
					String sql = "DELETE FROM MOVIE_DIRECTOR";
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					String sql =	"INSERT INTO MOVIE_DIRECTOR\n" +"(MID, DIRECTOR_ID,DIRECTOR_NAME)\n"+
									"VALUES (?,?,?)";
					PreparedStatement pstmt = connection.prepareStatement(sql);
					for (Movie_director md : moviedirector) {
						pstmt.setInt(1, md.movieId);
						pstmt.setString(2, md.directorId);
						pstmt.setString(3, md.directorName);
						pstmt.executeUpdate();
					}
					System.out.println(" movie_director data inserted");

				} catch (SQLException e) {
					System.err.println(e.getErrorCode());
					e.printStackTrace();	
			}
				
				
				// reading data from movie_actors.dat
				ArrayList<Movie_actor> movieactor= new ArrayList<>();
				try {
					 br = new BufferedReader(new FileReader("src/DBMS/"+args[7]));
					} 
					catch (FileNotFoundException e) {
						
						System.out.println("file not found");
					e.printStackTrace();
					}
				
				try {
					while ((currentline = br.readLine()) != null)
					{
						try{
							String ar[] = currentline.trim().split("\\t");
								if(ar.length>1){
								int movieId =Integer.parseInt(ar[0]);//Integer.parseInt(ar[0]);
							    String actId= ar[1];
							    String actName= ar[2];
							    int rank= Integer.parseInt(ar[3]);
								Movie_actor temp= new Movie_actor (movieId,actId,actName, rank);
								movieactor.add(temp);
							}
						}
						catch(NumberFormatException E)
						{
							E.printStackTrace();
							System.err.println(E.getMessage());
							continue;
						}
					}	
				} 
					
					catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Statement stmt = connection.createStatement();
					String sql = "DELETE FROM MOVIE_ACTOR";
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					String sql =	"INSERT INTO MOVIE_ACTOR\n" +"(MID, ACTOR_ID,ACTOR_NAME,RANKING)\n"+
									"VALUES (?,?,?,?)";
					PreparedStatement pstmt = connection.prepareStatement(sql);
					for (Movie_actor mc : movieactor) {
						pstmt.setInt(1, mc.movieId);
						pstmt.setString(2, mc.actorId);
						pstmt.setString(3, mc.actorName);
						pstmt.setInt(4, mc.ranking);
						pstmt.executeUpdate();
					}
					System.out.println(" movie_actor data inserted");

				} catch (SQLException e) {
					System.err.println(e.getErrorCode());
					e.printStackTrace();	
			}
				
	}	
	}


	
	
