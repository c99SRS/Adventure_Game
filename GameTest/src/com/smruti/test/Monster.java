package com.smruti.test;

import java.io.Serializable;

public class Monster implements Serializable {
	
	int monsterHPower = 15;
	//monsterHPower = 15;

	public Monster() {
		// TODO Auto-generated constructor stub
	}
	
	public Monster(int monsterPower){
		this.monsterHPower = monsterPower;
	}
	
	public int getMonsterHPower() {
		return monsterHPower;
	}

	public void setMonsterHPower(int monsterHPower) {
		this.monsterHPower = monsterHPower;
	}
	
	public Monster getMonsterDetails(){
		
		Monster monster = new Monster();
		
		monster.setMonsterHPower(monsterHPower);
		
		return monster;
	}
	
	public int monsterDamage(){
		
		return new  java.util.Random().nextInt(4);
	}
	
	public Monster updatePower(Monster monster,int upPower){
		
		   //int oldPower = monster.getMonsterHPower();
		
		
			
			monster.setMonsterHPower(upPower);
			
			return monster;
		}
	
	
//	public static void main(){
//	
//	Monster monster = new Monster();
//	
//	monster  = monster.getMonsterDetails();
//	
//	//System.out.println(monster.getMonsterHPower());
//}
	
	
	
}
