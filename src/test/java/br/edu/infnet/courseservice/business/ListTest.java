package br.edu.infnet.courseservice.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListTest {

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {

        // Given / Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10);

        // When / Act & Then / Assert
        assertEquals(10, list.size());
        assertEquals(10, list.size());
        assertEquals(10, list.size());
    }

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {

        // Given / Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);

        // When / Act & Then / Assert
        assertEquals(10, list.size());
        assertEquals(20, list.size());
        assertEquals(20, list.size());
    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnInfnet() {

        // Given / Arrange
        var list = mock(List.class);
        when(list.get(0)).thenReturn("Infnet");

        // When / Act & Then / Assert
        assertEquals("Infnet", list.get(0));
        assertNull(list.get(1));
    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnInfnet() {

        // Given / Arrange
        var list = mock(List.class);

        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        when(list.get(anyInt())).thenReturn("Infnet");

        // When / Act & Then / Assert
        assertEquals("Infnet", list.get(anyInt()));
        assertEquals("Infnet", list.get(anyInt()));
    }

    @Test
    void testMockingList_When_ThrowsAnException() {

        // Given / Arrange
        var list = mock(List.class);

        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        when(list.get(anyInt())).thenThrow(new RuntimeException("Foo Bar!!"));

        // When / Act & Then / Assert
        assertThrows(RuntimeException.class,
                () -> {
                    // When / Act
                    list.get(anyInt());},
                () -> "Should have throw an RuntimeException");
    }

}
