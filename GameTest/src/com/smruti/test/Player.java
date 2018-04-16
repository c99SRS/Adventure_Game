/**
 * 
 */
package com.smruti.test;

import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Smruti Ranjan
 * 
 * This class is about Store player information
 *
 */
public class Player implements Serializable {
	
	transient Scanner sc = new Scanner(System.in);
	
	int life = 1;

	int playerHPower = 4;

	int milestone;

	int choice;


	int silverRing;

	private String playerName;
	String playerWeapon;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Player(String playerName,String playerWeapon,int life,int playerPower){
		
		this.playerName = playerName;
		this.playerWeapon = playerWeapon;
		this.life = life;
		this.playerHPower = playerPower;
	}
	
   public void setLife(int life){
	   this.life = life;
   }
   public int getLife(){
	   return life;
   }


	public int getMilestone() {
		return milestone;
	}


	public void setMilestone(int milestone) {
		this.milestone = milestone;
	}


	public int getPlayerHPower() {
		return playerHPower;
	}


	public void setPlayerHPower(int playerHPower) {
		this.playerHPower = playerHPower;
	}


	public int getChoice() {
		return choice;
	}


	public void setChoice(int choice) {
		this.choice = choice;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public String getPlayerWeapon() {
		return playerWeapon;
	}


	public void setPlayerWeapon(String playerWeapon) {
		this.playerWeapon = playerWeapon;
	}
	
	public Player getPlayerDetails(){
		
		Player player = new Player();
		
		
		playerWeapon = "Knife";
		
	
		
		System.out.println("\nBefore Proceeding........ \n");
		System.out.println("\n  Please Enter your Name: ");
		playerName = sc.next();
		
		String regEx = "[A-Za-z]{1,}[0-9\\.\\>\\<\\@\\#\\%\\&\\*\\(\\)\\-\\_\\~]{0,}[A-Za-z0-9]{0,}";
		
		Pattern pattern = Pattern.compile(regEx);
		Matcher match = pattern.matcher(playerName);
		
		if(!match.matches()){
			
			System.out.println("Please Note that your name should start  with Alphabet.");
			getPlayerDetails();
		}
		 
		if(playerName.equals(null) || playerName.equals("") ){
			getPlayerDetails();
		}else if ( Character.isDigit(playerName.charAt(0)) ){
			getPlayerDetails();
		}
		else if(playerName.length() > 9 || playerName.length() <= 1){
			System.out.println("Please give a Small name with in [1-8] length");
			getPlayerDetails();
		}
		
		player.setPlayerName(playerName);
		player.setPlayerHPower(playerHPower);
		player.setLife(life);
		player.setPlayerWeapon(playerWeapon);
		
		return player;
	}
	
	public Player playerPowerIncrease(Player player) {
		
		int old = player.getPlayerHPower();
		//System.out.println("old power in insc Method " + old);
		old = old + 5;
		//System.out.println("new power in insc Method " + old);

		player.setPlayerHPower(old);

		int newPower = player.getPlayerHPower();

		System.out.println("new power in insc Method " + newPower);
		
		return player;
		
	}
	
	public Player powerIncrease(Player player) {

		
		int power = 10;
//		player.setPlayerHPower(power);
		
		player.setPlayerHPower(power);

		int newPower = player.getPlayerHPower();

		System.out.println("new power in power insc Method " + newPower);
		
		return player;
		
	}


	public Player playerPowerDecrease(Player player) {
		
		
		int oldP = player.getPlayerHPower();
		System.out.println("old power in desc Method " + oldP);
		oldP = oldP -1;

		player.setPlayerHPower(oldP);

		int newPower = player.getPlayerHPower();
		System.out.println("new desc power in desc Method " + newPower);

		return player;

	}
	
	public int playerDamageKnife(){
		
		return new java.util.Random().nextInt(5);
	}
	
	public int playerDamageSword(){
		
		return new java.util.Random().nextInt(8);
	}
	
	public Player playerMilestoneStatus(int stage,Player player){
		
		player.setMilestone(stage);
		
		return player;
	}
	
	public Player updateWeapon(Player player,String weapon){
		
		player.setPlayerWeapon(weapon);
		
		return player;
	}
	
public Player updatePower(Player player,int upPower){
	
	
		
		player.setPlayerHPower(upPower);
		
		return player;
	}
	
//	public static void main(){
//		
//		Player player = new Player();
//		
//		Player player2  = player.getPlayerDetails();
//		
//		System.out.println(player2.getPlayerName() + " " + player2.getPlayerHPower()+" "+player2.getPlayerWeapon()+ " " + player2.getLife());
//	}


}
