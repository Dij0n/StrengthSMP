package dijon.strengthsmp.event;

import org.bukkit.block.Block;

public class Station {

    Block block;
    double chargeValue;
    String teamname;

    public Station(Block block, double chargeValue, String teamname) {
        this.block = block;
        this.chargeValue = chargeValue;
        this.teamname = teamname;
    }

    public Block getBlock() {
        return block;
    }

    public double getChargeValue() {
        return chargeValue;
    }

    public void setChargeValue(double chargeValue) {
        this.chargeValue = chargeValue;
    }

    public String getTeamname() {
        return teamname;
    }

    public void charge(double chargeValue) {
        this.chargeValue += chargeValue;
    }
}
