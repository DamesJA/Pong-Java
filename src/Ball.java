import java.awt.*;

public class Ball extends Rectangle {

    // default values for ball
    public static final int DEFAULT_BALL_WIDTH = 12;
    public static final int DEFAULT_BALL_X = Pong.GAME_WIDTH / 2 - (DEFAULT_BALL_WIDTH / 2);
    public static final int DEFAULT_BALL_HEIGHT = 12;
    public static final int DEFAULT_BALL_Y = Pong.GAME_HEIGHT / 2 - (DEFAULT_BALL_HEIGHT / 2);
    public static final int DEFAULT_BALL_X_DIRECTION = 0;
    public static final int DEFAULT_BALL_Y_DIRECTION = 0;
    public static final int DEFAULT_BALL_X_SPEED = 5;
    public static final int DEFAULT_BALL_Y_SPEED = 2;
    public static final Color DEFAULT_BALL_COLOR = Color.WHITE;

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

        // if ball collides with paddle1 then check if it is the top or bottom, else it had to be the front and do their respective actions based on where it hit the paddle
        if(this.getBounds2D().intersects(GamePanel.paddle1.getBounds2D())) {
            if(Ball.isBallTopCollisionOnVerticlePaddle(this, GamePanel.paddle1) || Ball.isBallBottomCollisionOnVerticlePaddle(this, GamePanel.paddle1)) {
                this.yDirection *= -1;
            } else {
                this.xDirection *= -1;
            }
        }

        // if ball collides with paddle2 then check if it is the top or bottom, else it had to be the front and do their respective actions based on where it hit the paddle
        if(this.getBounds2D().intersects(GamePanel.paddle2.getBounds2D())) {
            if(Ball.isBallTopCollisionOnVerticlePaddle(this, GamePanel.paddle2) || Ball.isBallBottomCollisionOnVerticlePaddle(this, GamePanel.paddle2)) {
                System.out.println("b");
                this.yDirection *= -1;
            } else {
                this.xDirection *= -1;
            }
        }

    }

    public void gameStartInitiatingBallMovementProperties(int xDirection, int yDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }

    public boolean nextBallPosCollidesWithPaddle1OrPaddle2TopOrBottom() {
        int newX = this.x + (this.xSpeed * this.xDirection);
        int newY = this.y + (this.ySpeed * this.yDirection);

        Rectangle checkingRectangleBall = new Rectangle(newX, newY, this.width, this.height);
        return Ball.isBallTopCollisionOnVerticlePaddle(checkingRectangleBall, GamePanel.paddle1) || Ball.isBallBottomCollisionOnVerticlePaddle(checkingRectangleBall, GamePanel.paddle1)
                || Ball.isBallTopCollisionOnVerticlePaddle(checkingRectangleBall, GamePanel.paddle2) || Ball.isBallBottomCollisionOnVerticlePaddle(checkingRectangleBall, GamePanel.paddle2);
    }

    public static boolean isBallTopCollisionOnVerticlePaddle(Rectangle ball, Paddle paddle) {
        // given ball collides with top of verticle paddle

        if(ball.getBounds2D().intersects(paddle.getBounds2D()) && (ball.getY() + ball.getHeight() <= paddle.getY())) {
//            System.out.println("a");
            return true;
        } else {
            return false;
        }

    }
    public static boolean isBallBottomCollisionOnVerticlePaddle(Rectangle ball, Paddle paddle) {
        // given ball collides with bottom of verticle paddle
        if(ball.getBounds2D().intersects(paddle.getBounds2D()) && (ball.getY() >= paddle.getY() + paddle.getHeight())) {
//            System.out.println("b");
            return true;
        } else {
            return false;
        }
    }

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
