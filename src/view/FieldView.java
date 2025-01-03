package view;

import model.Field;
import model.Square;

import javax.swing.*;
import java.awt.*;

public class FieldView extends JFrame {
    private Field field;
    private Timer timer;


    public FieldView(Field field) {
        this.field = field;
        setTitle("Farm Simulation");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Add a custom JPanel to draw the field
        FieldPanel fieldPanel = new FieldPanel();
        add(fieldPanel);

        timer = new Timer(100, e -> updateView());  // Every 100ms, call updateView()
        timer.start();
    }

    // Custom JPanel for drawing the field
    private class FieldPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the grid (tiles)
            for (int i = 0; i < field.getSize(); i++) {
                for (int j = 0; j < field.getSize(); j++) {
                    Square square = field.getSquare(i, j);
                    Color tileColor;

                    // Determine the color of the tile
                    if (square.hasCarrots) {
                        tileColor = Color.GREEN; // Tile with carrots is green
                    }
                    else if (square.isDamaged) {
                        tileColor = Color.RED; // Damaged tile is red
                    }
                    else {
                        tileColor = Color.GRAY;
                    }

                    // Draw each tile as a rectangle
                    g.setColor(tileColor);
                    g.fillRect(i * 50, j * 50, 50, 50);
                    g.setColor(Color.BLACK); // Draw border
                    g.drawRect(i * 50, j * 50, 50, 50);
                    if (square.hasDog){
                            g.setColor(Color.BLACK);
                            g.drawString("D", i * 50 + 5, j * 50 + 50 / 2);

                        //tileColor = Color.YELLOW; // Empty tile is gray
                    }
                    if (square.hasFarmer){
                        g.setColor(Color.BLACK);
                        g.drawString("F", i * 50 + 25, j * 50 + 50 / 2);
                    }
                    if (square.hasRabbit){
                        g.setColor(Color.BLACK);
                        g.drawString("R", i * 50 + 40, j * 50 + 50 / 2);
                    }
                }
            }
        }
    }

    // This method is to update the view when needed
    public void updateView() {
        repaint();  // Repaints the panel (re-draw the field)
    }

}
