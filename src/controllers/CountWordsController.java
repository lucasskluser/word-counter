/**
 * Trabalho II de Programação II
 * Alunos Lucas Samuel e Matheus Boing
 */
package controllers;

import services.TextFileService;
import services.WordService;
import views.MainView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;

/**
 * Controlador responsável por requisitar e exibir a contagem de palavras
 * de um determinado arquivo
 */
public class CountWordsController {
    private TextFileService textFileService;
    private WordService wordService;
    private MainView mainView;
    private String openedFile;

    public CountWordsController() {
        textFileService = new TextFileService();
        wordService = new WordService();
        this.mainView = new MainView(this);
    }

    /**
     * Exibe a view
     */
    public void view() {
        mainView.show();
    }

    /**
     * Abre a seleção de arquivos e processa um arquivo TXT
     * @param filePath Endereço do arquivo/diretório
     */
    public void read(String filePath) {
        File file = new File(filePath);
        JFileChooser fileChooser = new JFileChooser();

        try {
            if (file.isDirectory() || openedFile.equals(filePath)) {
                fileChooser.setCurrentDirectory(new File(filePath));

                FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
                fileChooser.setFileFilter(filter);

                int returnVal = fileChooser.showOpenDialog(mainView.getJPanel());

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }

            try {
                HashMap<String, Integer> wordsMap = countWordsInFile(file);
                mainView.loadWordsInTable(file.getAbsolutePath(), wordsMap);
                openedFile = file.getAbsolutePath();
            } catch (FileNotFoundException ex) {
                showError("Arquivo não encontrado");
            } catch (IllegalArgumentException ex) {
                showError("O endereço informado não é um arquivo válido");
            } catch (Exception ex) {
                showError("Não foi possível ler o arquivo");
            }
        } catch (Exception ex) {
            showError("Não foi possível abrir o arquivo");
        }
    }

    /**
     * Conta as palavras em um arquivo
     * @param file Arquivo
     * @return Mapa chave-valor das palavras e suas frequências
     * @throws FileNotFoundException
     */
    public HashMap<String, Integer> countWordsInFile(File file) throws FileNotFoundException {
        Collection<String> words = textFileService.readFileAsCollection(file);
        HashMap<String, Integer> wordsMap = wordService.countWords(words);
        return wordsMap;
    }

    /**
     * Exibe uma mensagem de informação
     * @param message Mensagem a ser exibida
     */
    public void showInfo(String message) {
        mainView.showInfo(message);
    }

    /**
     * Exibe uma mensagem de erro
     * @param message Mensagem a ser exibida
     */
    public void showError(String message) {
        mainView.showError(message);
    }
}
