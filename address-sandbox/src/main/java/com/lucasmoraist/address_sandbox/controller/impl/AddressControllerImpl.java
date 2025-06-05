package com.lucasmoraist.address_sandbox.controller.impl;

import com.lucasmoraist.address_sandbox.client.impl.ViaCepClientImpl;
import com.lucasmoraist.address_sandbox.controller.AddressController;
import com.lucasmoraist.address_sandbox.dto.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final ViaCepClientImpl viaCepClient;

    @Override
    public ResponseEntity<Address> getAddress(String zipCode) {
        log.info("Received request to get address for zip code: {}", zipCode);
        Address address = viaCepClient.getAddress(zipCode);
        return ResponseEntity.ok().body(address);
    }
}
