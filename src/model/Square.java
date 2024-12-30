package model;

public class Square extends Damageable{
    public boolean hasCarrots;
    public boolean hasRabbit;
    public boolean hasFarmer;
    public boolean hasDog;

    public Square(int[] position, int respawnTime) {
        super(position, respawnTime);
        this.hasCarrots = false;
        this.hasRabbit = false;
        this.hasFarmer= false;
        this.hasDog = false;
    }

    public int getRepairTime() {
        return this.repairTime;
    }

}
