import java.awt.*;
import java.util.ArrayList;

public class Ball extends Rectangle {

    // default values for ball
    public static final int DEFAULT_BALL_WIDTH = 12;
    public static final int DEFAULT_BALL_X = Pong.GAME_WIDTH / 2 - (DEFAULT_BALL_WIDTH / 2);
    public static final int DEFAULT_BALL_HEIGHT = 12;
    public static final int DEFAULT_BALL_Y = Pong.GAME_HEIGHT / 2 - (DEFAULT_BALL_HEIGHT / 2);
    public static final int DEFAULT_BALL_X_DIRECTION = -1;
    public static final int DEFAULT_BALL_Y_DIRECTION = 1;
    public static final int DEFAULT_BALL_X_SPEED = 5;
    public static final int DEFAULT_BALL_Y_SPEED = 2;
    public static final Color DEFAULT_BALL_COLOR = Color.WHITE;

//    public static ArrayList<Rectangle> ballInstances = new ArrayList<>();

    private int xDirection;
    private int yDirection;
    private int xSpeed;
    private int ySpeed;
    private Color color;


    public Ball(int x, int y, int width, int height, int xDirection, int yDirection, int xSpeed, int ySpeed, Color color) {
        super(x, y, width, height); // the x, y, width, and height are defined in the parent class (this is so they can be accessed by the Methods in Rectangle class like checkCollision())
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, this.width, this.height);
        // adding the ball instance to the ballList
//        if(ballInstances.size() < 2) {
//            ballInstances.add(new Rectangle(this.x, this.y, this.width, this.height));
//        } else {
//            ballInstances.add(new Rectangle(this.x, this.y, this.width, this.height));
//            ballInstances.remove(0);
//        }
    }

    public void move() {
        this.x += this.xSpeed * this.xDirection;
        this.y += this.ySpeed * this.yDirection;
        checkCollision();
    }

    public void checkCollision() {
        // ceiling collision
        if(this.y <= 0) {
            this.yDirection *= -1;
        }
         // floor collision
        if(this.y >= Pong.GAME_HEIGHT - this.height) {
            this.yDirection *= -1;
        }
        // left wall collision
        if(this.x <= 0) {
            GamePanel.reset();
        }
        // right wall collision
        if(this.x >= Pong.GAME_WIDTH - this.width) {
            GamePanel.reset();
        }

        // seperating padddle's 1 and 2 collisions to make it clearer to see
        // Although I could both paddles in one collision if statement using an OR, this makes it easier to see for now
        // checking collision between paddle and ball on all paddle sides that are not facing their own wall
        // paddle1 collision
        if(this.getBounds2D().intersects(GamePanel.paddle1.getBounds2D())) {
            // right-side collision
            if((this.x + this.xSpeed > GamePanel.paddle1.getX() + GamePanel.paddle1.getWidth())
                    && (this.y + this.height > GamePanel.paddle1.getY() + (this.height / 4.0)) // y value of bottom of ball is past 1/4 of the ball height from the top of the paddle
                    && (this.y < GamePanel.paddle1.getY() + GamePanel.paddle1.getHeight() - (this.height / 4.0))) { // y value of top of ball is less than 1/4 into the paddle from the bottom
                this.xDirection *= -1;
            // top collision
            } else if((this.x < GamePanel.paddle1.getX() + GamePanel.paddle1.getWidth() && ((this.y + this.height) <= GamePanel.paddle1.getY() + GamePanel.paddle1.getYSpeed() + this.ySpeed + (this.height / 4.0)))) { // assuming there is a collision and a right-side collision is not true, we are checking if the left of the ball is past the right of the paddle
                    // and the bottom of the ball is not further than the top of the paddle y-position + how much the paddle can move up on the y-axis + ball speed (maximum ball movement in one move) down on y-axis
                if(this.yDirection == 1) {
                    this.yDirection = -1;
                }
                GamePanel.isPaddle2Frozen = true;
                this.y = (int)(GamePanel.paddle1.getY() - this.height);
            // bottom collision
            } else if((this.x < GamePanel.paddle1.getX() + GamePanel.paddle1.getWidth() && (this.y >= GamePanel.paddle1.getY() + GamePanel.paddle1.getHeight() - GamePanel.paddle1.getYSpeed() - this.ySpeed - (this.height / 4.0)))) { // assuming there is a collision and a right-side collision is not true, we are checking if the left of the ball is past the right of the paddle
                    // and the top of the ball is further than the bottom of the paddle y-position - how much the paddle can move down on the y-axis - ball movement up on y-axis
                if(this.yDirection == -1) {
                    this.yDirection = 1;
                }
                GamePanel.isPaddle2Frozen = true;
                this.y = (int)(GamePanel.paddle1.getY() + GamePanel.paddle1.getHeight());
            }
        } else {
            // if there is no collision with paddle1 then:
            GamePanel.isPaddle1Frozen = false;
            GamePanel.isPaddle2Frozen = false;
//            if((this.x <= GamePanel.paddle1.getX() + GamePanel.paddle1.getWidth()) // left of ball is to the
//                    && ((this.y < GamePanel.paddle1.getY() && GamePanel.paddle1.getY() - (this.y + this.height) > GamePanel.paddle1.getYSpeed())
//                    || (this.y + this.height > GamePanel.paddle1.getY() + GamePanel.paddle1.getHeight() && this.y - (GamePanel.paddle1.getY() + GamePanel.paddle1.getHeight()) > GamePanel.paddle1.getYSpeed()))) {
//                GamePanel.isPaddle1Frozen = false;
//            } else if((this.x + this.width >= GamePanel.paddle2.getX())
//                    && ((this.y < GamePanel.paddle2.getY() && GamePanel.paddle2.getY() - (this.y + this.height) > GamePanel.paddle2.getYSpeed())
//                    || (this.y + this.height > GamePanel.paddle2.getY() + GamePanel.paddle2.getHeight() && this.y - (GamePanel.paddle2.getY() + GamePanel.paddle2.getHeight()) > GamePanel.paddle2.getYSpeed()))) {
//                GamePanel.isPaddle2Frozen = false;
//            }

        }
        // paddle2 collision
        if(this.getBounds2D().intersects(GamePanel.paddle2.getBounds2D())) {
            // left-side collision
            if((this.x + this.width - this.xSpeed < GamePanel.paddle2.getX())
                    && (this.y + this.height > GamePanel.paddle2.getY() + (this.height / 4.0)) // y value of bottom of ball is past 1/4 of the ball height from the top of the paddle
                    && (this.y < GamePanel.paddle2.getY() + GamePanel.paddle2.getHeight() - (this.height / 4.0))) { // y value of top of ball is less than 1/4 into the paddle from the bottom
                this.xDirection *= -1;
            // top collision
            } else if((this.x + this.width > GamePanel.paddle2.getX() && ((this.y + this.height) <= GamePanel.paddle2.getY() + GamePanel.paddle2.getYSpeed() + this.ySpeed + (this.height / 4.0)))) { // assuming there is a collision and a left collision is not true, we are checking if the right of the ball is past the left of the paddle
                    // and the bottom of the ball is not further than the top of the paddle y-position + how much the paddle can move up on the y-axis + ball movement down on y-axis
                if(this.yDirection == 1) {
                    this.yDirection = -1;
                }
                GamePanel.isPaddle2Frozen= true;
                this.y = (int)(GamePanel.paddle2.getY() - this.height);
            // bottom collision
            } else if((this.x + this.width > GamePanel.paddle2.getX() && (this.y >= GamePanel.paddle2.getY() + GamePanel.paddle2.getHeight() - GamePanel.paddle2.getYSpeed() - this.ySpeed - (this.height / 4.0)))) { // assuming there is a collision and a left collision is not true, we are checking if the right of the ball is past the left of the paddle
                    // and the top of the ball is further than the bottom of the paddle y-position - how much the paddle can move down on the y-axis - ball movement up on y-axis
                if(this.yDirection == -1) {
                    this.yDirection = 1;
                }
                GamePanel.isPaddle2Frozen = true;
                this.y = (int)(GamePanel.paddle2.getY() + GamePanel.paddle1.getHeight());
            }
        } else {
            // if there is no collision with paddle2 then:
            GamePanel.isPaddle1Frozen = false;
            GamePanel.isPaddle2Frozen = false;

//            if((this.x <= GamePanel.paddle1.getX() + GamePanel.paddle1.getWidth())
//                    && ((this.y < GamePanel.paddle1.getY() && GamePanel.paddle1.getY() - (this.y + this.height) > GamePanel.paddle1.getYSpeed())
//                    || (this.y + this.height > GamePanel.paddle1.getY() + GamePanel.paddle1.getHeight() && this.y - (GamePanel.paddle1.getY() + GamePanel.paddle1.getHeight()) > GamePanel.paddle1.getYSpeed()))) {
//                GamePanel.isPaddle1Frozen = false;
//            } else if((this.x + this.width >= GamePanel.paddle2.getX())
//                    && ((this.y < GamePanel.paddle2.getY() && GamePanel.paddle2.getY() - (this.y + this.height) > GamePanel.paddle2.getYSpeed())
//                    || (this.y + this.height > GamePanel.paddle2.getY() + GamePanel.paddle2.getHeight() && this.y - (GamePanel.paddle2.getY() + GamePanel.paddle2.getHeight()) > GamePanel.paddle2.getYSpeed()))) {
//                GamePanel.isPaddle2Frozen = false;
//            }
        }
    }

    public void gameStartInitiatingBallMovementProperties(int xDirection, int yDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }

