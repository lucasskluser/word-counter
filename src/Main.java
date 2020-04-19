import services.TextFileService;
import services.WordService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TextFileService textFileService = new TextFileService();
        Collection<String> lines = textFileService.readFileAsCollection(".\\temp\\file.txt");

        WordService wordService = new WordService();
        HashMap<String, Integer> words = wordService.countWords(lines);

        words.forEach((key, value) -> System.out.println(String.format("%s - %s", key, value)));
    }
}
