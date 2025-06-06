package com.lucasmoraist.address_sandbox.client.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmoraist.address_sandbox.client.ViaCepClient;
import com.lucasmoraist.address_sandbox.dto.Address;
import com.lucasmoraist.address_sandbox.exception.ViaCepException;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Log4j2
@Service
@RequiredArgsConstructor
public class ViaCepClientImpl {

    private final ViaCepClient viaCepClient;
    private final ObjectMapper objectMapper;

    public Address getAddress(String cep) {
        log.info("Fetching address for CEP: {}", cep);
        Response response = this.viaCepClient.getAddressByCep(cep);
        Address address = this.mapperResponse(response);
        log.debug("Address fetched: {}", address);
        return address;
    }

    private Address mapperResponse(Response response) {
        if (response == null || response.body() == null) {
            log.error("Response or response body is null");
            throw new ViaCepException("CEP not found or invalid response from ViaCep service");
        }

        try (InputStream in = response.body().asInputStream()) {
            log.info("Mapping response to Address object");
            Address address = objectMapper.readValue(in, Address.class);
            log.info("Successfully mapped response to Address object: {}", address);
            return address;
        } catch (Exception e) {
            log.error("Error mapping response to Address object", e);
            throw new ViaCepException("Failed to map response to Address object", e);
        }
    }

}
