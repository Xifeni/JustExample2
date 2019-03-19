import core.model.BaseClassItemManager;
import core.model.ClassItem;
import core.reader.file.BaseFileReader;
import core.reader.file.SourceReader;
import core.searcher.BaseSearcher;
import core.searcher.Searcher;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BaseSercherTest {
    private BaseClassItemManager manager;
    private Searcher searcher;

    @Before
    public void init() {
        SourceReader srcReader = new BaseFileReader("classes.txt");
        manager = new BaseClassItemManager(srcReader.getProcessedClasses());
    }

    @Test
    public void testUpperCaseSearchPattern() {
        searcher = new BaseSearcher(manager.getPreparedClassItems(), "FBB");
        assertEquals(searcher.search().get(0), new ClassItem("a.b", "FooBarBaz"));

        List<ClassItem> items = new ArrayList<>();
        items.add(new ClassItem("c.d", "FooBar"));
        items.add(new ClassItem("a.b", "FooBarBaz"));
        searcher = new BaseSearcher(manager.getPreparedClassItems(), "FB");
        assertEquals(searcher.search(), items);
    }

    @Test
    public void testLowerCaseSearchPattern() {
        searcher = new BaseSearcher(manager.getPreparedClassItems(), "fbb");
        assertEquals(searcher.search().get(0), new ClassItem("a.b", "FooBarBaz"));

        searcher = new BaseSearcher(manager.getPreparedClassItems(), "to");
        assertEquals(searcher.search().get(0), new ClassItem("", "TelephoneOperator"));
    }

    @Test
    public void testExistStarSearchPattern() {
        searcher = new BaseSearcher(manager.getPreparedClassItems(), "W*hMaker");
        assertEquals(searcher.search().get(0), new ClassItem("codeborne", "WishMaker"));
    }

    @Test
    public void testWhitespaceEndingTest() {
        searcher = new BaseSearcher(manager.getPreparedClassItems(), "FBar ");
        assertEquals(searcher.search().get(0), new ClassItem("c.d", "FooBar"));
    }

    @Test
    public void complexTest() {
        searcher = new BaseSearcher(manager.getPreparedClassItems(), "dontW*W*Luxsoft");
        assertEquals(searcher.search().get(0), new ClassItem("", "dontWorkWithLuxsoft"));
    }
}
