package representation;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JPanelimageModel extends Canvas {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final Random random = new Random();

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.add(new JPanelimageModel());

        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int x = 0; x < WIDTH; x += 20) {
            for (int y = 0; y < HEIGHT; y += 20) {
                g.setColor(randomColor());
                //g.drawLine(x, y, x, y);
                g.drawOval(x, y, 5, 5);
            }
        }
    }

    private Color randomColor() {
        return new Color(0, 0, 0);
//        return new Color(random.nextInt(256),
//                random.nextInt(256),
//                random.nextInt(256));
    }
}