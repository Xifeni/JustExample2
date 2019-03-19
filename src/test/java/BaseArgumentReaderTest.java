import core.reader.arguments.ArgumentsReader;
import core.reader.arguments.BaseArgumentsReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BaseArgumentReaderTest {

    private String[] args = {"classes.txt", "searchPattern"};
    private ArgumentsReader reader;

    @Before
    public void init() {
        reader = new BaseArgumentsReader(args);
    }

    @Test
    public void test(){
        assertEquals(reader.getFileName(), args[0]);
        assertEquals(reader.getSearchPattern(), args[1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectQuantityArgs(){
        reader = new BaseArgumentsReader("212","212","21212");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionEmptyArgs(){
        reader = new BaseArgumentsReader("", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionNullArgs(){
        reader = new BaseArgumentsReader(null, "");
    }
}
