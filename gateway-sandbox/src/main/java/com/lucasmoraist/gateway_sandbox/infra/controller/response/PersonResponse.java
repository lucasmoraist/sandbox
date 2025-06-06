package com.lucasmoraist.gateway_sandbox.infra.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasmoraist.gateway_sandbox.domain.information.Address;
import com.lucasmoraist.gateway_sandbox.domain.information.Contact;
import com.lucasmoraist.gateway_sandbox.domain.information.Documents;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PersonResponse(
        @NotBlank(message = "Name is required")
        @JsonProperty("fullName")
        String fullName,
        @NotNull(message = "Birth date is required")
        @JsonProperty("birthDate")
        LocalDate birthDate,
        @NotBlank(message = "Gender is required")
        @JsonProperty("gender")
        String gender,
        @JsonProperty("age")
        int age,
        @NotNull(message = "Documents are required")
        @JsonProperty("documents")
        Documents documents,
        @NotNull(message = "Contact is required")
        @JsonProperty("contact")
        Contact contact,
        @NotNull(message = "Address is required")
        @JsonProperty("address")
        Address address
) {
}
