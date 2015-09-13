package App;

import java.io.Serializable;

import Managers.ClientManager;
import Managers.FlightManager;

/** An administrator class which inherits Client class methods. */
public class Administrator extends Client implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3557034151933187881L;


	/**
	 * This is the Administrator constructor method.
	 * Creates a new administrator object with the given parameters.
	 * @param lastName a string of the last name of the administrator
	 * @param firstName a string of the first name of the administrator
	 * @param email a string of the the email address of the administrator
	 * @param address a string of the the address of the administrator
	 * @param creditCard a string of the credit card 
	 * number of the administrator
	 * @param ccExpDate credit card expiration date of the administrator
	 */
	public Administrator(String lastName,String firstName, String email, 
			String address, String creditCard, String ccExpDate) {
		
		/** Inherits the Client class methods */
		super(lastName, firstName, email, address, creditCard, ccExpDate);
		
	}

		
	/**
	 * Edits the chosen client's information the 
	 * administrator wishes to change.
	 * @param client this is the client which the information would be edited.
	 * @param option this is the section of the client's information 
	 * in which the admin will edit.
	 * @param info this is the new client information the admin 
	 * will replace with the previous.
	 */
	public void editClient(Client client, String option, String info) {
		
		/** Inherits the same clients editPersonalInfo method, 
		 * as it works exactly the same. */
		client.editPersonalInfo(option, info);
	}
	
	
	//Maybe the Admin can read the file and upload it.
	/**
	 * update the flightManager
	 * @param fm FlightManager stores all flight information
	 * @param flight the flight that to be uploaded
	 */
	public void uploadFlight(FlightManager fm, Flight flight){
		fm.add(flight);
	}
	
	
	/**
	 * Edits the current available flights.
	 * @param flight the flight which the administrator wishes to edit.
	 * @param option the section which the administrator wishes to edit.
	 * @param info the new information the administrator 
	 * wishes to replace the chosen option.
	 */
	public void editFlight(Flight flight, String option, String info) {
		
		if (option == "number"){
			
			flight.setFlightNum(info);
			
		} else if (option == "airline") {
			
			flight.setAirline(info);
			
		} else if (option == "origin") {
			
			flight.setOrigin(info);
			
		} else if (option == "destination") {
			
			flight.setDestination(info);
			
		} else if (option == "price") {
			
			flight.setPrice(info);
			
		} else if (option == "duration") {
		
		} else if (option == "departDateTime") {
			
			flight.setDepartTimeAndDate(info);
			
		}else if (option == "arrivalDateTime") {
			flight.setArrivalTimeAndDate(info);

		}else if(option == "numSeats"){
			flight.setNumSeats(info);
		}
	}
	
	
	/**
	 * Removes a specific flight which the administrator wishes to remove.
	 * @param FlightManager contains all the flights.
	 * @param flight the specific flight which the administrator 
	 * wishes to remove.
	 */
	public void removeFlight(FlightManager fm , Flight flight) {
		fm.remove(flight);
	}
}
