package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void equals_GivenTwoEqualNodes_ReturnsTrue() {
        assertEquals(new Node("A"), new Node("A"));
    }

    @Test
    public void equals_GivenTwoDifferentNodes_ReturnsFalse() {
        assertNotEquals(new Node("A"), new Node("B"));
    }
}
