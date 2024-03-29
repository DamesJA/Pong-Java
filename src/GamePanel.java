import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class GamePanel extends JPanel {

    public GamePanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        // setting the background color of the component
        this.setBackground(Color.BLACK);
    }

    public static SoundClip paddle_hit_sound_1;
    public static SoundClip left_and_right_wall_hit_sound_1;
    public static SoundClip top_and_bottom_wall_hit_sound_1;

    static {
        try {
            paddle_hit_sound_1 = new SoundClip("C:/Users/amant/OneDrive/Documents/Java Own Projects/Pong/src/resources/sounds/paddle_hit_sound_1.wav");
            left_and_right_wall_hit_sound_1 = new SoundClip("C:/Users/amant/OneDrive/Documents/Java Own Projects/Pong/src/resources/sounds/left_and_right_wall_hit_sound_1.wav");
            top_and_bottom_wall_hit_sound_1 = new SoundClip("C:/Users/amant/OneDrive/Documents/Java Own Projects/Pong/src/resources/sounds/top_and_bottom_wall_hit_sound_1.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isPaddle1Frozen = false;
    public static boolean isPaddle2Frozen = false;

    // creating paddles
    public static Paddle paddle1 = new Paddle(Paddle.DEFAULT_PADDLE1_XPOSITION, Paddle.DEFAULT_PADDLE_YPOSITION, Paddle.DEFAULT_PADDLE_WIDTH, Paddle.DEFAULT_PADDLE_HEIGHT, Paddle.DEFAULT_PADDLE_YSPEED, Paddle.DEFAULT_PADDLE1_COLOR);
    public static Paddle paddle2 = new Paddle(Paddle.DEFAULT_PADDLE2_XPOSITION, Paddle.DEFAULT_PADDLE_YPOSITION, Paddle.DEFAULT_PADDLE_WIDTH, Paddle.DEFAULT_PADDLE_HEIGHT, Paddle.DEFAULT_PADDLE_YSPEED, Paddle.DEFAULT_PADDLE2_COLOR);

    // creating ball
    public static Ball ball = new Ball(Ball.DEFAULT_BALL_X, Ball.DEFAULT_BALL_Y, Ball.DEFAULT_BALL_WIDTH, Ball.DEFAULT_BALL_HEIGHT, Ball.DEFAULT_BALL_X_DIRECTION, Ball.DEFAULT_BALL_Y_DIRECTION, Ball.DEFAULT_BALL_X_SPEED, Ball.DEFAULT_BALL_Y_SPEED, Ball.DEFAULT_BALL_COLOR);

    // creating middle line
    public static Line middleLine = new Line(Line.MIDDLE_LINE_X, Line.MIDDLE_LINE_Y, Line.MIDDLE_LINE_WIDTH, Line.MIDDLE_LINE_HEIGHT, Color.WHITE);

    // creating two scores
    public static Score score1 = new Score(Score.DEFAULT_SCORE1_X, Score.DEFAULT_SCORE1_Y, Score.DEFAULT_SCORE1_COLOR, Score.DEFAULT_SCORE1_FONT, Score.DEFAULT_SCORE1_STARTING_SCORE);
    public static Score score2 = new Score(Score.DEFAULT_SCORE2_X, Score.DEFAULT_SCORE2_Y, Score.DEFAULT_SCORE2_COLOR, Score.DEFAULT_SCORE2_FONT, Score.DEFAULT_SCORE2_STARTING_SCORE);

    public Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    // for resetting the game
    public static void reset() {
        paddle1 = new Paddle(Paddle.DEFAULT_PADDLE1_XPOSITION, Paddle.DEFAULT_PADDLE_YPOSITION, Paddle.DEFAULT_PADDLE_WIDTH, Paddle.DEFAULT_PADDLE_HEIGHT, Paddle.DEFAULT_PADDLE_YSPEED, Paddle.DEFAULT_PADDLE1_COLOR);
        paddle2 = new Paddle(Paddle.DEFAULT_PADDLE2_XPOSITION, Paddle.DEFAULT_PADDLE_YPOSITION, Paddle.DEFAULT_PADDLE_WIDTH, Paddle.DEFAULT_PADDLE_HEIGHT, Paddle.DEFAULT_PADDLE_YSPEED, Paddle.DEFAULT_PADDLE2_COLOR);
        ball = new Ball(Ball.DEFAULT_BALL_X, Ball.DEFAULT_BALL_Y, Ball.DEFAULT_BALL_WIDTH, Ball.DEFAULT_BALL_HEIGHT, Ball.DEFAULT_BALL_X_DIRECTION, Ball.DEFAULT_BALL_Y_DIRECTION, Ball.DEFAULT_BALL_X_SPEED, Ball.DEFAULT_BALL_Y_SPEED, Ball.DEFAULT_BALL_COLOR);
    }

    @Override
    public void paintComponent(Graphics g) {
        // this will save me lines of code as calling the method from where we overide it from will set the background for me based on the background color set to the component
        super.paintComponent(g);

        //drawing paddles
        paddle1.draw(g);
        paddle2.draw(g);
        // drawing ball
        ball.draw(g);
        // drawing the line down the middle
        middleLine.drawDottedLineDownScreen(g);
        // drawing the score
        score1.draw(g);
        score2.draw(g);

        // doing actions on the keys that have a value of 1 (currently active)
        for(Map.Entry<Integer, Integer> entry : Pong.activeKeysHashMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if(value == 1) {
                if(ball.getXDirection() == 0 & ball.getYDirection() == 0) {
                    // this means a key has been pressed so let the ball start moving
                    ball.gameStartInitiatingBallMovementProperties(1, -1);
                }
                if(key == 87 || key == 83) {
                    if(!isPaddle1Frozen) {
                        paddle1.move();
                    }
                }
                if(key == 38 || key == 40) {
                    // if paddle2 movement is true
                    if(!isPaddle2Frozen) {
                        paddle2.move();
                    }
                }

            }
        }

//      moving ball
        try {
            ball.move();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
