package uno.restapi.clients;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import uno.restapi.models.CardPlayed;
import uno.restapi.structs.Result;

import java.util.ArrayList;
import java.util.List;

@Component
@FeignClient("EngineClient")
public interface IEngineClient {

    @RequestLine("POST /newgame")
    @Body("{players}")
    void setNewGame(@Param("players") String players);

    @RequestLine("GET /newturn/{playerIdx}")
    Result getNewTurn(@Param("playerIdx") Integer playerIdx);

    @RequestLine("POST /effect")
    @Headers("Content-Type: application/json")
    Result getEffect(@RequestBody CardPlayed cardPlayed);
}
