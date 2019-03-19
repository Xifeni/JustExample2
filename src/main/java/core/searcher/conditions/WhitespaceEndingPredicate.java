package core.searcher.conditions;

import core.model.ClassItem;

import java.util.Collections;
import java.util.LinkedList;
import java.util.function.Predicate;

public class WhitespaceEndingPredicate implements Predicate<ClassItem> {

    private final String searchPattern;

    public WhitespaceEndingPredicate(String searchPattern) {
        this.searchPattern = searchPattern;
    }

    @Override
    public boolean test(ClassItem classItem) {
        LinkedList<String> results = new LinkedList<>();
        StringBuilder word = new StringBuilder();
        for (int i = searchPattern.length() - 1; i >= 0; i--) {
            char character = searchPattern.charAt(i);
            word.append(character);
            if (Character.isUpperCase(character)) {
                results.add(word.reverse().toString().trim());
                word = new StringBuilder();
            }
        }
        Collections.reverse(results);
        return classItem.getClassName().endsWith(results.getLast().trim());
        }
    }