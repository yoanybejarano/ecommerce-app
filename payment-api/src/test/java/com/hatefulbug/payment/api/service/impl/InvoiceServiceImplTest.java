package com.hatefulbug.payment.api.service.impl;

import com.hatefulbug.payment.api.enums.InvoiceStatus;
import com.hatefulbug.payment.api.model.Invoice;
import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.repository.InvoiceRepository;
import com.hatefulbug.payment.api.request.PartialInvoice;
import com.hatefulbug.payment.api.request.PartialInvoiceUpdate;
import com.hatefulbug.payment.api.request.RangeDateRequest;
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

class InvoiceServiceImplTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;
    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private UserService userService;
    private static Invoice invoice;
    private static User user;
    private static PartialInvoice partialInvoice;
    private static PartialInvoiceUpdate partialInvoiceUpdate;

    @BeforeAll
    public static void init() {
        BigDecimal totalAmount = new BigDecimal("100.00");
        Instant invoiceDate = Instant.parse("2024-12-12T10:15:30.00Z");
        Instant dueDate = Instant.parse("2024-12-20T10:15:30.00Z");

        user = new User();
        user.setId(1);
        user.setFirstName("Issac");
        user.setLastName("Asimov");
        user.setPhoneNumber("111-111-1111");
        user.setEmail("asimov@email.com");

        invoice = new Invoice();
        invoice.setId(1);
        invoice.setUser(user);
        invoice.setTotalAmount(totalAmount);
        invoice.setInvoiceDate(invoiceDate);
        invoice.setDueDate(dueDate);
        invoice.setInvoiceStatus(InvoiceStatus.Unpaid);

        partialInvoice = PartialInvoice.builder()
                .userId(user.getId())
                .dueDate(Date.from(dueDate))
                .build();

        partialInvoiceUpdate = PartialInvoiceUpdate.builder()
                .invoiceId(invoice.getId())
                .amount(totalAmount)
                .dueDate(Date.from(dueDate))
                .status("Unpaid")
                .build();

    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInvoice() {
        when(invoiceRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(invoice));
        Invoice found = invoiceService.getInvoice(invoice.getId());
        assertThat(found.getUser()).isSameAs(invoice.getUser());
        assertThat(found.getInvoiceStatus()).isSameAs(invoice.getInvoiceStatus());
        assertThat(found.getTotalAmount()).isSameAs(invoice.getTotalAmount());
        assertThat(found.getInvoiceDate()).isSameAs(invoice.getInvoiceDate());
        assertThat(found.getDueDate()).isSameAs(invoice.getDueDate());
    }

    @Test
    void getUserInvoices() {
        when(invoiceRepository.getInvoicesByUserId(user.getId())).
                thenReturn(new ArrayList<>(Collections.singleton(invoice)));
        assertThat(invoiceService.getUserInvoices(user.getId()).get(0).getId()).
                isEqualTo(invoice.getId());
    }

    @Test
    void getInvoicesByStatus() {
        when(invoiceRepository.findAllByInvoiceStatus(InvoiceStatus.Unpaid)).
                thenReturn(new ArrayList<>(Collections.singleton(invoice)));
        assertThat(invoiceService.getInvoicesByStatus(InvoiceStatus.Unpaid.name()).get(0).getId()).
                isEqualTo(invoice.getId());
    }

    @Test
    void getInvoicesByDates() {
        Instant startDate = Instant.parse("2024-12-10T10:15:30.00Z");
        Instant endDate = Instant.parse("2024-12-20T10:15:30.00Z");
        RangeDateRequest range = RangeDateRequest.builder()
                .startDate(Date.from(startDate))
                .endDate(Date.from(endDate))
                .build();
        when(invoiceRepository.findAllByInvoiceDateBetween(startDate, endDate)).
                thenReturn(new ArrayList<>(Collections.singleton(invoice)));
        assertThat(invoiceService.getInvoicesByDates(range).get(0).getId()).
                isEqualTo(invoice.getId());
    }

    @Test
    void createInvoice() {
        when(userService.getUserById(Mockito.anyInt())).thenReturn(user);
        when(invoiceRepository.save(ArgumentMatchers.any(Invoice.class))).thenReturn(invoice);
        Invoice created = invoiceService.createInvoice(partialInvoice);
        assertThat(created.getUser()).isSameAs(invoice.getUser());
        assertThat(created.getInvoiceStatus()).isSameAs(invoice.getInvoiceStatus());
        assertThat(created.getTotalAmount()).isSameAs(invoice.getTotalAmount());
        assertThat(created.getInvoiceDate()).isSameAs(invoice.getInvoiceDate());
        assertThat(created.getDueDate()).isSameAs(invoice.getDueDate());
    }

    @Test
    void updateInvoice() {
        when(invoiceRepository.findById(invoice.getId())).thenReturn(Optional.of(invoice));
        when(invoiceRepository.save(ArgumentMatchers.any(Invoice.class))).thenReturn(invoice);
        Invoice updated = invoiceService.updateInvoice(partialInvoiceUpdate);
        assertThat(updated.getUser()).isSameAs(invoice.getUser());
        assertThat(updated.getInvoiceStatus()).isSameAs(invoice.getInvoiceStatus());
        assertThat(updated.getTotalAmount()).isSameAs(invoice.getTotalAmount());
        assertThat(updated.getInvoiceDate()).isSameAs(invoice.getInvoiceDate());
        assertThat(updated.getDueDate()).isSameAs(invoice.getDueDate());
    }

    @Test
    void updateInvoiceStatus() {
        when(invoiceRepository.findById(invoice.getId())).thenReturn(Optional.of(invoice));
        when(invoiceRepository.save(ArgumentMatchers.any(Invoice.class))).thenReturn(invoice);
        Invoice updated = invoiceService.updateInvoiceStatus(invoice.getId(), InvoiceStatus.Paid.name());
        assertThat(updated.getUser()).isSameAs(invoice.getUser());
        assertThat(updated.getInvoiceStatus()).isSameAs(invoice.getInvoiceStatus());
        assertThat(updated.getTotalAmount()).isSameAs(invoice.getTotalAmount());
        assertThat(updated.getInvoiceDate()).isSameAs(invoice.getInvoiceDate());
        assertThat(updated.getDueDate()).isSameAs(invoice.getDueDate());
    }
}