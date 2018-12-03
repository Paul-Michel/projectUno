package uno.engine.clients;


import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient("PlayedGame")
public interface IPlayedGame {

    @RequestLine("POST /")
    @Headers("Content-Type: application/json")
    void setPlayedGame(@RequestBody List<String> WinnersId);
}
