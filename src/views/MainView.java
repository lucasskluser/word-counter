/**
 * Trabalho II de Programação II
 * Alunos Lucas Samuel e Matheus Boing
 */
package views;

import controllers.CountWordsController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainView {
    private JTextField textFieldFile;
    private JButton buttonAnalyse;
    private JRadioButton radioButtonSortByKey;
    private JRadioButton radioButtonSortByValue;
    private JTable tableWordsFrequency;
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private JLabel labelTotalWords;
    private JLabel labelTotalDistinctWords;

    private DefaultTableModel tableModel;
    private TableRowSorter<TableModel> tableSorter;

    private CountWordsController countWordsController;
    private int columnIndexSorter;

    public MainView(CountWordsController countWordsController) {
        this.countWordsController = countWordsController;
        createListeners();
        setColumnIndexSorter(0);
    }

    /**
     * Instancia componentes da view
     */
    private void createUIComponents() {
        Object[] columns = { "Palavra", "Frequência" };
        tableModel = new DefaultTableModel(columns, 0);
        tableWordsFrequency = new JTable(tableModel);

        tableSorter = new TableRowSorter<>(tableWordsFrequency.getModel());
        tableWordsFrequency.setRowSorter(tableSorter);

        jScrollPane = new JScrollPane(tableWordsFrequency);
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    /**
     * Define o índice da coluna para ordenação, define o radio button
     * de ordenação respectivo e solicita a ordenação da tabela
     * @param columnIndex Índice da coluna
     */
    private void setColumnIndexSorter(int columnIndex) {
        columnIndexSorter = columnIndex;

        if (columnIndexSorter == 0)
            radioButtonSortByKey.setSelected(true);
        else
            radioButtonSortByValue.setSelected(true);

        sortTableByColumnIndex(columnIndexSorter);
    }

    /**
     * Instancia um novo JFrame
     * @return Novo JFrame
     */
    private JFrame createJFrame() {
        JFrame frame = new JFrame();
        frame.setContentPane(jPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Contador de Palavras - Lucas Samuel & Matheus Boing");
        frame.setAutoRequestFocus(true);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);

        return frame;
    }

    /**
     * Define os listeners de ação
     */
    private void createListeners() {
        buttonAnalyse.addActionListener(e -> buttonAnalyseOnClickListener());

        radioButtonSortByKey.addActionListener(e -> radioButtonSortByKeyListener());
        radioButtonSortByValue.addActionListener(e -> radioButtonSortByValueListener());

        textFieldFile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    textFieldFileOnEnterPressListener();
                } catch (Exception ex) {
                    showError("Não foi possível processar o caminho do arquivo");
                }
            }
        });
    }

    private void buttonAnalyseOnClickListener() {
        if (textFieldFile.getText().length() > 0) {
            countWordsController.selectFile(textFieldFile.getText());
        }
    }

    private void radioButtonSortByKeyListener() {
        setColumnIndexSorter(0);
    }

    private void radioButtonSortByValueListener() {
        setColumnIndexSorter(1);
    }

    private void textFieldFileOnEnterPressListener() {
        buttonAnalyseOnClickListener();
    }

    /**
     * Exibe o JFrame
     */
    public void show() {
        JFrame frame = createJFrame();
        textFieldFile.setText(System.getProperty("user.dir"));
        frame.setVisible(true);
    }

    /**
     * Carrega os dados das palavras contadas na tabela
     * @param absolutePath Caminho absoluto do arquivo analisado
     * @param wordsMap Mapa da contagem de palavras
     */
    public void loadWordsInTable(String absolutePath, HashMap<String, Integer> wordsMap) {
        textFieldFile.setText(absolutePath);
        int totalWords = 0;

        tableModel.setRowCount(0);

        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            tableModel.addRow(new Object[] { entry.getKey(), entry.getValue() });
            totalWords += entry.getValue();
        }

        labelTotalWords.setText(String.valueOf(totalWords));
        labelTotalDistinctWords.setText(String.valueOf(wordsMap.size()));

        sortTableByColumnIndex(columnIndexSorter);
    }

    /**
     * Ordena a tabela por uma coluna
     * @param columnIndex Índice da coluna
     */
    private void sortTableByColumnIndex(int columnIndex) {
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        SortOrder sortOrder = columnIndex == 0 ? SortOrder.ASCENDING : SortOrder.DESCENDING;

        sortKeys.add(new RowSorter.SortKey(columnIndex, sortOrder));
        tableSorter.setSortKeys(sortKeys);
        tableSorter.sort();
    }

    /**
     * Exibe uma mensagem de erro
     * @param message Mensagem a ser exibida
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(jPanel, message, "Ops!", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Exibe uma mensagem de informação
     * @param message Mensagem a ser exibida
     */
    public void showInfo(String message) {
        JOptionPane.showMessageDialog(jPanel, message, null, JOptionPane.INFORMATION_MESSAGE);
    }
}
