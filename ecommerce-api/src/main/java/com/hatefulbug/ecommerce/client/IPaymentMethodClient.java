package com.hatefulbug.ecommerce.client;

import com.hatefulbug.ecommerce.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "paymentMethod", url = "http://localhost:8090", path = "/api/v1/paymentMethods")
public interface IPaymentMethodClient {

    @RequestMapping(method = RequestMethod.GET,value = "/all")
    ApiResponse getPaymentMethods();

}
