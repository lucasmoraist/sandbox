package com.lucasmoraist.gateway_sandbox.domain.information;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record Documents(
        @NotBlank(message = "CPF is required")
        @JsonProperty("cpf")
        String cpf,
        @JsonProperty("rg")
        String rg
) {
}
