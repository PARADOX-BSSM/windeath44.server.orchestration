package windeath44.orchestration.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.domain.port.out.CharacterEventPublisher;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimeServiceTest {

    @Mock
    private CharacterEventPublisher characterMemorializingEventPublisher;

    @InjectMocks
    private AnimeService animeService;

    @Test
    @DisplayName("memorial 메서드 호출 시 publisher가 null 파라미터로 호출되는지 테스트")
    void memorialTest() {
        // Given
        doNothing().when(characterMemorializingEventPublisher).publish(null);

        // When
        animeService.memorial();

        // Then
        verify(characterMemorializingEventPublisher, times(1)).publish(null);
    }
}