package wordguessinggame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// создал отдельый класс для хранения слов из файла и методов доступа к ним, а также метода закачки слов
public class WordBank {

    public static final String FILE_PATH = "wordguessinggame/res/words.txt";

    private static final List<String> words = new ArrayList<>();

    static {
        try {
            loadWordsFromFile();
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке слов: " + e.getMessage());
        }
    }

    public static List<String> getWords() {
        return new ArrayList<>(words);
    }

    private static void loadWordsFromFile() throws IOException {
        try (InputStream inputStream = WordBank.class.getClassLoader().getResourceAsStream(WordBank.FILE_PATH)) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    words.add(line.trim());
                }
            }
        }
    }
}



