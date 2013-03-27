import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create club (use 15 for demo)
		Club c = new Club(69);
		
		//Get list of all members from the club, and sort list by elevation
		ArrayList<Member> members = c.getMembers();
		ArrayList<Member> sortedMembers = quickSort(members);
		
		//Used for testing a small group of members
		/**
		Member dylan = new Member(1205, "Dylan DiBona");
		Member alan = new Member(209251, "Alan Schacter");
		Member alec = new Member(6959, "Alec C.");
		Member alex = new Member(2457, "Alex L.");
		
		ArrayList<Member> ms = new ArrayList<Member>();
		
		ms.add(dylan);
		ms.add(alan);
		ms.add(alec);
		ms.add(alex);
		
		System.out.println(dylan.getName() + ": " + dylan.getElevation());
		System.out.println(alan.getName() + ": " + alan.getElevation());
		System.out.println(alec.getName() + ": " + alec.getElevation());
		System.out.println(alex.getName() + ": " + alex.getElevation());
		
		ArrayList<Member> sorted = quickSort(ms);
		
		System.out.println(sorted);
		**/
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(0);
		
		//Print results
		int i = 1;
		for (Member m : sortedMembers) {
			double elevation = m.getElevation();
			int rides = m.getNumRides();
			double avg;
			if (rides == 0) {
				avg = 0.0;
			}
			else{
				avg = elevation / rides;
			}
			
			System.out.println(i + ": " + m.getName() + "     Elevation Gained: " + df.format(elevation) + "     Rides: " + rides + "     Average Elevation Gain per Ride: " + df.format(avg));
			i++;
		}
		
	

	}
	
	//Method to sort club members by elevation gained this months
	public static ArrayList<Member> quickSort(ArrayList<Member> members) {
		
		//if list size <= 1, list is already sorted
		if (members.size() <= 1 ) {
			return members;
		}
		
		//chose and remove pivot
		int pivot = members.size() / 2;
		Member pivM = members.get(pivot);
		members.remove(pivM);
		
		double pivValue = pivM.getElevation();
		
		//every value smaller than the pivot should be placed in "less",
		//every value larger than pivot should be placed in "greater"
		ArrayList<Member> less = new ArrayList<Member>();
		ArrayList<Member> greater = new ArrayList<Member>();
		
		for (Member m : members) {
			if (m.getElevation() < pivValue ) {
				less.add(m);
			}
			else {
				greater.add(m);
			}
		}
		
		
		//recursively sort less and greater
		ArrayList<Member> recurse = new ArrayList<Member>();
		
		recurse.addAll(quickSort(greater));
		recurse.add(pivM);
		recurse.addAll(quickSort(less));
		
		return recurse;
	}



}
