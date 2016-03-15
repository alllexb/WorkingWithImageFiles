package tasks.imagecomparison;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Allexb.
 */

public class ImageLoader {
    private File ImageFile = null;
    private BufferedImage img = null;
    private int width = 0;
    private int height = 0;

    public ImageLoader(String fileName) {
        try {
            String packagePath = "\\src\\main\\resources\\";
            this.ImageFile = new File(Paths.get("").toRealPath() + packagePath + fileName);
            this.img = ImageIO.read(ImageFile);
            this.width = img != null ? img.getWidth():0;
            this.height = img != null ? img.getHeight():0;
        } catch (IOException e) {
            System.out.println("Image not found!");
        }
    }

    public boolean isImageLoad() {
        return img != null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getPixelArray() {
        if (img == null) {
            throw new NullPointerException();
        }

        width = img.getWidth();
        height = img.getHeight();

        final int[][] resultArray = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                resultArray[i][j] = img.getRGB(j, i);
            }
        }

        return resultArray;
    }

    public boolean comparableWith (ImageLoader loader) {
        if (!(loader.isImageLoad() || this.isImageLoad())) {
            return false;
        }
        if (this.getHeight() == loader.getHeight() && this.getWidth() == loader.getWidth()) {
            return true;
        }
        return false;
    }

}
