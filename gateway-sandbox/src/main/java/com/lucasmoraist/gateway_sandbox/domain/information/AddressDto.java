package com.lucasmoraist.gateway_sandbox.domain.information;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressDto(
        @JsonProperty("cep") String zipCode,
        @JsonProperty("logradouro") String street,
        @JsonProperty("unidade") String number,
        @JsonProperty("complemento") String complement,
        @JsonProperty("bairro") String neighborhood,
        @JsonProperty("localidade") String city,
        @JsonProperty("estado") String state
) {
}
