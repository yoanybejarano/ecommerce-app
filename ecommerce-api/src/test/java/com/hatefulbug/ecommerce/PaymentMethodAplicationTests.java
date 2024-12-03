package com.hatefulbug.ecommerce;


import static com.github.tomakehurst.wiremock.client.WireMock.*;

class PaymentMethodAplicationTests {

    public static void stubPaymentMethod() {
        stubFor(get(urlEqualTo("/api/v1/paymentMethods/all"))
                .willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json"))
                .willReturn(ok()))
        ;
    }

}
