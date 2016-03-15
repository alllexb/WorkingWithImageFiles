package tasks.pictureparser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Allexb.
 */
public class ImageWriter {

    public void writeByteImageInterpretation(byte[] byteImage, int imageWidth, int imageHeight, String fileName, String imageType) {
        if (imageType.equals("bmp") || imageType.equals("jpg") || imageType.equals("gif") || imageType.equals("png") || imageType.equals("wbmp"))
        {
            try {
                final String packagePath = "\\src\\main\\resources\\";
                final File imageFile = new File(Paths.get("").toRealPath() + packagePath + "result\\" + fileName);
                final BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_3BYTE_BGR);
                image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferByte(byteImage, byteImage.length), new Point()));
                ImageIO.write(image, imageType, imageFile);
                System.out.println("Writing has been completed correct!");
            } catch (IOException e) {
                System.out.println("Writing file error!");
            }
        } else {
            System.out.println("Wrong image format!");
        }
    }


    public byte[] getByteArray(int[][] inPixels, int pixelLength) {
        final byte[] resultByte = new byte[pixelLength*inPixels.length*inPixels[0].length];
        if ((inPixels == null) && (pixelLength == 0)) return null;
        int index = 0;
        for(int i = 0; i < inPixels.length; i++) {
            for (int j = 0; j < inPixels[i].length; j++) {
                for (int k = 0; k < pixelLength; k++) {
                    resultByte[index] = (byte)(-inPixels[i][j]);
                    index++;
                }
            }
        }
        return resultByte;
    }

}
