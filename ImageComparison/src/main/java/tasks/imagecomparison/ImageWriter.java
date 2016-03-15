package tasks.imagecomparison;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Allexb.
 */

public class ImageWriter {

    public void writeImage(int[][] imageArray , String fileName, String imageType) {
        if (imageType.equals("bmp") || imageType.equals("jpg") || imageType.equals("gif") || imageType.equals("png") || imageType.equals("wbmp"))
        {
            try {
                final String packagePath = "\\src\\main\\resources\\";
                final File imageFile = new File(Paths.get("").toRealPath() + packagePath + "result\\" + fileName);
                final BufferedImage image = new BufferedImage(imageArray[0].length, imageArray.length, BufferedImage.TYPE_INT_RGB);
                for (int i = 0; i < imageArray.length; i++) {
                    for(int j = 0; j < imageArray[i].length; j++) {
                        image.setRGB(j, i, imageArray[i][j]);
                    }
                }
                ImageIO.write(image, imageType, imageFile);
                System.out.println("Writing has been completed correct!");
            } catch (IOException e) {
                System.out.println("Writing file error!");
            }
        } else {
            System.out.println("Wrong image format!");
        }
    }
}
