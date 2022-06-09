package ncorps_double;

import static java.lang.Math.*;
import static java.util.stream.IntStream.range;

public class CalculsDb implements ParametresDb {

    public final CorpsDb[][] ncorps;

    public CalculsDb() {
        initialisation();
        ncorps = NCorpsT0;

        range(0, Tmax - 1).forEach(k -> {
            range(0, NbCorps).forEach(n -> {


                range(0,NbCorps).forEach(m-> {

                    double fx = 0.0;
                    double fy = 0.0;
                    double fz = 0.0;

                    if (n != m) {

                        CorpsDb cn = ncorps[n][k];
                        CorpsDb cm = ncorps[m][k];

                        double dx = cn.param[0] - cm.param[0];
                        double dy = cn.param[1] - cm.param[1];
                        double dz = cn.param[2] - cm.param[2];

                        double Denom = pow(sqrt(dx * dx + dy * dy + dz * dz), 3);

                        fx = fx + Gm * dx / Denom;
                        fy = fy + Gm * dy / Denom;
                        fz = fz + Gm * dz / Denom;

                        double[] param = new double[6];
                        param[0] = 0.5 * fx * pow(pas, 2) + cn.param[3] * pas + cn.param[0];
                        param[1] = 0.5 * fy * pow(pas, 2) + cn.param[4] * pas + cn.param[1];
                        param[2] = 0.5 * fz * pow(pas, 2) + cn.param[5] * pas + cn.param[2];

                        param[3] = fx * pas + cn.param[3];
                        param[4] = fy * pas + cn.param[4];
                        param[5] = fz * pas + cn.param[5];

                        CorpsDb corps = new CorpsDb();
                        corps.param = param;
                        ncorps[n][k + 1] = corps;
                    }

                } );

                for (int m = 0; m < NbCorps; m++) {


                }

            });

        });
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
            corps.param = param;
            NCorpsT0[n][0] = corps;
        }
    }


}

