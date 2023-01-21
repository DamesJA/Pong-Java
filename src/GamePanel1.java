import javax.swing.*;
import java.awt.*;

public class GamePanel1 extends JPanel {
    public GamePanel1() {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(100, 0, 50, 50);
    }
}
