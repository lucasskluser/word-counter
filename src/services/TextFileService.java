/**
 * Trabalho II de Programação II
 * Alunos Lucas Samuel e Matheus Boing
 */
package services;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Serviço responsável por processar um arquivo de texto
 */
public class TextFileService {
    /**
     * Lê um arquivo de texto como uma coleção
     * @param file Arquivo
     * @return Coleção de strings do arquivo
     * @throws FileNotFoundException
     */
    public Collection<String> readFileAsCollection(File file) throws FileNotFoundException {
        validateFile(file);

        Scanner scanner = new Scanner(file);
        Collection<String> lines = new ArrayList<>();

        do {
            lines.add(scanner.nextLine());
        } while (scanner.hasNext());

        return lines;
    }

    /**
     * Valida o arquivo a ser processado
     * @param file Arquivo
     * @throws FileNotFoundException
     */
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
