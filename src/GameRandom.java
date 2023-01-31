import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameRandom {
    private final Scanner scanner;
    private final Random random;

    GameRandom() {
        scanner = new Scanner(System.in);
        random = new Random();
    }

    public void game() {
        int countBid = 0;
        int games = 0;
        int winGames = 0;

        while (true) {
            System.out.println("Назовите ваше число от 1 до 10!");
            int num = checkInputNum();

            System.out.println("Введите вашу ставку - количество монет!");
            int bid = checkPositiveInt();

            int randomNumber = random.nextInt(10) + 1;

            int finalBid = bid * 2;
            if (num == randomNumber) {
                System.out.println("Вы угадали число! Ваш выигрыш - " + (finalBid) + " монет!");
                countBid += finalBid;
                winGames++;
            } else {
                System.out.println("Вы не угадали число! Ставка проиграна.");
                countBid -= bid;
            }
            if (countBid < 0) {
            System.out.println("На данный момент вы проиграли всего " + countBid*(-1) + " монет.");
            } else {
                System.out.println("На данный момент вы выиграли всего " + countBid + " монет.");
            }
            games++;
            if (countBid < -500) {
                System.out.println("Вы проиграли слишком много денег!");
                break;
            }

            System.out.println("Было загадано число " + randomNumber);
            System.out.println("Сыграем еще?");
            String yourAnswer = scanner.next();

            if (yourAnswer.equals("Нет")) {
                break;
            }
        }
        double resultGames = ((double) winGames / games) * 100;
        System.out.println("Ваша статистика: вы выигрывали в " + resultGames + " % случаев");
    }

    private int checkPositiveInt() {
        while (true) {
            try {
                int number = scanner.nextInt();
                if (number <= 0) {
                    throw new NegativeException();
                }

                System.out.println("Вы ввели: " + number);

                return number;
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели некорректное число. Попробуйте снова.");

                scanner.next();
            } catch (NegativeException e) {
                System.out.println("Вы ввели число больше 10 или меньше 1. Попробуйте снова.");
            }
        }
    }

    private int checkInputNum() {
        while (true) {
            int num = checkPositiveInt();

            try {
                if (num > 10) {
                    throw new NegativeException();
                }

                return num;
            } catch (NegativeException e) {
                System.out.println("Вы ввели некорректное число. Попробуйте снова.");
            }
        }
    }
}

