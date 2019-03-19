package core.searcher.conditions;

import core.model.ClassItem;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

public class ExistUppercaseLetterPredicate implements Predicate<ClassItem> {

    private final String searchPattern;

    public ExistUppercaseLetterPredicate(String searchPattern) {
        this.searchPattern = searchPattern;
    }


    @Override
    public boolean test(ClassItem classItem) {
        final List<String> conditions = getPreparedFilter(searchPattern);
        final List<String> words = getPreparedFilter(classItem.getClassName());
        if (words.size() < conditions.size()) {
            return false;
        }
        for (int i = 0; i < conditions.size(); i++) {
            if (!words.get(i).startsWith(conditions.get(i))) {
                return false;
            }
        }
        return true;
    }

    private List<String> getPreparedFilter(String words) {
        List<String> results = new LinkedList<>();
        StringBuilder word = new StringBuilder();
        for (int i = words.length() - 1; i >= 0; i--) {
            char character = words.charAt(i);
            word.append(character);
            if (Character.isUpperCase(character)) {
                results.add(word.reverse().toString().trim());
                word = new StringBuilder();
            }
        }
        Collections.reverse(results);
        ListIterator<String> iterator = results.listIterator();
        while (iterator.hasNext()){
            String part = iterator.next();
            if (part.contains("*")) {
                iterator.set(part.substring(0,0));
            }
        }
        return results;
    }
}
