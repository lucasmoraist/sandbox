package com.lucasmoraist.gateway_sandbox.domain.information;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record Contact(
        @JsonProperty("phoneNumber")
        String phoneNumber,
        @JsonProperty("homePhone")
        String homePhone,
        @NotBlank(message = "Email is required")
        @JsonProperty("email")
        String email
) {
}
