import java.io.*;
import java.net.*;
import java.util.*;

import net.sf.json.JSONArray;




public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		int id = 15;
		
		
		try{
		//Create URL
		URL query = new URL("http://www.strava.com/api/v1/clubs/" + id + "/members");

		//Create Reader
		BufferedReader in = new BufferedReader(
				new InputStreamReader(query.openStream()));

		//Capture Query Results
		String inputLine;
		String URLread = "";

		while((inputLine = in.readLine()) != null){
			URLread = URLread + inputLine;
		}

		//System.out.println(URLread);
		

		
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		/**
		Club c = new Club(1);
		System.out.println(c);
		
		Member m = new Member(21, "Pan Thomakos");
		System.out.println(m);
		m.printRides();
		
		
		try {
			String queryResult = m.searchRides();
			//System.out.println(queryResult);
			//System.out.println(m.parseRides(queryResult));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/

	}



}
