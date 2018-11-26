package uno.engine.clients;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import uno.engine.entities.Card;

import java.util.List;

@Component
@FeignClient("CardClient")
public interface ICardClient {

    @RequestLine("GET /{id}")
    Object getOne(@Param("id") Long id);

    @RequestLine("GET /")
    List<Card> getAll();
}
