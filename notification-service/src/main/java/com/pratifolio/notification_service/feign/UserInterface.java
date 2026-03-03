package com.pratifolio.notification_service.feign;

import com.pratifolio.notification_service.model.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("USER-SERVICE")
public interface UserInterface {
    @GetMapping("user/getUserDetails/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable int id);
}
