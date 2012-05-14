package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import structures.DisjointSet;

public class DisjointSetTest {

    @Test
    public void testConnected1() {
        final DisjointSet set = new DisjointSet(1);
        assertTrue(set.isConnected(0, 0));
    }

    @Test
    public void testConnected2() {
        final DisjointSet set = new DisjointSet(4);
        set.union(0, 1);
        set.union(0, 2);
        assertTrue(set.isConnected(0, 1));
        assertTrue(set.isConnected(0, 2));
        assertFalse(set.isConnected(0, 3));
        assertFalse(set.isConnected(2, 3));
    }

    @Test
    public void testConnected3() {
        final DisjointSet set = new DisjointSet(9);
        set.union(0, 1);
        set.union(1, 2);
        set.union(2, 5);
        assertTrue(set.isConnected(1, 5));
        assertFalse(set.isConnected(7, 8));
    }

    @Test
    public void testFind() {
        final DisjointSet set = new DisjointSet(10);
        set.union(6, 3);
        set.union(6, 4);
        assertTrue(set.find(6) == 3);
        assertTrue(set.find(3) == 3);
        assertTrue(set.find(4) == 3);
        set.union(10, 7);
        set.union(10, 8);
        assertTrue(set.find(10) == 7);
        assertTrue(set.find(7) == 7);
        assertTrue(set.find(8) == 7);
    }

    @Test
    public void testFind2() {
        final DisjointSet set = new DisjointSet(9);
        set.union(65, 56);
        set.union(67, 58);
        set.union(69, 60);
        set.union(74, 65);
        set.union(75, 74);
        set.union(65, 67);
        set.union(76, 75);
        set.union(78, 69);
        assertTrue(set.find(78) == 60);
        assertTrue(set.find(69) == 60);
        assertTrue(set.find(60) == 60);
        assertTrue(set.find(58) == 56);
        assertTrue(set.find(67) == 56);
        assertTrue(set.find(76) == 56);
        assertTrue(set.find(75) == 56);
        assertTrue(set.find(74) == 56);
        assertTrue(set.find(65) == 56);
    }
}
