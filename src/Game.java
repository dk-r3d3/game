import java.util.logging.FileHandler;
import java.util.logging.Logger;

public interface Game {
    void start(Integer sizeWord, Integer maxTry, Integer language);

    Answer inputValue(String value);

    GameStatus getGameStatus();
}
