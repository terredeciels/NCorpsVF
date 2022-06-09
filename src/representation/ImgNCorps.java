package representation;



import ncorps.Calculs;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static ncorps.Parametres.Tmax;


public class ImgNCorps extends Canvas {
    private final Calculs calculs;
    int WIDTH = 600;
    int HEIGHT = 600;

    public ImgNCorps() {
        calculs = new Calculs();
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
                BigDecimal X = calculs.ncorps[n][t].param[0];
                BigDecimal Y = calculs.ncorps[n][t].param[1];
                int x = X.intValue();
                int y = Y.intValue();
                g.drawOval(x, y, 2, 2);
            }
        }
    }


}