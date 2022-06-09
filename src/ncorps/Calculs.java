package ncorps;

import java.math.BigDecimal;

import static java.lang.Math.random;

public class Calculs implements Parametres {

    public final Corps[][] ncorps;

    public Calculs() {
       // initialisation();
        initialisation(0);
        ncorps = NCorpsT0;
        for (int k = 0; k < Tmax - 1; k++)
            for (int n = 0; n < 3; n++) { //corps n
                BigDecimal fx = new BigDecimal("0.0");
                BigDecimal fy = new BigDecimal("0.0");
                BigDecimal fz = new BigDecimal("0.0");
                for (int m = 0; m < 3; m++)
                    if (n != m) {

                        BigDecimal dx = ncorps[n][k].param[0].subtract(ncorps[m][k].param[0]);
                        BigDecimal dy = ncorps[n][k].param[1].subtract(ncorps[m][k].param[1]);
                        BigDecimal dz = ncorps[n][k].param[2].subtract(ncorps[m][k].param[2]);

                        // VOIR MathContext mc de pow
                        BigDecimal D = dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));

                        BigDecimal Denom = D.sqrt(mc).pow(3, mc);

                        fx = fx.add(Gm.multiply(dx.divide(Denom, scale, rnd)));
                        fy = fy.add(Gm.multiply(dy.divide(Denom, scale, rnd)));
                        fz = fz.add(Gm.multiply(dz.divide(Denom, scale, rnd)));

                        BigDecimal[] param = new BigDecimal[6];
                        param[0] = (new BigDecimal("0.5")).multiply(fx.multiply(pas.pow(2)))
                                .add((ncorps[n][k].param[3]).multiply(pas)).add(ncorps[n][k].param[0]);
                        param[1] = (new BigDecimal("0.5")).multiply(fy.multiply(pas.pow(2)))
                                .add((ncorps[n][k].param[4]).multiply(pas)).add(ncorps[n][k].param[1]);
                        param[2] = (new BigDecimal("0.5")).multiply(fz.multiply(pas.pow(2)))
                                .add((ncorps[n][k].param[5]).multiply(pas)).add(ncorps[n][k].param[2]);

                        param[3] = fx.multiply(pas).add(ncorps[n][k].param[3]);
                        param[4] = fy.multiply(pas).add(ncorps[n][k].param[4]);
                        param[5] = fz.multiply(pas).add(ncorps[n][k].param[5]);


                        Corps corps = new Corps();
                        corps.param = param;
                        ncorps[n][k + 1] = corps;
                    }
            }
    }

    //
//            484;542;91
//            319;372;161
//            79;445;30
    //
    private void initialisation(int i) {
        NCorpsT0[0][0]=new Corps();
        NCorpsT0[0][0].param[0] = BigDecimal.valueOf(684);
        NCorpsT0[0][0].param[1] = BigDecimal.valueOf(542);
        NCorpsT0[0][0].param[2] = BigDecimal.valueOf(91);
        NCorpsT0[1][0]=new Corps();
        NCorpsT0[1][0].param[0] = BigDecimal.valueOf(319);
        NCorpsT0[1][0].param[1] = BigDecimal.valueOf(372);
        NCorpsT0[1][0].param[2] = BigDecimal.valueOf(161);
        NCorpsT0[2][0]=new Corps();
        NCorpsT0[2][0].param[0] = BigDecimal.valueOf(79);
        NCorpsT0[2][0].param[1] = BigDecimal.valueOf(445);
        NCorpsT0[2][0].param[2] = BigDecimal.valueOf(30);
        for (int c = 3; c < 6; c++)
            for (int n = 0; n < NbCorps; n++) NCorpsT0[n][0].param[c] = new BigDecimal(0);

    }

    private void initialisation() {
        for (int n = 0; n < NbCorps; n++) {
            Corps corps = new Corps();
            BigDecimal[] param = new BigDecimal[6];
            // coord aléatoires, vitesses nulles
            for (int c = 0; c < 3; c++) param[c] = new BigDecimal(random() * DimXYZ);
            System.out.println(param[0].intValue() + ";" + param[1].intValue() + ";" + param[2].intValue());
            for (int c = 3; c < 6; c++) param[c] = new BigDecimal(0);
            corps.param = param;
            NCorpsT0[n][0] = corps;
        }
    }

    public static class Corps {

        public BigDecimal[] param;

        public Corps() {
            param = new BigDecimal[6];
        }

    }
}

