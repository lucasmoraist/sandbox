package com.lucasmoraist.gateway_sandbox.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasmoraist.gateway_sandbox.domain.enums.Gender;
import com.lucasmoraist.gateway_sandbox.domain.information.Address;
import com.lucasmoraist.gateway_sandbox.domain.information.Contact;
import com.lucasmoraist.gateway_sandbox.domain.information.Documents;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonRequest(

        @NotBlank(message = "Name is required")
        @JsonProperty("fullName")
        String fullName,
        @NotNull(message = "Birth date is required")
        @JsonProperty("birthDate")
        LocalDate birthDate,
        @NotNull(message = "Gender is required")
        @JsonProperty("gender")
        Gender gender,
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
