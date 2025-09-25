package kh.com.kshrd.productservice.client;

import kh.com.kshrd.productservice.model.dto.response.APIResponse;
import kh.com.kshrd.productservice.model.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-service", path = "/api/v1/users")
public interface UserClient {

    @GetMapping
    ResponseEntity<APIResponse<UserResponse>> getUserInfo();

}
