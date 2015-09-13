package App;

import java.io.Serializable;
import java.util.ArrayList;


/** A Client class which inherits the User class methods. */
public class Client extends User implements Serializable{

	
	private static final long serialVersionUID = 5471553918501579956L;

	/**
	 * This is the Client constructor method.
	 * Creates a new Client object with the given parameters.
	 * @param lastname a string of the last name of the client.
	 * @param firstname a string of the first name of the client.
	 * @param email a string of the email of the client.
	 * @param address a string of the address of the client.
	 * @param creditCard a string of the credit information of the client.
	 * @param ccExpDate a string of the credit card expiration 
	 * date of the client.
	 */
	public Client(String lastname, String firstname, String email,  
			String address, String creditCard, String ccExpDate) {
		
		/** Inherits the User class methods */
		super(lastname, firstname, email, address, creditCard, ccExpDate);	
	}
	
	
	/**
	 * Returns the current clients stored personal information.
	 * @return a string of all the clients personal information.
	 */
	public String viewPersonalInfo() {
		
		return
				"First Name: " + firstName + "\n"
				+ "Last Name: " + lastName + "\n"
				+ "Email: " + email + "\n" 
				+ "Address: " + address + "\n" 
				+ "Credit Card: " + creditCard + "\n" 
				+ "Expiry Date: " + ccExpDate;		
	}
	
	
	/**
	 * Sets the client's Credit Card number to what string 
	 * is represented in the parameter.
	 * @param creditCard a string of the client's credit card number.
	 */
	public void setCreditCard(String creditCard) {
		
		this.creditCard = creditCard;
	}

	
	/**
	 * Sets the client's Credit Card expiration date to what 
	 * string is represented in the parameter.
	 * @param creditCard a string of the client's credit card number.
	 */
	public void setccExpDate(String ccExpDate) {
		
		this.ccExpDate = ccExpDate;
	}
	
	
	/**
	 * Adds a new itinerary to the clients current itinerary list.
	 * @param newitineray a new itinerary which the client wishes to book.
	 */
	public void bookItinerary(Itinerary newitineray) {
		
		this.ItineraryList.add(newitineray);
	}
	
	public boolean checkBookItinerary(String newitineray) {
		
		for(Itinerary itinerary: this.ItineraryList){
			if(itinerary.toString().equals(newitineray))
				return true;
		}
		return false;
	}
	
	
	/**
	 * Removes a specific itinerary from the clients current itinerary list.
	 * @param itineray the itinerary which the clients wishes to remove.
	 */
	public void cancelItinerary(int index) {
		
		this.ItineraryList.remove(index);
	}
	
	
	/**
	 * Returns the string representation of the current 
	 * itinerary the client currently has.
	 * @return a string of the current itinerary that the client has.
	 */
	public ArrayList<Itinerary> checkBookedItinerary() {
		return this.ItineraryList;
	}
	
	/**
	 * Edits the personal information of the current client.
	 * @param option a string lets the client input which section 
	 * he/she wishes to edit.
	 * @param info a string which the client writes to update the 
	 * chosen section to the new value.
	 */
	public void editPersonalInfo(String option, String info){
		
		/** Inherits the editUserInfo class methods */
		super.editUserInfo(option, info);
		
		/** Implements the ability for clients to change 
		 * their credit card details. */
		if(option.equals("creditCard")){
			this.setCreditCard(info);
			
		}
		if(option.equals("ccExpDate")){
			this.setccExpDate(info);
		}
	}
	
	public Itinerary getItinerary(int index){
		return this.ItineraryList.get(index);
	}
	
	/**
	 * A to-String method which returns a string 
	 * representation about the client.
	 */
	@Override
	public String toString() {
		
		return lastName + ","+ firstName + ","+ email
				+ "," + address + "," + creditCard + "," + ccExpDate;
	}


}
