import java.io.*;
import java.net.*;
import java.util.*;


public class Member {

	public static String date = "2013-03-01";

	private int id;
	private String name;
	private ArrayList<Ride> rides;

	//"query to get rides for competition: http://www.strava.com/api/v1/rides?athleteId="+"startDate=2013-03-01"

	public Member(int i, String s) {
		id = i;
		name = s;
		rides = createRides();
	}

	public ArrayList<Ride> createRides(){
		String ridesString = "";

		
		try {
			ridesString = searchRides();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 


		return parseRides(ridesString);
	}

	public String searchRides() throws MalformedURLException, IOException {

		String fullquery = "";
		String URLread = "";
		int offset = 0;

		while (! URLread.equals("{\"rides\":[]}")){
			
			if (URLread.equals("{\"error\":\"Invalid athleteId\"}")) {
				break;
			}
			
			fullquery = fullquery + URLread;
			URLread = "";

			//Create URL
			URL query = new URL("http://www.strava.com/api/v1/rides?athleteId="+this.id+"&offset=" + offset);

			//Create Reader
			BufferedReader in = new BufferedReader(
					new InputStreamReader(query.openStream()));

			//Capture Query Results
			String inputLine;

			while((inputLine = in.readLine()) != null){
				URLread = URLread + inputLine;
			}
			
			offset += 50;
		}

		return fullquery;
	}
	public ArrayList<Ride> parseRides(String s){

		//check for empty string
		if (s.equals("") || s == null) {
			return new ArrayList<Ride>();
		}

		//check for error
		if (s.equals("{\"error\":\"Invalid clubs/"+this.id+"\"}")){
			return new ArrayList<Ride>();
		}

		//initiate aray
		ArrayList<Ride> members = new ArrayList<Ride>();

		int startID;
		int endID;

		//trim string to be only members
		//s = s.substring(s.indexOf("["), s.lastIndexOf("]")+1);

		while (s.indexOf("\"id\":") >= 0) {
			
			//find substring of id
			startID = s.indexOf("\"id\":") + 5;
			endID = s.indexOf(",", startID);
			
			//parse id to number, create new member
			String num = s.substring(startID,endID);
			members.add(new Ride(Integer.parseInt(num)));
			
			//trim substring to find next member
			s = s.substring(endID, s.length());
		}

		return members;
	}

	public String toString(){
		return "{\"id\":"+ this.id + ",\"name\":\"" + this.name + "\"}";
	}
	
	public void printRides(){
		System.out.println(this.rides);
	}
}
