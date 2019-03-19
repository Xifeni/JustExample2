package core.searcher;

import core.model.ClassItem;
import core.searcher.conditions.ExistStarPredicate;
import core.searcher.conditions.ExistUppercaseLetterPredicate;
import core.searcher.conditions.ExitOnlyLowercaseLetterPredicate;
import core.searcher.conditions.WhitespaceEndingPredicate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseSearcher implements Searcher {

    private final List<ClassItem> classes;
    private final String searchPattern;

    public BaseSearcher(List<ClassItem> classes, String searcherPattern) {
        this.classes = classes;
        this.searchPattern = searcherPattern;
    }

    public List<ClassItem> search() {
        Stream<ClassItem> stream = classes.stream();

        if (searchPattern.contains("*")) {
            stream = stream.filter(new ExistStarPredicate(searchPattern));
        }
        if (checkSearchPatternFromUpperCase()) {
            stream = stream.filter(new ExistUppercaseLetterPredicate(searchPattern));
        } else {
            stream = stream.filter(new ExitOnlyLowercaseLetterPredicate(searchPattern));
        }
        if (searchPattern.endsWith(" ")) {
            stream = stream.filter(new WhitespaceEndingPredicate(searchPattern));
        }
        return stream.sorted(Comparator.comparing(ClassItem::getClassName))
                .collect(Collectors.toList());
    }

    private boolean checkSearchPatternFromUpperCase() {
        char[] letters = searchPattern.toCharArray();
        for (Character letter : letters) {
            if (Character.isUpperCase(letter)) {
                return true;
            }
        }
        return false;
    }
}
