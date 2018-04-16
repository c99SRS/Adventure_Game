package com.smruti.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import com.smruti.test.*;

import com.smruti.test.Monster.*;
import com.smruti.test.Player.*;

public class SaveManager{
	

    
    FileInputStream fileRead = null;
    ObjectInputStream read = null;
    
    String fileName = "/adventure.ser" ;

	public static String filePath(){

		String userHome = "user.home" ;

		String systemPath = System.getProperty(userHome);

		//System.out.println("home path: " + systemPath);

		return systemPath;
	}
    
    public void save( Player player,Monster monster){
    	
    	//System.out.println("Inside save method\n");
    	
//    	String fileName = "/adventure.ser" ;
    	
    	FileOutputStream fileWrite = null;
        
        
//        System.out.println("Before saving fileName:"+fileName);
    	
    	System.out.println("\nSaving game.....\n");
    	HashMap<String,Object> gameMap ;
    	
    	if(monster == null){
    		//System.out.println("inside monster null check condition");
    		gameMap =  new HashMap<>();
        	gameMap.put("player",player);
    	}
    	else{
    	
    	gameMap =  new HashMap<>();
    	gameMap.put("player",player);
    	gameMap.put("monster",monster);
    	}
    	
    	
    	String path = filePath();

		String fileLocal = path+fileName ;
		
		//System.out.println("FileName: " + fileName);
    	
    	File fileCheck = new File(fileLocal);
    	
    	try{
    		
    		if(!fileCheck.exists()){
    			fileWrite = new FileOutputStream(fileLocal);
               }else{
            	   fileWrite = new FileOutputStream(fileLocal);
               }
    		
  
    		ObjectOutputStream writeObj = new ObjectOutputStream(fileWrite);
    		 

    		 writeObj.writeObject(gameMap);
    		 
    		  writeObj.close();
    		 fileWrite.close();
    		 
 			System.out.println("\n\n   Game saved Successfully!!!!!!");
    	}
    	catch(FileNotFoundException e) {
    		System.out.print("Save failed");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings({ "unchecked", "unused" })
	public HashMap<String, Object> load() {
    	
    	
    	
    	String path = filePath();

		String fileLocal = path+fileName ;
    	
    	File fileCheck = new File(fileLocal);
    	
    	//System.out.println("Inside load method");
    	
    	 System.out.print("\n\n Loading game......\n\n");
    	 
    	 
    	 //System.out.println("fileName in load: "+fileName);
    	 HashMap<String,Object> gameRetrieve = null;
    	 
    	try{
    		
    		if(fileCheck.exists()){
   			 fileRead = new FileInputStream(fileLocal);
   			  read = new ObjectInputStream(fileRead);
      		gameRetrieve  = (HashMap<String,Object>)read.readObject();
      		
      		read.close();
			fileRead.close();
			
			Player player = (Player) gameRetrieve.get("player");
            
      		
            }else{
//            	fileRead = new FileInputStream(fileName);
            }
    		
    		 
    		
    		
//    		System.out.println("Now showing previous Player Info:\n");
//    		System.out.println("Name: " + player.getPlayerName() + "\nPower: "+player.getPlayerHPower() + "\nWeapon: "+player.getPlayerWeapon() + "\nLife: "+player.getLife());

    		
    	}
    	
    	
    	catch(FileNotFoundException e) {
    		System.out.println("Can't the find the file");
    		return null;
       }
    	catch(IOException ex)
        {
            System.out.println("IOException is caught");
            return null;
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
    	
    	
    	return gameRetrieve;
    }

}
