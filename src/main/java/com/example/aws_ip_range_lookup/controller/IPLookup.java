package com.example.aws_ip_range_lookup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IPLookup {
    @GetMapping("/ip-ranges")
    @ResponseBody
    public String getIPRanges(@RequestParam String region) {
        return region;
    }
}
