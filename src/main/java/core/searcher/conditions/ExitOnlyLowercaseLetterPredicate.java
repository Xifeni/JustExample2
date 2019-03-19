package core.searcher.conditions;

import core.model.ClassItem;

import java.util.function.Predicate;

import static java.lang.Character.isUpperCase;

public class ExitOnlyLowercaseLetterPredicate implements Predicate<ClassItem> {
    private final String searchPattern;

    public ExitOnlyLowercaseLetterPredicate(String searchPattern) {
        this.searchPattern = searchPattern;
    }


    @Override
    public boolean test(ClassItem classItem) {
        final String processedClassName = getProcessedClassName(classItem.getClassName());
        return processedClassName.equalsIgnoreCase(searchPattern);
    }

    private String getProcessedClassName(String className) {
        char[] letters = className.toCharArray();
        StringBuilder result = new StringBuilder();
        for (Character letter : letters) {
            if (isUpperCase(letter)){
                result.append(letter);
            }
        }
        return result.toString();
    }
}
