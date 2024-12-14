package com.hatefulbug.payment.api.service.impl;

import com.hatefulbug.payment.api.enums.CurrencyType;
import com.hatefulbug.payment.api.enums.PaymentStatus;
import com.hatefulbug.payment.api.model.Payment;
import com.hatefulbug.payment.api.model.PaymentMethod;
import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.repository.PaymentRepository;
import com.hatefulbug.payment.api.request.PartialPayment;
import com.hatefulbug.payment.api.request.RangeDateRequest;
import com.hatefulbug.payment.api.service.PaymentMethodService;
import com.hatefulbug.payment.api.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class PaymentServiceImplTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private UserService userService;
    @Mock
    private PaymentMethodService paymentMethodService;
    private static Payment payment;
    private static PartialPayment partialPayment;
    private static User user;
    private static PaymentMethod paymentMethod;

    @BeforeAll
    public static void init() {
        BigDecimal amount = new BigDecimal("100.00");
        Instant paymentDate = Instant.parse("2024-12-12T10:15:30.00Z");

        user = new User();
        user.setId(1);
        user.setFirstName("Issac");
        user.setLastName("Asimov");
        user.setPhoneNumber("111-111-1111");
        user.setEmail("asimov@email.com");

        paymentMethod = new PaymentMethod();
        paymentMethod.setId(1);
        paymentMethod.setMethodName("Credit Card");
        paymentMethod.setDescription("Payments made using a credit card, such as Visa, Mastercard, or Amex");

        payment = new Payment();
        payment.setId(1);
        payment.setAmount(amount);
        payment.setCurrency(CurrencyType.USD);
        payment.setPaymentDate(paymentDate);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(PaymentStatus.Completed);
        payment.setUser(user);

        partialPayment = PartialPayment.builder()
                .userId(user.getId())
                .amount(amount)
                .paymentMethodId(paymentMethod.getId())
                .currency(CurrencyType.USD.name())
                .build();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPayment() {
        when(userService.getUserById(Mockito.anyInt())).thenReturn(user);
        when(paymentMethodService.getPaymentMethod(Mockito.anyInt())).thenReturn(paymentMethod);
        when(paymentRepository.save(ArgumentMatchers.any(Payment.class))).thenReturn(payment);
        Payment created = paymentService.createPayment(partialPayment);
        assertThat(created.getPaymentDate()).isSameAs(payment.getPaymentDate());
        assertThat(created.getUser().getId()).isSameAs(payment.getUser().getId());
        assertThat(created.getPaymentStatus()).isSameAs(payment.getPaymentStatus());
        assertThat(created.getCurrency()).isSameAs(payment.getCurrency());
        assertThat(created.getAmount()).isSameAs(payment.getAmount());
        assertThat(created.getPaymentMethod().getId()).isSameAs(payment.getPaymentMethod().getId());
    }

    @Test
    void updatePaymentStatus() {
        when(paymentRepository.findById(payment.getId())).thenReturn(Optional.of(payment));
        when(paymentRepository.save(ArgumentMatchers.any(Payment.class))).thenReturn(payment);
        Payment updated = paymentService.updatePaymentStatus(payment.getId(), PaymentStatus.Failed.name());
        assertThat(updated.getPaymentDate()).isSameAs(payment.getPaymentDate());
        assertThat(updated.getUser().getId()).isSameAs(payment.getUser().getId());
        assertThat(updated.getPaymentStatus()).isSameAs(payment.getPaymentStatus());
        assertThat(updated.getCurrency()).isSameAs(payment.getCurrency());
        assertThat(updated.getAmount()).isSameAs(payment.getAmount());
        assertThat(updated.getPaymentMethod().getId()).isSameAs(payment.getPaymentMethod().getId());
    }

    @Test
    void getPaymentById() {
        when(paymentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(payment));
        Payment found = paymentService.getPaymentById(payment.getId());
        assertThat(found.getPaymentDate()).isSameAs(payment.getPaymentDate());
        assertThat(found.getUser().getId()).isSameAs(payment.getUser().getId());
        assertThat(found.getPaymentStatus()).isSameAs(payment.getPaymentStatus());
        assertThat(found.getCurrency()).isSameAs(payment.getCurrency());
        assertThat(found.getAmount()).isSameAs(payment.getAmount());
        assertThat(found.getPaymentMethod().getId()).isSameAs(payment.getPaymentMethod().getId());
    }

    @Test
    void getPaymentsByUser() {
        when(paymentRepository.findAllByUserId(user.getId())).
                thenReturn(new ArrayList<>(Collections.singleton(payment)));
        assertThat(paymentService.getPaymentsByUser(user.getId()).get(0).getId()).
                isEqualTo(payment.getId());
    }

    @Test
    void getPaymentsByStatus() {
        when(paymentRepository.findAllByPaymentStatus(PaymentStatus.Completed)).
                thenReturn(new ArrayList<>(Collections.singleton(payment)));
        assertThat(paymentService.getPaymentsByStatus("Completed").get(0).getId()).
                isEqualTo(payment.getId());
    }

    @Test
    void getPaymentsByDates() {
        Instant startDate = Instant.parse("2024-12-10T10:15:30.00Z");
        Instant endDate = Instant.parse("2024-12-20T10:15:30.00Z");
        RangeDateRequest range = RangeDateRequest.builder()
                .startDate(Date.from(startDate))
                .endDate(Date.from(endDate))
                .build();
        when(paymentRepository.findAllByPaymentDateBetween(startDate, endDate)).
                thenReturn(new ArrayList<>(Collections.singleton(payment)));
        assertThat(paymentService.getPaymentsByDates(range).get(0).getId()).
                isEqualTo(payment.getId());
    }
}