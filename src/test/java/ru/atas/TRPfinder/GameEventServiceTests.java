package ru.atas.TRPfinder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import ru.atas.TRPfinder.Records.GameEventRecord;
import ru.atas.TRPfinder.Repositories.GameEventRepository;
import ru.atas.TRPfinder.Services.GameEventService;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest()
public class GameEventServiceTests {

    @Autowired
    private GameEventService gameEventService;

    private static GameEventRecord gameRecord;

    @BeforeAll
    public static void initData(){
        ZonedDateTime time = ZonedDateTime.now();
        gameRecord = new GameEventRecord(time, "testGame", "nowhere", "description");
    }

    @Test
    @DisplayName("test test")
    public void getDoubledValueTest(){
        assertEquals(6, gameEventService.getDoubledValue(3));
    }

    @Test
    @DisplayName("adding game")
    public void addGameTest(){
        gameEventService.addNewGame(gameRecord);

        var games = gameEventService.getAllGames();


        assertTrue(games.stream().anyMatch(x -> x.getName() == gameRecord.name()));

    }
}
