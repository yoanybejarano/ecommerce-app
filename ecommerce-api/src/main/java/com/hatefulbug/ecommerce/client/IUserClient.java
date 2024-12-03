package com.hatefulbug.ecommerce.client;

import com.hatefulbug.ecommerce.client.partial.PartialUser;
import com.hatefulbug.ecommerce.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user", url = "http://localhost:8090", path = "/api/v1/users")
public interface IUserClient {

    @RequestMapping(method = RequestMethod.POST,value = "/create")
    ApiResponse createUser(@RequestBody PartialUser user);

    @RequestMapping(method = RequestMethod.GET,value = "/email/{email}")
    ApiResponse getUserByEmail(@PathVariable(value = "email") String email);
}
