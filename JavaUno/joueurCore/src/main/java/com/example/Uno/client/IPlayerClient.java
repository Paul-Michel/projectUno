package com.example.Uno.client;

import com.example.Uno.entities.Player;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FeignClient("PlayerClient")
public interface IPlayerClient {

    @RequestLine("GET /")
    List<Player> getAll();

    @RequestLine("GET /{id}")
    List<Player> getOneById(@Param("id") String id);

}
