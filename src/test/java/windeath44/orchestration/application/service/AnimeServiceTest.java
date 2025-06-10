package windeath44.orchestration.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.domain.port.out.CharacterEventPublisher;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AnimeServiceTest {

    @Mock
    private CharacterEventPublisher characterMemorializingEventPublisher;

    @InjectMocks
    private AnimeService animeService;

    @Test
    void memorial_ShouldCallPublisher() {
        // Act
        animeService.memorial();

        // Assert
        verify(characterMemorializingEventPublisher, times(1)).publish(null);
    }
}