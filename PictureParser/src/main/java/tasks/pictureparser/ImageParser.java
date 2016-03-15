package tasks.pictureparser;

/**
 * Created by Allexb.
 */

public class ImageParser {
    public static void main(String[] args) {
        String imageFileName = "image_test_small.bmp";
        String imageType = imageFileName.substring(imageFileName.lastIndexOf('.') + 1);
        ImageLoader loader = new ImageLoader(imageFileName);
        FileNameGenerator generator = new FileNameGenerator(imageFileName);
        if (loader.isImageLoad()) {
            Analyzer analyzer = new Analyzer(loader.getBinaryInterpretation());
            ImageWriter writer = new ImageWriter();
            while (!analyzer.isWhite()) {
                byte[] image = writer.getByteArray(analyzer.extractFigure(), loader.getPixelLength());
                writer.writeByteImageInterpretation(image, loader.getWidth(), loader.getHeight(), generator.nextName(), imageType);
            }
        }
    }
}
