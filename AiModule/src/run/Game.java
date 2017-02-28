package run;

import ai.AiRun;
import ai.DifficultyLevel;

import static ai.difficulty.DifficultyLevels.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {

    static Map<Pos,Integer> pos = new HashMap<>();

    private static int[] db = new int[77], dw = new int[77], t = new int[17];

    private static boolean first = true;

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            new Game(scanner).start();
        }
    }

    private final Scanner scanner;

    private Game(Scanner scanner) throws IOException {
        this.scanner = scanner;
    }

    private int readLimitedInt(String name, int minLimit, int maxlimit) {
        int value;

        System.out.println("Введите " + name + ":");

        while (true) {
            try {
                value = scanner.nextInt();

                if (value < minLimit || maxlimit < value) {
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


    private void start() {
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

    private void run(DifficultyLevel difficultyLevel) {
        /* File file = new File("BookOf.positions");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BookOf.positions"))) {
                pos = (Map<Pos, Integer>) ois.readObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } */
        while (true) {
            t[1]=1;t[4]=1;t[9]=1;t[12]=1;
            dw[10]=1;dw[5]=2;dw[2]=2;
           int playerColumn = readLimitedInt("номер столбца", first ? 0 : 1, 16);
           if (playerColumn!=0)
            auth(playerColumn, 0);
            first = false;
            if (AiRun.win(db, dw) == 1) {
                System.out.println("Вы Выиграли!");
                break;
            }

            int aiColumn;

            if (VERY_EASY == difficultyLevel) {
                aiColumn = AiRun.engine_1(db, dw, t);
                auth(aiColumn, 1);
            } else if (EASY == difficultyLevel) {
                aiColumn = AiRun.engine_2(db, dw, t);
                auth(aiColumn, 1);
            } else if (AVERAGE == difficultyLevel) {
                aiColumn = AiRun.engine(db.clone(), dw.clone(), t.clone());
                auth(aiColumn, 1);
            } else {
                int[] move = {0, 0, 0, 0, 0, 0,0,0,0};
                Pos position = new Pos(db, dw);
                if (pos.get(position) == null) {
                    System.out.println("Идет анализ ходов ...");
                    aiColumn = (MAXIMAL == difficultyLevel) ? AiRun.analyze(db.clone(), dw.clone(), t.clone(), 0, 6,0,move) : AiRun.analyze(db.clone(), dw.clone(), t.clone(), 0, 4,0,move);
                    if (MAXIMAL == difficultyLevel)
                    pos.put(position, aiColumn);
                } else
                    aiColumn = pos.get(position);
                System.out.println();
                auth(aiColumn, 1);

            }
            System.out.println("Ход:");
            System.out.println(aiColumn);

            if (AiRun.win(db, dw) == -1) {
                System.out.println("Компьютер Выиграл!");
                break;
            }


            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BookOf.positions"))) {
                oos.writeObject(pos);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("------------");

        }
        System.out.println("Нажмите Enter для завершения игры.");
        try {
            System.in.read();
        } catch (IOException cannotHappen) { }
    }

    private void auth(int n,int color) {

        int x, y = 0, z;
        x=(n-1)%4+1;
        if ((n >= 1) & (n <= 4))
            y = 4;
        if ((n >= 5) & (n <= 8))
            y = 3;
        if ((n >= 9) & (n <= 12))
            y = 2;
        if ((n >= 13) & (n <= 16))
            y = 1;
        z = t[n] + 1;
        if (color==0) {
            for (int i = 1; i < 5; i++)
                if ((y == i) & (z == 1)) {
                    dw[i] = dw[i] + 1;
                }
            for (int i = 1; i < 5; i++) {
                if ((x == i) & (z == 1))
                    dw[i + 4] = dw[i + 4] + 1;
            }
            if ((5 - x == y) & (z == 1))
                dw[9] = dw[9] + 1;

            if ((x == y) & (z == 1))
                dw[10] = dw[10] + 1;

            for (int i = 1; i < 5; i++) {
                if ((y == i) & (z == 2))
                    dw[10 + i] = dw[10 + i] + 1;
            }
            for (int i = 1; i < 5; i++) {
                if ((x == i) & (z == 2))
                    dw[i + 14] = dw[i + 14] + 1;
            }
            if ((5 - x == y) & (z == 2))
                dw[19] = dw[19] + 1;

            if ((x == y) & (z == 2))
                dw[20] = dw[20] + 1;

            for (int i = 1; i < 5; i++) {
                if ((y == i) & (z == 3))
                    dw[i + 20] = dw[i + 20] + 1;
            }
            for (int i = 1; i < 5; i++) {
                if ((x == i) & (z == 3))
                    dw[i + 24] = dw[i + 24] + 1;
            }
            if ((5 - x == y) & (z == 3))
                dw[29] = dw[29] + 1;

            if ((x == y) & (z == 3))
                dw[30] = dw[30] + 1;

            for (int i = 1; i < 5; i++) {
                if ((y == i) & (z == 4))
                    dw[i + 30] = dw[i + 30] + 1;
            }
            for (int i = 1; i < 5; i++) {
                if ((x == i) & (z == 4))
                    dw[i + 34] = dw[i + 34] + 1;
            }
            if ((5 - x == y) & (z == 4))
                dw[39] = dw[39] + 1;

            if ((x == y) & (z == 4))
                dw[40] = dw[40] + 1;

            for (int i = 1; i < 5; i++) {
                if ((x == i) & (5 - y == z))
                    dw[40 + i] = dw[40 + i] + 1;
                if ((x == i) & (y == z))
                    dw[44 + i] = dw[44 + i] + 1;
                if ((y == 5 - i) & (x == z))
                    dw[48 + i] = dw[48 + i] + 1;
                if ((y == 5 - i) & (5 - x == z))
                    dw[52 + i] = dw[52 + i] + 1;
            }

            if ((x == z) & (x == 5 - y))
                dw[57] = dw[57] + 1;

            if ((x == 5 - z) & (x == y))
                dw[58] = dw[58] + 1;

            if ((y == z) & (x == 5 - y))
                dw[59] = dw[59] + 1;

            if ((x == z) & (x == y))
                dw[60] = dw[60] + 1;

            dw[n + 60] = dw[n + 60] + 1;
            t[n] = t[n] + 1;
        }
        if (color==1) {
            for (int i = 1; i < 5; i++)
                if ((y == i) & (z == 1)) {
                    db[i] = db[i] + 1;
                }
            for (int i = 1; i < 5; i++) {
                if ((x == i) & (z == 1))
                    db[i + 4] = db[i + 4] + 1;
            }
            if ((5 - x == y) & (z == 1))
                db[9] = db[9] + 1;

            if ((x == y) & (z == 1))
                db[10] = db[10] + 1;

            for (int i = 1; i < 5; i++) {
                if ((y == i) & (z == 2))
                    db[10 + i] = db[10 + i] + 1;
            }
            for (int i = 1; i < 5; i++) {
                if ((x == i) & (z == 2))
                    db[i + 14] = db[i + 14] + 1;
            }
            if ((5 - x == y) & (z == 2))
                db[19] = db[19] + 1;

            if ((x == y) & (z == 2))
                db[20] = db[20] + 1;

            for (int i = 1; i < 5; i++) {
                if ((y == i) & (z == 3))
                    db[i + 20] = db[i + 20] + 1;
            }
            for (int i = 1; i < 5; i++) {
                if ((x == i) & (z == 3))
                    db[i + 24] = db[i + 24] + 1;
            }
            if ((5 - x == y) & (z == 3))
                db[29] = db[29] + 1;

            if ((x == y) & (z == 3))
                db[30] = db[30] + 1;

            for (int i = 1; i < 5; i++) {
                if ((y == i) & (z == 4))
                    db[i + 30] = db[i + 30] + 1;
            }
            for (int i = 1; i < 5; i++) {
                if ((x == i) & (z == 4))
                    db[i + 34] = db[i + 34] + 1;
            }
            if ((5 - x == y) & (z == 4))
                db[39] = db[39] + 1;

            if ((x == y) & (z == 4))
                db[40] = db[40] + 1;

            for (int i = 1; i < 5; i++) {
                if ((x == i) & (5 - y == z))
                    db[40 + i] = db[40 + i] + 1;
                if ((x == i) & (y == z))
                    db[44 + i] = db[44 + i] + 1;
                if ((y == 5 - i) & (x == z))
                    db[48 + i] = db[48 + i] + 1;
                if ((y == 5 - i) & (5 - x == z))
                    db[52 + i] = db[52 + i] + 1;
            }

            if ((x == z) & (x == 5 - y))
                db[57] = db[57] + 1;

            if ((x == 5 - z) & (x == y))
                db[58] = db[58] + 1;

            if ((y == z) & (x == 5 - y))
                db[59] = db[59] + 1;

            if ((x == z) & (x == y))
                db[60] = db[60] + 1;

            db[n + 60] = db[n + 60] + 1;
            t[n] = t[n] + 1;
        }
    }
}

class Pos implements Serializable {

    private int[] db,dw;

    Pos(int[] db, int[] dw) {
        this.db = db.clone();
        this.dw = dw.clone();
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof run.Pos)) return false;

        run.Pos other = (run.Pos) obj;

        for (int i = 0; i < db.length; ++i) {
            if (db[i] != other.db[i]) return false;
            if (dw[i] != other.dw[i]) return false;
        }

        return true;
    }

    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < db.length; ++i) {
            hash += db[i];
            hash *= 7;
            hash += dw[i];
            hash *= 7;
        }
        return hash;
    }
}