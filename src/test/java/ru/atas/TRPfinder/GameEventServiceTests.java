package ru.atas.TRPfinder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.atas.TRPfinder.Repositories.GameEventRepository;
import ru.atas.TRPfinder.Services.GameEventService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GameEventServiceTests {
    @Mock
    private GameEventRepository gameEventRepository;

    @InjectMocks
    private GameEventService gameEventService;

    @Test
    @DisplayName("test test")
    public void getDoubledValueTest(){
        assertEquals(6, gameEventService.getDoubledValue(3));
    }
}
