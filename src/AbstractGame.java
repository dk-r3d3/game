import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public abstract class AbstractGame implements Game {

    Integer sizeWord;
    Integer maxTry;
    String computerWord;
    Integer language;
    GameStatus gameStatus = GameStatus.INIT;

    public AbstractGame() {
        super();
    }

    abstract ArrayList<String> generateCharListEn();

    abstract ArrayList<String> generateCharListRus();


    @Override
    public void start(Integer sizeWord, Integer maxTry, Integer language) {
        this.sizeWord = sizeWord;
        this.maxTry = maxTry;
        this.language = language;
        computerWord = generateWord();
        gameStatus = GameStatus.START;
    }

    private String generateWord() {
        Random random = new Random();
        String res = "";
        if (Objects.equals(language, 1)) {
            List<String> alphabet = generateCharListRus();
            for (int i = 0; i < sizeWord; i++) {
                int randomIndex = random.nextInt(alphabet.size());
                res += alphabet.get(randomIndex);
                alphabet.remove(randomIndex);
            }
            Main.logger.info("СomputerWord - " + res);
            return res;
        } else if (Objects.equals(language, 2)) {
            List<String> alphabet = generateCharListEn();
            for (int i = 0; i < sizeWord; i++) {
                int randomIndex = random.nextInt(alphabet.size());
                res += alphabet.get(randomIndex);
                alphabet.remove(randomIndex);
            }
            Main.logger.info("СomputerWord - " + res);
            return res;
        } else {
            return "You entered an incorrect value.";
        }
    }

    @Override
    public Answer inputValue(String value) {
        Main.logger.info("You word - " + value);
        int bull = 0;
        int cow = 0;
        try {
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) == computerWord.charAt(i)) {
                    bull++;
                    cow++;
                } else if (computerWord.contains(String.valueOf(value.charAt(i)))) {
                    cow++;
                }
            }
            if (bull == computerWord.length()) {
                gameStatus = GameStatus.WIN;
            }
            maxTry--;
            if (maxTry == 0 && gameStatus != GameStatus.WIN) {
                gameStatus = GameStatus.LOSE;
            }
            return new Answer(bull, cow, maxTry);
        } catch (Exception e) {
            System.out.println("Enter a word of length - " + computerWord.length());
        }
        return null;
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
