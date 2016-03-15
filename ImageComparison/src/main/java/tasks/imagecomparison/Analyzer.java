package tasks.imagecomparison;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Allexb.
 */

public class Analyzer {

    private int inPixels[][];
    private int comparePixels[][];
    private ArrayList<Pixel> extract = null;

    public Analyzer(int[][] inPixels, int[][] comparePixels) {
        this.inPixels = inPixels;
        this.comparePixels = comparePixels;
    }

    public boolean isSame() {
        for (int i = 0; i < inPixels.length; i++) {
            for (int j = 0; j < inPixels[0].length; j++) {
                if (inPixels[i][j] != comparePixels[i][j]) {
                    return false;
                };
            }
        }
        return true;
    }

    public int[][] markDifferences() {
        final int[][] figure = new int[inPixels.length][inPixels[0].length];

        for (int i = 0; i < inPixels.length; i++ ) {
            for (int j = 0; j < inPixels[0].length; j++) {
                figure[i][j] = comparePixels[i][j];
            }
        }

        while (!isSame()) {
            removeFigure();
            int minX = extract.get(0).getX();
            int maxX = extract.get(0).getX();
            int minY = extract.get(0).getY();
            int maxY = extract.get(0).getY();
            for (Pixel pixel : extract) {
                if (minX > pixel.getX()) minX = pixel.getX();
                if (maxX < pixel.getX()) maxX = pixel.getX();
                if (minY > pixel.getY()) minY = pixel.getY();
                if (maxY < pixel.getY()) maxY = pixel.getY();
            }
            for (int i = minX - 1; i <= maxX + 1; i++) {
                if (!((i < 0) || (i >= figure.length) || (minY - 1 < 0) || (maxY + 1 >= figure[0].length))) {
                    figure[i][minY - 1] = Color.RED.getRGB();
                    figure[i][maxY + 1] = Color.RED.getRGB();
                }
            }
            for (int j = minY - 1; j <= maxY + 1; j++) {
                if (!((minX - 1 < 0) || (maxX + 1 >= figure.length) || (j < 0) || (j >= figure[0].length))) {
                    figure[minX - 1][j] = Color.RED.getRGB();
                    figure[maxX + 1][j] = Color.RED.getRGB();
                }
            }
            System.out.println("Figure marked!");
        }
        return figure;
    }

    public void removeFigure(){
        if (comparePixels == null) return;
        for (int i = 0; i < comparePixels.length; i++) {
            for (int j = 0; j < comparePixels[i].length; j++) {
                if (comparePixels[i][j] != inPixels[i][j]) {
                    pointAreaClean(i,j);
                    return;
                }
            }
        }

    }

    private void pointAreaClean(int pointX, int pointY) {
        ArrayList<Pixel> blackPixels = new ArrayList<Pixel>();
        blackPixels.add(new Pixel(pointX, pointY));
        comparePixels[pointX][pointY] = inPixels[pointX][pointY];
        int size = 0;
        do {
            size = blackPixels.size();
            for (int i = 0; i < size; i++)
            {
                Pixel pixel = blackPixels.get(i);
                int analizX = pixel.getX();
                int analizY = pixel.getY();
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++ ) {
                        if (!((analizX + x < 0) || (analizX + x >= comparePixels.length) || (analizY + y < 0) || (analizY + y >= comparePixels[0].length))) {
                            if (comparePixels[analizX + x][analizY + y] != inPixels[analizX + x][analizY + y]) {
                                Pixel analizPixel = new Pixel(analizX + x, analizY + y);
                                if (!blackPixels.contains(analizPixel)) {
                                    blackPixels.add(analizPixel);
                                    comparePixels[analizX + x][analizY + y] = inPixels[analizX + x][analizY + y];
                                }
                            }
                        }
                    }
                }
            }
        } while (size != blackPixels.size());
        this.extract = blackPixels;
    }
}
