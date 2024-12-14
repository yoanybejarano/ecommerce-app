package com.hatefulbug.payment.api.service.impl;

import com.hatefulbug.payment.api.enums.CurrencyType;
import com.hatefulbug.payment.api.enums.PaymentStatus;
import com.hatefulbug.payment.api.enums.RefundStatus;
import com.hatefulbug.payment.api.model.Payment;
import com.hatefulbug.payment.api.model.PaymentMethod;
import com.hatefulbug.payment.api.model.Refund;
import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.repository.RefundRepository;
import com.hatefulbug.payment.api.request.PartialRefund;
import com.hatefulbug.payment.api.service.PaymentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class RefundServiceImplTest {

    @InjectMocks
    private RefundServiceImpl refundService;
    @Mock
    private RefundRepository refundRepository;
    @Mock
    private PaymentService paymentService;
    private static Refund refund;
    private static PartialRefund partialRefund;
    private static Payment payment;
    private static User user;

    @BeforeAll
    public static void init() {
        BigDecimal amount = new BigDecimal("100.00");
        String reason = "Refund Reason";
        Instant paymentDate = Instant.parse("2024-12-12T10:15:30.00Z");

        user = new User();
        user.setId(1);
        user.setFirstName("Issac");
        user.setLastName("Asimov");
        user.setPhoneNumber("111-111-1111");
        user.setEmail("asimov@email.com");

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(1);
        paymentMethod.setMethodName("Credit Card");
        paymentMethod.setDescription("Payments made using a credit card, such as Visa, Mastercard, or Amex");

        payment = new Payment();
        payment.setId(1);
        payment.setAmount(amount);
        payment.setCurrency(CurrencyType.USD);
        payment.setPaymentStatus(PaymentStatus.Completed);
        payment.setPaymentDate(paymentDate);
        payment.setPaymentMethod(paymentMethod);
        payment.setUser(user);

        partialRefund = PartialRefund.builder()
                .paymentID(1)
                .refundAmount(amount)
                .refundReason(reason)
                .build();

        refund = new Refund();
        refund.setId(1);
        refund.setRefundAmount(amount);
        refund.setRefundReason(reason);
        refund.setPayment(payment);
        refund.setRefundDate(Instant.now());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   @Test
    void getRefund() {
        when(refundRepository.findById(refund.getId())).thenReturn(Optional.of(refund));
        Refund found = refundService.getRefund(refund.getId());
        assertThat(found.getRefundReason()).isSameAs(refund.getRefundReason());
        assertThat(found.getRefundAmount()).isSameAs(refund.getRefundAmount());
        assertThat(found.getRefundDate()).isSameAs(refund.getRefundDate());
        assertThat(found.getRefundStatus()).isSameAs(refund.getRefundStatus());
        assertThat(found.getPayment()).isSameAs(refund.getPayment());
    }

    @Test
    void updateRefundStatus() {
        when(refundRepository.findById(refund.getId())).thenReturn(Optional.of(refund));
        when(refundRepository.save(ArgumentMatchers.any(Refund.class))).thenReturn(refund);
        Refund updated = refundService.updateRefundStatus(refund.getId(), RefundStatus.Processed.name());
        assertThat(updated.getRefundReason()).isSameAs(refund.getRefundReason());
        assertThat(updated.getRefundAmount()).isSameAs(refund.getRefundAmount());
        assertThat(updated.getRefundDate()).isSameAs(refund.getRefundDate());
        assertThat(updated.getRefundStatus()).isSameAs(refund.getRefundStatus());
        assertThat(updated.getPayment()).isSameAs(refund.getPayment());
    }

    @Test
    void createRefund() {
        refund.setRefundStatus(RefundStatus.Initiated);
        when(paymentService.getPaymentById(payment.getId())).thenReturn(payment);
        when(refundRepository.save(ArgumentMatchers.any(Refund.class))).thenReturn(refund);
        partialRefund.setRefundStatus(RefundStatus.Initiated.name());
        Refund created = refundService.createRefund(partialRefund);
        assertThat(created.getRefundReason()).isSameAs(refund.getRefundReason());
        assertThat(created.getRefundAmount()).isSameAs(refund.getRefundAmount());
        assertThat(created.getRefundDate()).isSameAs(refund.getRefundDate());
        assertThat(created.getRefundStatus()).isSameAs(refund.getRefundStatus());
        assertThat(created.getPayment()).isSameAs(refund.getPayment());
    }

    @Test
    void getAllRefundsByUser() {
        when(refundRepository.findAllByUserId(user.getId())).
                thenReturn(new ArrayList<>(Collections.singleton(refund)));
        assertThat(refundService.getAllRefundsByUser(user.getId()).get(0).getId()).
                isEqualTo(refund.getId());
    }
}