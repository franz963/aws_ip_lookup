package com.example.aws_ip_range_lookup.controller;

import com.example.aws_ip_range_lookup.service.IPLookupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class IPLookupTest {
    @Mock
    IPLookupService ipLookupService;

    @InjectMocks
    IPLookup ipLookup = new IPLookup();

    @Test
    public void testGetIPRangesReturnsValidResultWithStatusCodeOK() {
        String response = "ip_prefix: 3.5.140.0/22\n" +
                "region: ap-northeast-2\n" +
                "service: AMAZON\n" +
                "network_border_group: ap-northeast-2\n";
        Mockito.when(ipLookupService.getIPData("ap")).thenReturn(response);
        ResponseEntity<String> actual = ipLookup.getIPRanges("AP");

        assertEquals(200, actual.getStatusCodeValue());
        assertEquals(response, actual.getBody());
    }

    @Test
    public void testGetIPRangesReturnsErrorMessageAndStatusCodeBadRequest() {
        Mockito.when(ipLookupService.getIPData("abc")).thenReturn("");
        ResponseEntity<String> actual = ipLookup.getIPRanges("ABC");

        assertEquals(400, actual.getStatusCodeValue());
        assertEquals("The region you provided is invalid", actual.getBody());
    }
}
