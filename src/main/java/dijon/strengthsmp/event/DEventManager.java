package dijon.strengthsmp.event;

import dijon.strengthsmp.event.handlers.StationHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class DEventManager {

    public static boolean eventActive = false;

    public static ArrayList<ArrayList<UUID>> teams = new ArrayList<>();
    public static ArrayList<BossBar> bossBars = new ArrayList<>();

    public DEventManager(){
        initializeTeams();
    }

    public static void start(){
        eventActive = true;
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("§6The Portal has been summoned! "
                    + "§e\uD83D\uDDF2 " //Lightning emojis
                    + "§b§l"
                    + "Charge your team's batteries at -300, 300. First team to 100% wins.");
            p.sendMessage("§c§oGood Luck!");
            p.playSound(p, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 10, 1);
        }
        initializeBossBars();
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

    public static void teamMessage(ArrayList<UUID> team, String s){
        for(UUID uuid : team){
            if(Bukkit.getPlayer(uuid) == null) continue;
            Bukkit.getPlayer(uuid).sendMessage(s);
        }
    }

    public static void otherTeamMessage(ArrayList<UUID> otherteam, String s){
        for(ArrayList<UUID> team : teams){
            if(team.equals(otherteam)) continue;
            for(UUID uuid : team){
                if(Bukkit.getPlayer(uuid) == null) continue;
                Bukkit.getPlayer(uuid).sendMessage(s);
            }
        }
    }

    public static void batteryCharged(int teamIndex){
        for(BossBar bar : bossBars){
            bar.setVisible(false);
            bar.setProgress(0);
        }
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("§e\uD83D\uDDF2 §6" + StationHandler.stations.get(teamIndex).getTeamname() + "'s battery is fully charged!");
            p.sendMessage(ChatColor.DARK_RED + "✦ The Portal has been opened! " + "§c§o§l-180 120");
            p.playSound(p, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 10, 1);
        }
        //Open portal code here
        //Send Trial Chamber coords here
        eventActive = false;
    }

    public void initializeTeams(){
        ArrayList<UUID> team1 = new ArrayList<>();
        team1.add(UUID.fromString("8ff75664-6afd-4b6f-9677-5d8120943f10")); //Flame
        team1.add(UUID.fromString("7e8a77ca-daf1-4224-ae1d-2df8bab1eccb")); //Cold
        team1.add(UUID.fromString("d893df7c-182a-4c4f-b332-933c9ec72208")); //Epax
        team1.add(UUID.fromString("38bbb2fb-0ca0-448b-98c2-51ffb843a07e")); //Dijon for testing
        ArrayList<UUID> team2 = new ArrayList<>();
        team2.add(UUID.fromString("050adf1f-5e4c-4c52-8e4a-a54f31060dd9")); //Tai
        team2.add(UUID.fromString("373bf969-07fd-4459-a401-521ac52625d0")); //Salem
        team2.add(UUID.fromString("638ba0a4-4213-4e16-90b2-cbf0f49df9d9")); //Falcon
        team2.add(UUID.fromString("7ef33ded-a809-4526-816e-304be23793af")); //Ultra
        team2.add(UUID.fromString("b303cce4-ceba-454e-81df-3c285e2f07ac")); //Otter
        ArrayList<UUID> team3 = new ArrayList<>();
        team3.add(UUID.fromString("1bcf9ad4-65fb-409c-9ce9-59507f159868")); //Leo
        team3.add(UUID.fromString("a90761b0-6ed5-4ce7-aba8-9f82d3d922f7")); //Nuf
        team3.add(UUID.fromString("653cf53a-944a-49bf-ac7b-2c6eacf32050")); //Rev
        team3.add(UUID.fromString("dc230590-b138-4f86-9e01-5686c6010a62")); //Lucas
        team3.add(UUID.fromString("341fa4c0-a831-40e4-9add-90ecb77449d1")); //Slushy
        ArrayList<UUID> team4 = new ArrayList<>();
        team4.add(UUID.fromString("575f8a66-9c21-47f6-96fa-f3466d1f5077")); //Boomie
        team4.add(UUID.fromString("631eb13c-dae2-475c-b6af-71e0d9543268")); //Dragon
        team4.add(UUID.fromString("d2ff63c3-5897-4edc-b5d8-1881b65a4db9")); //Maksha
        team4.add(UUID.fromString("8af5d2db-276c-4be4-b0a1-1c90fd5b46bd")); //Potato
        team4.add(UUID.fromString("5aaf3e65-fea0-4973-886e-881f0c4c5202")); //Noob
        ArrayList<UUID> team5 = new ArrayList<>();
        team5.add(UUID.fromString("b7437a6c-5daa-435e-a2f1-5443093ce1ea")); //Napa
        team5.add(UUID.fromString("4d478f72-f71f-42ad-8b9d-e244a456b863")); //Rasplin
        team5.add(UUID.fromString("e66dcfa7-7340-418e-80dc-c9e8db0676b2")); //Names
        team5.add(UUID.fromString("c964d55c-1933-41d9-9784-babbacec6ede")); //Nyx
        team5.add(UUID.fromString("0051d3f0-be5e-496f-9be1-99c4f7492029")); //Gel
        ArrayList<UUID> team6 = new ArrayList<>();
        team6.add(UUID.fromString("2b3ab5ed-aef2-41cb-a604-867a319ba126")); //Spongs
        team6.add(UUID.fromString("768d19d9-583b-48ee-b551-14f3ee092595")); //Sky
        team6.add(UUID.fromString("70114f41-6588-484b-bd75-7957258d4010")); //Kalal
        team6.add(UUID.fromString("37b45e17-493a-4d47-a9bf-08b6bfd8138c")); //Kieng
        team6.add(UUID.fromString("22e89e4b-02d0-4570-bfec-c661af81f516")); //Lettuce
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
        teams.add(team5);
        teams.add(team6);
    }

    public static void initializeBossBars(){
        bossBars = new ArrayList<>();
        String title = "§e\uD83D\uDDF2 §6§lBattery §e\uD83D\uDDF2";
        BossBar bar1 = Bukkit.createBossBar(title, BarColor.YELLOW, BarStyle.SEGMENTED_10);
        BossBar bar2 = Bukkit.createBossBar(title, BarColor.YELLOW, BarStyle.SEGMENTED_10);
        BossBar bar3 = Bukkit.createBossBar(title, BarColor.YELLOW, BarStyle.SEGMENTED_10);
        BossBar bar4 = Bukkit.createBossBar(title, BarColor.YELLOW, BarStyle.SEGMENTED_10);
        BossBar bar5 = Bukkit.createBossBar(title, BarColor.YELLOW, BarStyle.SEGMENTED_10);
        BossBar bar6 = Bukkit.createBossBar(title, BarColor.YELLOW, BarStyle.SEGMENTED_10);
        bossBars.add(bar1);
        bossBars.add(bar2);
        bossBars.add(bar3);
        bossBars.add(bar4);
        bossBars.add(bar5);
        bossBars.add(bar6);

        reloadBossBars();
    }

    public static void reloadBossBars(){
        for(BossBar bar : bossBars){
            bar.setVisible(true);
            bar.setProgress(StationHandler.stations.get(bossBars.indexOf(bar)).chargeValue / 100);
            for(UUID uuid : teams.get(bossBars.indexOf(bar))){
                if(Bukkit.getPlayer(uuid) == null) continue;
                bar.addPlayer(Bukkit.getPlayer(uuid));
            }
        }
    }

}
