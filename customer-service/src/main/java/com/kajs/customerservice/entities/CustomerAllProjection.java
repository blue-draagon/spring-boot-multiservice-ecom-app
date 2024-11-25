package com.kajs.customerservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "all", types = Customer.class)
public interface CustomerAllProjection {
    String getName();
    String getEmail();
}
