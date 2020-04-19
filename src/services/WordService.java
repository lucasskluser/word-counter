package services;

import java.util.Collection;
import java.util.HashMap;

public class WordService {
    public HashMap<String, Integer> countWords(Collection<String> words) {
        HashMap<String, Integer> wordsMap = new HashMap<>();

        words.forEach(word -> {
            String[] split = word.split(" ");

            for (int i = 0; i < split.length; i++) {
                countWord(wordsMap, split[i]);
            }
        });

        return wordsMap;
    }

    private void countWord(HashMap<String, Integer> wordsMap, String word) {
        if (wordsMap.containsKey(word)) {
            int count = wordsMap.get(word);
            wordsMap.replace(word, ++count);
        } else {
            wordsMap.put(word, 1);
        }
    }
}
