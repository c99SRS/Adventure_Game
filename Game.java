package com.smruti.test;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;


import com.smruti.test.Player.*;
import com.smruti.test.Monster.*;
import com.smruti.test.SaveManager.*;



public class Game implements Serializable{
	
	Scanner sc = new Scanner(System.in);
	Scanner enterScanner = new Scanner(System.in);

	Player savePlayer;
	Monster saveMonster;
	
	boolean checkChoice;

	int life = 1;

	int milestone=0;
	int playerHPower;
	int choice;
	int monsterHPower;
	int monsterUpdatePower;
	int playerPowerCounter;

	int silverRing;
	
	int counter=0;

	String playerName;
	String playerWeapon;
	

	public Game(){
		
	}
	public Game(Player player,Monster monster){
		
		savePlayer = player;
		saveMonster = monster;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		Game game;
		game = new Game();
		
		game.gameLoad();
		
		
//		System.out.println();
//        System.out.println("+-------------------------------------------------------------+");
//        System.out.println("|                                                             |");
//        System.out.println("|                                                             |");
//        System.out.println("|                 Let's Play One Adventure Game               |");
//        System.out.println("|                                                             |");
//        System.out.println("|                                                             |");
//        System.out.println("+-------------------------------------------------------------+");
//        System.out.println("\n\n");
//        
//       
//        
		Player player1 = game.playerSetUp();
		game.townGate(player1);
		
	}
	
	
	private int checkInput(){
		
		int userChoice = 0;
		
		String userEnter;
		
		userEnter = sc.next();
		
		if(StringUtils.isNumeric(userEnter)){
			
			userChoice = Integer.parseInt(userEnter);
		}
		else{
			System.out.println("Please enter a valid option ");
			return 0;
		}
		
		return userChoice;
		
	}


	private Player playerSetUp() {
		// TODO Auto-generated method stub
		
	    Player player = new Player();
     
		
		Player player2 = player.getPlayerDetails();
		
		playerName = player2.getPlayerName();
		playerHPower = player2.getPlayerHPower();
		playerWeapon = player2.getPlayerWeapon();
		life = player2.getLife();
		
		System.out.println("Your Max Power: " + playerHPower);
		System.out.println("Your   Weapon :  " + playerWeapon);
		System.out.println("You have " + player2.getLife() +" Extra Life\n\n");
		

		
		System.out.println("Hello  " + playerName + ", let's start the game!");
		System.out.println();
		System.out.println();
		System.out.println("You are about to Enter into the kingdom. There is a TownGate,With heavy Security.");
		System.out.println(".....................");
		System.out.println(".....................");
		System.out.println(".....................");
		System.out.println("Now you have reached");
		
		
		return player2;
	}
	
	private void townGate(Player player) {
		
		
		
		savePlayerState(player);
		
		Player power = null;
		
		Player incPower;
		Player decPower;
		
		System.out.println("\n------------------------------------------");
		
		System.out.println("A guard is standing infront of you.");
		System.out.println();
		System.out.println("What do you want to do to him?");
		System.out.println();
		System.out.println("1: Talk to the guard                   \t\t 4: Quit\n"); 
		System.out.println("2: Attack the guard                                 \n");  
		System.out.println("3: Leave");
		System.out.println("\n------------------------------------------------");
		
		
		choice = checkInput() ;
	

		if(choice == 0 || (choice < 0)){
			townGate(player);
		}
			
		
		if(choice == 1){
			if(silverRing ==1){
				ending(player);
			}
			else{
				System.out.println("Guard: Hello there, stranger. So your name is " + playerName + "? \n Sorry but we cannot let enter out kingdom. ");
			   //enterScanner.next();
			   townGate(player);
			}
			
		}
		
		else if(choice ==2 && counter == 0){
			//System.out.println("Your power is reducing.........");
			//playerHPower = playerHPower -1 ;
			
			if(playerPowerCounter == 0){
			power = player.playerPowerDecrease(player);
			playerHPower = power.getPlayerHPower();
			}else{
				//playerHPower = player.getPlayerHPower();
			}
			

			 
			 System.out.println("Guard: Hey don't be stupid. \n\nThe guard hit you so hard and you gave up.\n(You received 1 damage)\n");
			

			
			
			if(playerHPower >0){
				System.out.println("Your power is reducing...........\n Your power is:  " + playerHPower);

			}
			else
				System.out.println("You don't have any power....   \n\n");
			
			
			
			
			/*===============================  Life Concept Added =================================================*/
			if(life<1 && playerHPower < 1){
				System.out.println("You don't have any life. Those guards are more powerfull. You can't defeat them.You can't fight anymore....");
				System.out.println("Plaese try some other actions\n\n");
				milestone = 1;
				player = player.playerMilestoneStatus(milestone, player);
				
				//save game
				System.out.println("Now you have successfully completed Stage 1. Next time you play, we will start from here.\n\n");
				
				savePlayerGame(player);
				townGate(player);
			}
			
			else{
				if(playerHPower < 1){
					System.out.println("Do you want to use your life?(Y/N)");
					String userInput = sc.next();
					if(userInput.equalsIgnoreCase("YES") || userInput.equalsIgnoreCase("Y") ){
						System.out.println("\n\n");
						life = life - 1;
						 
						power = power.playerPowerIncrease(power);
						playerHPower = power.getPlayerHPower();
						

						boolean lifeStatus = true;
						System.out.println("You got 1 extra life.\t\t Try to kill those guards......\n\n");

						fightAgainGuard(lifeStatus,playerHPower,power,player);
						
					}
					else if (userInput.equalsIgnoreCase("NO") || userInput.equalsIgnoreCase("N")){
						dead();
					}
					else{
						System.out.println("Sorry!!! It seems you don't  have interest to fight....\n\n");
						townGate(power);
					}
				}
				else{
					
					townGate(power);  
					
				}
			}
			/*================================================================================*/

			
			
		}
		
		else if(choice == 3 && counter == 0){
			crossRoad(player);
		}
		else if(choice == 4 ){
			quitGame(player);
		}
		else{
			
			townGate(player);
		}
		
	}
	
