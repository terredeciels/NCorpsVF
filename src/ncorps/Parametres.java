package ncorps;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public interface Parametres {
    int NbCorps = 3;

    int Tmax = 1000;
    int DimXYZ = 600;
    BigDecimal Gm = new BigDecimal("-1.0");
    BigDecimal pas = new BigDecimal("10");

    int scale = 12;
    int precision = 12;
    MathContext mc = new MathContext(precision);
    RoundingMode rnd = RoundingMode.HALF_DOWN;

    Calculs.Corps[][] NCorpsT0 = new Calculs.Corps[3][Tmax];

    String pathname = "C:\\Users\\gille\\IdeaProjects\\3 Corps V2\\";
    String filename1 = "3corpsX";
    String filename2 = "CorpsN";

}
