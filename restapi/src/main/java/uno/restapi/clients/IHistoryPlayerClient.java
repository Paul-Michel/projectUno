package uno.restapi.clients;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("HistoryPlayerClient")
public interface IHistoryPlayerClient {

    @RequestLine("GET /{id}")
    Object getOne(@Param("id") Long id);
}
