package tasks.blackwhite;

import java.util.ArrayList;

/**
 * Created by Allexb.
 */

public class Analyzer /** implements Runnable */ {

    private int pixels[][];
    private int picCount = 0;

    public Analyzer(int[][] pixels) {
        picCount = 0;
        this.pixels = pixels;
    }

    public boolean isWhite() {
        if (pixels == null) return false;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean isBlack() {
        if (pixels == null) return false;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (!(pixels[i][j] == 0)) return false;
            }
        }
        return true;
    }

    public void removeFigure(){
        if (pixels == null) return;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j] == 0) {
                    pointAreaClean(i,j);
                    return;
                }
            }
        }

    }

    private void pointAreaClean(int pointX, int pointY) {

        ArrayList<Pixel> blackPixels= new ArrayList<Pixel>();
        blackPixels.add(new Pixel(pointX, pointY));
        pixels[pointX][pointY] = 1;
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
                        if (!((analizX + x < 0) || (analizX + x >= pixels.length) || (analizY + y < 0) || (analizY + y >= pixels[0].length))) {
                            if (pixels[analizX + x][analizY + y] == 0) {
                                Pixel analizPixel = new Pixel(analizX + x, analizY + y);
                                if (!blackPixels.contains(analizPixel)) {
                                    blackPixels.add(analizPixel);
                                    pixels[analizX + x][analizY + y] = 1;
                                }
                            }
                        }
                        }
                    }
            }
        } while (size != blackPixels.size());
    }

    public int getPicCount() {
        return picCount;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public void Count() {
        if (pixels == null) return;
        if (isBlack()) {
            picCount = 1;
            return;
        }
        while (!isWhite()) {
            removeFigure();
            picCount++;
        }
    }

//    @Override
//    public void run() {
//        this.Count();
//    }
}
