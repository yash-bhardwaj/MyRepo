package com.nagarro.test.payments;

import java.io.IOException;

import com.nagarro.test.payments.impl.PaymentServiceImpl;
import com.nagarro.test.payments.service.PaymentService;

public class Main {
    public static void main(String[] args) throws IOException {
        final PaymentService paymentService = new PaymentServiceImpl();
        paymentService.processEmpPayments();
    }
}
