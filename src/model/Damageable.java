package model;

abstract public class Damageable {
    final protected int[] position;
    final protected int repairTime;
    public boolean isDamaged;

    public Damageable(int[] position, int repairTime) {
        this.position = position;
        this.repairTime = repairTime;
        this.isDamaged = false;
    }
}
