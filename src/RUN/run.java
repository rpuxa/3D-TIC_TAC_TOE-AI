package RUN;


import java.util.Scanner;

public class run {

    private static int[] db = new int[77], dw = new int[77], t = new int[17];

    public static boolean show = true;

    public static long p = 0,dif = 0;

    public static void main(String[] agrs) {
        System.out.println("Очень легкий:");
        System.out.println("    Сложность для начинающих, которая поможет вам понять основные принципы игры.");
        System.out.println("    Использует только очень слабый движок.");
        System.out.println();
        System.out.println("Легкий");
        System.out.println("    Если вы поняли основные принципы, попробуйте этот тип сложности.");
        System.out.println("    Поначалу будет сложно, но после слишком легко.");
        System.out.println("    Использует только основные возможности движка.");
        System.out.println();
        System.out.println("Средний");
        System.out.println("    Выработав свою тактику, попробуйте эту сложность.");
        System.out.println("    Использует весь движок.");
        System.out.println();
        System.out.println("Сложный");
        System.out.println("    Приготовьтесь к тяжкому испытанию! Для победы");
        System.out.println("    нужно будет не слабо потренироваться!");
        System.out.println("    Начинает использовать анализ позиций, полностью использует");
        System.out.println("    движок и наполовину древо ходов.");
        System.out.println("    Анализирует примерно 5000 позиций за ход.");
        System.out.println();
        System.out.println("Максимальный");
        System.out.println("    Чтобы одолеть максимальную сложность, нужно иметь");
        System.out.println("    стратегическое мышление, огромную внимательность");
        System.out.println("    и быть очень сосредоточенным!");
        System.out.println("    Использует все возможности по максимуму:");
        System.out.println("    древо ходов, анализ, движок.");
        System.out.println("    Анализирует примерно 60000 позиций за ход!");
        System.out.println();
        System.out.println("Выберете уровень сложности:");
        System.out.println("1) Очень легкий");
        System.out.println("2) Легкий");
        System.out.println("3) Средний");
        System.out.println("4) Сложный");
        System.out.println("5) Максимальный");
        Scanner scanner = new Scanner(System.in);
        dif = scanner.nextInt();
        System.out.println();
        run.run_application();
    }

    public static void run_application()
    {
        System.out.println("Введите номер столбца:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if ((0<n) & (n<17)) {
            auth(n, 0);

            if (ii_run.win(db, dw) == 1) {
                System.out.println("Вы Выиграли!");
                int prosto = scanner.nextInt();
                System.exit(0);
            }
        }
        int a = 0;
        if (dif==1)
        {
            a = ii_run.engine_1(db,dw,t);
            auth(a, 1);
        }
        if (dif==2)
        {
            a = ii_run.engine_2(db,dw,t);
            auth(a, 1);
        }
        if (dif==3)
        {
            a = ii_run.engine(db,dw,t);
            auth(a, 1);
        }
        if (dif>3) {
            System.out.println("Идет анализ ходов ...");
            int[] k = dif==5 ? ii_run.tree(db, dw, t): ii_run.tree_2(db, dw, t);
            a = k[0];
            System.out.println();
            auth(a, 1);
            if (show) {
                System.out.println("// Проанализировано " + p + " позиций, итог:");
                System.out.println("// Текущая оценка: " + ii_run.analyz(db, dw, t));
                System.out.print("// Оценка: " + k[1] + ", при возможных ходах:  ");
                if (k[2]!=0)
                    if (k[3] == 0)
                        System.out.println(k[2] + "#");
                    else if (k[4] == 0)
                        System.out.println(k[2] + "," + k[3] + "#");
                    else if (k[5] == 0)
                        System.out.println(k[2] + "," + k[3] + "  " + k[4] + "#");
                    else {
                        if (dif==5)
                            System.out.println(k[2] + "," + k[3] + "  " + k[4] + "," + k[5] + "  " + k[6] + "," + k[7]);
                        else
                            System.out.println(k[2] + "," + k[3] + "  " + k[4] + "," + k[5]);
                    }
            }
        }
        System.out.println("Ход:");
        System.out.println(a);

        if (ii_run.win(db,dw)==-1)
        {
            System.out.println("Компьютер Выиграл!");
            int prost2o = scanner.nextInt();
            System.exit(0);
        }

        System.out.println("------------");
        run_application();
    }

    public static void auth(int n,int color)
    {

        int x = 0, y = 0, z;
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



