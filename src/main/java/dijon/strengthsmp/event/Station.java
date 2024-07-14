package dijon.strengthsmp.event;

import org.bukkit.block.Block;

public class Station {

    Block block;
    double chargeValue;

    public Station(Block block, double chargeValue) {
        this.block = block;
        this.chargeValue = chargeValue;
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

    public void charge(double chargeValue) {
        this.chargeValue += chargeValue;
    }
}
