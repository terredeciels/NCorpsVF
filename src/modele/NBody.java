package modele;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.fill;

public class NBody {
    Vector3D origin = new Vector3D(0, 0, 0);
    int timeSteps;
    double gc;
    int bodies;
    double[] masses;
    Vector3D[] positions;
    Vector3D[] velocities;
    Vector3D[] accelerations;

    public NBody(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path);

        String[] gbt = lines.get(0).split(" ");
        gc = parseDouble(gbt[0]);
        bodies = parseInt(gbt[1]);
        timeSteps = parseInt(gbt[2]);

        masses = new double[bodies];
        positions = new Vector3D[bodies];
        fill(positions, origin);
        velocities = new Vector3D[bodies];
        fill(velocities, origin);
        accelerations = new Vector3D[bodies];
        fill(accelerations, origin);

        for (int i = 0; i < bodies; ++i) {
            masses[i] = parseDouble(lines.get(i * 3 + 1));
            positions[i] = decompose(lines.get(i * 3 + 2));
            velocities[i] = decompose(lines.get(i * 3 + 3));
        }

        System.out.printf("Contents of %s\n", fileName);
        for (String line : lines) System.out.println(line);
        System.out.println();
        System.out.print("Body   :      x          y          z    |");
        System.out.println("     vx         vy         vz");
    }

    Vector3D decompose(String line) {
        String[] xyz = line.split(" ");
        double x = parseDouble(xyz[0]);
        double y = parseDouble(xyz[1]);
        double z = parseDouble(xyz[2]);
        return new Vector3D(x, y, z);
    }

    void compute() {
        for (int i = 0; i < bodies; ++i) {
            accelerations[i] = origin;
            for (int j = 0; j < bodies; ++j) {
                if (i != j) {
                    double temp = gc * masses[j] / Math.pow((positions[i].minus(positions[j])).mod(), 3);
                    accelerations[i] = accelerations[i].plus(positions[j].minus(positions[i]).times(temp));
                }
            }
        }
        for (int i = 0; i < bodies; ++i) {
            velocities[i] = velocities[i].plus(accelerations[i]);
        }
        for (int i = 0; i < bodies; ++i) {
            positions[i] = positions[i].plus(velocities[i]).plus(accelerations[i].times(0.5));
        }
        // resolve Collisions
        for (int i = 0; i < bodies; ++i) {
            for (int j = i + 1; j < bodies; ++j) {
                if (positions[i].x == positions[j].x
                        && positions[i].y == positions[j].y
                        && positions[i].z == positions[j].z) {
                    Vector3D temp = velocities[i];
                    velocities[i] = velocities[j];
                    velocities[j] = temp;
                }
            }
        }
    }

    public void printResults() {
        String fmt = "Body %d : % 8.6f  % 8.6f  % 8.6f | % 8.6f  % 8.6f  % 8.6f\n";
        for (int i = 0; i < bodies; ++i) {
            System.out.printf(
                    fmt,
                    i + 1,
                    positions[i].x, positions[i].y, positions[i].z,
                    velocities[i].x, velocities[i].y, velocities[i].z
            );
        }
    }
}
