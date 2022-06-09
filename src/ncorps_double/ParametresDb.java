package ncorps_double;

public interface ParametresDb {
    int NbCorps = 3;
    int Tmax = 2000;
    CorpsDb[][] NCorpsT0 = new CorpsDb[NbCorps][Tmax];
    double Gm = -1.0;
    double pas = 20;

    int DimXYZ = 600;
}
