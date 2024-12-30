package model;

public class Square extends Damageable{
    public boolean hasCarrot;

    public Square(int[] position, int respawnTime) {
        super(position, respawnTime);
        this.hasCarrot = false;
    }

}
