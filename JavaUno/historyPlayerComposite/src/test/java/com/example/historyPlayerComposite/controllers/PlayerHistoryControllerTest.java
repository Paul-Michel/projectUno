package com.example.historyPlayerComposite.controllers;


import com.example.historyPlayerComposite.entities.History;
import com.example.historyPlayerComposite.entities.PlayerHistory;
import com.example.historyPlayerComposite.services.PlayerHistoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerHistoryControllerTest {

    @Mock
    private PlayerHistoryService playerHistoryService;

    @Autowired
    @InjectMocks
    private PlayerHistoryController playerHistoryController;

    @Autowired
    private MockMvc mockMvc;

    private static final String CONTROLLER_RESPONSE =
            "{\"id\": 1,"
            + "\"name\":\"Flacype\","
            + "\"playedGames\": ["
            + "{\" id\":1,"
            + "\"datePlayed\":\"2018-11-13T10:31:24.601+0000\","
            + "\"firstWinnerId\":\" 1\","
            + "\"secondWinnerId\":\" 4\","
            + "\"thirdWinnerId\":\" 3\","
            + "\"fourthWinnerId\":\" 2\"},"
            + "{\"id\": 4,"
            + "\"datePlayed\": \"2018-11-13T10:31:24.700+0000\","
            + "\"firstWinnerId\":\" 1\","
            + "\"secondWinnerId\":\" 4\","
            + "\"thirdWinnerId\":\" 3\","
            + "\"fourthWinnerId\":\" 2\"]}"
            + "}";

    private List<History> HISTORY_RESPONSE = new ArrayList<>();


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        //playerHistoryController.setPlayerHistoryService(playerHistoryService);
    }


    @Test
    public void should_return_valid_response() throws Exception {
        HISTORY_RESPONSE.add(new History(1L, 2L, 3L, 4L));
        Mockito.when(playerHistoryService.getOneById(1L)).thenReturn(new PlayerHistory(1L, "Flacype", HISTORY_RESPONSE));
        this.mockMvc.perform(get("/wonbyplayer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(CONTROLLER_RESPONSE)));
    }
}