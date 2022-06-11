package ncorps_double;

import static java.lang.Math.*;
import static java.util.stream.IntStream.range;

public class CalculsDb implements ParametresDb {

    public final CorpsDb[][] ncorps;

    public CalculsDb() {
        initialisation();
        ncorps = NCorpsT0;

        range(0, Tmax - 1).forEach(k -> range(0, NbCorps).forEach(n -> range(0, NbCorps)
                .filter(m -> n != m).forEach(m -> {

                    double[] f = new double[3];
                    CorpsDb cn = ncorps[n][k];
                    CorpsDb cm = ncorps[m][k];

                    double denom = pow(sqrt(pow(cn.p[0] - cm.p[0], 2) + pow(cn.p[1] - cm.p[1], 2)
                            + pow(cn.p[2] - cm.p[2], 2)), 3);

                    range(0, 3).forEach(i -> f[i] += Gm * (cn.p[i] - cm.p[i]) / denom);

                    ncorps[n][k + 1] = new CorpsDb();
                    range(0, 3).forEach(i -> ncorps[n][k + 1].p[i] = 0.5 * f[i] * pow(pas, 2)
                            + cn.p[i + 3] * pas + cn.p[i]);
                    range(0, 3).forEach(i -> ncorps[n][k + 1].p[i + 3] = f[i] * pas + cn.p[i + 3]);
                })));
    }

    //            122;106;494
//            560;189;271
//            239;450;237
    private void initialisation() {
        for (int n = 0; n < NbCorps; n++) {
            CorpsDb corps = new CorpsDb();
            double[] param = new double[6];
            // coord aléatoires, vitesses nulles
            for (int c = 0; c < 3; c++) param[c] = random() * DimXYZ;
            for (int c = 3; c < 6; c++) param[c] = 0;
            corps.p = param;
            NCorpsT0[n][0] = corps;
        }
    }


}

