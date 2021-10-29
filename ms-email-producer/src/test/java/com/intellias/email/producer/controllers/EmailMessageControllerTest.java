package com.intellias.email.producer.controllers;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellias.email.common.dto.SendEmailMessageRequest;
import com.intellias.email.common.dto.SendEmailMessageResponse;
import com.intellias.email.producer.services.EmailService;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(
        value = EmailMessageController.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE
        )
)
class EmailMessageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmailService emailService;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Test
    void givenSendEmailRequestWhenSendThenCallEmailService() throws Exception {
        final var sendEmailMessageRequest = SendEmailMessageRequest.builder()
                .to(List.of("me@shahinnazarov.com"))
                .content("Test email")
                .from("no-reply@shahinnazarov.com")
                .subject("Test message")
                .build();
        final var sendEmailResponse = SendEmailMessageResponse.builder()
                .timestamp(System.currentTimeMillis())
                .id(UUID.randomUUID().toString())
                .build();
        when(emailService.send(eq(sendEmailMessageRequest))).thenReturn(sendEmailResponse);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/email-messages")
                .content(OBJECT_MAPPER.writeValueAsString(sendEmailMessageRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).characterEncoding(UTF_8.displayName())
        ).andExpect(status().isOk()).andReturn();

        verify(emailService, times(1)).send(eq(sendEmailMessageRequest));
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
