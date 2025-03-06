package com.jocata.oms.service;

import com.jocata.oms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/check")
public class CustomUserDetailsService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/get")
    public Mono<User> getUserDetails(@RequestParam String email) {
        String url = "http://localhost:8081/users/admin/getpassandid?email=" + email;

        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(User.class)
                .doOnNext(user -> {
                    System.out.println(user.getUserId());
                    System.out.println(user.getPasswordHash());
                    System.out.println(user.getRoleName());
                });
    }
}
