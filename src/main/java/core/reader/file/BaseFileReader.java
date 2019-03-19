package core.reader.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;

public class BaseFileReader implements SourceReader {

    private final List<String> classes;

    public BaseFileReader(String fileName) {
        classes = read(fileName);
    }

    public List<String> getProcessedClasses() {
        return classes.stream()
                .filter(e -> !e.isEmpty())
                .map(String::trim)
                .collect(toList());
    }

    private List<String> read(String fileName) {
        try {
            return Files.lines(get(ClassLoader.getSystemResource(fileName).toURI()), StandardCharsets.UTF_8).collect(toList());
        } catch (IOException | URISyntaxException e) {
            throw new IllegalArgumentException("File " + fileName + " doesn't exists.");
        }
    }
}
