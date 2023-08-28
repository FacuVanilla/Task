package com.task.task;

import com.task.task.model.AirtimeApiResponse;
import com.task.task.model.AirtimePaymentRequest;
import com.task.task.services.AirtimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AirtimeServiceTest {

        @InjectMocks
        private AirtimeService airtimeService;

        @Mock
        private RestTemplate restTemplate;

        @Test
        public void testMakeAirtimePayment() {
            AirtimePaymentRequest paymentRequest = new AirtimePaymentRequest();
            ResponseEntity<AirtimeApiResponse> responseEntity = ResponseEntity.ok(new AirtimeApiResponse("success"));

            given(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(AirtimeApiResponse.class)))
                    ;

            AirtimeApiResponse response = airtimeService.makeAirtimePayment(paymentRequest);

            assertNotNull(response);
            assertEquals("success", response.getStatus());
        }
}


