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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import App.Administrator;
import App.Client;

/** 
 * A class representation of a Client Manager.
 * Manages the saving and loading of Clients.
 */
public class AdminManager {
	
	/** A mapping of all the flight objects. */
    private Map<String, Administrator> Administrators;
    
    // Help me with a description of this.
    /** (enter description here) */
    private static final Logger logger = 
            Logger.getLogger(FlightManager.class.getPackage().getName());
    
    // Help me with a description of this.
    /** (enter description here) */
    private static final Handler consoleHandler = new ConsoleHandler();
   
    /** Creates a new hash map to store all the flights. */
    public AdminManager(){
    	this.Administrators = new HashMap<String, Administrator>();
    }
	
    /**
     * Creates a new empty FlightManager.
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public AdminManager(String filePath) throws ClassNotFoundException, 
    IOException {
    	
        this.Administrators = new HashMap<String, Administrator>();
        
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
        Administrator administrator;

        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            
            administrator = new Administrator(record[0], record[1], record[2], record[3], 
            		record[4], record[5]);
 
            Administrators.put(administrator.email, administrator); //No getter and can access
        }
        scanner.close();
    }
    
    public void readFromCSVFile(File file) throws FileNotFoundException{
    	
    	Scanner scanner  = new Scanner(file);
    	String[] record;
    	Administrator administrator;
    	
    	 while(scanner.hasNextLine()) {
             record = scanner.nextLine().split(",");
             
             administrator = new Administrator(record[0], record[1], record[2], record[3], 
             		record[4], record[5]);
  
             Administrators.put(administrator.email, administrator); //No getter and can access
         }
         scanner.close();
    }
    
    /**
     * Adds record to this StudentManager.
     * @param record a record to be added.
     */
    public void add(Administrator record) {
    	Administrators.put(record.email, record); //No getter and can access.
        
        // Log the addition of a Admin.
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
        output.writeObject(Administrators);
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
        output.writeObject(Administrators);
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
            this.Administrators = (Map<String, Administrator>) input.readObject();
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
            this.Administrators = (Map<String, Administrator>) input.readObject();
            input.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input.", ex);
        }    	
    }
    
    public void remove(String eamil){
    	this.Administrators.remove(eamil);
    	
    }
    /**
     * Returns a client object from the given email provided.
     * @param email a string of the email of the specific 
     * client wished to search for
     * @return the client object found.
     */
    public Administrator getAdmin(String email){
    	
    	return Administrators.get(email);
    }
    
    public boolean checkAdmin(String email){
    	
    	if(this.Administrators.containsKey(email)){
    		return true;
    	}
    	return false;
    }
    
    /**
     * Returns a string representation of the Client object. 
     */
    @Override
    public String toString() {
        String result = "";
 
        for (Client r : Administrators.values()) {
        	result += r.toString() + "\n";
        }

        return result;

    }
}

