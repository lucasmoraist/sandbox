package com.lucasmoraist.gateway_sandbox.infra.controller.impl;

import com.lucasmoraist.gateway_sandbox.application.email.EmailSender;
import com.lucasmoraist.gateway_sandbox.infra.controller.PersonController;
import com.lucasmoraist.gateway_sandbox.infra.controller.request.PersonRequest;
import com.lucasmoraist.gateway_sandbox.infra.controller.response.PersonResponse;
import com.lucasmoraist.gateway_sandbox.domain.information.Address;
import com.lucasmoraist.gateway_sandbox.domain.information.AddressDto;
import com.lucasmoraist.gateway_sandbox.application.address.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PersonControllerImpl implements PersonController {

    private final AddressService addressService;
    private final EmailSender emailSender;

    @Override
    public ResponseEntity<PersonResponse> savePerson(PersonRequest person) {
        AddressDto address = this.addressService.getAddress(person.address().zipCode());
        this.emailSender.sendEmail(person.contact().email(), person.fullName());
        int age = person.birthDate().until(LocalDate.now()).getYears();
        PersonResponse response = PersonResponse.builder()
                .fullName(person.fullName())
                .birthDate(person.birthDate())
                .gender(person.gender().getGender())
                .age(age)
                .documents(person.documents())
                .contact(person.contact())
                .address(new Address(address))
                .build();

        return ResponseEntity.ok().body(response);
    }
}
