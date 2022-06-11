package modele;

import static java.lang.Math.sqrt;

public class Vector3D {
    public double x;
    public double y;
    public double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D plus(Vector3D rhs) {
        return new Vector3D(x + rhs.x, y + rhs.y, z + rhs.z);
    }

    public Vector3D minus(Vector3D rhs) {
        return new Vector3D(x - rhs.x, y - rhs.y, z - rhs.z);
    }

    public Vector3D times(double s) {
        return new Vector3D(s * x, s * y, s * z);
    }

    public double mod() {
        return sqrt(x * x + y * y + z * z);
    }
}
