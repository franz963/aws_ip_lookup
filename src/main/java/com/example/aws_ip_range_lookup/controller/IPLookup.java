package com.example.aws_ip_range_lookup.controller;

import com.example.aws_ip_range_lookup.service.IPLookupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IPLookup {
    private IPLookupService ipLookupService;

    public IPLookup() {
        ipLookupService = new IPLookupService();
    }

    @GetMapping("/ip-ranges")
    @ResponseBody
    public ResponseEntity<String> getIPRanges(@RequestParam String region) {
        String ipData = ipLookupService.getIPData(region.toLowerCase());
        if (ipData.isBlank()) {
            return new ResponseEntity<>("The region you provided is invalid", HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(ipData, HttpStatus.OK);
        }
    }
}
