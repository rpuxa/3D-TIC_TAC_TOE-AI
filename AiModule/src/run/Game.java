package run;

import ai.AiRun;
import ai.DifficultyLevel;

import static ai.difficulty.DifficultyLevels.*;

import java.io.IOException;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new Game(scanner).start();
        }
    }

    private final Scanner scanner;

    Game(Scanner scanner) {
        this.scanner = scanner;
    }

    int readLimitedInt(String name, int minLimit, int maxLimit) {
        int value;

        System.out.println("Введите " + name + ":");

        while (true) {
            try {
                value = scanner.nextInt();

                if (value < minLimit || maxLimit < value) {
                    throw new IOException();
                }

                break;
            } catch (Exception e) {
                System.out.println(
                        String.format("Некорректный %s. Повторите ввод", name)
                );

                scanner.nextLine();
            }
        }

        System.out.println();

        return value;
    }


    void start() {
        for (DifficultyLevel difficultyLevel : LEVELS) {
            System.out.println(difficultyLevel);
        }

        System.out.println("Выберите уровень сложности:");
        for (int index = 0; index < LEVELS.length; ++index) {
            System.out.println(
                    String.format("%d) %s", index + 1, LEVELS[index].getName())
            );
        }

        int difficultyIndex = readLimitedInt("уровень сложности", 1 , LEVELS.length) - 1;

        DifficultyLevel difficultyLevel = LEVELS[difficultyIndex];
        run(difficultyLevel);
    }

    private final static int WIN_LINES_COUNT = 77;
    private final static int COLUMNS_COUNT = 17;
    private final static int MAX_ANALYZE_DEPTH = 6;

    private void run(DifficultyLevel difficultyLevel) {
        int[] aiWinLineCounts = new int[WIN_LINES_COUNT];
        int[] playerWinLineCounts = new int[WIN_LINES_COUNT];
        int[] columnHeights = new int[COLUMNS_COUNT];

        boolean first = true;
        while (true) {
            int playerColumn = readLimitedInt("номер столбца", first ? 0 : 1, COLUMNS_COUNT - 1);
            first = false;

            auth(playerColumn, playerWinLineCounts, columnHeights);
            if (AiRun.win(aiWinLineCounts, playerWinLineCounts) == 1) {
                System.out.println("Вы выиграли!");
                break;
            }

            int aiColumn = 0;

            if (VERY_EASY == difficultyLevel) {
                aiColumn = AiRun.engine_1(aiWinLineCounts, playerWinLineCounts, columnHeights);
            } else if (EASY == difficultyLevel) {
                aiColumn = AiRun.engine_2(aiWinLineCounts, playerWinLineCounts, columnHeights);
            } else if (AVERAGE == difficultyLevel) {
                aiColumn = AiRun.engine(aiWinLineCounts, playerWinLineCounts, columnHeights);
            } else {
                System.out.println("Идет анализ ходов ...");
                int[] move = new int[MAX_ANALYZE_DEPTH];
                aiColumn = (MAXIMAL == difficultyLevel) ? AiRun.analyze(db.clone(), dw.clone(), t.clone(),0,6,move) : AiRun.analyze(db.clone(), dw.clone(), t.clone(),0,4,move);
                System.out.println();
            }

            auth(aiColumn, aiWinLineCounts, columnHeights);

            System.out.println("Номер столбца, выбранный компьютером:");
            System.out.println(aiColumn);

            if (AiRun.win(aiWinLineCounts, playerWinLineCounts) == -1) {
                System.out.println("Компьютер выиграл!");
                break;
            }

            System.out.println("------------");
        }

        System.out.println("Нажмите Enter для завершения игры.");
        try {
            System.in.read();
        } catch (IOException cannotHappen) { }
    }

    private void auth(int n, int[] winLineCounts, int[] columnHeights) {

        int x, y = 0;
        x=(n-1)%4+1;
        if ((n >= 1) & (n <= 4))
            y = 4;
        if ((n >= 5) & (n <= 8))
            y = 3;
        if ((n >= 9) & (n <= 12))
            y = 2;
        if ((n >= 13) & (n <= 16))
            y = 1;

        columnHeights[n]++;
        int z = columnHeights[n];

        for (int i = 1; i < 5; i++)
            if ((y == i) & (z == 1)) {
                winLineCounts[i] = winLineCounts[i] + 1;
            }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 1))
                winLineCounts[i + 4] = winLineCounts[i + 4] + 1;
        }
        if ((5 - x == y) & (z == 1))
            winLineCounts[9] = winLineCounts[9] + 1;

        if ((x == y) & (z == 1))
            winLineCounts[10] = winLineCounts[10] + 1;

        for (int i = 1; i < 5; i++) {
            if ((y == i) & (z == 2))
                winLineCounts[10 + i] = winLineCounts[10 + i] + 1;
        }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 2))
                winLineCounts[i + 14] = winLineCounts[i + 14] + 1;
        }
        if ((5 - x == y) & (z == 2))
            winLineCounts[19] = winLineCounts[19] + 1;

        if ((x == y) & (z == 2))
            winLineCounts[20] = winLineCounts[20] + 1;

        for (int i = 1; i < 5; i++) {
            if ((y == i) & (z == 3))
                winLineCounts[i + 20] = winLineCounts[i + 20] + 1;
        }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 3))
                winLineCounts[i + 24] = winLineCounts[i + 24] + 1;
        }
        if ((5 - x == y) & (z == 3))
            winLineCounts[29] = winLineCounts[29] + 1;

        if ((x == y) & (z == 3))
            winLineCounts[30] = winLineCounts[30] + 1;

        for (int i = 1; i < 5; i++) {
            if ((y == i) & (z == 4))
                winLineCounts[i + 30] = winLineCounts[i + 30] + 1;
        }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 4))
                winLineCounts[i + 34] = winLineCounts[i + 34] + 1;
        }
        if ((5 - x == y) & (z == 4))
            winLineCounts[39] = winLineCounts[39] + 1;

        if ((x == y) & (z == 4))
            winLineCounts[40] = winLineCounts[40] + 1;

        for (int i = 1; i < 5; i++) {
            if ((x == i) & (5 - y == z))
                winLineCounts[40 + i] = winLineCounts[40 + i] + 1;
            if ((x == i) & (y == z))
                winLineCounts[44 + i] = winLineCounts[44 + i] + 1;
            if ((y == 5 - i) & (x == z))
                winLineCounts[48 + i] = winLineCounts[48 + i] + 1;
            if ((y == 5 - i) & (5 - x == z))
                winLineCounts[52 + i] = winLineCounts[52 + i] + 1;
        }

        if ((x == z) & (x == 5 - y))
            winLineCounts[57] = winLineCounts[57] + 1;

        if ((x == 5 - z) & (x == y))
            winLineCounts[58] = winLineCounts[58] + 1;

        if ((y == z) & (x == 5 - y))
            winLineCounts[59] = winLineCounts[59] + 1;

        if ((x == z) & (x == y))
            winLineCounts[60] = winLineCounts[60] + 1;

        winLineCounts[n + 60] = winLineCounts[n + 60] + 1;
    }
}



