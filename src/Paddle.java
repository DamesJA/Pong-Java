import java.awt.*;

public class Paddle extends Rectangle {
    // default values for ALL paddles
    public static final int DEFAULT_PADDLE_WIDTH = 12;
    public static final int DEFAULT_PADDLE_HEIGHT = 50;
    public static final int DEFAULT_PADDLE_YPOSITION = (Pong.GAME_HEIGHT / 2) - (DEFAULT_PADDLE_HEIGHT / 2);
    public static final int DEFAULT_PADDLE_YSPEED = 8;
    public static final int DISTANCE_FROM_WALL = 50;

    // default values for paddle 2
    public static final int DEFAULT_PADDLE2_XPOSITION = Pong.GAME_WIDTH - DEFAULT_PADDLE_WIDTH - DISTANCE_FROM_WALL;
    public static final Color DEFAULT_PADDLE2_COLOR = Color.WHITE;

    // default values for paddle 1
    public static final int DEFAULT_PADDLE1_XPOSITION = DISTANCE_FROM_WALL;
    public static final Color DEFAULT_PADDLE1_COLOR = Color.WHITE;

    private int ySpeed;
    private int yDirection;
    private Color color;

    public Paddle(int x, int y, int width, int height, int ySpeed, Color color) {
        super(x, y, width, height); // the x and y and width and height are defined by the Rectangle constructor. x and y are defined by the parent class
        this.ySpeed = ySpeed;
        this.color = color;
    }


    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, width, height);
    }

    public void move() {
        this.y += ySpeed * yDirection;
        // check collision and uncollide after every movement with checkCollisionAndUncollide method
        checkCollisionAndUncollide();
    }

    public void checkCollisionAndUncollide() {
        // checking collision with top of Frame
        if(this.y < 0) {
            this.y = 0;
        }
        //checking collision with bottom of Frame
        if(this.y > Pong.GAME_HEIGHT - DEFAULT_PADDLE_HEIGHT) {
            this.y = Pong.GAME_HEIGHT - DEFAULT_PADDLE_HEIGHT;
        }
    }

    // setters
    public void setYDirection(int yDirection) {
        this.yDirection = yDirection;
    }
    // getters
    public int getYSpeed() {
        return this.ySpeed;
    }
}