import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Timeout(22)
    @Disabled
    void main() throws Exception {
        String[] arrayArgs = new String[2];
        Main.main(arrayArgs);
    }
}