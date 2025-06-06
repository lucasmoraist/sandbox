package com.lucasmoraist.gateway_sandbox.application.address.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmoraist.gateway_sandbox.infra.client.AddressClient;
import com.lucasmoraist.gateway_sandbox.domain.information.AddressDto;
import com.lucasmoraist.gateway_sandbox.application.address.AddressService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Log4j2
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressClient addressClient;
    private final ObjectMapper objectMapper;

    @Override
    public AddressDto getAddress(String zipCode) {
        try {
            log.info("Fetching address for zip code: {}", zipCode);
            Response response = this.addressClient.getAddress(zipCode);
            AddressDto address = this.mapperResponse(response);
            log.debug("Received address: {}", address);
            return address;
        } catch (Exception e) {
            log.error("Error fetching address for zip code: {}", zipCode, e);
            return null;
        }
    }

    private AddressDto mapperResponse(Response response) {
        if (response == null || response.body() == null) {
            log.error("Received null response or body from address client");
            return null;
        }
        try (InputStream in = response.body().asInputStream()) {
            AddressDto address = this.objectMapper.readValue(in, AddressDto.class);
            log.info("Successfully mapped response to Address: {}", address);
            return address;
        } catch (Exception e) {
            log.error("Error mapping response to Address", e);
            return null;
        }
    }
}
