package ncorps_double;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static ncorps_bd.Parametres.Tmax;


public class ImgNCorpsDb extends Canvas {
    private final CalculsDb calculs;
    int WIDTH = 600;
    int HEIGHT = 600;

    public ImgNCorpsDb() {
        calculs = new CalculsDb();
        JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.add(this);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color[] tCoul = {new Color(0, 0, 0), new Color(221, 27, 27), new Color(46, 62, 169)};
//        for (int x = 0; x < WIDTH; x += 20) {
//            for (int y = 0; y < HEIGHT; y += 20) {
//                g.setColor(coul);
//                g.drawOval(x, y, 5, 5);
//            }
//        }

        for (int n = 0; n < 3; n++) {
            for (int t = 0; t < Tmax; t++) {
                g.setColor(tCoul[n]);
                double X = calculs.ncorps[n][t].p[0];
                double Y = calculs.ncorps[n][t].p[1];
                int x = (int) X;
                int y = (int) Y;
                g.drawOval(x, y, 2, 2);
            }
        }
    }


}