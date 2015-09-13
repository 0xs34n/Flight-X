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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import App.Client;
import App.Flight;

/** 
 * A class representation of a Client Manager.
 * Manages the saving and loading of Clients.
 */
public class ClientManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4837058070483408395L;

	/** A mapping of all the flight objects. */
    public Map<String, Client> Clients;
    
    // Help me with a description of this.
    /** (enter description here) */
    private static final Logger logger = 
            Logger.getLogger(FlightManager.class.getPackage().getName());
    
    // Help me with a description of this.
    /** (enter description here) */
    private static final Handler consoleHandler = new ConsoleHandler();
   
    /** Creates a new hash map to store all the flights. */
    public ClientManager(){
    	this.Clients = new HashMap<String, Client>();
    }
	
    /**
     * Creates a new empty FlightManager.
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public ClientManager(String filePath) throws ClassNotFoundException, 
    IOException {
    	
        this.Clients = new HashMap<String, Client>();
        
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
        
        // FileInputStream can be used for reading raw bytes, like an image. 
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        String[] record;
        Client client;

        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            
            //Added - Will it cause prolblems?
            
            if (Clients.containsKey(record[2])){
                   Clients.remove(record[2]);}
            
            client = new Client(record[0], record[1], record[2], record[3], 
            		record[4], record[5]);
 
            Clients.put(client.email, client); //No getter and can access
        }
        scanner.close();
    }
    
    public void readFromCSVFile(File file) throws FileNotFoundException{
    	
    	Scanner scanner  = new Scanner(file);
    	String[] record;
    	Client client;
    	
    	 while(scanner.hasNextLine()) {
             record = scanner.nextLine().split(",");
             
             client = new Client(record[0], record[1], record[2], record[3], 
             		record[4], record[5]);
  
             Clients.put(client.email, client); //No getter and can access
         }
         scanner.close();
    }
    
    /**
     * Adds record to this StudentManager.
     * @param record a record to be added.
     */
    public void add(Client record) {
        Clients.put(record.email, record); //No getter and can access.
        
        // Log the addition of a student.
        logger.log(Level.FINE, "Added a new student " + record.toString());
    }
    
    /**
     * Writes the students to file at filePath.
     * @param filePath the file to write the records to
     * @throws IOException 
     */
    public void saveToFile(String filePath) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(Clients);
        output.close();
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
        output.writeObject(Clients);
        output.close();
    }
    
    
    
    /**
     * Creates a Map<String, Flight> object based on the file specified by the 
     * path.
     * Then assigns that object to the class variable clients.
     * @param path the location of the file.
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
	private void readFromFile(String path) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the Map
            this.Clients = (Map<String, Client>) input.readObject();
            input.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input.", ex);
        }    	
    }
    
	@SuppressWarnings("unchecked")
	public void readFromFile(File path) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the Map
            this.Clients = (Map<String, Client>) input.readObject();
            input.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input.", ex);
        }    	
    }
    
    
    /**
     * Returns a client object from the given email provided.
     * @param email a string of the email of the specific 
     * client wished to search for
     * @return the client object found.
     */
    public Client getClient(String email){
    	
    	return Clients.get(email);
    }
    
    public Client reomoveClient(String email){
    	
    	return Clients.remove(email);
    }
   
    public boolean CheckClient(String email){
    	if(Clients.containsKey(email)){
    		return true;
    	}
    	return false;
    }
    
    public void uploadClient(ClientManager cm){
    	for(Map.Entry<String, Client> entry: cm.Clients.entrySet()){
    		if(!this.CheckClient(entry.getValue().email)){
    			this.add(entry.getValue());
    		}
    	}
    } 
    
    public Map<String, Client> getClients(){
    	
    	return this.Clients;
    }
    public int size(){
    	return this.Clients.size();
    }
    /**
     * Returns a string representation of the Client object. 
     */
    @Override
    public String toString() {
        String result = "";
 
        for (Client r : Clients.values()) {
        	result += r.toString() + "\n";
        }

        return result;

    }
}
