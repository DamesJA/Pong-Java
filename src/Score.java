import java.awt.*;

public class Score extends Rectangle {

    //default values for score1 and score2
    public static final int DEFAULT_SCORE_SPACE_FROM_CENTER = 150;
    public static final int DEFAULT_SCORE_SPACE_FROM_TOP = 10;

    // default values for score1
    public static final Font DEFAULT_SCORE1_FONT = new Font("Arial", Font.PLAIN, 60);
    public static final int DEFAULT_SCORE1_STARTING_SCORE = 0;
    public static final int DEFAULT_SCORE1_X = (Pong.GAME_WIDTH / 2) - Score.DEFAULT_SCORE_SPACE_FROM_CENTER;
    public static final int DEFAULT_SCORE1_Y = DEFAULT_SCORE_SPACE_FROM_TOP;
    public static final Color DEFAULT_SCORE1_COLOR = Color.WHITE;

    // default values for score2
    public static final Font DEFAULT_SCORE2_FONT = new Font("Arial", Font.PLAIN, 60);
    public static final int DEFAULT_SCORE2_STARTING_SCORE = 0;
    public static final int DEFAULT_SCORE2_X = (Pong.GAME_WIDTH / 2) + Score.DEFAULT_SCORE_SPACE_FROM_CENTER;
    public static final int DEFAULT_SCORE2_Y = DEFAULT_SCORE_SPACE_FROM_TOP;
    public static final Color DEFAULT_SCORE2_COLOR = Color.WHITE;


    private Color color;
    private Font font;
    private int widthOfFontBasedOnScore;
    private int heightOfFont;
    private int score;

    public Score(int x, int y, Color color, Font font, int startingScore) {
        // starting the rectangle at it's x and y but 0 for width and 0 for height until the first draw is called
        // this is because it's width and height will depend on the font so that I can keep the rectangle tightly wrapped around the font
        super(x, y, 0, 0);
        this.color = color;
        this.font = font;
        this.score = startingScore;
    }

    public void draw(Graphics g) {
        // NOTE: strings draw from the bottom left

        // we give variables to the width and height of the font based on the current score and font one every draw because this score can change.
        this.widthOfFontBasedOnScore = widthOfFontBasedOnScore(this.font, g, String.valueOf(this.score));
        this.heightOfFont = heightOfFont(font, g);

        // setting the width and height of this Score instance (extends Rectangle) based the Font and a string(the score of this instance of Score) and in order to do this I need access to the Graphics class instance that is being used and figured it would be best to do this through draw method
        // these will change every time the font changes width or height based on the score change
        if(this.width != this.widthOfFontBasedOnScore || this.height != this.heightOfFont) {
            this.width = this.widthOfFontBasedOnScore;
            this.height = this.heightOfFont;
        }

        // we are not able to define the paddle's width's as constants because they change based on the font and current score
        // therefore need to subtract the width from paddle1's x value as this will place it in the correct place
        if(this.x == Score.DEFAULT_SCORE1_X) {
            this.x -= this.width;
        }

        // drawing the string(score) in the middle of the rectangle
        Score.drawStringInMiddleOfRectnagle(g, font, String.valueOf(this.score), this, this.color);
    }

    public static void drawStringInMiddleOfRectnagle(Graphics g, Font font, String string, Rectangle rect, Color color) {
        FontMetrics fontMetrics = g.getFontMetrics(font);

        int StringWidthWithFont = fontMetrics.stringWidth(string);
        int StringHeightWithFont = fontMetrics.getHeight();

        int xValueOfString = rect.x + ((int)rect.getWidth() / 2) - (StringWidthWithFont / 2);
        int yValueOfString = rect.y + ((int)rect.getHeight() / 2) + (StringHeightWithFont / 2);

        g.setColor(color);
        g.setFont(font);
        g.drawString(string, xValueOfString, yValueOfString);
    }

    public int widthOfFontBasedOnScore(Font font, Graphics g, String string) {
        FontMetrics fontMetrics = g.getFontMetrics(font);
        return fontMetrics.stringWidth(string);
    }
    public int heightOfFont(Font font, Graphics g) {
        FontMetrics fontMetrics = g.getFontMetrics(font);
        return fontMetrics.getHeight();
    }

    public void addToScore(int addAmount) {
        this.score += addAmount;
    }

    // setters
    // getters
}
