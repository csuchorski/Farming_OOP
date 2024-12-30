package main;

import model.Dog;
import model.Farmer;
import model.Field;
import view.FieldView;

import java.util.Vector;

public class Main {
    public static void main(String[] args)
    {
        int fieldSize = 10;
        int farmerCount = 3;
        int turnsCount = 30;
        Field field = new Field(fieldSize);
        Vector<Thread> threads = new Vector<>();

        Vector<Dog> dogs = new Vector<>();
        Vector<Farmer> farmers = new Vector<>();


        for (int i = 0; i < farmerCount; i++){
            dogs.add(new Dog(new int[]{0,0}, field));
            farmers.add(new Farmer(new int[]{0,0}, dogs.elementAt(i),field));

            Thread dogThread = new Thread(dogs.get(i));
            Thread farmerThread = new Thread(farmers.get(i));
            threads.add(dogThread);
            threads.add(farmerThread);
            farmerThread.start();
            dogThread.start();
        }


        FieldView fieldView = new FieldView(field);
        fieldView.setVisible(true);

    }
}