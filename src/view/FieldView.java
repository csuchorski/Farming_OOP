package view;

import model.Field;
import model.Square;

import javax.swing.*;
import java.awt.*;

public class FieldView extends JFrame {
    private Field field;
    private Timer timer;
    private Image dogImage;
    private Image rabbitImage;
    private Image farmerImage;
    private Image carrotsImage;
    private Image damagedImage;
    private Image emptyTileImage;

    public FieldView(Field field) {
        this.field = field;

        try {
            this.dogImage = loadImage("src/main/assets/dog.png");
            this.rabbitImage = loadImage("src/main/assets/rabbit.png");
            this.farmerImage = loadImage("src/main/assets/farmer.png");
            this.carrotsImage = loadImage("src/main/assets/carrots.jpg");
            this.emptyTileImage = loadImage("src/main/assets/grass.jpg");
            this.damagedImage = loadImage("src/main/assets/damaged.jpg");
        } catch (RuntimeException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Exit the application
        }


        setTitle("Farm Simulation");
        setSize(800, 800);
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
                    Image background = getBackgroundImage(square);
                    g.drawImage(background, i * 80, j * 80, 80, 80, this);

                    g.setColor(Color.BLACK); // Draw border
                    g.drawRect(i * 80, j * 80, 80, 80);
                    if (square.hasDog){
                        g.drawImage(dogImage, i * 80 + 5, j * 80 + 5, 40, 40, this);
                    }
                    if (square.hasFarmer){
                        g.drawImage(farmerImage, i * 80 + 5, j * 80 + 5, 60, 60, this);
                    }
                    if (square.hasRabbit){
                        g.drawImage(rabbitImage, i * 80 + 5, j * 80 + 5, 40, 40, this);
                    }
                }
            }
        }
    }

    // This method is to update the view when needed
    public void updateView() {
        repaint();  // Repaints the panel (re-draw the field)
    }

    private Image loadImage(String path) {
        try {
            ImageIcon icon = new ImageIcon(path);
            if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new RuntimeException("Failed to load image from path: " + path);
            }
            return icon.getImage();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image from path: " + path, e);
        }
    }
    private Image getBackgroundImage(Square square) {
        if (square.isDamaged) {
            return damagedImage;  // Dirt image for damaged squares
        } else if (square.hasCarrots) {
            return carrotsImage;  // Carrots image for squares with carrots
        } else {
            return emptyTileImage;  // Default background (grass) image for empty squares
        }
    }
}
