import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GuessTheNumberGame {
    private final int guessNumber;
    private final JTextField inputField;
    private final JTextArea feedbackArea;

    public GuessTheNumberGame() throws IOException {
        // Чтение числа из файла
        guessNumber = readNumberFromFile("C:\\Users\\Anton\\IdeaProjects\\main__guess_number\\src\\number.txt");

        JFrame frame = new JFrame("Угадай число");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("Введите число от 1 до 1000:");
        label.setBounds(50, 20, 300, 30);
        frame.add(label);

        inputField = new JTextField();
        inputField.setBounds(50, 50, 300, 30);
        frame.add(inputField);

        JButton guessButton = new JButton("Угадать");
        guessButton.setBounds(150, 100, 100, 30);
        frame.add(guessButton);

        feedbackArea = new JTextArea();
        feedbackArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        scrollPane.setBounds(50, 150, 300, 100);
        frame.add(scrollPane);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        frame.setVisible(true);
    }

    private int readNumberFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line.trim());
            } else {
                throw new IOException("Файл пустой");
            }
        }
    }

    private void checkGuess() {
        try {
            int userNumber = Integer.parseInt(inputField.getText());
            if (userNumber > guessNumber) {
                feedbackArea.append("Меньше " + userNumber + "\n");
            } else if (userNumber < guessNumber) {
                feedbackArea.append("Больше " + userNumber + "\n");
            } else {
                feedbackArea.append("Угадал! " + guessNumber + "\n");
            }
            inputField.setText("");

            feedbackArea.setCaretPosition(feedbackArea.getDocument().getLength());
        } catch (NumberFormatException e) {
            feedbackArea.append("Пожалуйста, введите корректное число.\n");
            inputField.setText("");

            feedbackArea.setCaretPosition(feedbackArea.getDocument().getLength());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new GuessTheNumberGame();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка чтения файла: " + e.getMessage());
            }
        });
    }
}