	private void quitGame(Player player) {
		
		
		try{
		 savePlayerGame(player);
		
		
		System.out.println("Closing game......");
		
		// call save instance of progress
        System.exit(1);
		}
		catch(Exception ex){
			
			System.out.println("Exception occured while saving the Game!!!");
		}
		
	}
	
	
	public Player savePlayerState(Player playerState){
		
		
		return playerState;
	}
	
  public void savePlayerGame(Player playerState){
	  
	  //System.out.println("Inside savePLayer game");
	  
	  //System.out.println(playerState.getPlayerName() + " " + playerState.playerWeapon + " " + playerState.getPlayerHPower() + " " + playerState.getMilestone());
		
		SaveManager save = new SaveManager();
		
		save.save(playerState,null);
	}
	
	public void savePlayerGame(Player playerState , Monster monster){
		
		SaveManager save = new SaveManager();
		
		save.save(playerState,monster);
	}

	private void gameLoad(){
		
		int checkStage = 0;
		boolean isStartOfGame;
		Object playerObj;
		Object monsterObj;
		
		Player oldPlayer = null;
		Monster oldMonster;
		
		SaveManager load = new SaveManager();
		HashMap<String,Object> gameRetrieve = load.load();
		
		if(gameRetrieve != null){
			isStartOfGame = gameStarter(false);
			if(isStartOfGame){
				if(gameRetrieve.get("player")!= null && ((Player)gameRetrieve.get("player")).getMilestone()==2){
					System.out.println("You have already completed the game... \n\nLets start a new game..");
					isStartOfGame = false;
					gameStarter(true);
					
				}else{
					if(gameRetrieve.size() == 1){
						//System.out.println("inside 1 object retrieve condtn");
						playerObj = gameRetrieve.get("player");
						oldPlayer = (Player)playerObj;
						
						checkStage = oldPlayer.getMilestone();
					}
					else if(gameRetrieve.size() == 2){
						//System.out.println("inside 2 object retrieve condtn");
						playerObj = gameRetrieve.get("player");
						oldPlayer = (Player)playerObj;
						
						monsterObj = gameRetrieve.get("monster");
						oldMonster = (Monster)monsterObj;
						
						checkStage = oldPlayer.getMilestone();
						
					}
				}
			}
		
		}
		else{
			isStartOfGame=gameStarter(true);
		}
		
		if(isStartOfGame){
			
			if(checkStage == 1){
				
				crossRoad(oldPlayer);
			}
			else if(checkStage == 2){
				
				win(oldPlayer);
			}
		}

		
	}

	
	
	private boolean gameStarter(boolean isStart) {
		if(!isStart){
			System.out.println("1: To start a new game\n");
			System.out.println("2: Resume old game\n");
			
			choice = checkInput(); 
			
	
			
			if(choice == 0 || (choice < 0))
				gameStarter(false);
			
			 if(choice == 1){
				 System.out.println();
			        System.out.println("+-------------------------------------------------------------+");
			        System.out.println("|                                                             |");
			        System.out.println("|                                                             |");
			        System.out.println("|                 Let's Play One Adventure Game               |");
			        System.out.println("|                                                             |");
			        System.out.println("|                                                             |");
			        System.out.println("+-------------------------------------------------------------+");
			        System.out.println("\n\n");
			        return false;
			 }
			 else if(choice == 2){
				 return true;
			 }
			 else{
				 System.out.println("invalid Choice");
				 gameStarter(false);
				 return true;
			 }
		}
		else{
			System.out.println();
	        System.out.println("+-------------------------------------------------------------+");
	        System.out.println("|                                                             |");
	        System.out.println("|                                                             |");
	        System.out.println("|                 Let's Play One Adventure Game               |");
	        System.out.println("|                                                             |");
	        System.out.println("|                                                             |");
	        System.out.println("+-------------------------------------------------------------+");
	        System.out.println("\n\n");
	        return false;
	        
		}
		
	}
	
