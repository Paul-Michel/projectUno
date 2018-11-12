package restAPI.clients;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@FeignClient("PlayedGame")
public interface IPlayedGameClient {

    @RequestLine("GET /{id}")
    ResponseEntity getOneById(@Param("id") Long id);
}
