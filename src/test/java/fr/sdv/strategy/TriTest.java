package fr.sdv.strategy;

import org.junit.Test;
import static org.junit.Assert.*;

public class TriTest {

    @Test
    public void testBulleSort() {
        Integer[] arr = {12, -5, 7, 0, 8, 4, -3, 9, 15};
        Tri tri = new Tri();
        tri.setStrategy(StrategyFactory.getInstance(TypeTri.BULLE));
        tri.exec(arr);

        assertArrayEquals(new Integer[]{-5, -3, 0, 4, 7, 8, 9, 12, 15}, arr);
    }

    @Test
    public void testInsertionSort() {
        Integer[] arr = {12, -5, 7, 0, 8, 4, -3, 9, 15};
        Tri tri = new Tri();
        tri.setStrategy(StrategyFactory.getInstance(TypeTri.INSERTION));
        tri.exec(arr);

        assertArrayEquals(new Integer[]{-5, -3, 0, 4, 7, 8, 9, 12, 15}, arr);
    }

    @Test
    public void testSelectionSort() {
        Integer[] arr = {12, -5, 7, 0, 8, 4, -3, 9, 15};
        Tri tri = new Tri();
        tri.setStrategy(StrategyFactory.getInstance(TypeTri.SELECTION));
        tri.exec(arr);

        assertArrayEquals(new Integer[]{-5, -3, 0, 4, 7, 8, 9, 12, 15}, arr);
    }

    @Test(expected = IllegalStateException.class)
    public void testAucuneStrategie() {
        new Tri().exec(new Integer[]{1, 2, 3});
    }
}
