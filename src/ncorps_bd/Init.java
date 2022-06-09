package ncorps_bd;

import java.math.BigDecimal;

import static java.lang.Math.random;

public class Init implements Parametres{
//            470;454;51
//            359;448;145
//            518;296;51
    void initialisation(int i) {
        //
//            484;542;91
//            319;372;161
//            79;445;30
        //
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

    void initialisation() {
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

}
