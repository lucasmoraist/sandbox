package com.lucasmoraist.gateway_sandbox.controller;

import com.lucasmoraist.gateway_sandbox.controller.request.PersonRequest;
import com.lucasmoraist.gateway_sandbox.controller.response.PersonResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/person")
public interface PersonController {

    @PostMapping
    ResponseEntity<PersonResponse> savePerson(@Valid @RequestBody PersonRequest person);
}
