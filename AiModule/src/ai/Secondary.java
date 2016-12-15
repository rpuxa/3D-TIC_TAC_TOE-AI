package ai;

class Secondary {

    static int[] auth_d(int[] d, int[] t, int l) {
        int x, y = 0, z;
        x = (l - 1) % 4 + 1;
        if ((l >= 1) & (l <= 4))
            y = 4;
        if ((l >= 5) & (l <= 8))
            y = 3;
        if ((l >= 9) & (l <= 12))
            y = 2;
        if ((l >= 13) & (l <= 16))
            y = 1;
        z = t[l] + 1;

        for (int i = 1; i < 5; i++)
            if ((y == i) & (z == 1)) {
                d[i] += 1;
            }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 1))
                d[i + 4] += 1;
        }
        if ((5 - x == y) & (z == 1))
            d[9] += 1;

        if ((x == y) & (z == 1))
            d[10] += 1;

        for (int i = 1; i < 5; i++) {
            if ((y == i) & (z == 2))
                d[10 + i] = d[10 + i] + 1;
        }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 2))
                d[i + 14] = d[i + 14] + 1;
        }
        if ((5 - x == y) & (z == 2))
            d[19] = d[19] + 1;

        if ((x == y) & (z == 2))
            d[20] = d[20] + 1;

        for (int i = 1; i < 5; i++)
            if ((y == i) & (z == 3))
                d[i + 20] = d[i + 20] + 1;

        for (int i = 1; i < 5; i++)
            if ((x == i) & (z == 3))
                d[i + 24] = d[i + 24] + 1;

        if ((5 - x == y) & (z == 3))
            d[29] = d[29] + 1;

        if ((x == y) & (z == 3))
            d[30] = d[30] + 1;

        for (int i = 1; i < 5; i++) {
            if ((y == i) & (z == 4))
                d[i + 30] = d[i + 30] + 1;
        }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 4))
                d[i + 34] = d[i + 34] + 1;
        }
        if ((5 - x == y) & (z == 4))
            d[39] = d[39] + 1;

        if ((x == y) & (z == 4))
            d[40] = d[40] + 1;

        for (int i = 1; i < 5; i++) {
            if ((x == i) & (5 - y == z))
                d[40 + i] = d[40 + i] + 1;
            if ((x == i) & (y == z))
                d[44 + i] = d[44 + i] + 1;
            if ((y == 5 - i) & (x == z))
                d[48 + i] = d[48 + i] + 1;
            if ((y == 5 - i) & (5 - x == z))
                d[52 + i] = d[52 + i] + 1;
        }

        if ((x == z) & (x == 5 - y))
            d[57] = d[57] + 1;

        if ((x == 5 - z) & (x == y))
            d[58] = d[58] + 1;

        if ((y == z) & (x == 5 - y))
            d[59] = d[59] + 1;

        if ((x == z) & (x == y))
            d[60] = d[60] + 1;

        d[l + 60] = d[l + 60] + 1;

        return d;
    }



    private static boolean[] line(int[] db, int[] t) {
        int[] dw1 = new int[77];
        System.arraycopy(db, 0, dw1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);

        boolean[] fw = new boolean[17];
        for (int i = 1; i < 17; i++)
            if (t1[i] < 3) {
                t1[i] += 1;
                int[] dwx = new int[77];
                System.arraycopy(auth_d(dw1.clone(), t1, i), 0, dwx, 0, 77);
                //Условие
                for (int k = 1; k < 77; k++) {
                    if (dwx[k] == 4) {
                        fw[i] = true;
                        break;
                    }
                }
                //Конец условия

            }

        return fw;
    }

