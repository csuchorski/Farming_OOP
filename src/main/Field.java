package main;

public class Field {
    public int size;
    public Square[][] squares;

    public Field(int size) {
        this.size = size;
        squares = new Square[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                squares[i][j] = new Square(new int[]{i,j}, 3);
            }
        }
    }
}
