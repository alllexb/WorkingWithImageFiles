package tasks.blackwhite;

/**
 * Created by Allexb.
 */
public class BlackAndWhite {

    public static void main(String[] args) {
        final ImageLoader loader = new ImageLoader("pngSmallImage2.png");
        if (loader.isImageLoad()) {
            final Analyzer analyzer = new Analyzer(loader.getBinaryInterpretation());
            analyzer.Count();
            final int picCount = analyzer.getPicCount();
            System.out.println("Black picture count: " + picCount);
        }
    }
}
