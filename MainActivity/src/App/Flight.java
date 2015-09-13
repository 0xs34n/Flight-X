package App;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/** A class representation of a Flight. */
@SuppressLint("NewApi")
public class Flight implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3647124396623847087L;

	/** The flight number of the flight. */
	public String flightNum;
	
	/** The airline of the flight. */
	public String airline;
	
	/** The origin of the flight. */
	public String origin;
	
	/** The destination of the flight. */
	public String destination;
	
	public int numSeats;
	
	public int currentBooked;
	/** The price of the flight. */
	public double price;
	
	public int totalmin;
	
	public boolean check;
	// HH/MM
	/** The duration of the flight. */
	public int[] duration;
	
	/** The departure time & date of the flight. */
	public Calendar departTimeAndDate;
	
	/** The arrival time & date of the flight. */
	public Calendar arrivalTimeAndDate;

	
	/** The depart date of the flight. */
	public String departDate;
	
	/** The arrival date of the flight. */
	public String arriveDate;
	
	/** The depart time of the flight. */
	public String departTime;
	
	/** The arrive time of the flight. */
	public String arriveTime;
	
	
	/**
	 * This is the Flight constructor method.
	 * Creates a new Flight object with the given parameters.
	 * @param flightNum a string with the flight number of the Flight.
	 * @param departDateTime a string with the 
	 * departure date and time of the Flight.
	 * @param arrivalDateTime a string with the arrival date 
	 * and time of the Flight.
	 * @param airline a string with the airline of the Flight.
	 * @param origin a string with the origin of the Flight.
	 * @param destination a string with the destination of the Flight.
	 * @param price a double with the price of the Flight.
	 */
	public Flight(String flightNum, String departDateTime, 
			String arrivalDateTime, String airline,
					String origin, String destination, double price, int numSeats) {
		
		this.flightNum = flightNum;
		this.airline = airline;
		this.origin = origin;
		this.destination = destination;
		this.arriveDate = arrivalDateTime.split(" ")[0];
		this.arriveTime = arrivalDateTime.split(" ")[1];
		this.departDate = departDateTime.split(" ")[0];
		this.departTime = departDateTime.split(" ")[1];
		this.departTimeAndDate = new GregorianCalendar(
				Integer.parseInt(departDateTime.split(" ")[0].split("-")[0]),
				Integer.parseInt(departDateTime.split(" ")[0].split("-")[1]) - 1, 						   
				Integer.parseInt(departDateTime.split(" ")[0].split("-")[2]),
				Integer.parseInt(departDateTime.split(" ")[1].split(":")[0]),
				Integer.parseInt(departDateTime.split(" ")[1].split(":")[1]),
														  00);
		this.arrivalTimeAndDate = new GregorianCalendar(
				Integer.parseInt(arrivalDateTime.split(" ")[0].split("-")[0]),
				Integer.parseInt(arrivalDateTime.split(" ")[0].split("-")[1]) -1, 
				Integer.parseInt(arrivalDateTime.split(" ")[0].split("-")[2]),
				Integer.parseInt(arrivalDateTime.split(" ")[1].split(":")[0]),							
				Integer.parseInt(arrivalDateTime.split(" ")[1].split(":")[1]),
														00);
		this.price = price;
		this.numSeats= numSeats;
		this.currentBooked = 0;
	    this.duration = hoursBetween(departTimeAndDate, arrivalTimeAndDate);
	}
	
	/**
	 * Sets the Flight's flight number to the string represented in the parameter.
	 * @param flightNum a string of the Flight's flight number.
	 */
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}
	
	/**
	 * Sets the Flight's arrival time & date to the string
	 *  represented in the parameter.
	 * @param arrivalTimeAndDate a string of the Flight's arrival time & date.
	 */
	public void setArrivalTimeAndDate(String arrivalDateTime) {
		this.arrivalTimeAndDate = new GregorianCalendar(
				Integer.parseInt(arrivalDateTime.split(" ")[0].split("-")[0]),
				Integer.parseInt(arrivalDateTime.split(" ")[0].split("-")[1])-1, 
				Integer.parseInt(arrivalDateTime.split(" ")[0].split("-")[2]),
				Integer.parseInt(arrivalDateTime.split(" ")[1].split(":")[0]),							
				Integer.parseInt(arrivalDateTime.split(" ")[1].split(":")[1]),
														00);
	}
	
	
	/**
	 * Sets the Flight's departure time & date to the string 
	 * represented in the parameter.
	 * @param departTimeAndDate a string of the Flight's departure time & date.
	 */
	public void setDepartTimeAndDate(String departDateTime) {
		this.departTimeAndDate = new GregorianCalendar(
				Integer.parseInt(departDateTime.split(" ")[0].split("-")[0]),
				Integer.parseInt(departDateTime.split(" ")[0].split("-")[1])-1, 						   
				Integer.parseInt(departDateTime.split(" ")[0].split("-")[2]),
				Integer.parseInt(departDateTime.split(" ")[1].split(":")[0]),
				Integer.parseInt(departDateTime.split(" ")[1].split(":")[1]),
														  00);
	}
	
	
	/**
	 * Sets the Flight's airline to the string represented in the parameter.
	 * @param airline a string of the Flight's airline name.
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	
	/**
	 * Sets the Flight's origin to the string represented in the parameter.
	 * @param origin a string of the Flight's origin.
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
	/**
	 * Sets the Flight's destination to the string represented in the parameter.
	 * @param destination a string of the Flight's destination.
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	/**
	 * Sets the Flight's price to the double represented in the parameter.
	 * @param destination a double of the Flight's price.
	 */	
	public void setPrice(String price) {
		this.price = Double.parseDouble(price);
	}
	public void setNumSeats(String seats) {
		this.numSeats = Integer.parseInt(seats); 
	}

	/**
	 * Returns the difference between the end date and time
	 *  with the start date and time.
	 * @param startDate the starting date and time.
	 * @param endDate the ending date and time.
	 * @return an integer value of the difference between start and end.
	 */
	public int[] hoursBetween(Calendar startDate, Calendar endDate) {
		
	    long start = startDate.getTimeInMillis();
	    long end = endDate.getTimeInMillis();
	    long timeInMinutes = TimeUnit.MILLISECONDS.toMinutes(end - start);
	    this.totalmin = (int)timeInMinutes;
	    int [] duration = {(int)timeInMinutes/60, (int)timeInMinutes%60} ;
	    		
	    if(timeInMinutes < 0){
	    	this.check = false;
	    }
	    else
	    {
	    	this.check = true;
	    }
	    return duration;
	}
	
	
	/**
	 * Returns whether or not the time in minutes is greater 
	 * More than 0 because can only go forward in time. And Maximum 6 hour lay over time!
	 * @param flight the flight object.
	 * @return a boolean value saying if the time is >= 0 and <= 360
	 */
	public boolean validWait(Flight flight){

		long start = this.arrivalTimeAndDate.getTimeInMillis();
		long end = flight.departTimeAndDate.getTimeInMillis();
		long timeInMinutes = 
				TimeUnit.MILLISECONDS.toMinutes(end-start);
		return (timeInMinutes >= 0) && (timeInMinutes <= 360);
	}
	
	
	/**
	 * Converts the duration into minutes.
	 * @return an integer indicating the total minutes.
	 */
	public int convertMin(){
		return duration[0]*60 + duration[1];
	}
	
	
	/**
	 * Returns a string representation of the Flight object. 
	 */
	@Override
	/**
	 * Number,DepartureDateTime,ArrivalDateTime,
	 * Airline,Origin,Destination,Price
	 * (the dates are in the format YYYY-MM-DD; the price has exactly two
	 * decimal places) 
	 * This is use for driver.getFlight
	 */
	public String toString() {
		
		return  flightNum + "," + departDate + " " + departTime + ","
				+ arriveDate + " " +arriveTime + ","
				+ airline + "," + origin + ","
				+ destination + "," + this.twoDecimal();
		
	}
	
	

	

	/**
	 * Exactly two decimal 
	 * @return total cost of this itinerary and convert to Exactly to decimal
	 */
	public String twoDecimal(){
		double twoDecimal = this.price;
		return new DecimalFormat("#.00").format(twoDecimal);
	}
	
	public boolean bookable(){
		return this.currentBooked < this.numSeats;
	}
	
	public void bookFlight(){

			this.currentBooked = this.currentBooked + 1;
		
	}
	public void cancelBook(){
		this.currentBooked = this.currentBooked - 1;
	}

}