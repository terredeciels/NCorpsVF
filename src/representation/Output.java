package representation;

import ncorps.Calculs;
import ncorps.Parametres;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import static java.awt.image.Raster.createInterleavedRaster;

public class Output implements Parametres {

    private final boolean[][] coord;
    private final Calculs calculs;
    private WriteToFile W;

    public Output() throws IOException {
        calculs = new Calculs();

        for (int ncoord = 0; ncoord < NbCorps; ncoord++) {
            W = new WriteToFile(pathname, filename1 + ncoord + ".txt");
            toFile(ncoord);
            W.writter.close();
        }
        coord = new boolean[DimXYZ][DimXYZ];
        for (int k = 0; k < Tmax; k++) {
            convert3DTo2DToInt(k);
        }
        for (int numcorps = 0; numcorps < NbCorps; numcorps++) {
            W = new WriteToFile(pathname, filename2 + numcorps + ".txt");
            toFile2(numcorps);
            W.writter.close();
        }
        image(preimg());
    }

    void toFile(int ncoord) throws IOException {
        Calculs.Corps[][] I = calculs.ncorps;
        for (int t = 0; t < Tmax; t++) {
            for (int n = 0; n < NbCorps; n++) {
                W.write(I[n][t].param[ncoord]);
                W.write(";");
            }
            W.write("\n");
        }
    }

    void toFile2(int numcorps) throws IOException {
        Calculs.Corps[][] I = calculs.ncorps;
        for (int t = 0; t < Tmax; t++) {
            for (int c = 0; c < 3; c++) {
                W.write(I[numcorps][t].param[c]);
                if (c != 2) W.write(";");
            }
            W.write("\n");
        }
    }

    void convert3DTo2DToInt(int k) {
        Calculs.Corps[][] I = calculs.ncorps;

        for (int n = 0; n < NbCorps; n++) {
            BigDecimal[] param = I[n][k].param;
            int X = param[0].intValue();
            int Y = param[1].intValue();
            System.out.print(X);
            System.out.print(" ; ");
            System.out.print(Y);
            System.out.println(" ; ");
            if (X < 100 && Y < 100 && X > 0 && Y > 0) coord[X][Y] = true;

        }

    }

    byte[] preimg() {
        byte[] preimg = new byte[3 * DimXYZ * DimXYZ];
        int k = 0;
        for (int i = 0; i < DimXYZ; i++) {
            for (int j = 0; j < DimXYZ; j++) {
                if (!coord[i][j]) {
                    preimg[k] = (byte) 0xffff;
                    preimg[k++] = (byte) 0xffff;
                    preimg[k++] = (byte) 0xffff;
                } else {
                    preimg[k] = 0x0;
                    preimg[k++] = 0x0;
                    preimg[k++] = 0x0;
                }
                k++;
            }

        }
        return preimg;
    }

    void image(byte[] preimg) throws IOException {
        int width = DimXYZ;

        DataBuffer buffer = new DataBufferByte(preimg, preimg.length);
        //3 bytes per pixel: red, green, blue
        WritableRaster raster = createInterleavedRaster(buffer, width, DimXYZ,
                3 * width, 3, new int[]{0, 1, 2}, null);
        ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false,
                true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        BufferedImage image = new BufferedImage(cm, raster, true, null);
        ImageIO.write(image, "png", new File("image.png"));
        // ImageIO.write(image, "png", new File("image.png"));
    }


    public static class WriteToFile {

        public final FileWriter writter;

        public WriteToFile(String pathname, String filename) throws IOException {
            File toFile = new File(pathname + filename);
            boolean existe = toFile.createNewFile();
            if (existe) System.out.println("fichier existe");
            writter = new FileWriter(filename);
        }

        public void write(BigDecimal bd) throws IOException {
            writter.write(String.valueOf(bd));
        }

        public void write(String s) throws IOException {
            writter.write(s);
        }

    }

}
