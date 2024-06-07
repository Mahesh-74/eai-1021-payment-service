package com.payment.service.api.service;



import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.service.api.entity.Payment;
import com.payment.service.api.repository.PaymentRepository;

@Service
public class PaymentService {
	Logger logger= LoggerFactory.getLogger(PaymentService.class);
	@Autowired
	private PaymentRepository paymentRepository;

	public Payment doPayment(Payment payment) {
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransationId(UUID.randomUUID().toString());
		return paymentRepository.save(payment);

	}
	public String paymentProcessing() {
		//api call should be 3 rd party payment gateway(paypal,paytm)
		return new Random().nextBoolean()?"SUCESS":"false";
		
	}
    public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
        Payment payment=paymentRepository.findByOrderId(orderId);
        logger.info("paymentService findPaymentHistoryByOrderId : {}",new ObjectMapper().writeValueAsString(payment));
        return payment ;
    }
}
