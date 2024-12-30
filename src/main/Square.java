package main;

import java.awt.*;

public class Square extends Damageable{
    boolean hasCarrots;

    public Square(int[] position, int respawnTime) {
        super(position, respawnTime);
        this.hasCarrots = false;
    }

}
