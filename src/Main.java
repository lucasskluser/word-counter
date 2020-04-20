/**
 * Trabalho II de Programação II
 * Alunos Lucas Samuel e Matheus Boing
 */
import controllers.CountWordsController;

public class Main {
    public static void main(String[] args) {
        CountWordsController countWordsController = new CountWordsController();

        try {
            countWordsController.view();
        } catch (Exception ex) {
            countWordsController.showError("Houve um erro durante a execução do programa.");
            System.exit(-1);
        }
    }
}
