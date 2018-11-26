package com.example.Uno.client;

import com.example.Uno.entities.Player;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("PlayerClient")
public interface IPlayerClient {

    @RequestLine("GET /{id}")
    Player getOneById(@Param("id") Long id);

}
