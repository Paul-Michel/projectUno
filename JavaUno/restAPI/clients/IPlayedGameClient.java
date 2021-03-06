package restAPI.clients;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@FeignClient("PlayedGameClient")
public interface IPlayedGameClient {

    @RequestLine("GET /{id}")
    Object getOne(@Param("id") Long id);

    @RequestLine("GET /")
    Object getAll();
}
