package core.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BaseClassItemManager implements ClassItemManager {

    private final List<ClassItem> classItems;

    public BaseClassItemManager(List<String> processedClasses) {
        this.classItems =
                Collections.unmodifiableList(
                        processedClasses.stream()
                                .map(item -> new ClassItem(prepareClassPath(item), prepareClassName(item)))
                                .collect(Collectors.toList()));
    }

    @Override
    public List<ClassItem> getPreparedClassItems() {
        return classItems;
    }

    private String prepareClassPath(String classPath) {
        if (!classPath.contains(".")) {
            return "";
        }
        return classPath.substring(0, classPath.lastIndexOf("."));
    }

    private String prepareClassName(String className) {
        if (!className.contains(".")) {
            return className;
        }
        return className.substring(className.lastIndexOf(".") + 1);
    }
}
