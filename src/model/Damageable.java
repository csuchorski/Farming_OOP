package model;

abstract public class Damageable {
    private int[] position;
    private int repairTime;
    public boolean isDamaged;

    public Damageable(int[] position, int repairTime) {
        this.position = position;
        this.repairTime = repairTime;
        this.isDamaged = false;
    }
}