//engine

    static int attack(int[] db, int[] t) {
        int[] db1 = new int[77];
        System.arraycopy(db, 0, db1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);

        int a = 0;
        for (int i = 1; i < 17; i++)
            if (t1[i] != 4) {
                int[] dbx = new int[77];
                System.arraycopy(auth_d(db1.clone(), t1, i), 0, dbx, 0, 77);
                //Условие
                for (int k = 1; k < 77; k++) {
                    if (dbx[k] == 4) {
                        a = i;
                        break;
                    }
                }
                //Конец условия
                if (a != 0)
                    break;

            }

        return a;
    }

    static int lip_a(int[] db, int[] dw, int[] t) {
        int[] db1 = new int[77];
        System.arraycopy(db, 0, db1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(line(db1, t1), 0, fw1, 0, 17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(line(dw, t1), 0, fb1, 0, 17);
        int a, b, c = 0;
        for (int i = 1; i < 77; i++)
            if ((db[i] == 3) & (dw[i] == 0))
                c = c + 1;
        for (int i = 1; i < 17; i++)
            if ((t1[i] < 4) & (!fb1[i])) {
                a = 0;
                b = 0;
                int[] dbx = new int[77];
                System.arraycopy(auth_d(db1.clone(), t1, i), 0, dbx, 0, 77);
                int[] tx = new int[17];
                System.arraycopy(t1, 0, tx, 0, 17);
                tx[i] += 1;
                for (int k = 1; k < 77; k++)
                    if ((dbx[k] == 3) & (dw[k] == 0))
                        b++;
                for (int j = 1; j < 17; j++)
                    if (t1[j] != 4) {
                        int[] dbx1 = new int[77];
                        System.arraycopy(auth_d(dbx.clone(), tx, j), 0, dbx1, 0, 77);
                        for (int k = 1; k < 77; k++)
                            if (dbx1[k] == 4)
                                a++;


                    }
                if (((1 + c < b) | ((c < b) & (fw1[i]))) & (a == 2)) {
                    return i;
                }
            }

        return 0;
    }

    static int lip_d(int[] db, int[] dw, int[] t) {
        int[] dw1 = new int[77];
        System.arraycopy(dw, 0, dw1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(line(dw1, t1), 0, fb1, 0, 17);
        int a, b, c = 0;
        for (int i = 1; i < 77; i++)
            if ((dw[i] == 3) & (db[i] == 0))
                c = c + 1;
        for (int i = 1; i < 17; i++)
            if (t1[i] < 4) {
                boolean[] xm = new boolean[17];
                a = 0;
                b = 0;
                int[] dwx = new int[77];
                System.arraycopy(auth_d(dw1.clone(), t1, i), 0, dwx, 0, 77);
                int[] tx = new int[17];
                System.arraycopy(t1, 0, tx, 0, 17);
                tx[i] += 1;
                for (int k = 1; k < 77; k++)
                    if ((dwx[k] == 3) & (db[k] == 0))
                        b = b + 1;
                for (int j = 1; j < 17; j++)
                    if (t1[j] != 4) {
                        int[] dwx1 = new int[77];
                        System.arraycopy(auth_d(dwx.clone(), tx, j), 0, dwx1, 0, 77);
                        for (int k = 1; k < 77; k++)
                            if (dwx1[k] == 4) {
                                a = a + 1;
                                xm[j] = true;
                            }


                    }
                for (int j = 1; j < 17; j++)
                    if (((1 + c < b) & (!fb1[i]) & (a >= 2)) | ((c < b) & (fb1[i]) & (!fb1[j]) & (a >= 2) & (xm[j]) & (t1[j] != 4))) {
                        if ((1 + c < b) & (!fb1[i]))
                            return i;
                         else return j;
                    }
            }

        return 0;
    }

    static int debut(int[] t) {
        if (t[1] == 0)
            return 1;
        if (t[4] == 0)
            return 4;
        if (t[13] == 0)
            return 13;
        if (t[16] == 0)
            return 16;
        return 0;
    }

    static int d3_a(int[] db, int[] dw, int[] t) {
        int[] db1 = new int[77];
        System.arraycopy(db, 0, db1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(line(dw, t1), 0, fw1, 0, 17);
        int a, b, c = 0;
        for (int i = 1; i < 77; i++)
            if ((db[i] == 3) & (dw[i] == 0))
                c = c + 1;
        for (int i = 1; i < 17; i++)
            if (t1[i] < 4) {
                a = 0;
                b = 0;
                int[] dbx = new int[77];
                System.arraycopy(auth_d(db1.clone(), t1, i), 0, dbx, 0, 77);
                int[] tx = new int[17];
                System.arraycopy(t1, 0, tx, 0, 17);
                tx[i] += 1;
                for (int k = 1; k < 77; k++)
                    if ((dbx[k] == 3) & (dw[k] == 0))
                        b = b + 1;
                for (int j = 1; j < 17; j++)
                    if (t1[j] != 4) {
                        int[] dbx1 = new int[77];
                        System.arraycopy(auth_d(dbx.clone(), tx, j), 0, dbx1, 0, 77);
                        for (int k = 1; k < 77; k++)
                            if (dbx1[k] == 4)
                                a++;


                    }
                if ((c < b) & (!fw1[i]) & (a == 0)) {
                    return i;
                }
            }

        return 0;
    }

    static int d3_d(int[] db, int[] dw, int[] t) {
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(line(dw, t1), 0, fw1, 0, 17);
        int a, b, c = 0;
        for (int i = 1; i < 77; i++)
            if ((dw[i] == 3) & (db[i] == 0))
                c = c + 1;
        for (int i = 1; i < 17; i++)
            if (t1[i] < 4) {
                a = 0;
                b = 0;
                int[] dwx = new int[77];
                System.arraycopy(auth_d(dw.clone(), t1, i), 0, dwx, 0, 77);
                int[] tx = new int[17];
                System.arraycopy(t1, 0, tx, 0, 17);
                tx[i] += 1;
                for (int k = 1; k < 77; k++)
                    if ((dwx[k] == 3) & (db[k] == 0))
                        b++;
                for (int j = 1; j < 17; j++)
                    if (t1[j] != 4) {
                        int[] dwx1 = new int[77];
                        System.arraycopy(auth_d(dwx.clone(), tx, j), 0, dwx1, 0, 77);
                        for (int k = 1; k < 77; k++)
                            if (dwx1[k] == 4)
                                a = a + 1;


                    }
                if ((c < b) & (!fw1[i]) & (a == 0)) {
                    return i;
                }
            }

        return 0;
    }

    static int dc2(int[] db, int[] dw, int[] t) {
        int[] db1 = new int[77];
        System.arraycopy(db, 0, db1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(line(db, t1), 0, fw1, 0, 17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(line(dw, t1), 0, fb1, 0, 17);
        int b, c = 0;
        for (int i = 41; i < 61; i++)
            if ((db[i] == 2) & (dw[i] == 0))
                c = c + 1;
        for (int i = 1; i < 17; i++)
            if ((t1[i] != 4) & (!fw1[i]) & (!fb1[i])) {
                b = 0;
                int[] dbx = new int[77];
                System.arraycopy(auth_d(db1.clone(), t1, i), 0, dbx, 0, 77);
                for (int k = 41; k < 61; k++)
                    if ((dbx[k] == 2) & (dw[k] == 0))
                        b++;
                if (1 + c < b) {
                    return i;
                }
            }

        return 0;
    }

    static int dc1(int[] db, int[] dw, int[] t) {
        int[] db1 = new int[77];
        System.arraycopy(db, 0, db1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(line(db, t1), 0, fw1, 0, 17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(line(dw, t1), 0, fb1, 0, 17);
        int b, c = 0;
        for (int i = 41; i < 61; i++)
            if ((db[i] == 2) & (dw[i] == 0))
                c = c + 1;
        for (int i = 1; i < 17; i++)
            if ((t1[i] != 4) & (!fw1[i]) & (!fb1[i])) {
                b = 0;
                int[] dbx = new int[77];
                System.arraycopy(auth_d(db1.clone(), t1, i), 0, dbx, 0, 77);
                for (int k = 41; k < 61; k++)
                    if ((dbx[k] == 2) & (dw[k] == 0))
                        b++;
                if (c < b) {
                    return i;
                }
            }

        return 0;
    }

    static int def_3(int[] db, int[] dw, int[] t) {
        int[] db1 = new int[77];
        System.arraycopy(dw, 0, db1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(line(db, t1), 0, fw1, 0, 17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(line(dw, t1), 0, fb1, 0, 17);
        int b, c = 0;
        for (int i = 1; i < 77; i++)
            if ((dw[i] == 3) & (db[i] == 0))
                c = c + 1;
        for (int i = 1; i < 17; i++)
            if ((t1[i] != 4) & (!fw1[i]) & (!fb1[i])) {
                b = 0;
                int[] dbx = new int[77];
                System.arraycopy(auth_d(db1.clone(), t1, i), 0, dbx, 0, 77);
                for (int k = 1; k < 77; k++)
                    if ((dbx[k] == 3) & (db[k] == 0))
                        b++;
                if (c < b) {
                    return i;
                }
            }

        return 0;
    }

    static int rand(int[] db, int[] dw, int[] t) {
        boolean[] fw1 = new boolean[17];
        System.arraycopy(line(db, t), 0, fw1, 0, 17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(line(dw, t), 0, fb1, 0, 17);
        for (int i = 1; i < 17; i++)
            if ((t[i] != 4) & (!fw1[i]) & (!fb1[i])) {
                return i;
            }
        for (int i = 1; i < 17; i++)
            if (t[i] != 4) {
                return i;
            }
        return 0;
    }

    static int random(int[] db, int[] dw, int[] t) {
        boolean[] fw1 = new boolean[17];
        System.arraycopy(line(db, t), 0, fw1, 0, 17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(line(dw, t), 0, fb1, 0, 17);
        for (int i = 1; i < 17; i++) {
            int j = (int) (Math.random() * 16 + 1);

            if ((t[j] != 4) & (!fw1[j]) & (!fb1[j])) {
                return j;
            } else if (t[j] != 4) {
                return j;
            }
        }
        return 0;
    }

    static int double_(int[] db, int[] dw, int[] t) {
        int[] db1 = new int[77];
        System.arraycopy(db, 0, db1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(line(dw, t1), 0, fb1, 0, 17);
        for (int j=1;j<17;j++)
        for (int i = 1; i < 17; i++)
           if ((t1[i] < 2) & (!fb1[i]) & (t1[j] < 4) & (!fb1[j]) & (j!=i)) {
               int[] dbx = new int[77];
               System.arraycopy(auth_d(db1.clone(), t1, j), 0, dbx, 0, 77);
               int[] tx = new int[17];
               System.arraycopy(t1, 0, tx, 0, 17);
               tx[i] += 1;
               dbx = auth_d(dbx.clone(), tx, i);
               tx[i] += 1;
               dbx = auth_d(dbx.clone(), tx, i);
               int a=0;
               for (int k = 1; k < 77; k++)
                   if (dbx[k] == 4) {
                       a++;
                       if (a == 2)
                           return j;

                   }
            }
                return 0;
    }
}
