package tasks.blackwhite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Allexb.
 */

public class ImageLoader {

    private File ImageFile = null;
    private BufferedImage img = null;

    public ImageLoader(String fileName) {
        try {
            String packagePath = "\\src\\main\\resources\\blackwhiteimage\\";
            ImageFile = new File(Paths.get("").toRealPath() + packagePath + fileName);
            img = ImageIO.read(ImageFile);
        } catch (IOException e) {
            System.out.println("Image not found!");
        }
    }

    public boolean isImageLoad() {
        return img != null;
    }

    public int[][] getBinaryInterpretation() {
        final byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        final int width = img.getWidth();
        final int height = img.getHeight();
        final int pixelLength = pixels.length/width/height;
        final int [][] byteResult = new int[height][width];
        int col = 0;
        int row = 0;
        for (int i = 0; i < pixels.length; i+=pixelLength) {
            int onePixel = 0;
            for (int j = 0; j < pixelLength; j++){
                onePixel = onePixel + (int)pixels[i + j];
            }
            if (onePixel == -pixelLength)       //check for black color
            {
                byteResult[row][col] = 1;
            } else {
                byteResult[row][col] = 0;
            }
            col++;
            if (col == width) {
                col = 0;
                row++;
            }
        }
        return byteResult;
    }

}