//    public boolean nextBallPosCollidesWithPaddle1OrPaddle2TopOrBottom() {
//        int newX = this.x + (this.xSpeed * this.xDirection);
//        int newY = this.y + (this.ySpeed * this.yDirection);
//
//        Rectangle checkingRectangleBall = new Rectangle(newX, newY, this.width, this.height);
//        return Ball.isBallTopCollisionOnVerticlePaddle(checkingRectangleBall, GamePanel.paddle1) || Ball.isBallBottomCollisionOnVerticlePaddle(checkingRectangleBall, GamePanel.paddle1)
//                || Ball.isBallTopCollisionOnVerticlePaddle(checkingRectangleBall, GamePanel.paddle2) || Ball.isBallBottomCollisionOnVerticlePaddle(checkingRectangleBall, GamePanel.paddle2);
//    }

//    public static boolean isBallTopCollisionOnVerticlePaddle(Rectangle ball, Paddle paddle) {
//        // given ball collides with top of verticle paddle
//
//        if(ball.getBounds2D().intersects(paddle.getBounds2D()) && (ball.getY() + ball.getHeight() <= paddle.getY())) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }
//    public static boolean isBallBottomCollisionOnVerticlePaddle(Rectangle ball, Paddle paddle) {
//        // given ball collides with bottom of verticle paddle
//        if(ball.getBounds2D().intersects(paddle.getBounds2D()) && (ball.getY() >= paddle.getY() + paddle.getHeight())) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    // setters
    public void setYDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    // getters
    public int getXSpeed() {
        return this.xSpeed;
    }
    public int getYSpeed() {
        return this.ySpeed;
    }
    public int getXDirection() {
        return this.xDirection;
    }
    public int getYDirection() {
        return this.yDirection;
    }
}
