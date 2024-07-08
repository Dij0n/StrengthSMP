package dijon.strengthsmp.data;

import org.bukkit.entity.Player;

import java.util.Random;

public class PlayerData {

    public Player player;

    public int strength;

    public String basicAttack;

    public String ultimateAttack;

    public Random random = new Random();

    public int playerHits;

    public PlayerData(Player player) {
        this.player = player;
        this.strength = 2;
        this.basicAttack = getRandomBasicAttack();
        this.ultimateAttack = null;
        playerHits = 0;
    }


    public void incStrength(){
        if(strength < 3) strength++;
    }

    public void decStrength(){
        if(strength > 1) strength--;
    }


    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) { this.strength = strength;}

    public int getPlayerHits() {
        return playerHits;
    }

    public void incPlayerHits() {
        playerHits++;
        if(playerHits >= 5){
            playerHits = 0;
        }
    }

    public String getBasicAttack() {
        return this.basicAttack;
    }

    public String getUltimateAttack() {
        return this.ultimateAttack;
    }

    public void setBasicAttack(String basicAttack) {
        this.basicAttack = basicAttack;
    }

    public void setUltimateAttack(String ultimateAttack) {
        this.ultimateAttack = ultimateAttack;
    }

    public String getRandomBasicAttack() {
        return PlayerDataManager.BASIC_ATTACKS[random.nextInt(PlayerDataManager.BASIC_ATTACKS.length)];
    }

    public String getDifferentBasicAttack(){
        String attack = basicAttack;
        while(attack.equals(basicAttack)){
            attack = PlayerDataManager.BASIC_ATTACKS[random.nextInt(PlayerDataManager.BASIC_ATTACKS.length)];
        }
        return attack;
    }

    public String getRandomUltimateAttack() {
        return PlayerDataManager.ULTIMATE_ATTACKS[random.nextInt(PlayerDataManager.ULTIMATE_ATTACKS.length)];
    }

    public String getDifferentUltimateAttack(){
        String attack = ultimateAttack;
        while(attack.equals(ultimateAttack)){
            attack = PlayerDataManager.ULTIMATE_ATTACKS[random.nextInt(PlayerDataManager.BASIC_ATTACKS.length)];
        }
        return attack;
    }

}
