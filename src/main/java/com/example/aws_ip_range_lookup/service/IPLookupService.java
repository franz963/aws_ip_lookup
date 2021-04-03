package com.example.aws_ip_range_lookup.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class IPLookupService {
    private DataRetrievalService dataRetrievalService;

    public IPLookupService() {
        dataRetrievalService = new DataRetrievalService();
    }

    public String getIPData(String region) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL("https://ip-ranges.amazonaws.com/ip-ranges.json");
            String ipData = dataRetrievalService.getIPDataFromConnection(url);
            if (ipData.isBlank()) {
                return "";
            }
            JSONObject ipResponseObject = new JSONObject(ipData);
            JSONArray ipRanges = ipResponseObject.getJSONArray("prefixes");
            for (int i = 0; i < ipRanges.length(); i++) {
                JSONObject ipRange = ipRanges.getJSONObject(i);
                String awsRegion = ipRange.get("region").toString();
                if (region.equals("all") || awsRegion.substring(0,2).equals(region)) {
                    response.append("ip_prefix: ").append(ipRange.get("ip_prefix")).append("\n").append("region: ").append(awsRegion).append("\n").append("service: ").append(ipRange.get("service")).append("\n").append("network_border_group: ").append(ipRange.get("network_border_group")).append("\n\n");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
