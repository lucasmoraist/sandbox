package com.lucasmoraist.address_sandbox.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmoraist.address_sandbox.client.impl.ViaCepClientImpl;
import com.lucasmoraist.address_sandbox.dto.Address;
import com.lucasmoraist.address_sandbox.exception.ViaCepException;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViaCepClientImplTest {

    @Mock
    private ViaCepClient viaCepClient;

    @Mock
    private Response response;

    private ObjectMapper objectMapper;
    private ViaCepClientImpl viaCepClientImpl;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        viaCepClientImpl = new ViaCepClientImpl(viaCepClient, objectMapper);
    }

    @Test
    @DisplayName("Test getAddress with valid CEP")
    void case01() throws Exception {
        String cep = "01001-000";
        String responseBody = "{"
                + "\"cep\":\"01001-000\","
                + "\"logradouro\":\"Praça da Sé\","
                + "\"complemento\":\"lado ímpar\","
                + "\"unidade\":\"\","
                + "\"bairro\":\"Sé\","
                + "\"localidade\":\"São Paulo\","
                + "\"uf\":\"SP\","
                + "\"estado\":\"São Paulo\","
                + "\"regiao\":\"Sudeste\","
                + "\"ibge\":\"3550308\","
                + "\"gia\":\"1004\","
                + "\"ddd\":\"11\","
                + "\"siafi\":\"7107\""
                + "}";

        byte[] utf8Bytes = responseBody.getBytes(StandardCharsets.UTF_8);
        Address expectedAddress = objectMapper.readValue(responseBody, Address.class);

        Response.Body mockedBody = mock(Response.Body.class);
        when(mockedBody.asInputStream()).thenReturn(new ByteArrayInputStream(utf8Bytes));

        when(viaCepClient.getAddressByCep(cep)).thenReturn(response);
        when(response.body()).thenReturn(mockedBody);

        Address actualAddress = viaCepClientImpl.getAddress(cep);

        assertNotNull(actualAddress);
        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    @DisplayName("Test getAddress with invalid CEP")
    void case02() {
        String cep = "00000-000";

        when(viaCepClient.getAddressByCep(cep)).thenReturn(response);
        when(response.body()).thenReturn(null);

        assertThrows(ViaCepException.class, () -> viaCepClientImpl.getAddress(cep));
    }

    @Test
    @DisplayName("Test getAddress with null response")
    void case03() throws IOException {
        String cep = "01001-000";

        // Mock Response.Body
        Response.Body mockedBody = mock(Response.Body.class);
        when(mockedBody.asInputStream()).thenReturn(new ByteArrayInputStream("invalid-json".getBytes()));

        when(viaCepClient.getAddressByCep(cep)).thenReturn(response);
        when(response.body()).thenReturn(mockedBody);

        assertThrows(RuntimeException.class, () -> viaCepClientImpl.getAddress(cep));
    }

}