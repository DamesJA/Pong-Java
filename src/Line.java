import java.awt.*;

public class Line extends Rectangle {

    // static values for middle line
    public static final int MIDDLE_LINE_WIDTH = 3;
    public static final int MIDDLE_LINE_HEIGHT = Pong.GAME_HEIGHT;
    public static final int MIDDLE_LINE_X = (Pong.GAME_WIDTH / 2) - (MIDDLE_LINE_WIDTH / 2);
    public static final int MIDDLE_LINE_Y = 0;
    public static final Color MIDDLE_LINE_COLOR = Color.WHITE;

    private Color color;

    public Line(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    public void drawDottedLineDownScreen(Graphics g) {
        int heightOfFilledInSpot = this.width * 2;
        for(int currY = 0; currY <= MIDDLE_LINE_HEIGHT; currY += heightOfFilledInSpot) {
            if(currY % (heightOfFilledInSpot * 2) == 0) {
                g.setColor(this.color);
                g.fillRect(this.x, currY, this.width, heightOfFilledInSpot);
            }
        }
    }
}
