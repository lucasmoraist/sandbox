package com.lucasmoraist.gateway_sandbox.service;

import com.lucasmoraist.gateway_sandbox.domain.information.AddressDto;

public interface AddressService {
    AddressDto getAddress(String zipCode);
}
