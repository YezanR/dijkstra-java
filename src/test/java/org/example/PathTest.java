package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathTest {

    @Test
    public void equals_GivenTwoEqualPaths_ReturnsTrue() {
        Path path1 = new Path(new Node("A"), new Node("B"));
        Path path2 = new Path(new Node("A"), new Node("B"));

        assertEquals(path1, path2);
    }

    @Test
    public void equals_GivenTwoDifferentPaths_ReturnsFalse() {
        Path path1 = new Path(new Node("A"), new Node("B"));
        Path path2 = new Path(new Node("C"), new Node("B"));

        assertNotEquals(path1, path2);
    }
}
