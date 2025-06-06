package com.lucasmoraist.gateway_sandbox.infra.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-client", url = "${client.url.address}")
public interface AddressClient {
    @GetMapping("/{zipCode}")
    Response getAddress(@PathVariable String zipCode);
}
