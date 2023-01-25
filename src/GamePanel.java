import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GamePanel extends JPanel {

    public GamePanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        // setting the background color of the component
        this.setBackground(Color.BLACK);
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
        ball.move();
    }
}
