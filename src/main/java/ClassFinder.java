import core.model.BaseClassItemManager;
import core.model.ClassItemManager;
import core.reader.arguments.ArgumentsReader;
import core.reader.arguments.BaseArgumentsReader;
import core.reader.file.BaseFileReader;
import core.reader.file.SourceReader;
import core.searcher.BaseSearcher;
import core.searcher.Searcher;

public class ClassFinder {

    public static void main(String[] args) {
        ArgumentsReader argReader = new BaseArgumentsReader(args);
        SourceReader srcReader = new BaseFileReader(argReader.getFileName());
        ClassItemManager itemManager = new BaseClassItemManager(srcReader.getProcessedClasses());
        Searcher searcher = new BaseSearcher(itemManager.getPreparedClassItems(), argReader.getSearchPattern());

        searcher.search().forEach(e -> System.out.println(e.getAbsolutePath()));
    }
}
