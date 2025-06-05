package com.lucasmoraist.address_sandbox.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep", url = "${client.url.viacep}")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    Response getAddressByCep(@PathVariable String cep);

}
