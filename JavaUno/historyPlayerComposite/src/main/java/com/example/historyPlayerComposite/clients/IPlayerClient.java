package com.example.historyPlayerComposite.clients;


import com.example.historyPlayerComposite.entities.Player;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("PlayerClient")
public interface IPlayerClient {

    @RequestLine("GET /{username}")
    Player getOneByUsername(@Param("username") String username);

    @RequestLine("GET /id/{id}")
    Player getOneById(@Param("id") String id);

}
