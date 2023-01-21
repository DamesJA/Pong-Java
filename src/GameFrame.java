import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {

    public static final String TITLE = "Pong Game";

    public GameFrame(Boolean isVisible, boolean isResizeable) {
        // calling all the methods using the frames properties
        this.setVisible(isVisible);
        this.setResizable(isResizeable);

        this.setTitle(TITLE);
    }
}

//

/*
getContentPane() called from a JFrame class (in this case GameFrame) returns the contentpane which is in the property in the JFrame instance and is of the type JRootPane.
The frame gets its JRooPane type property and then calls getContentPane() on that, invoking the getContentPane() method in the JRootFrame class which returns its contentpane property.
This property is of type Container, and is made as a JPanel of type JComponent and then set as the contentpane property value through the setContentPane() method in JRootFrame
 */