package com.lucasmoraist.gateway_sandbox.domain.information;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Address(
        @JsonProperty("zipCode") String zipCode,
        @JsonProperty("street") String street,
        @JsonProperty("number") String number,
        @JsonProperty("complement") String complement,
        @JsonProperty("neighborhood") String neighborhood,
        @JsonProperty("city") String city,
        @JsonProperty("state") String state
) {
    public Address(AddressDto address) {
        this(
                address.zipCode(),
                address.street(),
                address.number(),
                address.complement(),
                address.neighborhood(),
                address.city(),
                address.state()
        );
    }
}
