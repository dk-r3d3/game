import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {

        FileHandler fileHandler = null;
        Boolean flag = true;
        try {
            // функционал с дз по java, где делали логгирование сортировки пузырьком
            System.setProperty("java.util.logging.SimpleFormatter.format",
                    "%1$tF %1$tR %5$s %n");
            fileHandler = new FileHandler("log.txt");
            logger.addHandler(fileHandler);
            SimpleFormatter sFormatter = new SimpleFormatter();
            fileHandler.setFormatter(sFormatter);

            while (flag) {
                Scanner scanner = new Scanner(System.in);
                gameOperation();
                System.out.println("Do you want to restart?\n1 - yes \n2 - no \n3 - check logs");
                Integer word = scanner.nextInt();
                if (word == 2) {
                    System.out.println("GAME OVER!");
                    flag = false;
                }
                if (word == 3) {
                    try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                    }
                    flag = false;
                }
            }
        } catch (Exception e) {
        }
    }

    public static void gameOperation() {
        Scanner scanner = new Scanner(System.in);
        Game game = new AplhabetGame();

        System.out.println("Enter the length of the word: ");
        Integer sizeWord = Integer.parseInt(scanner.nextLine());
        logger.info("Your sizeWord - " + sizeWord.toString());

        System.out.println("Enter the number of attempts: ");
        Integer maxTry = Integer.parseInt(scanner.nextLine());
        logger.info("Your maxTry - " + maxTry.toString());

        System.out.println("Choise language(1 - RU, 2 - EN): ");
        Integer language = Integer.parseInt(scanner.nextLine());
        logger.info("Your language - " + language.toString());

        game.start(sizeWord, maxTry, language);
        while (game.getGameStatus() != GameStatus.WIN && game.getGameStatus() != GameStatus.LOSE) {
            System.out.println("Enter your word(UPPERCASE): ");
            Answer answer = game.inputValue(scanner.nextLine());
            System.out.println("Result: " + answer);
        }
        System.out.println("Your status - " + game.getGameStatus());
    }
}
