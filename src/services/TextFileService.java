package services;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class TextFileService {
    public Collection<String> readFileAsCollection(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        validateFile(file);

        Scanner scanner = new Scanner(file);
        Collection<String> lines = new ArrayList<>();

        do {
            lines.add(scanner.nextLine());
        } while (scanner.hasNext());

        return lines;
    }

    private void validateFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException("The path is not a file.");
        }

        if (!file.getName().endsWith(".txt")) {
            throw new IllegalArgumentException("Invalid file format. The file is not in TXT format.");
        }
    }
}
