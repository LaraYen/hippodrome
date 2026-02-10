import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @ParameterizedTest
    @NullSource
    void constructorWithNullParamException(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 0.0, 0.0));
    }

    @Test
    void constructorWithNullParamExceptionMessage() {
        String messageExpected = "Name cannot be null.";
        try {
            new Horse(null, 0.0, 0.0);
        } catch (Exception e) {
            assertEquals(messageExpected, e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { " ", "", "\t", "\n" })
    void constructorWithEmptyParamException(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 0.0, 0.0));
    }

    @ParameterizedTest
    @ValueSource(strings = { " ", "", "\t", "\n" })
    void constructorWithEmptyParamExceptionMessage(String name) {
        String messageExpected = "Name cannot be blank.";
        try {
            new Horse(name, 0.0, 0.0);
        } catch (Exception e) {
            assertEquals(messageExpected, e.getMessage());
        }
    }

    @Test
    void constructorWithSecondNegativeParamException() {
        double speed = -1.0;
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("name", speed, 0.0));
    }

    @Test
    void constructorWithSecondNegativeParamExceptionMessage() {
        double speed = -1.0;
        String messageExpected = "Speed cannot be negative.";
        try {
            new Horse("name", speed, 0.0);
        } catch (Exception e) {
            assertEquals(messageExpected, e.getMessage());
        }
    }

    @Test
    void constructorWithThirdNegativeParamException() {
        double distance = -1.0;
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("name", 0.0, distance));
    }

    @Test
    void constructorWithThirdNegativeParamExceptionMessage() {
        double distance = -1.0;
        String messageExpected = "Distance cannot be negative.";
        try {
            new Horse("name", 0.0, distance);
        } catch (Exception e) {
            assertEquals(messageExpected, e.getMessage());
        }
    }

    @Test
    void getName() {
        String name = "Horse";
        Horse horse = new Horse(name, 0.0, 0.0);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed() {
        double speed = 1.0;
        Horse horse = new Horse("name", 1.0, 0.0);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        double distance = 1.0;
        Horse horse = new Horse("name", 0.0, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void getDistanceWhenConstructorIncomplete() {
        Horse horse = new Horse("name", 0.0);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void moveInvokeMethodGetRandomDouble() {
        Horse horse = new Horse("name", 0.0, 0.0);
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void moveWithCorrectDistance() {
        Horse horse = new Horse("name", 2.0, 0.0);
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(0.3);
            horse.move();
        }
        double expectedDistance = horse.getSpeed() * 0.3;
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

}
