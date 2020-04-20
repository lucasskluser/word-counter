package services;

import java.util.Collection;
import java.util.HashMap;

/**
 * Serviço de contagem e tratamento de palavras
 */
public class WordService {
    /**
     * Faz a contagem de palavras em uma coleção de palavras
     * @param words Coleção de palavras ou linhas
     * @return Mapa chave-valor com as palavras e suas frequências
     */
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

    /**
     * Inclui a palavra no mapa chave-valor ou atualiza
     * a sua respectiva frequência
     * @param wordsMap Mapa chave-valor de palavras
     * @param word Palavra
     */
    private void countWord(HashMap<String, Integer> wordsMap, String word) {
        if (word.length() > 0) {
            word = normalizeWord(word);

            if (wordsMap.containsKey(word)) {
                int count = wordsMap.get(word);
                wordsMap.replace(word, ++count);
            } else {
                wordsMap.put(word, 1);
            }
        }
    }

    /**
     * Normaliza a palavra, removendo pontuações e espaços em branco
     * @param word Palavra a ser normalizada
     * @return Palavra normalizada
     */
    private String normalizeWord(String word) {
        String[] punctuations = {
          ".", ",", ";", ":", "\"", "'", "\\", "/", "(", ")", "[", "]", "{", "}", "<", ">", "-", "+", "*", " "
        };

        for (String punctuation : punctuations) {
            word = word.replace(punctuation, "");
        }

        return word;
    }
}
