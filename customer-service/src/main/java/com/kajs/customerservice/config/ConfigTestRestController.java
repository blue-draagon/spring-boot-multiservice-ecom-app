package com.kajs.customerservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestRestController {
    @Value("${global.params.p1}")
    private String p1;
    @Value("${global.params.p2}")
    private String p2;

    private CustomerConfigParams customerConfigParams;

    @Autowired
    public void setCustomerConfigParams(CustomerConfigParams customerConfigParams) {
        this.customerConfigParams = customerConfigParams;
    }

    @GetMapping("/api/test-config")
    public Map<String, String> configTest() {
        return Map.of("p1", p1, "p2", p2);
    }

    @GetMapping("/api/test-config-2")
    public CustomerConfigParams configTest2() {
        return customerConfigParams;
    }
}
