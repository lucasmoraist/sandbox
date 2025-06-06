package com.lucasmoraist.gateway_sandbox.application.address;

import com.lucasmoraist.gateway_sandbox.domain.information.AddressDto;

public interface AddressService {
    AddressDto getAddress(String zipCode);
}