	private void fightAgainGuard(boolean lifeStatus,int newPlayerPower,Player powerPlayer,Player player) {
		
		
		savePlayerState(powerPlayer);
		
		playerHPower = powerPlayer.getPlayerHPower();
		
		System.out.println("power in fight Again: " + playerHPower);
		
		powerPlayer = powerPlayer.playerPowerDecrease(player);
		
		playerHPower = powerPlayer.getPlayerHPower();
		
		
		System.out.println("Guard: Hey don't be stupid. \n\nThe guard hit you so hard and you gave up.\n(You received 1 damage)\n");
		System.out.println("Your power is reducing...........\n Your power is:  " +playerHPower);
		
		System.out.println("You have to keep fighting till you powerless...");
		
		System.out.println("try your lucky spell\n");
		enterScanner.next();
		
		if(playerHPower > 0 && lifeStatus){
			fightAgainGuard(lifeStatus,playerHPower,powerPlayer,player);
		}
		else{
			lifeStatus = false;
			playerPowerCounter = 1;
			townGate(player);
		}
		
	}
	

	private void crossRoad(Player player) {
		
						
		
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are at a crossroad. If you go south, you will go back to the town.\n\n");
		System.out.println("Go to North to regain strength...");
		System.out.println("1: Go north");
		System.out.println("2: Go east");
		System.out.println("3: Go south");
		System.out.println("4: Go west                              \t5: Quit");
		System.out.println("\n\n------------------------------------------------------------------\n");
		
		choice = checkInput(); 
		

		
		if(choice == 0 || (choice < 0))
			crossRoad(player);
		
		 if(choice == 1){
			 north(player);
		 }
		 else if(choice == 2){
			 east(player);
		 }
		 else if(choice == 3){
			 townGate(player);
		 }
		 else if(choice == 4){
			 west(player);
		 }
		 else if (choice == 5){
			 quitGame(player);
		 }
		 else
			 crossRoad(player);
		
	}



	private void north(Player player) {
		
		System.out.println("\n----------------------------------------------\n");
		System.out.println("There is a river. You drink the water and rest at the riverside.");
		System.out.println("Your power is recovered.........");
		
		player = player.powerIncrease(player);
		playerHPower = player.getPlayerHPower();
		System.out.println("Your Power:  " + playerHPower);
		
		System.out.println("\n\n1: Go back to the crossroad");
		System.out.println("\n----------------------------------------------\n");
		
		choice = checkInput() ; 
		
		if(choice == 0 || (choice < 0))
			north(player);
		
			if(choice ==1 )
				crossRoad(player);
			else
				north(player);

	}
	
	public void east(Player player){
		
		System.out.println("\n----------------------------------------------------\n");
		System.out.println("You are walking towards forest. Woooohh.......... \n   you know what?\n  "
				+ "People are talking about a Long Sword made of Gold. It is in this forest.");
		System.out.println("  Wooooohh.......... \n Finally you got the Sword. Everybody was searching for this.");
		
		playerWeapon = "Long Sword";
		
		System.out.println("Your Weapon: "+playerWeapon);
		
		player = player.updateWeapon(player,playerWeapon);
		
		System.out.println("\n\n1: Go back to the crossroad");
		System.out.println("\n----------------------------------------------\n");
		
		choice =  checkInput() ; 
		
		if(choice == 0 || (choice < 0))
			east(player);
		
			if(choice ==1 )
				crossRoad(player);
			else{
				east(player);
			}

		
	}
	
	public void west(Player player){
		
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You encounter a goblin!\n");
		System.out.println("1: Fight");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");
		
		choice =  checkInput() ; 
		
		Monster monster = new Monster();
		
		if(choice == 0 || (choice < 0))
			west(player);
		
			
			if(choice ==1){
				fight(player,monster);
			}
			else if(choice == 2)
				crossRoad(player);
			else{
				west(player);
			}
		
		
	}

