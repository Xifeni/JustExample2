package core.model;

import java.util.Objects;

public class ClassItem {

    private final String className;
    private final String classPath;

    public ClassItem( String classPath, String className) {
        this.className = className;
        this.classPath = classPath;
    }

    public String getAbsolutePath() {
        return classPath.equals("") ? className : classPath + "." + className;
    }

    public String getClassName() {
        return className;
    }

    public String getClassPath() {
        return classPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassItem item = (ClassItem) o;
        return Objects.equals(className, item.className) &&
                Objects.equals(classPath, item.classPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, classPath);
    }
}
