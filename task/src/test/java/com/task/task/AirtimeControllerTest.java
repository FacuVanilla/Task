package com.task.task;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.task.controller.AirtimeController;
import com.task.task.model.AirtimeApiResponse;
import com.task.task.model.AirtimePaymentRequest;
import com.task.task.services.AirtimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AirtimeController.class)
public class AirtimeControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private AirtimeService airtimeService;

        @Test
        public void testMakeAirtimePayment() throws Exception {
            AirtimePaymentRequest paymentRequest = new AirtimePaymentRequest();
            AirtimeApiResponse response = new AirtimeApiResponse("success");

            given(airtimeService.makeAirtimePayment(any(AirtimePaymentRequest.class)));

            mockMvc.perform(post("/airtime-payment")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(paymentRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value("success"));
        }

        // Helper method to convert objects to JSON string
        private static String asJsonString(final Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}
