package main;

public class Main {
    public static void main(String[] args)
    {
        int fieldSize = 10;
        int farmerCount = 3;
        int turnsCount = 30;

        Dog[] dogs = new Dog[farmerCount];
        Farmer[] farmers = new Farmer[farmerCount];
        Field field = new Field(fieldSize);

        for (int i = 0; i < farmerCount; i++){
            dogs[i] = new Dog(new int[]{0, 0});
            farmers[i] = new Farmer(new int[]{0, 0},dogs[i]);
        }



    }
}