package App;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Managers.FlightManager;

/** An abstract class representation of a User. */

public abstract class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1138219669532321242L;


	/** 
	 * These are all the details a user is expected to have.
	 * Whether it be an Administrator or Client.
	 */
	
	
	/** The first name of the User. */
	protected String firstName;
	
	
	/** The last name of the User. */
	protected String lastName;
	
	
	/** The email of the User. */
	public String email;
	
	
	/** The address of the User. */
	protected String address;
	
	
	/** An itinerary list of which would store 
	 * information about the current User's
	 * flight information.
	 */
	protected ArrayList<Itinerary> ItineraryList;
	
	
	/** Credit card number of the User. */
	protected String creditCard;
	
	
	/** Credit card expiration date for verification purposes. */
	protected String ccExpDate;
	
	
	/**
	 * This is the User constructor method.
	 * Creates a new User object with the given parameters.
	 * @param lastName a string of the last name of the User
	 * @param firstName a string of the first name of the User
	 * @param email a string of the the email address of the User
	 * @param address a string of the the address of the User
	 * @param creditCard a string of the credit card number of the User
	 * @param ccExpDate credit card expiration date of the User
	 */
	
	
	public User(String lastName, String firstName, String email, 
			String address, String creditCard, String ccExpDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.creditCard = creditCard;
		this.ccExpDate = ccExpDate;
		this.ItineraryList = new ArrayList<Itinerary>();
	}
	
	
	/**
	 * Sets the user's firstName to what string is represented in the parameter.
	 * @param firstName a string of the User's first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	/**
	 * Sets the user's lastName to what string is represented in the parameter.
	 * @param lastName a string of the User's first name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	/**
	 * Sets the user's email to what string is represented in the parameter.
	 * @param email a string of the User's email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/**
	 * Sets the user's email to what string is represented in the parameter.
	 * @param address a string of the User's email.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	/**
	 * Edits the information of the current User.
	 * @param option a string lets the user input which 
	 * section he/she wishes to edit.
	 * @param info a string which the user writes to 
	 * update the chosen section to the new value.
	 */
	public void editUserInfo(String option, String info ){

		if(option.equals("firstname")){
			this.setFirstName(info);
		}
		if(option.equals("lastname")){
			this.setLastName(info);
		}
		if(option.equals("email")){
			this.setEmail(info);
		}
		if(option.equals("address")){
			this.setAddress(info);
		}
	}
	
	/**
	 * 
	 * @param fm FlightManager stores all flight information
	 * @param departDate a search criteria for the flights (departure date).
     * @param origin a search criteria for the flights (origin).
     * @param destination a search criteria for the flights (destination).
     * @return an itinerary of the flights found through the given criteria's.
	 */
	public String getFlights(FlightManager fm, String date, 
			String origin, String destination) {
		
		
		String result = "" + fm.getFlight(date, origin, 
							destination).itinerary.get(0).toString();
		for(int i = 1; i < fm.getFlight(date, origin, destination).size(); 
				i++){
			result = result + "\n" + fm.getFlight(date, origin, 
					destination).itinerary.get(0).toString();
		}
		return result;
	}
	
	/**
	 * Searches for existing flights which have the same 
	 * origin - destination the client input.
	 * @param Totalflight all the existing flights available
	 * @param origin the origin the client wishes to depart from.
	 * @param destination the destination the client wishes to arrive.
	 * @param temp a temporary storage for the itinerary.
	 */
	public ArrayList<Itinerary> getItineraries(FlightManager fm, String date, 
			String origin, String destination) {
		
		return fm.getItineraries(date, origin, destination);
		
	}
	 
	/**
     * Returns a sorted itinerary list in ascending order 
     * in respect to the cost.
     * @param TotalItinerary the whole current itinerary available
     * Returns a sorted itinerary  itinerary list which 
     * matches the given criteria.
     * @param departDate a search criteria for the flights (departure date).
     * @param origin a search criteria for the flights (origin).
     * @param destination a search criteria for the flights (destination).
     * @return a sorted itinerary list which matches the criteria in String
     */
	public ArrayList<Itinerary> getItinerariesSortedByCost(FlightManager fm, String date, 
			String origin, String destination) {
		// TODO: complete this method body
		
		ArrayList<Itinerary> temp = fm.sortByCost(fm.getItineraries(date, origin, 
				destination));
		
		return temp;
	}
	
	 /**
     * Returns a sorted itinerary list in ascending order in 
     * respect to the Time.
     * @param TotalItinerary the whole current itinerary available
     * Returns a sorted itinerary  itinerary list which 
     * matches the given criteria.
     * @param departDate a search criteria for the flights (departure date).
     * @param origin a search criteria for the flights (origin).
     * @param destination a search criteria for the flights (destination).
     * @return a sorted itinerary list which matches the criteria in String
     */
	public ArrayList<Itinerary> getItinerariesSortedByTime(FlightManager fm, String date, 
			String origin, String destination) {
		// TODO: complete this method body
		ArrayList<Itinerary> temp = fm.sortByTime(fm.getItineraries(date,
				origin, destination));
		
		
		return temp;
	}
	
	
}