	private void fight(Player player,Monster monster) {
		
		playerHPower = player.getPlayerHPower();
		
		
		
		int monsterPower = monster.getMonsterHPower();
		
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Your Power: "+ playerHPower );
		System.out.println("You know Monster Power: " + monsterPower);
		System.out.println("Take Deep breath............... \nAre you sure you want to do this by risking your life\n");
		System.out.println("\n1: Attack");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");
		
		choice =  checkInput() ; 
		
		if(choice == 0 || (choice < 0))
			fight(player,monster);
		

			
			if(choice ==1){
				attackEnemy(player,monster);
			}
			else if(choice == 2)
				crossRoad(player);
			else{
				fight(player,monster);
			}
		
		
	}



	private void attackEnemy(Player player,Monster monster) {
		
		//Monster monster = new Monster();
		
		//Player updatePlayerPower;
		
		//System.out.println("monster update power before assign: "+monsterUpdatePower);
		
		if(monsterUpdatePower == 0){
		monsterHPower = monster.getMonsterHPower();
		monsterUpdatePower = monsterHPower;
		}
		
		//System.out.println("monster update power after assign: "+monsterUpdatePower);
		
		
		
		playerHPower = player.getPlayerHPower();
		//System.out.println("Player power before match: " + playerHPower);
		
		System.out.println("Player weapon: " + player.getPlayerWeapon());
		
		//System.out.println("playerWeapon: " + playerWeapon);
		
		playerWeapon = player.getPlayerWeapon();
		
		int playerDamage =0;
		
		if(playerWeapon.equals("Knife")){
			playerDamage = 	player.playerDamageKnife();			
		}
		else if(playerWeapon.equals("Long Sword")){
			playerDamage = player.playerDamageSword();	
		}
		
		System.out.println("You attacked the monoster and gave  " + playerDamage + " damage!");
		
		//monsterHPower = monsterHPower - playerDamage;
		
		monsterUpdatePower = monsterUpdatePower - playerDamage;
		
		monster = monster.updatePower(monster, monsterUpdatePower);
		
		System.out.println("\n\nMonster update power: " + monsterUpdatePower);
		
		if(monsterUpdatePower < 1){				//(monsterHPower < 1){
			win(player);
		}
		else if(monsterUpdatePower > 0){					//if ( monsterHPower> 0){
			int monsterDamage = 0;
			
			monsterDamage = monster.monsterDamage();				//new java.util.Random().nextInt(4);
			System.out.println("Monoster attacked you and gave  " + monsterDamage + " damage!");	
		

			if(  playerHPower > monsterDamage){
				playerHPower = playerHPower - monsterDamage;
			}else{
				dead();
			}
			
			
			player = player.updatePower(player, playerHPower);
			
			System.out.println("Player power: " + playerHPower);
			
			if (playerHPower < 1){
				dead();
			}
			
			else if(playerHPower > 0){
				fight(player,monster);
			}
		
		}
		
	}



	private void dead() {
		// TODO Auto-generated method stub
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("       You are dead!!!");
		System.out.println("\n\n+------------------------------------------------------------------+\n");
		System.out.println("\n  +------------------------------------------------------------------+\n");
		System.out.println("\n  +------------------------- GAME OVER ------------------------------+");
		System.out.println("\n  +------------------------------------------------------------------+\n");
		System.out.println("\n  +------------------------------------------------------------------+\n");
		
		System.exit(0);
	}

	public void win(Player player){
		
		silverRing = 1;
		counter = 1;
		
		milestone = 2;
		player = player.playerMilestoneStatus(milestone, player);
		
		
		//save game
		savePlayerGame(player);
				
		
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You killed that big monster!");
		System.out.println("The monster dropped a ring!");
		System.out.println("You obtaind a gold ring!\n\n");
		System.out.println("1: Now Go to CrossRoad and from there go towards town and Ask the guard......");
		System.out.println("He will guide you  " + player.getPlayerName());
		System.out.println("\n------------------------------------------------------------------\n");
		
		
		
		choice =  checkInput() ; 
		
		if(choice == 0 || (choice < 0))
			win(player);
		

			
			if(choice == 1)
				crossRoad(player);
			else{
				win(player);
			}

		
	}
	
	
	public void ending(Player player){
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Guard: Oh you killed that goblin!?? Great!!!");
		System.out.println("Guard: It seems you are a brave guy. Our King is looking for you."
				+ " You will be our next King.... ");
		
		System.out.println("Guard:  Our gate is open for you. Now you can entry.");
		
		System.out.println("King:   Welcome " +player.getPlayerName() + " to our kingdom Camelot." );
		System.out.println();
		

		System.out.println("\n---    ---------------------------------------------    ------------\n");
		System.out.println("\n---    ---------------------------------------------    -------------\n");
		System.out.println("\n\n                           THE END                              ");
		System.out.println("\n---    ---------------------------------------------    -------------\n");
		System.out.println("\n---    ---------------------------------------------    -----------\n");

		
		System.exit(1);
	}
	

}
