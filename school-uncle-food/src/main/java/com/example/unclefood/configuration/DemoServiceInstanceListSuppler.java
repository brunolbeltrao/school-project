package com.example.unclefood.configuration;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class DemoServiceInstanceListSuppler implements ServiceInstanceListSupplier {
    private final String serviceId;

    public DemoServiceInstanceListSuppler(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceId() {
        return null;
    }

    @Override
    public Flux<List<ServiceInstance>> get(Request request) {
        return ServiceInstanceListSupplier.super.get(request);
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8081, false),
                        new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8082, false)));
    }
}
