package uno.restapi.clients;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@FeignClient("CardClient")
public interface ICardClient {

    @RequestLine("GET /{id}")
    Object getOne(@Param("id") Long id);

    @RequestLine("GET /")
    Object getAll();
}
