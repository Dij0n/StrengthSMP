package dijon.strengthsmp.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class DEventManager {

    public static boolean eventActive = false;

    public static ArrayList<ArrayList<UUID>> teams = new ArrayList<>();

    public DEventManager(){
        initializeTeams();
    }


    public static int getTeamIndexByPlayer(Player p){
        for(ArrayList<UUID> team : teams){
            if(team.contains(p.getUniqueId())){
                return teams.indexOf(team);
            }
        }
        return -1;
    }

    public static boolean onSameTeam(Player p1, Player p2){
        for(ArrayList<UUID> team : teams){
            if(team.contains(p1.getUniqueId()) && team.contains(p2.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public static void batteryCharged(int teamIndex){
        //END EVENT CODE HERE
    }

    public void initializeTeams(){
        ArrayList<UUID> team1 = new ArrayList<>();
    }

}
