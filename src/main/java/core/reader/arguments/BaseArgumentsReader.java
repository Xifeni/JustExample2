package core.reader.arguments;

public class BaseArgumentsReader implements ArgumentsReader {

    private final String fileName;
    private final String searchPattern;

    public BaseArgumentsReader(String... args) {
        checkParams(args);
        this.fileName = args[0];
        this.searchPattern = args[1];
    }

    private void checkParams(String... args) {
        if(args.length != 2) {
            throw new IllegalArgumentException("Only two arguments are required. Use this goal: ClassFinder <fileName> <searchPattern>");
        }
        if ((args[0] == null || args[0].isEmpty()) && (args[1] == null || args[1].isEmpty())) {
            throw new IllegalArgumentException("Not empty arguments are required!");
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getSearchPattern() {
        return searchPattern;
    }
}
