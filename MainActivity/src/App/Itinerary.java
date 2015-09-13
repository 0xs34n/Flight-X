package App;


import android.annotation.SuppressLint;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** A class representation of an Itinerary. */
@SuppressLint("NewApi")
public class Itinerary implements Serializable{
	
	

	private static final long serialVersionUID = 1091870282279376078L;
	
	/** The itinerary list of flights. */
	public List<Flight> itinerary;
	
	/** Creates an empty itinerary. */
	public Itinerary(){	
		this.itinerary = new ArrayList<Flight>();
	}
	
	
	/**
	 * Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
	 * followed by total price (on its own line, exactly two decimal places),
	 * followed by total duration (on its own line, in format HH:MM).
	 */
	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i < itinerary.size(); i++){
			result = result + itinerary.get(i).flightNum + "," 
					+ itinerary.get(i).departDate + " " 
					+ itinerary.get(i).departTime+"," 
					+ itinerary.get(i).arriveDate + " " 
					+ itinerary.get(i).arriveTime + "," 
					+ itinerary.get(i).airline + "," + itinerary.get(i).origin 
					+ "," + itinerary.get(i).destination +"\n";
		}
		result = result + this.twoDecimal() + "\n" + this.duration() ;
		return result;
	}


	/**
	 * This is the Itinerary constructor method.
	 * @param itinerary an array list of type flight.
	 */
	public Itinerary(List<Flight> itinerary){
		this.itinerary = itinerary;
	}
	
	
	/**
	 * Appends a new flight to the current itinerary.
	 * @param flight a flight object wished to be added into the itinerary.
	 */
	public void append(Flight flight){
		this.itinerary.add(flight);
	}
	
	
	/**
	 * Returns the total travel time of all the flights in the 
	 * itinerary and converts to minutes.
	 * @return an int of the total travel time in the itinerary.
	 */
	public int totaltime(){
		
		return (int) this.minutesBetweenTwoDates(
				this.itinerary.get(0).departTimeAndDate, 
				this.itinerary.get(this.itinerary.size()-1).arrivalTimeAndDate);
	}
	
    public long minutesBetweenTwoDates(Calendar startDate, Calendar endDate) {
		
		long start = startDate.getTimeInMillis();
		long end = endDate.getTimeInMillis();
		long timeInMinutes = TimeUnit.MILLISECONDS.toMinutes(end - start);

		return timeInMinutes;
		}
	/**
	 * Returns the total time of all the flights in the itinerary.
	 * @return the duration of a itinerary in format HH:MM
	 */
	@SuppressLint("DefaultLocale")
	public String duration(){

	long duration = this.minutesBetweenTwoDates(
	this.itinerary.get(0).departTimeAndDate, 
	this.itinerary.get(this.itinerary.size()-1).arrivalTimeAndDate);
	return String.format("%02d:%02d", duration/60, duration%60);
	}
	
	
	/**
	 * computes the total cost of this itinerary
	 * for sort itinerary by cost 
	 * @return double total cost
	 */
	public double totalcost(){
		double totalcost = 0;
		for(Flight flight: itinerary){
			totalcost = totalcost + flight.price;
		}
		return totalcost;
	}
	

	/**
	 * Exactly two decimal 
	 * @return total cost of this itinerary and convert to Exactly to decimal
	 */
	public String twoDecimal(){
		double twoDecimal = this.totalcost();
		return new DecimalFormat("#.00").format(twoDecimal);
	}
	
	/**
	 * Returns the number of itineraries currently available.
	 * @return an int of the number of itineraries available.
	 */
	public int size(){
		return this.itinerary.size(); 
	 }


	/**
	 * Returns the very last flight object added into the itinerary.
	 * @return the very last flight object added.
	 */
	public Flight lastFlight(){
		return this.itinerary.get(this.itinerary.size()-1);
	}
	
	public boolean contains(Flight cflight){
		for(Flight flight : itinerary){
			if(flight.equals(cflight)){
				return true;
			}
		}
		return false;
	}
	public boolean bookable(){
		for(Flight flight : this.itinerary){
			if(!flight.bookable()){
				return false;
			}
		}
		return true;
	}
	
	public void bookItinerary(){

			for(Flight flight : this.itinerary){
				flight.bookFlight();
			}

	}
	
	public void cancelItinerary(){
		for(Flight flight : this.itinerary){
			flight.cancelBook();
		}
		
	}
	/**
	 * Adds a specific itinerary to the itinerary list.
	 * @param itinerary the new itinerary to be added into the list.
	 */
	public void addItinerary(Itinerary itinerary){
		
		for(Flight e : itinerary.itinerary){
			this.itinerary.add(e);
		}
	}
	public void remove(Flight flight){
		itinerary.remove(flight);
	}
}
