import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;

public class HippodromeTest {
    @ParameterizedTest
    @NullSource
    void constructorWithNullListException(List<Horse> horses) {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
    }

    @ParameterizedTest
    @NullSource
    void constructorWithNullListExceptionMessage(List<Horse> horses) {
        String messageExpected = "Horses cannot be null.";
        try {
            new Hippodrome(horses);
        } catch (Exception e) {
            assertEquals(messageExpected, e.getMessage());
        }
    }

    @Test
    void constructorWithEmptyListException() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
    }

    @Test
    void constructorWithEmptyListExceptionMessage() {
        String messageExpected = "Horses cannot be empty.";
        List<Horse> horses = new ArrayList<>();
        try {
            new Hippodrome(horses);
        } catch (Exception e) {
            assertEquals(messageExpected, e.getMessage());
        }
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("horse" + i, i+1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertArrayEquals(horses.toArray(), hippodrome.getHorses().toArray());
    }

    @Test
    void move() {
        List <Horse> horsesMock = new ArrayList<>();
        Horse horseMock = Mockito.mock(Horse.class);

        for (int i = 0; i < 50; i++) {
            horsesMock.add(horseMock);
        }
        Hippodrome hippodrome = new Hippodrome(horsesMock);
        hippodrome.move();
        Mockito.verify(horseMock, Mockito.times(50)).move();
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        Horse winnerHorse = new Horse("winner", 5.1, 6.0);
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                horses.add(winnerHorse);
                continue;
            }
            horses.add(new Horse("horse" + i, i + 1, 5.0));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(winnerHorse, hippodrome.getWinner());
    }
}
