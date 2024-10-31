package wordguessinggame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordBank {

    public static final String FILE_PATH = "wordguessinggame/res/words.txt";

    private static final List<String> words = new ArrayList<>();

    static {
        try {
            loadWordsFromFile(FILE_PATH);
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке слов: " + e.getMessage());
        }
    }

    public static List<String> getWords() {
        return new ArrayList<>(words);
    }

    private static void loadWordsFromFile(String filePath) throws IOException {
        try (InputStream inputStream = WordBank.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream == null) {
                throw new IOException("Файл не найден: " + filePath);
            }
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        }
    }
}



