package Managers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import App.Client;
import App.Flight;
import App.Itinerary;

/** 
 * A class representation of a Flight Manager.
 * Manages the saving and loading of Flights..
 */

public class FlightManager implements Serializable{
	

	private static final long serialVersionUID = 2584190199016430533L;
	
	/** A mapping of all the flight objects. */
    private Map<String,Flight> flights;
    // Help me with a description of this.
    /** (enter description here) */
    private static final Logger logger = 
            Logger.getLogger(FlightManager.class.getPackage().getName());
    
    // Help me with a description of this.
    /** (enter description here) */
    private static final Handler consoleHandler = new ConsoleHandler();
    
    /** Creates a new hash map to store all the flights. */
    public FlightManager(){
    	this.flights = new HashMap<String,Flight>();
    	
    }
	
    
    /**
     * Creates a new empty FlightManager.
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public FlightManager(String filePath) throws 
    ClassNotFoundException, IOException {
    	
        this.flights = new HashMap<String,Flight>();
        
        // Associate the handler with the logger.
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        
        // Reads serializable objects from file.
        // Populates the record list using stored data, if it exists.
        File file = new File(filePath);
        if (file.exists()) {
            readFromFile(filePath);
        } else {
            file.createNewFile();
        }
    }
    
    
    /**
     * Populates the records map from the file at path filePath.
     * @param filePath the path of the data file  
     * @throws FileNotFoundException if filePath is not a valid path
     */
    public void readFromCSVFile(String filePath) 
            throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileInputStream(filePath));
        String[] record;
        Flight flight;

        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            flight = new Flight(record[0], record[1], record[2], record[3], 
            		record[4],record[5],
            		Double.parseDouble(record[6]), Integer.parseInt(record[7]));
           flights.put(flight.flightNum, flight);
        }
        scanner.close();
    }
    
    
    /**
     * Adds record to this StudentManager.
     * @param record a record to be added.
     */
    public void add(Flight record) {
    	
    	flights.put(record.flightNum, record);
    	
        // Log the addition of a flight.
        logger.log(Level.FINE, "Added a new flight " + record.toString());
    }
    
    /**
     * if flights contain this flight then remove, after removing the
     * given flight check if the list is empty now.  
     * @param record the given flight that need to be removed
     */
    public void remove(Flight record){
    	
    	flights.remove(record.flightNum);
    	
    }
    
    
    /**
     * Writes the students to file at filePath.
     * @param filePath the file to write the records to
     * @throws IOException 
     */
    public void saveToFile(File filePath) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(flights);
        output.close();
    }
    
    
    /**
     * Creates a Map<String, Flight> object based on the file 
     * specified by the path.
     * Then assigns that object to the class variable flights.
     * @param path the location of the file.
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
	private void readFromFile(String path) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            // deserialize the Map
            this.flights = (Map<String, Flight>) input.readObject();
            input.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input.", ex);
        }    	
    }
    
    public void androidReadFile(File file) throws FileNotFoundException{
    	Scanner scanner = new Scanner(file);
        String[] record;
        Flight flight;

        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            flight = new Flight(record[0], record[1], record[2], record[3], 
            		record[4],record[5],
            		Double.parseDouble(record[6]), Integer.parseInt(record[7]));
           flights.put(flight.flightNum, flight);
        }
        scanner.close();
    	
    }
    /**
     * Returns all the specific flights as an itinerary from the flights 
     * information provided.
     * @param departDate a search criteria for the flights (departure date).
     * @param origin a search criteria for the flights (origin).
     * @param destination a search criteria for the flights (destination).
     * @return an itinerary of the flights found through the given criteria's.
     */
    public Itinerary getFlight(String departDate,String origin, 
			String destination){
 
    	Itinerary temp = new Itinerary();
 
 		for(Map.Entry<String, Flight> entry: flights.entrySet()){
 				/*System.out.println("loop" + entry);*/
 				System.out.println(entry.getValue().origin+" WHAT IS INPUTED "+ departDate + "ACTUAL FLIGHT DEPART " + entry.getValue().departDate);
 				if(entry.getValue().departDate.equals(departDate)){
					/*System.out.println("date passed");*/
 					if(entry.getValue().origin.equals(origin)){
						/*System.out.println("origin passed");*/
 						if(entry.getValue().destination.equals(destination)){
							/*System.out.println("destination passed");*/
							temp.append(entry.getValue());
 						}
 					}
 				}
 		}
 		return temp;
	}
    public ArrayList<Itinerary> getFlightItinerary(String departDate,String origin, 
			String destination){
    	Itinerary temp = this.getFlight(departDate, origin, destination);
    	ArrayList<Itinerary> result = new ArrayList<Itinerary>();
    	for(Flight flight: temp.itinerary){
				if(flight.departDate.equals(departDate)){
					/*System.out.println("date passed");*/
					if(flight.origin.equals(origin)){
						/*System.out.println("origin passed");*/
						if(flight.destination.equals(destination)){
							/*System.out.println("destination passed");*/
							Itinerary newtemp = new Itinerary();
								newtemp.append(flight);
								result.add(newtemp);
						}
					}
				}
		}
		return result;
    	

    	
    }
    
    /**
     * Returns all the specific flights as an itinerary from the flights 
     * information provided.
     * @param departDate a search criteria for the flights (departure date).
     * @param origin a search criteria for the flights (origin).
     * @return all flights with same departDate, origin as required.
     */
    public Itinerary getItinerariesHelper1(String departDate,String origin){
  
    	Itinerary temp = new Itinerary();
 		
 			for(Map.Entry<String, Flight> entry: flights.entrySet()){
 				if(entry.getValue().departDate.equals(departDate)){
 					if(entry.getValue().origin.equals(origin)){
 						temp.append(entry.getValue());
 					}
 				}
 			} 		
 			return temp;
	}
    
    
    // Not sure about this description. Correct if not fully correct.
    /**
     * Adds all the specific flights as an itinerary from the flights 
     * information provided.
     * @param origin a search criteria for the flights (origin).
     * @param destination a search criteria for the flights (destination).
     * @param ItineraryList a search criteria for the flights (itinerary list).
     * @param temp a search criteria for the flights (temp).
     */
    public void getItinerariesHelper2(String origin, 
			String destination, ArrayList<Itinerary> ItineraryList, Itinerary temp){

  
			for(Map.Entry<String, Flight> entry: flights.entrySet()){
				if(entry.getValue().origin.equals(origin)){
	    			if(temp.lastFlight().validWait(entry.getValue())){
	    				if(entry.getValue().destination.equals(destination)){
	    					Itinerary newtemp = new Itinerary();
	    					newtemp.addItinerary(temp);
	    					newtemp.append(entry.getValue());
							ItineraryList.add(newtemp);
	    				}
	    				else
	    				{
	    					Itinerary newtemp = new Itinerary();
	    					newtemp.addItinerary(temp);
	    					newtemp.append(entry.getValue());
	    					this.getItinerariesHelper2(entry.getValue().destination,
	    							destination,
	    							ItineraryList,newtemp);
	    				}
	    			}
	    		}
	    	}	
    	
	}
    
    
    /**
     * Returns the itinerary list which matches the given criteria.
     * @param departDate a search criteria for the flights (departure date).
     * @param origin a search criteria for the flights (origin).
     * @param destination a search criteria for the flights (destination).
     * @return the itinerary list which matches the criteria.
     */
    public ArrayList<Itinerary> getItineraries(String departDate, String origin,
    		String destination) {
    	
  		// TODO: complete this method body
    	Itinerary start = this.getItinerariesHelper1(departDate,origin);
    	ArrayList<Itinerary> ItineraryList = new ArrayList<Itinerary>();
    	for(Flight flight: start.itinerary){
    		Itinerary temp = new Itinerary();
    		temp.append(flight);
    		if(flight.destination.equals(destination)){
    			ItineraryList.add(temp);
    		}
    		else
    			this.getItinerariesHelper2(flight.destination, 
    					destination,ItineraryList, temp);
    	}
    	return ItineraryList;
  	}
    
    
    /**
     * Returns a sorted itinerary list in ascending order in respect 
     * to the travel time.
     * @param TotalItinerary the whole current itinerary available
     * @return a sorted list of type itinerary.
     */
    public ArrayList<Itinerary> sortByTime(ArrayList<Itinerary> TotalItinerary) {
    	
		Itinerary temp; 
		for(int i = 0; i< TotalItinerary.size(); i++ ){
			for(int j = 1; j < TotalItinerary.size();j++){
				if(TotalItinerary.get(j-1).totaltime() 
						> TotalItinerary.get(j).totaltime()){
					temp = TotalItinerary.get(j-1);
					TotalItinerary.set(j-1, TotalItinerary.get(j));
					TotalItinerary.set(j, temp);
				}	
				
			}
		}
		
		return TotalItinerary;
	}
	
    /**
     * Returns a sorted itinerary list in 
     * ascending order in respect to the cost.
     * @param TotalItinerary the whole current itinerary available
     * @return a sorted list of type itinerary.
     */
	public ArrayList<Itinerary> sortByCost(ArrayList<Itinerary> TotalItinerary) {
		
		Itinerary temp; 
		for(int i = 0; i< TotalItinerary.size(); i++ ){
			for(int j = 1; j <TotalItinerary.size();j++){
				if(TotalItinerary.get(j-1).totalcost() 
						> TotalItinerary.get(j).totalcost()){
					temp = TotalItinerary.get(j-1);
					TotalItinerary.set(j-1, TotalItinerary.get(j));
					TotalItinerary.set(j, temp);
				}	
			}
		}
		return TotalItinerary;
	}
	
	
	/**
	 * Returns the flight object required.
	 * @param key the unique string which distinguishes each flight.
	 * @return the flight object which matches the string provided.
	 */
	public Flight getFlight(String key){
		return flights.get(key);
	}
	
	public Map<String,Flight> getFlights(){
		return flights;
	}
	
	@SuppressWarnings("unchecked")
	public void readFromFile(File fileName) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(fileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the Map
            this.flights = (Map<String,Flight>) input.readObject();
            input.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input.", ex);
        }    	
    }
	public boolean checkFlight(String number){
		if(this.flights.containsKey(number)){
			return true;
		}
		return false;
	}
    
	public void uploadFlight(FlightManager fm){
    	for(Map.Entry<String, Flight> entry: fm.flights.entrySet()){
    		if(!this.checkFlight((entry.getValue().flightNum))){
    			this.add(entry.getValue());
    		}
    	}
    } 
	
	public int size(){
		return this.flights.size();
	}
	/**
	 * Returns a string representation of the flights in flight manager. 
	 */
    @Override
    public String toString() {
        String result = "";
        for (Flight r : flights.values()) {
            result += r.toString() + "\n";
        }
        return result;
    }
}
    

    

