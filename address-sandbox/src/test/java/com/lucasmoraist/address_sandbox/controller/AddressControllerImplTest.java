package com.lucasmoraist.address_sandbox.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmoraist.address_sandbox.client.impl.ViaCepClientImpl;
import com.lucasmoraist.address_sandbox.controller.impl.AddressControllerImpl;
import com.lucasmoraist.address_sandbox.dto.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressControllerImpl.class)
class AddressControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ViaCepClientImpl viaCepClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("case01 - should return address when zip code is valid")
    void case01_shouldReturnAddress_whenZipCodeIsValid() throws Exception {
        String zipCode = "01001-000";
        Address mockAddress = Address.builder()
                .cep(zipCode)
                .logradouro("Praça da Sé")
                .localidade("São Paulo")
                .uf("SP")
                .build();

        when(viaCepClient.getAddress(zipCode)).thenReturn(mockAddress);

        mockMvc.perform(get("/api/address/01001-000") // valor vai na URI
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("01001-000"))
                .andExpect(jsonPath("$.logradouro").value("Praça da Sé"))
                .andExpect(jsonPath("$.localidade").value("São Paulo"))
                .andExpect(jsonPath("$.uf").value("SP"));


        verify(viaCepClient).getAddress(zipCode);
    }

    @Test
    @DisplayName("case02 - should return 404 when address is not found")
    void case02_shouldReturnNotFound_whenAddressIsNull() throws Exception {
        String zipCode = "00000-000";
        when(viaCepClient.getAddress(zipCode)).thenReturn(null);

        mockMvc.perform(get("/api/address/00000-000")
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(viaCepClient).getAddress(zipCode);
    }

}