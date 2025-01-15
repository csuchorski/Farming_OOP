package main;

import model.Dog;
import model.Farmer;
import model.Field;
import model.Rabbit;
import view.FieldView;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.random.RandomGenerator;

public class Main {
    public static void main(String[] args)
    {
        int fieldSize = 10;
        int farmerCount = 3;
        int rabbitCount = 12;
        Field field = new Field(fieldSize);

        Vector<Thread> threads = new Vector<>();
        //Map<Rabbit, Thread> rabbitThreadMap = new HashMap<>();
        Vector<Runnable> entities = new Vector<>();
        Vector<Dog> dogs = new Vector<>();
        Vector<Farmer> farmers = new Vector<>();
        Vector<Rabbit> rabbits = new Vector<>();


        for (int i = 0; i < farmerCount; i++){
            int x = RandomGenerator.getDefault().nextInt(fieldSize);
            int y = RandomGenerator.getDefault().nextInt(fieldSize);
            Dog dog = new Dog(new int[]{x,y}, field);
            Farmer farmer = new Farmer(new int[]{x,y}, dog,field);
            dogs.add(dog);
            farmers.add(farmer);
            entities.add(dog);
            entities.add(farmer);
        }

        for (int i = 0; i < rabbitCount; i++){
            int x = RandomGenerator.getDefault().nextInt(fieldSize);
            int y = RandomGenerator.getDefault().nextInt(fieldSize);
            Rabbit rabbit = new Rabbit(new int[]{x,y},3000, field);
            rabbits.add(rabbit);
            entities.add(rabbit);
        }

        for (Runnable entity : entities) {
            Thread entityThread = new Thread(entity);
            threads.add(entityThread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        FieldView fieldView = new FieldView(field);
        fieldView.setVisible(true);

    }
}