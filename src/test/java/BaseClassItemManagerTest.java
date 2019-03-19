import core.model.BaseClassItemManager;
import core.model.ClassItem;
import core.reader.file.BaseFileReader;
import core.reader.file.SourceReader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BaseClassItemManagerTest {

    private int expected = 11;
    private BaseClassItemManager manager;
    private List<ClassItem> items;

    @Before
    public void init() {
        SourceReader srcReader = new BaseFileReader("classes.txt");
        manager = new BaseClassItemManager(srcReader.getProcessedClasses());
        items = new ArrayList<>();
        items.add(new ClassItem("a.b","FooBarBaz"));
        items.add(new ClassItem("c.d","FooBar"));
        items.add(new ClassItem("","BrBaz"));
        items.add(new ClassItem("codeborne","WishMaker"));
        items.add(new ClassItem("codeborne","MindReader"));
        items.add(new ClassItem("","TelephoneOperator"));
        items.add(new ClassItem("","ScubaArgentineOperator"));
        items.add(new ClassItem("","YoureLeavingUsHere"));
        items.add(new ClassItem("","YouveComeToThisPoint"));
        items.add(new ClassItem("","YourEyesAreSpinningInTheirSockets"));
        items.add(new ClassItem("","dontWorkWithLuxsoft"));
        items = Collections.unmodifiableList(items);
    }

    @Test
    public void testLength(){
        assertEquals(manager.getPreparedClassItems().size(), expected);
    }

    @Test
    public void testEqualsItems(){
             assertTrue(items.containsAll(manager.getPreparedClassItems()));
    }
}
