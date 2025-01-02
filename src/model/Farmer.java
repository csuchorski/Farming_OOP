package model;

import java.util.random.RandomGenerator;


public class Farmer implements Runnable {
    final private int plantingTime = 2000;
    final private int moveTime = 1000;

    private int[] position;
    private Dog dog;
    private Field field;

    public Farmer(int[] position, Dog dog, Field field) {
        this.position = position;
        this.dog = dog;
        this.field = field;

        this.field.squares[position[0]][position[1]].hasFarmer = true;

    }

    public void run() {
        while (true) {
            try {
                Square currentSquare = field.getSquare(position[0], position[1]);
                if (currentSquare.isDamaged) {
                    this.repairLand();
                    Thread.sleep(currentSquare.repairTime);
                } else if (!currentSquare.hasCarrots) {
                    this.plantCarrots();
                    Thread.sleep(this.plantingTime);
                } else {
                    this.move();
                    Thread.sleep(moveTime);

                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void plantCarrots() {
        this.field.squares[this.position[0]][this.position[1]].hasCarrots = true;
    }

    public void repairLand() {
        this.field.squares[this.position[0]][this.position[1]].isDamaged = false;
    }

    public void spotRabbit() {
    }

    public void move() {
        this.field.squares[position[0]][position[1]].hasFarmer = false;

        int dx = RandomGenerator.getDefault().nextInt(3) - 1;
        int dy = RandomGenerator.getDefault().nextInt(3) - 1;

        int new_x = Math.min(this.field.getSize()-1,Math.max(0,position[0] + dx));
        int new_y = Math.min(this.field.getSize()-1,Math.max(0,position[1] + dy));
        this.position[0] = new_x;
        this.position[1] = new_y;

        this.field.squares[position[0]][position[1]].hasFarmer = true;

    }
}
