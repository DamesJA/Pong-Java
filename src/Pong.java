import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Pong {

    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = (int)(GAME_WIDTH * (5.0/9.0));

    // the HashMap that will hold 0's and 1's for the keycodes for active keys
    public static HashMap<Integer, Integer> activeKeysHashMap = new HashMap<>();


    public static void main(String[] args) {
        GameFrame frame = new GameFrame(true, false);
        // adding key event listeners to the frame
        frame.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case (87) -> {
                        // changing yDirection for paddle1
                        GamePanel.paddle1.setYDirection(-1);

                        // setting this key to active (value of 1) when the key gets pressed
                        activeKeysHashMap.put(87, 1);
                    }
                    case (83) -> {
                        // changing ydirection for paddle 1
                        GamePanel.paddle1.setYDirection(1);

                        // setting this key to active (value of 1) when the key gets pressed
                        activeKeysHashMap.put(83, 1);
                    }
                    case (38) -> {
                        // changing ydirection for paddle2
                        GamePanel.paddle2.setYDirection(-1);

                        // setting this key to active (value of 1) when the key gets pressed
                        activeKeysHashMap.put(38, 1);
                    }
                    case (40) -> {
                        // changing ydirection for paddle 2
                        GamePanel.paddle2.setYDirection(1);

                        // setting this key to active (value of 1) when the key gets pressed
                        activeKeysHashMap.put(40, 1);
                    }
                }
            }
                @Override
            public void keyReleased(KeyEvent e){
                switch (e.getKeyCode()) {
                    case (87) -> {
                        // setting this key to inactive (value of 0) here in the keyReleased method
                        activeKeysHashMap.put(87, 0);
                    }
                    case (83) -> {
                        // setting this key to inactive (value of 0) here in the keyReleased method
                        activeKeysHashMap.put(83, 0);
                    }
                    case (38) -> {
                        // setting this key to inactive (value of 0) here in the keyReleased method
                        activeKeysHashMap.put(38, 0);
                    }
                    case (40) -> {
                        // setting this key to inactive (value of 0) here in the keyReleased method
                        activeKeysHashMap.put(40, 0);
                    }
                }
            }
        });

        // making a new JPanel(subclass of JComponent which is a subclass of Container) and adding it to the frames container
        GamePanel panel = new GamePanel(GAME_WIDTH, GAME_HEIGHT);
        frame.add(panel);

        // resizing the frame to fit the panel dimensions
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // calling the timer in the JPanel class after everything is created which keeps on calling repaint() every time the timer invokes it's action listener's action performed event
        panel.timer.start();
    }
}