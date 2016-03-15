package tasks.imagecomparison;

/**
 * Created by Allexb.
 */

public class ImageComparison {
    public static void main(String[] args) {
        String imageInFileName = "image1.png";
        String imageCompareFileName = "image2.png";
        String imageType = imageInFileName.substring(imageInFileName.lastIndexOf('.') + 1);
        ImageLoader loaderIn = new ImageLoader(imageInFileName);
        ImageLoader loaderCompare = new ImageLoader(imageCompareFileName);
        if (loaderIn.comparableWith(loaderCompare)) {
            Analyzer analyzer = new Analyzer(loaderIn.getPixelArray(), loaderCompare.getPixelArray());
            ImageWriter writer = new ImageWriter();
            writer.writeImage(analyzer.markDifferences(),"image_result" + "." + imageType, imageType);
        }
    }
}
