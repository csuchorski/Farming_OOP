package model;

import java.util.random.RandomGenerator;

public class Rabbit extends Damageable implements Runnable {
    private int eatingSpeed = 2000;
    private int damagingSpeed = 100;
    final private int moveTime = 1000;
    private Field field;

    public Rabbit(int[] position, int respawnTime, Field field) {
        super(position, respawnTime);
        this.field = field;
        this.repairTime = respawnTime;
    }

    public void run() {
        while (true) {

            try {
                if (this.isDamaged) {
                    Thread.sleep(this.repairTime);
                    this.respawn();
                } else {
                    Square currentSquare = field.getSquare(position[0], position[1]);
                    synchronized (currentSquare) {
                        if (currentSquare.hasCarrots) {
                            this.eatCarrot();
                            Thread.sleep(this.eatingSpeed);
                            this.damageLand();
                            Thread.sleep(this.damagingSpeed);
                        }
                    }
                    this.moveRandomly();
                    Thread.sleep(moveTime);

                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

        }
    }

    public void becomeDead() {
        this.isDamaged = true;
    }

    private void respawn() {
        int new_x = RandomGenerator.getDefault().nextInt(this.field.getSize());
        int new_y = RandomGenerator.getDefault().nextInt(this.field.getSize());
        this.position[0] = new_x;
        this.position[1] = new_y;
        this.isDamaged = false;
    }

    public void moveRandomly() {

        Square currentSquare = this.field.squares[this.position[0]][this.position[1]];
        synchronized (currentSquare) {
            currentSquare.hasRabbit = false;
            currentSquare.rabbit = null;
        }


        int dx = RandomGenerator.getDefault().nextInt(3) - 1;
        int dy = RandomGenerator.getDefault().nextInt(3) - 1;

        int new_x = Math.min(this.field.getSize() - 1, Math.max(0, position[0] + dx));
        int new_y = Math.min(this.field.getSize() - 1, Math.max(0, position[1] + dy));
        this.position[0] = new_x;
        this.position[1] = new_y;

        Square newSquare = this.field.squares[new_x][new_y];
        synchronized (newSquare) {
            newSquare.hasRabbit = true;
            newSquare.rabbit = this;
        }


    }

    public void eatCarrot() {
        this.field.squares[position[0]][position[1]].hasCarrots = false;
    }

    public void damageLand() {
        this.field.squares[position[0]][position[1]].isDamaged = true;
    }
}
