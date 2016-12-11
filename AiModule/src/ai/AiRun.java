package ai;

import run.Game;

public class AiRun {

    public static int engine(int[] db,int[] dw,int[] t)
    {
        //Копирование массивов
        int[] db1 = new int[77];
        System.arraycopy(db,0,db1,0,77);
        int[] dw1 = new int[77];
        System.arraycopy(dw,0,dw1,0,77);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,17);
        //

        //Атака 4 в ряд
        int a=0;
        int b =Secondary.attack(db1,t1);
        if (b!=0)
            a = b;

        //Защита 4 в ряд
        if (a==0) {
            b = Secondary.attack(dw1, t1);
            if (b != 0)
                a = b;
        }

        // Создание узлов
        if (a==0) {
            b = Secondary.lip_a(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        // Защита от узлов
        if (a==0) {
            b = Secondary.lip_d(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        // Создание двойных линий
        if (a==0) {
            b = Secondary.double_(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        // Защита от двойных линий
        if (a==0) {
            b = Secondary.double_(dw1, db1, t1);
            if (b != 0)
                a = b;
        }


        // Атака на 3 недостижимых в ряд
        if (a==0) {
            b = Secondary.d3_a(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        // Защита от 3 недостижимых в ряд
        if (a==0) {
            b = Secondary.d3_d(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        // Дебют
        if (a==0) {
            b = Secondary.debut(t1);
            if (b != 0)
                a = b;
        }

        // Захват 2 диагоналей
        if (a==0) {
            b = Secondary.dc2(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        // Захват 1 диагонали
        if (a==0) {
            b = Secondary.dc1(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        // Защита от 3 в ряд
        if (a==0) {
            b = Secondary.def_3(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        // Ранддом
        if (a==0) {
            b = Secondary.rand(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        return a;
    }

    public static int analyze(int[] db,int[] dw,int[] t)
    {
        //Копирование массивов
        int[] db1 = new int[77];
        System.arraycopy(db, 0, db1, 0, 77);
        int[] dw1 = new int[77];
        System.arraycopy(dw, 0, dw1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);
        //
       int [] fw = new int[17],fb = new int[17];
        for (int i = 1; i < 17; i++) {
            fw[i] = 0;
            fb[i]=0;
        }
       int anl=0;
        for (int j = 1; j < 17; j++)
            for (int i = 1; i < 4; i++)
            if (((t1[i]<3) && (i==1)) || ((t1[i]<2) && (i==2)) || ((t1[i]==0) && (i==3)))
            {
                int[] tx = new int[77];
                System.arraycopy(t1, 0, tx, 0, 17);
                tx[j]+=i;
                int[] dwx = new int[77];
                System.arraycopy(Secondary.auth_d(dw1, tx, j), 0, dwx, 0, 77);
                for (int k = 1; k < 77; k++)
                    if (dwx[k] == 4)
                    {
                        fw[j]=i;
                    }
            }

        for (int j = 1; j < 17; j++)
            for (int i = 1; i < 4; i++)
                if (((t1[i]<3) && (i==1)) || ((t1[i]<2) && (i==2)) || ((t1[i]==0) && (i==3)))
                {
                    int[] tx = new int[77];
                    System.arraycopy(t1, 0, tx, 0, 17);
                    tx[j]+=i;
                    int[] dwx = new int[77];
                    System.arraycopy(Secondary.auth_d(db1, tx, j), 0, dwx, 0, 77);
                    for (int k = 1; k < 77; k++)
                        if (dwx[k] == 4)
                        {
                            fb[j]=i;
                        }
                }
        for (int i = 1; i < 17; i++) {
            if (fb[i] < fw[i])
                anl += 50;
            if (fb[i] > fw[i])
                anl += -50;
        }
        for (int k=41;k<61;k++)
            if ((dw1[k]==2) & (db1[k]==0))
                anl+=5;

        for (int k=41;k<61;k++)
            if ((dw1[k]==1) & (db1[k]==0))
                anl+=1;

        for (int k=41;k<61;k++)
            if ((db1[k]==2) & (dw1[k]==0))
                anl+=-5;

        for (int k=41;k<61;k++)
            if ((db1[k]==1) & (dw1[k]==0))
                anl+=-1;

        if (win(db,dw)==-1)
            anl=-999;
        if (AiRun.win(db,dw)==1)
            anl=999;
        return anl;
    }

    public static int win(int[] db,int[] dw)
    {
        boolean a = false;
        for (int k=1;k<77;k++)
            if (dw[k] == 4)
            {
                a = true;
                break;
            }
        if (a)
            return 1;
        for (int k=1;k<77;k++)
            if (db[k] == 4)
            {
                a = true;
                break;
            }
        if (a)
            return -1;
        return 0;
    }

    public static int[] tree(int[] db,int[] dw,int[] t)
    {
        //Копирование массивов
        int[] db1 = new int[77];
        System.arraycopy(db, 0, db1, 0, 77);
        int[] dw1 = new int[77];
        System.arraycopy(dw, 0, dw1, 0, 77);
        int[] t1 = new int[17];
        System.arraycopy(t, 0, t1, 0, 17);
        //
        Game.p=0;
        int[] o = new int[17];
        int[][] s = new int[6][17];
        for (int i = 1; i < 17; i++)
            o[i]=-22222;
        for (int i = 1; i < 17; i++)
            if (t1[i] <4) {
                System.out.print(i+" ");
                int[] dbx = new int[77];
                int[] tx = new int[17];
                System.arraycopy(db1, 0, dbx, 0, 77);
                System.arraycopy(t1, 0, tx, 0, 17);
                dbx = Secondary.auth_d(dbx, tx, i);
                tx[i]+=1;
                for (int l = 1; l < 77; l++)
                    if (dbx[l] == 4) {
                        int[] k = new int[7];
                        k[0] = i;
                        k[1]=-999;
                        Game.p= Game.p+1;
                        return k;
                    }


                label2: for (int i2 = 1; i2 < 17; i2++)
                    if (tx[i2] < 4) {
                        int[] dwx = new int[77];
                        int[] tx2 = new int[17];
                        System.arraycopy(dw1, 0, dwx, 0, 77);
                        System.arraycopy(tx, 0, tx2, 0, 17);
                        dwx = Secondary.auth_d(dwx, tx2, i2);
                        tx2[i2]+=1;
                        for (int l = 1; l < 77; l++)
                            if (dwx[l] == 4) {
                                int o2 = 999;
                                if (o2>o[i]) {
                                    o[i] = o2;
                                    s[0][i]= i2;
                                    s[1][i] = 0;
                                    s[2][i] = 0;
                                    s[3][i] = 0;
                                    s[4][i] = 0;
                                    s[5][i] = 0;
                                    Game.p= Game.p+1;
                                }
                                break label2;
                            }

                        int a=engine(dbx,dwx,tx2);
                        int[] dbx1 = new int[77];
                        int[] tx3 = new int[17];
                        System.arraycopy(dbx, 0, dbx1, 0, 77);
                        System.arraycopy(tx2, 0, tx3, 0, 17);
                        dbx1 = Secondary.auth_d(dbx1, tx3, a);
                        tx3[a]+=1;
                        for (int l = 1; l < 77; l++)
                            if (dbx1[l] == 4) {
                                int o2 = -999;
                                if (o2 > o[i]) {
                                    o[i] = o2;
                                    s[0][i] = i2;
                                    s[1][i] = a;
                                    s[2][i] = 0;
                                    s[3][i] = 0;
                                    s[4][i] = 0;
                                    s[5][i] = 0;
                                    Game.p= Game.p+1;
                                }
                                continue label2;
                            }

                        label: for (int j2 = 1; j2 < 17; j2++)
                            if (tx3[j2] < 4) {
                                int[] dwx1 = new int[77];
                                int[] tx4 = new int[17];
                                System.arraycopy(dwx, 0, dwx1, 0, 77);
                                System.arraycopy(tx3, 0, tx4, 0, 17);
                                dwx1 = Secondary.auth_d(dwx1, tx4, j2);
                                tx4[j2]+=1;
                                for (int l = 1; l < 77; l++)
                                    if (dwx1[l] == 4) {
                                        int o2 = 999;
                                        if (o2>o[i]) {
                                            o[i] = o2;
                                            s[0][i] = i2;
                                            s[1][i] = a;
                                            s[2][i] = j2;
                                            s[3][i] = 0;
                                            s[4][i] = 0;
                                            s[5][i] = 0;
                                            Game.p= Game.p+1;
                                        }
                                        break label;
                                    }



                                int b = AiRun.engine(dbx1, dwx1, tx4);
                                int[] dbx2 = new int[77];
                                int[] tx5 = new int[17];
                                System.arraycopy(dbx1, 0, dbx2, 0, 77);
                                System.arraycopy(tx4, 0, tx5, 0, 17);
                                dbx2 = Secondary.auth_d(dbx2, tx5, b);
                                tx5[b]+=1;
                                for (int l = 1; l < 77; l++)
                                    if (dbx2[l] == 4) {
                                        int o2 = -999;
                                        if (o2>o[i]) {
                                            o[i] = o2;
                                            s[0][i] = i2;
                                            s[1][i] = a;
                                            s[2][i] = j2;
                                            s[3][i] = b;
                                            s[4][i] = 0;
                                            s[5][i] = 0;
                                            Game.p= Game.p+1;
                                        }
                                        continue label;
                                    }

                                label3: for (int k = 1; k < 17; k++)
                                    if (tx5[k] < 4) {
                                        int[] dwx2 = new int[77];
                                        int[] tx6 = new int[17];
                                        System.arraycopy(dwx1, 0, dwx2, 0, 77);
                                        System.arraycopy(tx5, 0, tx6, 0, 17);
                                        dwx2 = Secondary.auth_d(dwx2, tx6, k);
                                        tx6[k]+=1;
                                        for (int l = 1; l < 77; l++)
                                            if (dwx2[l] == 4) {
                                                int o2 = 999;
                                                if (o2 > o[i]) {
                                                    o[i] = o2;
                                                    s[0][i] = i2;
                                                    s[1][i] = a;
                                                    s[2][i] = j2;
                                                    s[3][i] = b;
                                                    s[4][i] = k;
                                                    s[5][i] = 0;
                                                    Game.p = Game.p + 1;
                                                }
                                                break label3;
                                            }


                                        int d = AiRun.engine(dbx2, dwx2, tx6);
                                        int[] dbx3 = new int[77];
                                        int[] tx7 = new int[17];
                                        System.arraycopy(dbx2, 0, dbx3, 0, 77);
                                        System.arraycopy(tx6, 0, tx7, 0, 17);
                                        dbx3 = Secondary.auth_d(dbx3, tx7, d);
                                        tx7[i]+=d;
                                        for (int l = 1; l < 77; l++)
                                            if (dbx3[l] == 4) {
                                                int o2 = -999;
                                                if (o2 > o[i]) {
                                                    o[i] = o2;
                                                    s[0][i] = i2;
                                                    s[1][i] = a;
                                                    s[2][i] = j2;
                                                    s[3][i] = b;
                                                    s[4][i] = k;
                                                    s[5][i] = d;
                                                    Game.p = Game.p + 1;
                                                }
                                                continue label3;
                                            }

                                        int o2 = analyze(dbx3, dwx2, tx7);
                                        Game.p = Game.p + 1;
                                        if (o2 > o[i]) {
                                            o[i] = o2;
                                            s[0][i] = i2;
                                            s[1][i] = a;
                                            s[2][i] = j2;
                                            s[3][i] = b;
                                            s[4][i] = k;
                                            s[5][i] = d;
                                        }



                                    }

                            }
                    }
            }

        int min = 1000;
        int[] k = new int[8];
        for (int i = 1; i < 17; i++)
            if ((o[i]<=min) & (o[i]!=-22222)) {
                min = o[i];
                k[0] = i;
            }
        if ((o[1]==999)  & (o[2]==999)  & (o[3]==999)  & (o[4]==999)  & (o[5]==999)  & (o[6]==999)  & (o[7]==999)  & (o[8]==999)  & (o[9]==999)  & (o[10]==999)  & (o[11]==999)  & (o[12]==999)  & (o[13]==999)  & (o[14]==999)  & (o[15]==999)  & (o[16]==999))
            k[0]=engine(db1,dw1,t1);
        k[1]=min;
        k[2]=s[0][k[0]];
        k[3]=s[1][k[0]];
        k[4]=s[2][k[0]];
        k[5]=s[3][k[0]];
        k[6]=s[4][k[0]];
        k[7]=s[5][k[0]];
        return k;

    }

    public static int engine_1(int[] db,int[] dw,int[] t)
    {
        //Копирование массивов
        int[] db1 = new int[77];
        System.arraycopy(db,0,db1,0,77);
        int[] dw1 = new int[77];
        System.arraycopy(dw,0,dw1,0,77);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,17);
        //

        //Атака 4 в ряд
        int a=0;
        int b =Secondary.attack(db1,dw1);
        if (b!=0)
            a = b;

        //Защита 4 в ряд
        if (a==0) {
            b = Secondary.attack(dw1, db1);
            if (b != 0)
                a = b;
        }

        // Ранддом
        if (a==0) {
            b = Secondary.random(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        return a;
    }

    public static int engine_2(int[] db,int[] dw,int[] t)
    {
        //Копирование массивов
        int[] db1 = new int[77];
        System.arraycopy(db,0,db1,0,77);
        int[] dw1 = new int[77];
        System.arraycopy(dw,0,dw1,0,77);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,17);
        //

        //Атака 4 в ряд
        int a=0;
        int b =Secondary.attack(db1,dw);
        if (b!=0)
            a = b;

        //Защита 4 в ряд
        if (a==0) {
            b = Secondary.attack(dw1, db1);
            if (b != 0)
                a = b;
        }

        // Создание узлов
        if (a==0) {
            b = Secondary.lip_a(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        // Защита от узлов
        if (a==0) {
            b = Secondary.lip_d(db1, dw1, t1);
            if (b != 0)
                a = b;
        }


        // Ранддом
        if (a==0) {
            b = Secondary.random(db1, dw1, t1);
            if (b != 0)
                a = b;
        }

        return a;
    }

    public static int[] tree_2(int[] db,int[] dw,int[] t)
    {
        //Копирование массивов
        int[] db1 = new int[77];
        System.arraycopy(db, 0, db1, 0, 77);
        int[] dw1 = new int[77];
        System.arraycopy(dw, 0, dw1, 0, 77);
        int[] t1 = new int[17];
        System.arraycopy(t, 0, t1, 0, 17);
        //
        Game.p=0;
        int[] o = new int[17];
        int[][] s = new int[6][17];
        for (int i = 1; i < 17; i++)
            o[i]=-22222;
        for (int i = 1; i < 17; i++)
            if (t1[i] <4) {
                System.out.print(i+" ");
                int[] dbx = new int[77];
                int[] tx = new int[17];
                System.arraycopy(db1, 0, dbx, 0, 77);
                System.arraycopy(t1, 0, tx, 0, 17);
                dbx = Secondary.auth_d(dbx, tx, i);
                tx[i]+=1;
                for (int l = 1; l < 77; l++)
                    if (dbx[l] == 4) {
                        int[] k = new int[7];
                        k[0] = i;
                        k[1]=-999;
                        Game.p= Game.p+1;
                        return k;
                    }


                label2: for (int i2 = 1; i2 < 17; i2++)
                    if (tx[i2] < 4) {
                        int[] dwx = new int[77];
                        int[] tx2 = new int[17];
                        System.arraycopy(dw1, 0, dwx, 0, 77);
                        System.arraycopy(tx, 0, tx2, 0, 17);
                        dwx = Secondary.auth_d(dwx, tx2, i2);
                        tx2[i2]+=1;
                        for (int l = 1; l < 77; l++)
                            if (dwx[l] == 4) {
                                int o2 = 999;
                                if (o2>o[i]) {
                                    o[i] = o2;
                                    s[0][i]= i2;
                                    s[1][i] = 0;
                                    s[2][i] = 0;
                                    s[3][i] = 0;
                                    Game.p= Game.p+1;
                                }
                                break label2;
                            }

                        int a=engine(dbx,dwx,tx2);
                        int[] dbx1 = new int[77];
                        int[] tx3 = new int[17];
                        System.arraycopy(dbx, 0, dbx1, 0, 77);
                        System.arraycopy(tx2, 0, tx3, 0, 17);
                        dbx1 = Secondary.auth_d(dbx1, tx3, a);
                        tx3[a]+=1;
                        for (int l = 1; l < 77; l++)
                            if (dbx1[l] == 4) {
                                int o2 = -999;
                                if (o2 > o[i]) {
                                    o[i] = o2;
                                    s[0][i] = i2;
                                    s[1][i] = a;
                                    s[2][i] = 0;
                                    s[3][i] = 0;
                                    Game.p= Game.p+1;
                                }
                                continue label2;
                            }

                        label: for (int j2 = 1; j2 < 17; j2++)
                            if (tx3[j2] < 4) {
                                int[] dwx1 = new int[77];
                                int[] tx4 = new int[17];
                                System.arraycopy(dwx, 0, dwx1, 0, 77);
                                System.arraycopy(tx3, 0, tx4, 0, 17);
                                dwx1 = Secondary.auth_d(dwx1, tx4, j2);
                                tx4[j2]+=1;
                                for (int l = 1; l < 77; l++)
                                    if (dwx1[l] == 4) {
                                        int o2 = 999;
                                        if (o2>o[i]) {
                                            o[i] = o2;
                                            s[0][i] = i2;
                                            s[1][i] = a;
                                            s[2][i] = j2;
                                            s[3][i] = 0;
                                            Game.p= Game.p+1;
                                        }
                                        break label;
                                    }



                                int b = AiRun.engine(dbx1, dwx1, tx4);
                                int[] dbx2 = new int[77];
                                int[] tx5 = new int[17];
                                System.arraycopy(dbx1, 0, dbx2, 0, 77);
                                System.arraycopy(tx4, 0, tx5, 0, 17);
                                dbx2 = Secondary.auth_d(dbx2, tx5, b);
                                tx5[b]+=1;
                                for (int l = 1; l < 77; l++)
                                    if (dbx2[l] == 4) {
                                        int o2 = -999;
                                        if (o2>o[i]) {
                                            o[i] = o2;
                                            s[0][i] = i2;
                                            s[1][i] = a;
                                            s[2][i] = j2;
                                            s[3][i] = b;
                                            Game.p= Game.p+1;
                                        }
                                        continue label;
                                    }


                                int o2 = analyze(dbx2, dwx1, tx5);
                                Game.p = Game.p + 1;
                                if (o2 > o[i]) {
                                    o[i] = o2;
                                    s[0][i] = i2;
                                    s[1][i] = a;
                                    s[2][i] = j2;
                                    s[3][i] = b;

                                }



                            }

                    }
            }

        int min = 1000;
        int[] k = new int[8];
        for (int i = 1; i < 17; i++)
            if ((o[i]<=min) & (o[i]!=-22222)) {
                min = o[i];
                k[0] = i;
            }
        if ((o[1]==999)  & (o[2]==999)  & (o[3]==999)  & (o[4]==999)  & (o[5]==999)  & (o[6]==999)  & (o[7]==999)  & (o[8]==999)  & (o[9]==999)  & (o[10]==999)  & (o[11]==999)  & (o[12]==999)  & (o[13]==999)  & (o[14]==999)  & (o[15]==999)  & (o[16]==999))
            k[0]=engine(db1,dw1,t1);
        k[1]=min;
        k[2]=s[0][k[0]];
        k[3]=s[1][k[0]];
        k[4]=s[2][k[0]];
        k[5]=s[3][k[0]];
        return k;

    }
}
