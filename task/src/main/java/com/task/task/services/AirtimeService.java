package com.task.task.services;


import com.task.task.model.AirtimeApiResponse;
import com.task.task.model.AirtimePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AirtimeService {
        @Autowired
        private RestTemplate restTemplate;

        public AirtimeApiResponse makeAirtimePayment(AirtimePaymentRequest paymentRequest) {
            // Replace with actual API endpoint and headers
            String apiUrl = "https://docsbillerservices.xpresspayments.com/bills-payment/airtime";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Add necessary headers

            HttpEntity<AirtimePaymentRequest> request = new HttpEntity<>(paymentRequest, headers);

            ResponseEntity<AirtimeApiResponse> responseEntity = restTemplate.exchange(
                    apiUrl, HttpMethod.POST, request, AirtimeApiResponse.class);

            return responseEntity.getBody();
        }
}


