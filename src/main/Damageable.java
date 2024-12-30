package main;

abstract public class Damageable {
    private int[] position;
    private int repairTime;
    private boolean isDamaged;

    public Damageable(int[] position, int repairTime) {
        this.position = position;
        this.repairTime = repairTime;
        this.isDamaged = false;
    }
}
