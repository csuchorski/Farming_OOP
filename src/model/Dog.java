package model;

import java.util.random.RandomGenerator;

public class Dog implements Runnable {
    private int[] position;
    final private int moveTime = 500;
    private Field field;


    public Dog(int[] position,Field field) {
        this.position = position;
        this.field = field;

        this.field.squares[position[0]][position[1]].hasDog = true;
    }
    public void run() {
        while (true) {
            try {
                    Square currentSquare = field.getSquare(position[0], position[1]);
                    synchronized (currentSquare){
                        if (currentSquare.hasRabbit) {
                            this.killRabbit(currentSquare.rabbit);
                            Thread.sleep(currentSquare.repairTime);
                        }
                        else {
                            this.moveRandomly();
                            Thread.sleep(moveTime);
                        }
                    }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
    public int[] detectRabbit(){
    return position;
    }
    public void moveRandomly() {
        this.field.squares[position[0]][position[1]].hasDog = false;

        int dx = RandomGenerator.getDefault().nextInt(3) - 1;
        int dy = RandomGenerator.getDefault().nextInt(3) - 1;

        int new_x = Math.min(this.field.getSize()-1,Math.max(0,position[0] + dx));
        int new_y = Math.min(this.field.getSize()-1,Math.max(0,position[1] + dy));
        this.position[0] = new_x;
        this.position[1] = new_y;

        this.field.squares[position[0]][position[1]].hasDog = true;
    }
    public void moveTowardsRabbit(){}

    private void killRabbit(Rabbit rabbit) {
        rabbit.becomeDead();
        this.field.squares[position[0]][position[1]].hasRabbit = false;
    }
}
