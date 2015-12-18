

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Populate{
	static final String JDBC_DRIVER="oracle.jdbc.driver.OracleDriver";
	static final String DB_URL="jdbc:oracle:thin:@dbserver.engr.scu.edu:1521:db11g";
	static final String Username="rkinkiri";
	static final String pass="ramya123";
	 
		public Populate(File file,File file1,File file2,File file4){
			   
			 Yelp_User(file4);
			 Friendslist(file4);
			 Business(file);
			 Attributes(file);
			 BAttributes();
			 Categories(file);
			 Hours(file);
			 Yelp_Review(file1);
			 
			 }
	public void Yelp_User(File readfile){
	    Connection conn=null;
		Statement stmt=null;
		PreparedStatement stmt1=null;
		JSONParser parser=new JSONParser();
try{
    InputStream fis=new FileInputStream(readfile);
    BufferedReader br=new BufferedReader(new InputStreamReader(fis));
    String line=null;
    int count=0;
    Class.forName("oracle.jdbc.driver.OracleDriver");
	System.out.println("connecting to database....");
	conn=DriverManager.getConnection(DB_URL,Username,pass);
	System.out.println("creating statement.....");
	stmt=conn.createStatement();
	String sqld="Truncate table YelpUser";
	
	stmt.execute(sqld);
    
    String sql1="INSERT INTO YelpUser(user_id,user_name,Avgstars,type,fans,reviewcount,yelping_since,votes_funny,votes_useful,votes_cool,comp_funny,comp_cool,comp_writer,comp_photos,comp_more) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    stmt1=conn.prepareStatement(sql1);
    while((line=br.readLine())!=null){
        count++;
        Object obj = parser.parse(line);
		JSONObject jsonobj=(JSONObject) obj;
		String userid=(String)jsonobj.get("user_id");
		String name=(String)jsonobj.get("name");
		Double avgstars=(Double) jsonobj.get("average_stars");
		String type=(String)jsonobj.get("type");
		Long fanslong=(Long)jsonobj.get("fans");
		Integer fans=new Integer(fanslong.intValue());
		Long reviewcountlong=(Long)jsonobj.get("review_count");
		int reviewcount=new Integer(reviewcountlong.intValue());
		String yelping_since=(String)jsonobj.get("yelping_since");
		JSONObject objv=(JSONObject)jsonobj.get("votes");
		JSONObject objc=(JSONObject)jsonobj.get("compliments");
		int votes_funny=new Integer(((Long)objv.get("funny")).intValue());
		int votes_useful=new Integer(((Long)objv.get("useful")).intValue());
		int votes_cool=new Integer(((Long)objv.get("cool")).intValue());
		int comp_funny,comp_cool,comp_writer,comp_photos,comp_more;
		if((Long)objc.get("funny")!=null){
		comp_funny=new Integer(((Long)objc.get("funny")).intValue());
		}else{
		comp_funny=0;
		}
		if((Long)objc.get("cool")!=null){
		comp_cool=new Integer(((Long)objc.get("cool")).intValue());
		}else{
			comp_cool=0;
		}
		if((Long)objc.get("writer")!=null){
		comp_writer=new Integer(((Long)objc.get("writer")).intValue());
		}else{
			comp_writer=0;
		}
		if((Long)objc.get("photos")!=null){
			comp_photos=new Integer(((Long)objc.get("photos")).intValue());
		}else{
			comp_photos=0;
		}
			
		if((Long)objc.get("more")!=null){
		comp_more=new Integer(((Long)objc.get("more")).intValue());
		}else{
			comp_more=0;
		}
		stmt1.setString(1, userid);
		stmt1.setString(2, name);
		stmt1.setDouble(3, avgstars);
		stmt1.setString(4, type);
		stmt1.setInt(5, fans);
		stmt1.setInt(6, reviewcount);
		stmt1.setString(7, yelping_since);
		stmt1.setInt(8, votes_funny);
		stmt1.setInt(9, votes_useful);
		stmt1.setInt(10, votes_cool);
		stmt1.setInt(11, comp_funny);
		stmt1.setInt(12, comp_cool);
		stmt1.setInt(13, comp_writer);
		stmt1.setInt(14, comp_photos);
		stmt1.setInt(15, comp_more);
		stmt1.executeUpdate();
    }
    br.close();
    System.out.println(count);
}catch(SQLException se){
	se.printStackTrace();
}
catch(Exception e){
    System.err.println("Error: Target File Cannot Be Read");
}finally{
	// nothing we can do
    try{
  	  if(stmt!=null){stmt.close();}
  	  if(stmt1!=null){stmt.close();}
       if(conn!=null)
          conn.close();
    }catch(SQLException se){
       se.printStackTrace();
    }//end finally try
 }//end try
 System.out.println("populated YelpUser");
	}




public void Friendslist(File readfile) {

	Connection conn=null;
	Statement stmt=null;
	PreparedStatement stmt1=null;
	JSONParser parser=new JSONParser();
	int count=0;

	try{
		FileReader file=new FileReader(readfile);
		BufferedReader br=new BufferedReader(file);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("connecting to database....");
		conn=DriverManager.getConnection(DB_URL,Username,pass);
		System.out.println("creating statement.....");
		stmt=conn.createStatement();
		
		
		String sqlc2="Truncate Table Friends_list";
        
        stmt.executeQuery(sqlc2);
        String sql1="INSERT INTO Friends_list(user_id,frnd_id) VALUES(?,?)";
        stmt1=conn.prepareStatement(sql1);
        String line=null;
        	while((line=br.readLine())!=null){
        		count++;
		Object obj = parser.parse(line);
		JSONObject jsonobj=(JSONObject) obj;
		JSONArray frnds=(JSONArray)jsonobj.get("friends");
		String userid=(String)jsonobj.get("user_id");
		if(frnds.size()!=0){
		for(int p=0;p<frnds.size();p++){
			stmt1.setString(1,userid);
			stmt1.setString(2, (frnds.get(p)).toString());
			stmt1.executeUpdate();
		}
		}else{
			continue;
			}
		}
        	System.out.println(count);
        	}catch(SQLException se){
		se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		// nothing we can do
	      try{
	    	  if(stmt!=null){stmt.close();}
	    	  if(stmt1!=null){stmt.close();}
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Friendslist populated");
	}
public void Business(File readfile){
	Connection conn=null;
	Statement stmt=null;
	PreparedStatement stmt1=null;
	BufferedReader br=null;
	JSONParser parser=new JSONParser();
	int count=0;
	

	try{
		FileReader file=new FileReader(readfile);
		br=new BufferedReader(file);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("connecting to database....");
		conn=DriverManager.getConnection(DB_URL,Username,pass);
		System.out.println("creating statement.....");
		stmt=conn.createStatement();
		String sqld="Truncate table Business";
		
		stmt.execute(sqld);
		
		String sql1="INSERT INTO Business(business_id,bname,address,City,State,open,latitude,longitude,stars,type,reviewcount) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		stmt1=conn.prepareStatement(sql1);
		String line;
		while((line=br.readLine())!=null){
			count++;
			Object jsonobj=parser.parse(line);
			JSONObject obj=(JSONObject) jsonobj;
			String bid=(String)obj.get("business_id");
			String bname=(String)obj.get("name");
			String address=(String)obj.get("full_address");
			String city=(String)obj.get("city");
			String state=(String)obj.get("state");
			String open=Boolean.toString((Boolean)obj.get("open"));
			Double latitude=(Double)obj.get("latitude");
			Double longitude=(Double)obj.get("longitude");
			int stars=new Integer(((Double)obj.get("stars")).intValue());
			String type=(String)obj.get("type");
			int rcount=new Integer(((Long)obj.get("review_count")).intValue());
			stmt1.setString(1, bid);
			stmt1.setString(2, bname);
			stmt1.setString(3, address);
			stmt1.setString(4, city);
			stmt1.setString(5, state);
			stmt1.setString(6, open);
			stmt1.setDouble(7, latitude);
			stmt1.setDouble(8,longitude);
			stmt1.setInt(9, stars);
			stmt1.setString(10, type);
			stmt1.setInt(11, rcount);
			stmt1.execute();
		}
		System.out.println(count);
		System.out.println("end of file");
}
catch(SQLException se){
		se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		// nothing we can do
	      try{
	    	  if(stmt!=null)
	    	  {stmt.close();
	    	  }
	    	  if(stmt1!=null)
	    	  {stmt.close();
	    	  }
	         if(conn!=null){
	            conn.close();
	         }
	         br.close(); 
	       
	      }catch(SQLException se){
		         se.printStackTrace();
		      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      }
	System.out.println("Business created");
	
	}


	public void Attributes(File readfile){
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement stmt1=null;
		JSONParser parser=new JSONParser();
		Set<String> akeys=new HashSet<String>();
		Set<String> pkeys=new HashSet<String>();
		Set<String> gkeys=new HashSet<String>();
		Set<String> mkeys=new HashSet<String>();
		Set<String> amkeys=new HashSet<String>();
		Set<String> hkeys=new HashSet<String>();
		Set<String> pakeys=new HashSet<String>();
		Set<String> dkeys=new HashSet<String>();
		try{
			FileReader file=new FileReader(readfile);
			BufferedReader br=new BufferedReader(file);
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("connecting to database....");
			conn=DriverManager.getConnection(DB_URL,Username,pass);
			System.out.println("creating statement.....");
			stmt=conn.createStatement();
			String sqld="Truncate table Attributes";
			
			stmt.execute(sqld);
			
			String sql1="INSERT INTO Attributes(business_id,"
					+ "Has_TV,"
					+ "Coat_Check,"
					+ "Open_24_Hours,"
					+ "Accepts_Insurance,"
					+ "Dogs_Allowed,"
					+ "Caters,"
					+ "Happy_Hour,"
					+ "Good_for_Kids,"
					+ "Good_For_Dancing,"
					+ "Outdoor_Seating,"
					+ "Takes_Reservations,"
					+ "Waiter_Service,"
					+ "Drive_Thru,"
					+ "Order_at_Counter,"
					+ "Accepts_Credit_Cards,"
					+ "BYOB_Corkage,"
					+ "Good_For_Groups,"
					+ "By_Appointment_Only,"
					+ "Take_out,"
					+ "Wheelchair_Accessible,"
					+ "BYOB,"
					+ "Delivery,"
					+ "Corkage,"
					+ "Parking_lot,"
					+ "Parking_validated,"
					+ "Parking_street,"
					+ "Parking_garage,"
					+ "Parking_valet,"
					+ "Good_For_lunch,"
					+ "Good_For_dessert,"
					+ "Good_For_brunch,"
					+ "Good_For_breakfast,"
					+ "Good_For_dinner,"
					+ "Good_For_latenight,"
					+ "Music_karaoke,"
					+ "Music_background_music,"
					+ "Music_playlist,"
					+ "Music_dj,"
					+ "Music_jukebox,"
					+ "Music_video,"
					+ "Music_live,"
					+ "Ambience_trendy,"
					+ "Ambience_romantic,"
					+ "Ambience_upscale,"
					+ "Ambience_divey,"
					+ "Ambience_casual,"
					+ "Ambience_hipster,"
					+ "Ambience_intimate,"
					+ "Ambience_touristy,"
					+ "Ambience_classy,"
					+ "Payment_Type_discover,"
					+ "Payment_Type_amex,"
					+ "Payment_Type_cash_only,"
					+ "Payment_Type_visa,"
					+ "Payment_Type_mastercard,"
					+ "Hair_extensions,"
					+ "Hair_curly,"
					+ "Hair_asian,"
					+ "Hair_coloring,"
					+ "Hair_africanamerican,"
					+ "Hair_perms,"
					+ "Hair_straightperms,"
					+ "Hair_kids,"
					+ "Diet_dairy_free,"
					+ "Diet_soy_free,"
					+ "Diet_vegetarian,"
					+ "Diet_halal,"
					+ "Diet_vegan,"
					+ "Diet_kosher,"
					+ "Diet_gluten_free,"
					+ "Alcohol_beer_and_wine,"
					+ "Alcohol_none,"
					+ "Alcohol_full_bar,"
					+ "Price_Range_1,"
					+ "Price_Range_2,"
					+ "Price_Range_3,"
					+ "Price_Range_4,"
					+ "Wi_Fi_no,"
					+ "Wi_Fi_paid,"
					+ "Wi_Fi_free,"
					+ "Noise_Level_very_loud,"
					+ "Noise_Level_average,"
					+ "Noise_Level_loud,"
					+ "Noise_Level_quiet,"
					+ "Attire_dressy,"
					+ "Attire_formal,"
					+ "Attire_casual,"
					+ "Ages_Allowed_allages,"
					+ "Ages_Allowed_18plus,"
					+ "Ages_Allowed_21plus,"
					+ "Smoking_no,"
					+ "Smoking_yes,"
					+ "Smoking_outdoor) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        //String sql1="INSERT INTO Attributes(business_id,Alcohol_beer_and_wine,Alcohol_none,Alcohol_full_bar,Price_Range_1,Price_Range_2,Price_Range_3,Price_Range_4,Wi_Fi_no,Wi_Fi_paid,Wi_Fi_free,Noise_Level_very_loud,Noise_Level_average,Noise_Level_loud,Noise_Level_quiet,Attire_dressy,Attire_formal,Attire_casual,Ages_Allowed_allages,Ages_Allowed_18plus,Ages_Allowed_21plus,Smoking_no,Smoking_yes,Smoking_outdoor) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt1=conn.prepareStatement(sql1);
			int rows1=0;
			String line=null;
			while(true){
	        	if((line=br.readLine())!=null){
	        		rows1++;
			Object obj = parser.parse(line);
			JSONObject jsonobj=(JSONObject) obj;
			JSONObject attr=(JSONObject) jsonobj.get("attributes");
			JSONObject park=(JSONObject)attr.get("Parking");
			JSONObject gfor=(JSONObject)attr.get("Good For");
			JSONObject ambience=(JSONObject)attr.get("Ambience");
			JSONObject ptypes=(JSONObject)attr.get("Payment Types");
			JSONObject htypes=(JSONObject)attr.get("Hair Types Specialized In");
			JSONObject music=(JSONObject)attr.get("Music");
			JSONObject diet=(JSONObject)attr.get("Dietary Restrictions");
			//collecting individual attribute keys
			HashMap<String, JSONObject> attributes=new HashMap<String, JSONObject>(attr);
			ArrayList<String> keyset=new ArrayList<String>(attributes.keySet());
			for(String s:keyset){
				akeys.add(s);
				}
			String[] subobj={"Good For","Parking","Ambience","Payment Types","Hair Types Specialized In","Music","Dietary Restrictions","Alcohol","Price_Range","Wi_Fi","Noise_Level","Attire","Ages_Allowed","Smoking"};
			for(String S:subobj){
				if(keyset.contains(S)){
					akeys.remove(S);
				}
			}
			//collecting parking attributes keys	
			if(park!=null){
			HashMap<String, JSONObject> parking=new HashMap<String, JSONObject>(park);
			ArrayList<String> pkeyslist=new ArrayList<String>(parking.keySet());
			for(String s:pkeyslist){
				pkeys.add(s);
			}
			}
			//collecting good for attribute keys
			if(gfor!=null){
			HashMap<String, JSONObject> goodfor=new HashMap<String, JSONObject>(gfor);
			ArrayList<String> gkeyslist=new ArrayList<String>(goodfor.keySet());
			for(String s:gkeyslist){
				gkeys.add(s);
			}
			}
			//collecting ambience keys
			if(ambience!=null){
				HashMap<String, JSONObject> amb=new HashMap<String, JSONObject>(ambience);
				ArrayList<String> ambkeys=new ArrayList<String>(amb.keySet());
				for(String s:ambkeys){
					amkeys.add(s);
				}
				}
			//collecting parkingtype keys
			if(ptypes!=null){
				HashMap<String, JSONObject> ptype=new HashMap<String, JSONObject>(ptypes);
				ArrayList<String> ptypekeys=new ArrayList<String>(ptype.keySet());
				for(String s:ptypekeys){
					pakeys.add(s);
				}
				}
			//collecting hair style type keys
			if(htypes!=null){
				HashMap<String, JSONObject> htype=new HashMap<String, JSONObject>(htypes);
				ArrayList<String> htypelist=new ArrayList<String>(htype.keySet());
				for(String s:htypelist){
					hkeys.add(s);
				}
				}
			//collecting music type keys
			if(music!=null){
				HashMap<String, JSONObject> mus=new HashMap<String, JSONObject>(music);
				ArrayList<String> mkeyslist=new ArrayList<String>(mus.keySet());
				for(String s:mkeyslist){	
					mkeys.add(s);
				}
				}
			//collecting diet type keys
			if(diet!=null){
				HashMap<String, JSONObject> diets=new HashMap<String, JSONObject>(diet);
				ArrayList<String> dkeyslist=new ArrayList<String>(diets.keySet());
				for(String s:dkeyslist){
					dkeys.add(s);
				}
				}
			}else{
	        	System.out.println("end of file and all key sets collected");
	        	break;
	        }
	        	}
			System.out.println(rows1);
		
}catch(SQLException se){
	se.printStackTrace();
}catch(Exception e){
	e.printStackTrace();
}
try{
			FileReader file=new FileReader(readfile);
			BufferedReader br=new BufferedReader(file);
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("connecting to database....");
			conn=DriverManager.getConnection(DB_URL,Username,pass);
			System.out.println("creating statement.....");
			stmt=conn.createStatement();
	        String line=null;
			int rows2=0;
			while(true){
	        	if((line=br.readLine())!=null){
	        		rows2++;
			Object obj = parser.parse(line);
			JSONObject jsonobj=(JSONObject) obj;
			JSONObject attr=(JSONObject) jsonobj.get("attributes");
			JSONObject park=(JSONObject)attr.get("Parking");
			JSONObject gfor=(JSONObject)attr.get("Good For");
			JSONObject ambience=(JSONObject)attr.get("Ambience");
			JSONObject ptypes=(JSONObject)attr.get("Payment Types");
			JSONObject htypes=(JSONObject)attr.get("Hair Types Specialized In");
			JSONObject music=(JSONObject)attr.get("Music");
			JSONObject diet=(JSONObject)attr.get("Dietary Restrictions");
			
			String iHas_TV,iCoat_Check,iOpen_24_Hours,iAccepts_Insurance,iDogs_Allowed,iCaters,iHappy_Hour,iGood_for_Kids,iGood_For_Dancing,iOutdoor_Seating,iTakes_Reservations,iWaiter_Service,iDrive_Thru,iOrder_at_Counter,iAccepts_Credit_Cards,iBYOB_Corkage,iGood_For_Groups,iBy_Appointment_Only,iTake_out,iWheelchair_Accessible,iBYOB,iDelivery,iCorkage,iplot,ipvalidated,ipstreet,ipgarage,ipvalet,iglunch,igdessert,igbrunch,igbreakfast,igdinner,iglatenight,imkaraoke,imbackground_music,implaylist,imdj,imjukebox,imvideo,imlive,iamtrendy,iamromantic,iamupscale,iamdivey,iamcasual,iamhipster,iamintimate,iamtouristy,iamclassy,ipadiscover,ipaamex,ipacash_only,ipavisa,ipamastercard,ihextensions,ihcurly,ihasian,ihcoloring,ihafricanamerican,ihperms,ihstraightperms,ihkids,iddairy_free,idsoy_free,idvegetarian,idhalal,idvegan,idkosher,idgluten_free,iAlcohol_beer_and_wine,iAlcohol_none,iAlcohol_full_bar,iPrice_1,iPrice_2,iPrice_3,iPrice_4,iWi_Fi_no,iWi_Fi_paid,iWi_Fi_free,iNoise_Level_very_loud,iNoise_Level_average,iNoise_Level_loud,iNoise_Level_quiet,iAttire_dressy,iAttire_formal,iAttire_casual,iAges_Allowed_allages,iAges_Allowed_18plus,iAges_Allowed_21plus,iSmoking_no,iSmoking_yes,iSmoking_outdoor;
			iHas_TV=iCoat_Check=iOpen_24_Hours=iAccepts_Insurance=iDogs_Allowed=iCaters=iHappy_Hour=iGood_for_Kids=iGood_For_Dancing=iOutdoor_Seating=iTakes_Reservations=iWaiter_Service=iDrive_Thru=iOrder_at_Counter=iAccepts_Credit_Cards=iBYOB_Corkage=iGood_For_Groups=iBy_Appointment_Only=iTake_out=iWheelchair_Accessible=iBYOB=iDelivery=iCorkage=iplot=ipvalidated=ipstreet=ipgarage=ipvalet=iglunch=igdessert=igbrunch=igbreakfast=igdinner=iglatenight=imkaraoke=imbackground_music=implaylist=imdj=imjukebox=imvideo=imlive=iamtrendy=iamromantic=iamupscale=iamdivey=iamcasual=iamhipster=iamintimate=iamtouristy=iamclassy=ipadiscover=ipaamex=ipacash_only=ipavisa=ipamastercard=ihextensions=ihcurly=ihasian=ihcoloring=ihafricanamerican=ihperms=ihstraightperms=ihkids=iddairy_free=idsoy_free=idvegetarian=idhalal=idvegan=idkosher=idgluten_free=iAlcohol_beer_and_wine=iAlcohol_none=iAlcohol_full_bar=iPrice_1=iPrice_2=iPrice_3=iPrice_4=iWi_Fi_no=iWi_Fi_paid=iWi_Fi_free=iNoise_Level_very_loud=iNoise_Level_average=iNoise_Level_loud=iNoise_Level_quiet=iAttire_dressy=iAttire_formal=iAttire_casual=iAges_Allowed_allages=iAges_Allowed_18plus=iAges_Allowed_21plus=iSmoking_no=iSmoking_yes=iSmoking_outdoor="false";
			String z="i";
			
			
			HashMap<String, String> a1=new HashMap<String, String>();
			//attribute keys
			for(String s1:akeys){
				
			if(attr.get(s1)!=null){
				if((attr.get(s1)) instanceof Boolean){
					String S=(z.concat(s1)).replaceAll("[\\s\\-]", "_");
				a1.put(S, (Boolean.toString((Boolean)attr.get(s1))));
				}
				if((attr.get(s1)) instanceof Integer){
					String S=(z.concat(s1)).replaceAll("[\\s\\-]", "_");
				a1.put(S,Integer.toString((Integer)attr.get(s1)));
				}
				if((attr.get(s1)) instanceof Long ){
					String S=(z.concat(s1)).replaceAll("[\\s\\-]", "_");
				a1.put(S,Long.toString((Long)attr.get(s1)));
				}
				if((attr.get(s1)) instanceof String){
					String S=(z.concat(s1)).replaceAll("[\\s\\-]", "_");
				a1.put(S,(String)attr.get(s1));
				}
			}
			}
			//Alcohol
			if((String)attr.get("Alcohol")!=null){
				String S=(String)attr.get("Alcohol");
				if(S.equals("beer_and_wine")){
					iAlcohol_beer_and_wine="true";
				}
				if(S.equals("none")){
					iAlcohol_none="true";
				}
				if(S.equals("full_bar")){
					iAlcohol_full_bar="true";
				}
			}
			//Price_Range
			if(attr.get("Price Range")!=null){
				String S=Long.toString((Long)attr.get("Price Range"));
				if(S.equals("1")){
					iPrice_1="true";
				}
				if(S.equals("2")){
					iPrice_2="true";
				}
				if(S.equals("3")){
					iPrice_3="true";
				}
				if(S.equals("4")){
					iPrice_4="true";
				}
			}
			
			
			
			//Wi_Fi
			if((String)attr.get("Wi Fi")!=null){
				String S=(String)attr.get("Wi Fi");
				if(S.equals("no")){
					iWi_Fi_no="true";
				}
				if(S.equals("paid")){
					iWi_Fi_paid="true";
				}
				if(S.equals("free")){
					iWi_Fi_free="true";
				}
			}
			//Noise_Level
			if((String)attr.get("Noise Level")!=null){
				String S=(String)attr.get("Noise Level");
				if(S.equals("very_loud")){
					iNoise_Level_very_loud="true";
				}
				if(S.equals("average")){
					iNoise_Level_average="true";
				}
				if(S.equals("loud")){
					iNoise_Level_loud="true";
				}
				if(S.equals("quiet")){
					iNoise_Level_quiet="true";
				}
			}
			//Attire
			if((String)attr.get("Attire")!=null){
				String S=(String)attr.get("Attire");
				if(S.equals("dressy")){
					iAttire_dressy="true";
				}
				if(S.equals("formal")){
					iAttire_formal="true";
				}
				if(S.equals("casual")){
					iAttire_casual="true";
				}
			}
			//Ages Allowed
			if((String)attr.get("Ages Allowed")!=null){
				String S=(String)attr.get("Ages Allowed");
				if(S.equals("allages")){
					iAges_Allowed_allages="true";
				}
				if(S.equals("18plus")){
					iAges_Allowed_18plus="true";
				}
				if(S.equals("21plus")){
					iAges_Allowed_21plus="true";
				}
			}
			//Smoking
			if((String)attr.get("Smoking")!=null){
				String S=(String)attr.get("Smoking");
				if(S.equals("no")){
					iSmoking_no="true";
				}
				if(S.equals("yes")){
					iSmoking_yes="true";
				}
				if(S.equals("outdoor")){
					iSmoking_outdoor="true";
				}
			}
			//park keys
			if(park!=null){
			for(String s2:pkeys){
				if(park.get(s2)!=null){
					if((park.get(s2)) instanceof Boolean){
						String S=(z.concat(("p").concat(s2))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Boolean.toString((Boolean)park.get(s2)));
					}
					if((park.get(s2)) instanceof Integer || (park.get(s2)) instanceof Long ){
						String S=(z.concat(("p").concat(s2))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Integer.toString((Integer)park.get(s2)));
					}
					if((park.get(s2)) instanceof String){
						String S=(z.concat(("p").concat(s2))).replaceAll("[\\s\\-]", "_");
					a1.put(S,(String)park.get(s2));
					}
				}
			}
			}
			//good for keys
			if(gfor!=null){
			for(String s3:gkeys){
				if(gfor.get(s3)!=null){
					if((gfor.get(s3)) instanceof Boolean){
						String S=(z.concat(("g").concat(s3))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Boolean.toString((Boolean)gfor.get(s3)));
					}
					if((gfor.get(s3)) instanceof Integer || (gfor.get(s3)) instanceof Long ){
						String S=(z.concat(("g").concat(s3))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Integer.toString((Integer)gfor.get(s3)));
					}
					if((gfor.get(s3)) instanceof String){
						String S=(z.concat(("g").concat(s3))).replaceAll("[\\s\\-]", "_");
					a1.put(S,(String)gfor.get(s3));
					}
				}
			}
			}
			//ambience keys
			if(ambience!=null){
			for(String s4:amkeys){
				if(ambience.get(s4)!=null){
					if((ambience.get(s4)) instanceof Boolean){
						String S=(z.concat(("am").concat(s4))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Boolean.toString((Boolean)ambience.get(s4)));
					}
					if((ambience.get(s4)) instanceof Integer || (ambience.get(s4)) instanceof Long ){
						String S=(z.concat(("am").concat(s4))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Integer.toString((Integer)ambience.get(s4)));
					}
					if((ambience.get(s4)) instanceof String){
						String S=(z.concat(("am").concat(s4))).replaceAll("[\\s\\-]", "_");
					a1.put(S,(String)ambience.get(s4));
					}
				}
			}
			}
			//music keys
			if(music!=null){
			for(String s5:mkeys){
				if(music.get(s5)!=null){
					if((music.get(s5)) instanceof Boolean){
						String S=(z.concat(("m").concat(s5))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Boolean.toString((Boolean)music.get(s5)));
					}
					if((music.get(s5)) instanceof Integer || (music.get(s5)) instanceof Long ){
						String S=(z.concat(("m").concat(s5))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Integer.toString((Integer)music.get(s5)));
					}
					if((music.get(s5)) instanceof String){
						String S=(z.concat(("m").concat(s5))).replaceAll("[\\s\\-]", "_");
					a1.put(S,(String)music.get(s5));
					}
				}
			}
			}
			//payment keys
			if(ptypes!=null){
			for(String s6:pakeys){
				if(ptypes.get(s6)!=null){
					if((ptypes.get(s6)) instanceof Boolean){
						String S=(z.concat(("pa").concat(s6))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Boolean.toString((Boolean)ptypes.get(s6)));
					}
					if((ptypes.get(s6)) instanceof Integer || (ptypes.get(s6)) instanceof Long ){
						String S=(z.concat(("pa").concat(s6))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Integer.toString((Integer)ptypes.get(s6)));
					}
					if((ptypes.get(s6)) instanceof String){
						String S=(z.concat(("pa").concat(s6))).replaceAll("[\\s\\-]", "_");
					a1.put(S,(String)ptypes.get(s6));
					}
				}
			}
			}
			//hair types
			if(htypes!=null){
			for(String s7:hkeys){
				if(htypes.get(s7)!=null){
					if((htypes.get(s7)) instanceof Boolean){
						String S=(z.concat(("h").concat(s7))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Boolean.toString((Boolean)htypes.get(s7)));
					}
					if((htypes.get(s7)) instanceof Integer || (htypes.get(s7)) instanceof Long ){
						String S=(z.concat(("h").concat(s7))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Integer.toString((Integer)htypes.get(s7)));
					}
					if((htypes.get(s7)) instanceof String){
						String S=(z.concat(("h").concat(s7))).replaceAll("[\\s\\-]", "_");
					a1.put(S,(String)htypes.get(s7));
					}
				}
			}
			}
			//diet types
			if(diet!=null){
			for(String s8:dkeys){
				if(diet.get(s8)!=null){
					if((diet.get(s8)) instanceof Boolean){
						String S=(z.concat(("d").concat(s8))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Boolean.toString((Boolean)diet.get(s8)));
					}
					if((diet.get(s8)) instanceof Integer || (diet.get(s8)) instanceof Long ){
						String S=(z.concat(("d").concat(s8))).replaceAll("[\\s\\-]", "_");
					a1.put(S,Integer.toString((Integer)diet.get(s8)));
					}
					if((diet.get(s8)) instanceof String){
						String S=(z.concat(("d").concat(s8))).replaceAll("[\\s\\-]", "_");
					a1.put(S,(String)diet.get(s8));
					}
				}
			}
			}
			String bid=(String)jsonobj.get("business_id");
			stmt1.setString(1,bid);
			stmt1.setString(2,a1.get("iHas_TV"));
			stmt1.setString(3,a1.get("iCoat_Check"));
			stmt1.setString(4,a1.get("iOpen_24_Hours"));
			stmt1.setString(5,a1.get("iAccepts_Insurance"));
			stmt1.setString(6,a1.get("iDogs_Allowed"));
			stmt1.setString(7,a1.get("iCaters"));
			stmt1.setString(8,a1.get("iHappy_Hour"));
			stmt1.setString(9,a1.get("iGood_for_Kids"));
			stmt1.setString(10,a1.get("iGood_For_Dancing"));
			stmt1.setString(11,a1.get("iOutdoor_Seating"));
			stmt1.setString(12,a1.get("iTakes_Reservations"));
			stmt1.setString(13,a1.get("iWaiter_Service"));
			stmt1.setString(14,a1.get("iDrive_Thru"));
			stmt1.setString(15,a1.get("iOrder_at_Counter"));
			stmt1.setString(16,a1.get("iAccepts_Credit_Cards"));
			stmt1.setString(17,a1.get("iBYOB_Corkage"));
			stmt1.setString(18,a1.get("iGood_For_Groups"));
			stmt1.setString(19,a1.get("iBy_Appointment_Only"));
			stmt1.setString(20,a1.get("iTake_out"));
			stmt1.setString(21,a1.get("iWheelchair_Accessible"));
			stmt1.setString(22,a1.get("iBYOB"));
			stmt1.setString(23,a1.get("iDelivery"));
			stmt1.setString(24,a1.get("iCorkage"));
			stmt1.setString(25,a1.get("iplot"));
			stmt1.setString(26,a1.get("ipvalidated"));
			stmt1.setString(27,a1.get("ipstreet"));
			stmt1.setString(28,a1.get("ipgarage"));
			stmt1.setString(29,a1.get("ipvalet"));
			stmt1.setString(30,a1.get("iglunch"));
			stmt1.setString(31,a1.get("igdessert"));
			stmt1.setString(32,a1.get("igbrunch"));
			stmt1.setString(33,a1.get("igbreakfast"));
			stmt1.setString(34,a1.get("igdinner"));
			stmt1.setString(35,a1.get("iglatenight"));
			stmt1.setString(36,a1.get("imkaraoke"));
			stmt1.setString(37,a1.get("imbackground_music"));
			stmt1.setString(38,a1.get("implaylist"));
			stmt1.setString(39,a1.get("imdj"));
			stmt1.setString(40,a1.get("imjukebox"));
			stmt1.setString(41,a1.get("imvideo"));
			stmt1.setString(42,a1.get("imlive"));
			stmt1.setString(43,a1.get("iamtrendy"));
			stmt1.setString(44,a1.get("iamromantic"));
			stmt1.setString(45,a1.get("iamupscale"));
			stmt1.setString(46,a1.get("iamdivey"));
			stmt1.setString(47,a1.get("iamcasual"));
			stmt1.setString(48,a1.get("iamhipster"));
			stmt1.setString(49,a1.get("iamintimate"));
			stmt1.setString(50,a1.get("iamtouristy"));
			stmt1.setString(51,a1.get("iamclassy"));
			stmt1.setString(52,a1.get("ipadiscover"));
			stmt1.setString(53,a1.get("ipaamex"));
			stmt1.setString(54,a1.get("ipacash_only"));
			stmt1.setString(55,a1.get("ipavisa"));
			stmt1.setString(56,a1.get("ipamastercard"));
			stmt1.setString(57,a1.get("ihextensions"));
			stmt1.setString(58,a1.get("ihcurly"));
			stmt1.setString(59,a1.get("ihasian"));
			stmt1.setString(60,a1.get("ihcoloring"));
			stmt1.setString(61,a1.get("ihafricanamerican"));
			stmt1.setString(62,a1.get("ihperms"));
			stmt1.setString(63,a1.get("ihstraightperms"));
			stmt1.setString(64,a1.get("ihkids"));
			stmt1.setString(65,a1.get("iddairy_free"));
			stmt1.setString(66,a1.get("idsoy_free"));
			stmt1.setString(67,a1.get("idvegetarian"));
			stmt1.setString(68,a1.get("idhalal"));
			stmt1.setString(69,a1.get("idvegan"));
			stmt1.setString(70,a1.get("idkosher"));
			stmt1.setString(71,a1.get("idgluten_free"));
			stmt1.setString(72,iAlcohol_beer_and_wine);
			stmt1.setString(73,iAlcohol_none);
			stmt1.setString(74,iAlcohol_full_bar);
			stmt1.setString(75,iPrice_1);
			stmt1.setString(76,iPrice_2);
			stmt1.setString(77,iPrice_3);
			stmt1.setString(78,iPrice_4);
			stmt1.setString(79,iWi_Fi_no);
			stmt1.setString(80,iWi_Fi_paid);
			stmt1.setString(81,iWi_Fi_free);
			stmt1.setString(82,iNoise_Level_very_loud);
			stmt1.setString(83,iNoise_Level_average);
			stmt1.setString(84,iNoise_Level_loud);
			stmt1.setString(85,iNoise_Level_quiet);
			stmt1.setString(86,iAttire_dressy);
			stmt1.setString(87,iAttire_formal);
			stmt1.setString(88,iAttire_casual);
			stmt1.setString(89,iAges_Allowed_allages);
			stmt1.setString(90,iAges_Allowed_18plus);
			stmt1.setString(91,iAges_Allowed_21plus);
			stmt1.setString(92,iSmoking_no);
			stmt1.setString(93,iSmoking_yes);
			stmt1.setString(94,iSmoking_outdoor);
			stmt1.executeUpdate();
	       }
			else{
	        	System.out.println("end of file");
	        	break;
	        }
	        	
			}
			
			System.out.println(rows2);
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
			
	         if(conn!=null)
	            conn.close();
		
		}catch(SQLException se){
	         se.printStackTrace();
	      }
	}
	


System.out.println("Attributes created");
}

public void BAttributes(){
Connection conn=null;
Statement stmt=null;
PreparedStatement stmt1=null;
JSONParser parser=new JSONParser();
int count=0;
int count1=0;
Set<String> Allattrlist=new HashSet<String>();
Set<String> bids=new HashSet<String>();
try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	System.out.println("connecting to database....");
	conn=DriverManager.getConnection(DB_URL,Username,pass);
	stmt=conn.createStatement();
String sqlc="Truncate Table BAttributes";
stmt.execute(sqlc);

String query1="INSERT INTO BAttributes(business_id,Hasattribute) VALUES(?,?)";
stmt1=conn.prepareStatement(query1);
String query="select * from Attributes";
ResultSet rs=stmt.executeQuery(query);
ResultSetMetaData rsmd=rs.getMetaData();
int columncount=rsmd.getColumnCount();
for(int i=1;i<columncount+1;i++){
	String a=rsmd.getColumnName(i);
	String b=a.toLowerCase();
	String c=b.substring(0, 1).toUpperCase() + b.substring(1);
	Allattrlist.add(c);
}
Allattrlist.remove("Business_id");
System.out.println("all attributes collected:"+Allattrlist.size());
String query2="SELECT business_id from Attributes";
ResultSet rs2=stmt.executeQuery(query2);
while(rs2.next()){
	bids.add(rs2.getString("business_id"));
}
System.out.println("all businessids collected:"+bids.size());
//System.out.println("all businessids collected:"+bids);
for(String b:bids){
	count ++;
for(String s:Allattrlist){
	
		String q4="SELECT "+s+" from Attributes WHERE business_id=\'"+b+"\'";
		ResultSet rs4=stmt.executeQuery(q4);
		while(rs4.next()){
			String value;
			if(rs4.getString(s)==null){
				value="false";
			}else{
			value=rs4.getString(s);
			}
			if(value.equals("true")){
				//System.out.println(s);
				stmt1.setString(1, b);
				stmt1.setString(2, s);
				stmt1.execute();
				count1 ++;
			}
		}						
    
	
}
//System.out.println(count);

}
}catch(SQLException se){
    se.printStackTrace();
}catch(Exception e){
	e.printStackTrace();
}
finally{
	try{
	
     if(conn!=null)
        conn.close();

	}catch(SQLException se){
     se.printStackTrace();
	}
	}
System.out.println(count+","+count1);
System.out.println("BAttributes created");
}

public void Categories(File readfile){
	Connection conn=null;
	Statement stmt=null;
	PreparedStatement stmt1=null;
	JSONParser parser=new JSONParser();
	int count=0;
	String[] Maincat={"Active Life","Arts & Entertainment","Automotive","Car Rental","Cafes","Beauty & Spas","Convenience Stores","Dentists","Doctors","Drugstores","Department Stores","Education","Event Planning & Services","Flowers & Gifts","Food","Health & Medical","Home Services","Home & Garden","Hospitals","Hotels & Travel","Hardware Stores","Grocery","Medical Centers","Nurseries & Gardening","Nightlife","Restaurants","Shopping","Transportation"};
	
	try{
		FileReader file=new FileReader(readfile);
		BufferedReader br=new BufferedReader(file);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("connecting to database....");
		conn=DriverManager.getConnection(DB_URL,Username,pass);
		System.out.println("creating statement.....");
		stmt=conn.createStatement();
		
		
		String sqld="Truncate TABLE Categories";
		stmt.execute(sqld);
		s
		String sql1="INSERT INTO Categories(business_id,Maincategory,Subcategory) VALUES(?,?,?)";
		stmt1=conn.prepareStatement(sql1);
		String line=null;
		while((line=br.readLine())!=null){
			count++;
			Object jsonobj = parser.parse(line);
			JSONObject obj=(JSONObject) jsonobj;
			String bid=(String)obj.get("business_id");
			JSONArray categories=(JSONArray)obj.get("categories");
			Set<String> maincat=new HashSet<String>();
			Set<String> subcat=new HashSet<String>();
			//Set<String> subcat=new HashSet<String>();
			ArrayList<String> list=new ArrayList<String>();
			for(int a=0;a<categories.size();a++){
				list.add((String)categories.get(a));
			}
			for(int b=0;b<Maincat.length;b++){
				if(list.contains(Maincat[b])){
					maincat.add(Maincat[b]);
					list.remove(Maincat[b]);
				}
			}
			for(String s:list){
				subcat.add(s);
			}
			//System.out.println();
			Object[] Amaincat=maincat.toArray();
			Object[] Asubcat=subcat.toArray();
			for(int c=0;c<Amaincat.length;c++){
				for(int d=0;d<Asubcat.length;d++){
				stmt1.setString(1,bid);
				stmt1.setString(2,(Amaincat[c]).toString());
				stmt1.setString(3,(Asubcat[d]).toString());
				stmt1.execute();
				}
			}
			//System.out.println(bid+"->"+maincat+subcat);
			//System.out.println("Inserted to table");
				
				
		}
		System.out.println("Entries:"+count);
	
	}catch(SQLException se){
		se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		// nothing we can do
	      try{
	    	  if(stmt!=null){stmt.close();}
	    	  if(stmt1!=null){stmt.close();}
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }
	System.out.println("categories created");
}	
public void Hours(File readfile){
	Connection conn=null;
	Statement stmt=null;
	PreparedStatement stmt1=null;
	JSONParser parser=new JSONParser();
	int count=0;

	try{
		FileReader file=new FileReader(readfile);
		BufferedReader br=new BufferedReader(file);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("connecting to database....");
		conn=DriverManager.getConnection(DB_URL,Username,pass);
		System.out.println("creating statement.....");
		stmt=conn.createStatement();
		String sqld="Truncate table Hours";
		
		stmt.execute(sqld);
		

        String sql1="INSERT INTO Hours(business_id,Monopen,Monclose,Tuesopen,Tuesclose,wedopen,wedclose,thuopen,thuclose,friopen,friclose,satopen,satclose,sunopen,sunclose) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        stmt1=conn.prepareStatement(sql1);
        
        String line=null;
        while((line=br.readLine())!=null){
        	count++;
		Object obj = parser.parse(line);
		JSONObject jsonobj=(JSONObject) obj;
		String bid=(String)jsonobj.get("business_id");
		JSONObject hours,mon,tue,wed,thu,fri,sat,sun;
		hours=(JSONObject)jsonobj.get("hours");
		mon=(JSONObject)hours.get("Monday");
		tue=(JSONObject)hours.get("Tuesday");
		wed=(JSONObject)hours.get("Wednesday");
		thu=(JSONObject)hours.get("Thursday");
		fri=(JSONObject)hours.get("Friday");
		sat=(JSONObject)hours.get("Saturday");
		sun=(JSONObject)hours.get("Sunday");
		String monopen,monclose,tueopen,tueclose,wedopen,wedclose,thuopen,thuclose,friopen,friclose,satopen,satclose,sunopen,sunclose;
		monopen=monclose=tueopen=tueclose=wedopen=wedclose=thuopen=thuclose=friopen=friclose=satopen=satclose=sunopen=sunclose=null;
		if(mon!=null){
		if((String)mon.get("open")!=null){
		monopen=(String)mon.get("open");
		}
		}
		if(mon!=null){
			if((String)mon.get("close")!=null){
				monclose=(String)mon.get("close");
		}
		}
		if(tue!=null){
			if((String)tue.get("open")!=null){
				tueopen=(String)tue.get("open");
		}
		}
		if(tue!=null){
			if((String)tue.get("close")!=null){
				tueclose=(String)tue.get("close");
		}
		}
		if(wed!=null){
			if((String)wed.get("open")!=null){
				wedopen=(String)wed.get("open");
		}
		}
		if(wed!=null){
			if((String)wed.get("close")!=null){
				wedclose=(String)wed.get("close");
		}
		}
		if(thu!=null){
			if((String)thu.get("open")!=null){
				thuopen=(String)thu.get("open");
		}
		}
		if(thu!=null){
			if((String)thu.get("close")!=null){
				thuclose=(String)thu.get("close");
		}
		}
		if(fri!=null){
			if((String)fri.get("open")!=null){
				friopen=(String)fri.get("open");
		}
		}
		if(fri!=null){
			if((String)fri.get("close")!=null){
				friclose=(String)fri.get("close");
		}
		}
		if(sat!=null){
			if((String)sat.get("open")!=null){
				satopen=(String)sat.get("open");
		}
		}
		if(sat!=null){
			if((String)sat.get("close")!=null){
				satclose=(String)sat.get("close");
		}
		}
		if(sun!=null){
			if((String)sun.get("open")!=null){
				sunopen=(String)sun.get("open");
		}
		}
		if(sun!=null){
			if((String)sun.get("close")!=null){
				sunclose=(String)sun.get("close");
		}
		}
		
		stmt1.setString(1,bid);
			stmt1.setString(2,monopen);
			stmt1.setString(3,monclose);
			stmt1.setString(4,tueopen);
			stmt1.setString(5,tueclose);
			stmt1.setString(6,wedopen);
			stmt1.setString(7,wedclose);
			stmt1.setString(8,thuopen);
			stmt1.setString(9,thuclose);
			stmt1.setString(10,friopen);
			stmt1.setString(11,friclose);
			stmt1.setString(12,satopen);
			stmt1.setString(13,satclose);
			stmt1.setString(14,sunopen);
			stmt1.setString(15,sunclose);
			stmt1.executeUpdate();
        }
        br.close();
        
	}catch(SQLException se){
		se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		// nothing we can do
	      try{
	    	  if(stmt!=null){stmt.close();}
	    	  if(stmt1!=null){stmt.close();}
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	System.out.println("Files Inserted:"+count);
	   System.out.println("categories created");
	}
public void Yelp_Review(File readfile){
	Connection conn=null;
	Properties connectionProps = new Properties();
	connectionProps.put("oracle.jdbc.RetainV9LongBindBehavior","true");
	Statement stmt=null;
	PreparedStatement stmt1=null;
	JSONParser parser=new JSONParser();
	int count=0;

	try{
		FileReader file=new FileReader(readfile);
		BufferedReader br=new BufferedReader(file);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("connecting to database....");
		conn=DriverManager.getConnection(DB_URL,Username,pass);
		System.out.println("creating statement.....");
		stmt=conn.createStatement();
		Clob CLOB=conn.createClob();
		String sqld="TRUNCATE TABLE Yelp_Review";
		
		stmt.execute(sqld);
		
        String sql1="INSERT INTO Yelp_review(review_id,user_id,business_id,type,rstars,rdate,rvotes_funny,rvotes_useful,rvotes_cool,Text) VALUES(?,?,?,?,?,?,?,?,?,?)";
        stmt1=conn.prepareStatement(sql1);
        String line=null;
        while((line=br.readLine())!=null){
        		count++;
		Object obj = parser.parse(line);
		JSONObject jsonobj=(JSONObject) obj;
		String review_id=(String)jsonobj.get("review_id");
		String user_id=(String)jsonobj.get("user_id");
		String business_id=(String)jsonobj.get("business_id");
		String type=(String)jsonobj.get("type");
		int rstars=new Integer(((Long) jsonobj.get("stars")).intValue());
		String rdate=(String)jsonobj.get("date");
		JSONObject objv=(JSONObject)jsonobj.get("votes");
		int rvotes_funny=new Integer(((Long)objv.get("funny")).intValue());
		int rvotes_useful=new Integer(((Long)objv.get("useful")).intValue());
		int rvotes_cool=new Integer(((Long)objv.get("cool")).intValue());
		String text=(String)jsonobj.get("text");
		stmt1.setString(1, review_id);
		stmt1.setString(2, user_id);
		stmt1.setString(3, business_id);
		stmt1.setString(4, type);
		stmt1.setInt(5, rstars);
		stmt1.setString(6, rdate);
		stmt1.setInt(7, rvotes_funny);
		stmt1.setInt(8, rvotes_useful);
		stmt1.setInt(9, rvotes_cool);
		CLOB.setString(1, text);
		stmt1.setClob(10,CLOB);
		stmt1.executeQuery();
        }
        
	}catch(SQLException se){
		se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
	      try{
	    	  if(stmt!=null){stmt.close();}
	    	  if(stmt1!=null){stmt.close();}
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	System.out.println(count);
	   System.out.println("Reviews populated");
	}

 
 public static void main(String[] args){
	 File file=new File(args[0]);
	 File file1=new File(args[1]);
	 File file2=new File(args[2]);
	 File file3=new File(args[3]);
	 
	 new Populate(file,file1,file2,file3);
 }
 }
 

