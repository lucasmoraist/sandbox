package com.lucasmoraist.address_sandbox.controller;

import com.lucasmoraist.address_sandbox.dto.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/address")
public interface AddressController {
    @GetMapping("/{zipCode}")
    ResponseEntity<Address> getAddress(@PathVariable String zipCode);
}
