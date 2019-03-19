import core.reader.file.BaseFileReader;
import org.junit.Test;

public class BaseFileReaderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        new BaseFileReader(" ");
    }
}
