package com.example.aws_ip_range_lookup.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPLookupTest {
    IPLookup ipLookup = new IPLookup();

    @Test
    public void testGetIPRangesReturnsInputParam() {
        String input = "US";
        String actual = ipLookup.getIPRanges(input);
        assertEquals(input, actual);
    }

    @Test
    public void testGetIPRangesReturnsInputParamAndNotADifferentOne() {
        String input = "US";
        String actual = ipLookup.getIPRanges(input);
        assertNotEquals("EU", actual);
    }
}