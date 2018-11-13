package com.example.historyPlayerComposite.clients;


import com.example.historyPlayerComposite.entities.History;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FeignClient("HistoryClient")
public interface IHistoryClient {

    @RequestLine("GET /winner/{id}")
    Object getAllByfirstWinnerId(@Param("id") Long id);
}
