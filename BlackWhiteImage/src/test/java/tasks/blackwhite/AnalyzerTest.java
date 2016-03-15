package tasks.blackwhite;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnalyzerTest {

    @Test
    public void testIsWhite() throws Exception {
        final int[][] pixels1 = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}};
        final Analyzer analyzer1 = new Analyzer(pixels1);
        assertTrue(analyzer1.isWhite());
        int[][] pixels2 = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 1}};
        final Analyzer analyzer2 = new Analyzer(pixels2);
        assertFalse(analyzer2.isWhite());
    }

    @Test
    public void testIsBlack() throws Exception {
        final int[][] pixels1 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        final Analyzer analyzer1 = new Analyzer(pixels1);
        assertTrue(analyzer1.isBlack());
        int[][] pixels2 = {
                {0, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 0, 0, 0},
                {0, 1, 0, 1}};
        final Analyzer analyzer2 = new Analyzer(pixels2);
        assertFalse(analyzer2.isBlack());
    }

    @Test
    public void testRemoveFigure() throws Exception {
        final int[][] in1 = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1}};
        final int[][] out1 = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}};

        final Analyzer analyzer1 = new Analyzer(in1);
        analyzer1.removeFigure();
        assertArrayEquals(out1, analyzer1.getPixels());

        final int[][] in2 = {
                {0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 1}};
        final int[][] out2 = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 1}};

        final Analyzer analyzer2 = new Analyzer(in2);
        analyzer2.removeFigure();
        assertArrayEquals(out2, analyzer2.getPixels());

        final int[][] in3 = {{1}};
        final int[][] out3 = {{1}};

        final Analyzer analyzer3 = new Analyzer(in3);
        analyzer3.removeFigure();
        assertArrayEquals(out3, analyzer3.getPixels());

        final int[][] in4 = {{0}};
        final int[][] out4 = {{1}};

        final Analyzer analyzer4 = new Analyzer(in4);
        analyzer4.removeFigure();
        assertArrayEquals(out4, analyzer4.getPixels());

        final int[][] in5 = null;
        final Analyzer analyzer5 = new Analyzer(in5);
        analyzer5.removeFigure();
        assertNull(analyzer5.getPixels());

    }

    @Test
    public void testGetPicCount() throws Exception {
        final int[][] in1 = {
                {0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 1}};
        final int count1 = 2;

        final Analyzer analyzer1 = new Analyzer(in1);
        analyzer1.Count();
        assertEquals(count1, analyzer1.getPicCount());

        final int[][] in2 = {
                {0, 1, 1, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 1, 0, 1, 1},
                {1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0}};
        final int count2 = 1;

        final Analyzer analyzer2 = new Analyzer(in2);
        analyzer2.Count();
        assertEquals(count2, analyzer2.getPicCount());

        final int[][] in3 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};
        final int count3 = 1;

        final Analyzer analyzer3 = new Analyzer(in3);
        analyzer3.Count();
        assertEquals(count3, analyzer3.getPicCount());

        final int[][] in4 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}};
        final int count4 = 0;

        final Analyzer analyzer4 = new Analyzer(in4);
        analyzer4.Count();
        assertEquals(count4, analyzer4.getPicCount());

        final int[][] in5 = null;
        final int count5 = 0;

        final Analyzer analyzer5 = new Analyzer(in5);
        analyzer5.Count();
        assertEquals(count5, analyzer5.getPicCount());
    }
}