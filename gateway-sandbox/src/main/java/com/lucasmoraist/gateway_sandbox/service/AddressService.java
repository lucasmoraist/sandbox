package com.lucasmoraist.gateway_sandbox.service;

import com.lucasmoraist.gateway_sandbox.domain.information.Address;

public interface AddressService {
    Address getAddress(String zipCode);
}
