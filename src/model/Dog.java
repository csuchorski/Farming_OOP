package model;

import java.util.random.RandomGenerator;

public class Dog implements Runnable {
    final private int[] position;
    final private int moveTime = 900;
    final private Field field;


    public Dog(int[] position, Field field) {
        this.position = position;
        this.field = field;

        this.field.getSquare(this.position[0], this.position[1]).hasDog = true;
    }

    public void run() {
        while (true) {
            try {
                Square currentSquare = field.getSquare(position[0], position[1]);
                synchronized (currentSquare) {
                    if (currentSquare.hasRabbit) {
                        this.killRabbit(currentSquare.rabbit);
                        Thread.sleep(currentSquare.repairTime);
                    } else {
                        int[] detection = detectRabbit();
                        if (detection != null) {
                            this.moveTowardsRabbit(detection[0], detection[1]);
                        } else {
                            this.moveRandomly();
                        }
                        Thread.sleep(moveTime);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public int[] detectRabbit() {
        for (int dx = -5; dx <= 5; dx++) {
            for (int dy = -5; dy <= 5; dy++) {
                if (this.position[0] + dx >= 0 && this.position[0] + dx < this.field.getSize() && this.position[1] + dy >= 0 && this.position[1] + dy < this.field.getSize()) {
                    if (this.field.getSquare(this.position[0] + dx, this.position[1] + dy).hasRabbit) {
                        return new int[]{this.position[0] + dx, this.position[1] + dy};
                    }
                }
            }
        }
        return null;
    }

    public void moveRandomly() {
        this.field.getSquare(this.position[0], this.position[1]).hasDog = false;

        int dx = RandomGenerator.getDefault().nextInt(3) - 1;
        int dy = RandomGenerator.getDefault().nextInt(3) - 1;

        int new_x = Math.min(this.field.getSize() - 1, Math.max(0, position[0] + dx));
        int new_y = Math.min(this.field.getSize() - 1, Math.max(0, position[1] + dy));
        this.position[0] = new_x;
        this.position[1] = new_y;

        this.field.getSquare(this.position[0], this.position[1]).hasDog = true;
    }

    public void moveTowardsRabbit(int rabbitX, int rabbitY) {
        this.field.getSquare(this.position[0], this.position[1]).hasDog = false;
        int dx = rabbitX - this.position[0];
        int dy = rabbitY - this.position[1];
        if (dx != 0) {
            this.position[0] += dx / Math.abs(dx);
        }
        if (dy != 0) {
            this.position[1] += dy / Math.abs(dy);
        }
        this.field.getSquare(this.position[0], this.position[1]).hasDog = true;

    }

    private void killRabbit(Rabbit rabbit) {
        rabbit.becomeDead();
        this.field.getSquare(this.position[0], this.position[1]).hasRabbit = false;
    }
}
