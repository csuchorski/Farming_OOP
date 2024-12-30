package main;

public class Rabbit extends Damageable{
    private int turnsEatingLeft;

    public Rabbit(int[] position, int respawnTime, int turnsEatingLeft) {
        super(position, respawnTime);
        this.turnsEatingLeft = turnsEatingLeft;
    }
    public void eatCarrot(){
    }
}
