package modele;

import java.io.IOException;

public class NBodySim {

    public NBodySim() throws IOException {
        String filename = "nbody.txt";
        NBody nb = new NBody(filename);
        for (int i = 0; i < nb.timeSteps; ++i) {
            System.out.printf("\nCycle %s\n", i + 1);
            nb.compute();
            nb.printResults();
        }
    }

}