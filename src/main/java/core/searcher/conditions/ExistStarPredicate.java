package core.searcher.conditions;

import core.model.ClassItem;

import java.util.function.Predicate;

public class ExistStarPredicate implements Predicate<ClassItem> {

    private final String searchPattern;

    public ExistStarPredicate(String searchPattern) {
        this.searchPattern = searchPattern;
    }

    @Override
    public boolean test(ClassItem classItem) {
        int offset = 0;
        final char[] patternLetters = searchPattern.replace("*", "").toCharArray();
        final char[] classNameLetters = classItem.getClassName().toCharArray();

        for (char currentChar : classNameLetters) {
            if (offset < patternLetters.length && patternLetters[offset] == currentChar) {
                offset++;
            }
        }

        return offset == patternLetters.length;
    }
}
