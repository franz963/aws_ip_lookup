package com.example.aws_ip_range_lookup.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class IPLookupServiceTest {
    String mockDataResponse = "{\"syncToken\":\"1617407053\",\"createDate\":\"2021-04-02-23-44-13\",\"prefixes\":[" +
            "{\"ip_prefix\":\"3.5.140.0/22\",\"region\":\"ap-northeast-2\",\"service\":\"AMAZON\",\"network_border_group\":\"ap-northeast-2\"}," +
            "{\"ip_prefix\":\"15.230.56.104/31\",\"region\":\"us-east-1\",\"service\":\"AMAZON\",\"network_border_group\":\"us-east-1\"}," +
            "{\"ip_prefix\":\"35.180.0.0/16\",\"region\":\"eu-west-3\",\"service\":\"AMAZON\",\"network_border_group\":\"eu-west-3\"}," +
            "{\"ip_prefix\":\"52.93.153.170/32\",\"region\":\"eu-west-2\",\"service\":\"AMAZON\",\"network_border_group\":\"eu-west-2\"}," +
            "{\"ip_prefix\":\"52.93.178.234/32\",\"region\":\"us-west-1\",\"service\":\"AMAZON\",\"network_border_group\":\"us-west-1\"}]}";

    @Mock
    DataRetrievalService dataRetrievalService;

    @InjectMocks
    IPLookupService ipLookupService = new IPLookupService();

    @Test
    public void testGetIPDataReturnsDataForSpecificRegion() {
        String response = "ip_prefix: 3.5.140.0/22\n" +
                "region: ap-northeast-2\n" +
                "service: AMAZON\n" +
                "network_border_group: ap-northeast-2\n\n";
        Mockito.when(dataRetrievalService.getIPDataFromConnection(any())).thenReturn(mockDataResponse);
        String actual = ipLookupService.getIPData("ap");

        assertEquals(response, actual);
    }

    @Test
    public void testGetIPDataReturnsDataForAllRegions() {
        String response = "ip_prefix: 3.5.140.0/22\n" +
                "region: ap-northeast-2\n" +
                "service: AMAZON\n" +
                "network_border_group: ap-northeast-2\n" +
                "\n" +
                "ip_prefix: 15.230.56.104/31\n" +
                "region: us-east-1\n" +
                "service: AMAZON\n" +
                "network_border_group: us-east-1\n" +
                "\n" +
                "ip_prefix: 35.180.0.0/16\n" +
                "region: eu-west-3\n" +
                "service: AMAZON\n" +
                "network_border_group: eu-west-3\n" +
                "\n" +
                "ip_prefix: 52.93.153.170/32\n" +
                "region: eu-west-2\n" +
                "service: AMAZON\n" +
                "network_border_group: eu-west-2\n" +
                "\n" +
                "ip_prefix: 52.93.178.234/32\n" +
                "region: us-west-1\n" +
                "service: AMAZON\n" +
                "network_border_group: us-west-1\n\n";
        Mockito.when(dataRetrievalService.getIPDataFromConnection(any())).thenReturn(mockDataResponse);
        String actual = ipLookupService.getIPData("all");

        assertEquals(response, actual);
    }

    @Test
    public void testGetIPDataReturnsEmptyStringIfInvalidRegionIsProvided() {
        Mockito.when(dataRetrievalService.getIPDataFromConnection(any())).thenReturn(mockDataResponse);
        String actual = ipLookupService.getIPData("abc");

        assertEquals("", actual);
    }

    @Test
    public void testGetIPDataReturnsEmptyStringIfNoDataIsFound() {
        Mockito.when(dataRetrievalService.getIPDataFromConnection(any())).thenReturn("");
        String actual = ipLookupService.getIPData("ca");

        assertEquals("", actual);
    }
}
