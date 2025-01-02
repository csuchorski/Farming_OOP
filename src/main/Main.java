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
        int farmerCount = 6;
        int rabbitCount = 12;
        Field field = new Field(fieldSize);

        Vector<Thread> threads = new Vector<>();
        //Map<Rabbit, Thread> rabbitThreadMap = new HashMap<>();
        Vector<Dog> dogs = new Vector<>();
        Vector<Farmer> farmers = new Vector<>();
        Vector<Rabbit> rabbits = new Vector<>();


        for (int i = 0; i < farmerCount; i++){
            int x = RandomGenerator.getDefault().nextInt(fieldSize);
            int y = RandomGenerator.getDefault().nextInt(fieldSize);
            dogs.add(new Dog(new int[]{x,y}, field));
            farmers.add(new Farmer(new int[]{x,y}, dogs.elementAt(i),field));

            Thread dogThread = new Thread(dogs.get(i));
            Thread farmerThread = new Thread(farmers.get(i));
            threads.add(dogThread);
            threads.add(farmerThread);
            farmerThread.start();
            dogThread.start();
        }

        for (int i = 0; i < rabbitCount; i++){
            int x = RandomGenerator.getDefault().nextInt(fieldSize);
            int y = RandomGenerator.getDefault().nextInt(fieldSize);
            Rabbit rabbit = new Rabbit(new int[]{x,y},3000, field);
            rabbits.add(rabbit);
            Thread rabbitThread = new Thread(rabbits.get(i));
            threads.add(rabbitThread);
            //rabbitThreadMap.put(rabbit, rabbitThread);
            rabbitThread.start();
        }


        FieldView fieldView = new FieldView(field);
        fieldView.setVisible(true);

    }
}