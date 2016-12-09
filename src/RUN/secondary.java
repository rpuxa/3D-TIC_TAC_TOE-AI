package RUN;


import java.util.Map;

public class secondary {

    public static int[] auth_d(int[] db, int[] t, int l)
    {
        int[] db1 = new int[77];
        System.arraycopy(db,0,db1,0,77);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,17);
        int n = l;
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
        z = t1[n] + 1;

        for (int i = 1; i < 5; i++)
            if ((y == i) & (z == 1)) {
                db1[i] = db1[i] + 1;
            }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 1))
                db1[i + 4] = db1[i + 4] + 1;
        }
        if ((5 - x == y) & (z == 1))
            db1[9] = db1[9] + 1;

        if ((x == y) & (z == 1))
            db1[10] = db1[10] + 1;

        for (int i = 1; i < 5; i++) {
            if ((y == i) & (z == 2))
                db1[10 + i] = db1[10 + i] + 1;
        }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 2))
                db1[i + 14] = db1[i + 14] + 1;
        }
        if ((5 - x == y) & (z == 2))
            db1[19] = db1[19] + 1;

        if ((x == y) & (z == 2))
            db1[20] = db1[20] + 1;

        for (int i = 1; i < 5; i++)
            if ((y == i) & (z == 3))
                db1[i + 20] = db1[i + 20] + 1;

        for (int i = 1; i < 5; i++)
            if ((x == i) & (z == 3))
                db1[i + 24] = db1[i + 24] + 1;

        if ((5 - x == y) & (z == 3))
            db1[29] = db1[29] + 1;

        if ((x == y) & (z == 3))
            db1[30] = db1[30] + 1;

        for (int i = 1; i < 5; i++) {
            if ((y == i) & (z == 4))
                db1[i + 30] = db1[i + 30] + 1;
        }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 4))
                db1[i + 34] = db1[i + 34] + 1;
        }
        if ((5 - x == y) & (z == 4))
            db1[39] = db1[39] + 1;

        if ((x == y) & (z == 4))
            db1[40] = db1[40] + 1;

        for (int i = 1; i < 5; i++) {
            if ((x == i) & (5 - y == z))
                db1[40 + i] = db1[40 + i] + 1;
            if ((x == i) & (y == z))
                db1[44 + i] = db1[44 + i] + 1;
            if ((y == 5 - i) & (x == z))
                db1[48 + i] = db1[48 + i] + 1;
            if ((y == 5 - i) & (5 - x == z))
                db1[52 + i] = db1[52 + i] + 1;
        }

        if ((x == z) & (x == 5 - y))
            db1[57] = db1[57] + 1;

        if ((x == 5 - z) & (x == y))
            db1[58] = db1[58] + 1;

        if ((y == z) & (x == 5 - y))
            db1[59] = db1[59] + 1;

        if ((x == z) & (x == y))
            db1[60] = db1[60] + 1;

        db1[n + 60] = db1[n + 60] + 1;

        return db1;
    }

    public static int[] auth_t(int[] t, int i)
    {
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,17);
        t1[i] = t1[i] + 1;
        return t1;
    }


//engine

    public static int attack(int[] db,int[] dw,int[] t)
    {
        int[] db1 = new int[77];
        System.arraycopy(db,0,db1,0,db.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);

        int a=0;
        for (int i=1;i<17;i++)
            if (t1[i]!=4)
            {
                int[] dbx= new int[77];
                System.arraycopy(auth_d(db1,t1,i),0,dbx,0,77);
                //Условие
                for (int k=1;k<77;k++)
                {
                    if (dbx[k] == 4)
                    {
                        a=i;
                        break;
                    }
                }
                //Конец условия
                if (a!=0)
                    break;

            }

        return a;
    }

    public static int defend(int[] db,int[] dw,int[] t)
    {
        int[] dw1 = new int[77];
        System.arraycopy(dw,0,dw1,0,dw.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);

        int a=0;
        for (int i=1;i<17;i++)
            if (t1[i]!=4)
            {
                int[] dwx= new int[77];
                System.arraycopy(auth_d(dw1,t1,i),0,dwx,0,77);
                //Условие
                for (int k=1;k<77;k++)
                {
                    if (dwx[k] == 4)
                    {
                        a=i;
                        break;
                    }
                }
                //Конец условия
                if (a!=0)
                    break;

            }

        return a;
    }

    public static boolean[] lined_dw(int[] db,int[] t)
    {
        int[] dw1 = new int[77];
        System.arraycopy(db,0,dw1,0,db.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);

        boolean[] fw = new boolean[17];
        for (int i=1;i<17;i++)
            if (t1[i]<3)
            {
                t1[i]=t1[i]+1;
                int[] dwx= new int[77];
                System.arraycopy(auth_d(dw1,t1,i),0,dwx,0,77);
                //Условие
                for (int k=1;k<77;k++)
                {
                    if (dwx[k] == 4)
                    {
                        fw[i]=true;
                        break;
                    }
                }
                //Конец условия

            }

        return fw;
    }

    public static boolean[] lined_db(int[] dw,int[] t)
    {
        int[] db1 = new int[77];
        System.arraycopy(dw,0,db1,0,dw.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);

        boolean[] fb = new boolean[17];
        for (int i=1;i<17;i++)
            if (t1[i]<3)
            {
                t1[i]=t1[i]+1;
                int[] dbx= new int[77];
                System.arraycopy(auth_d(db1,t1,i),0,dbx,0,77);
                //Условие
                for (int k=1;k<77;k++)
                {
                    if (dbx[k] == 4)
                    {
                        fb[i]=true;
                        break;
                    }
                }
                //Конец условия

            }

        return fb;
    }

    public static int lip_a(int[] db,int[] dw,int[] t)
    {
        int[] db1 = new int[77];
        System.arraycopy(db,0,db1,0,db.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(lined_dw(db1, t1),0,fw1,0,17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(lined_db(dw, t1),0,fb1,0,17);
        int a=0,b=0,c=0;
        for (int i=1;i<77;i++)
            if ((db[i]==3) & (dw[i]==0))
                c=c+1;
        for (int i=1;i<17;i++)
            if ((t1[i]<4) & (fb1[i]=false))
            {
                a=0; b=0;
                int[] dbx = new int[77];
                System.arraycopy(auth_d(db1, t1, i), 0, dbx, 0, 77);
                int[] tx = new int[17];
                System.arraycopy(auth_t(t1,i), 0, tx, 0, 17);
                for (int k=1;k<77;k++)
                    if ((dbx[k]==3) & (dw[k]==0))
                        b=b+1;
                for (int j = 1; j < 17; j++)
                    if (t1[j]!=4)
                    {
                        int[] dbx1 = new int[77];
                        System.arraycopy(auth_d(dbx, tx, j), 0, dbx1, 0, 77);
                        for (int k = 1; k < 77; k++)
                            if (dbx1[k] == 4)
                                a =a+1;


                    }
                if (((1 + c < b) | ((c < b) & (fw1[i] == true))) & (a == 2)) {
                    return i;
                }
            }

        return 0;
    }

    public static int lip_d(int[] db,int[] dw,int[] t)
    {
        int[] dw1 = new int[77];
        System.arraycopy(dw,0,dw1,0,dw.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(lined_db(dw1, t1),0,fb1,0,17);
        int a=0,b=0,c=0;
        for (int i=1;i<77;i++)
            if ((dw[i]==3) & (db[i]==0))
                c=c+1;
        for (int i=1;i<17;i++)
            if (t1[i]<4)
            {
                boolean[] xm = new boolean[17];
                a=0; b=0;
                int[] dwx = new int[77];
                System.arraycopy(auth_d(dw1, t1, i), 0, dwx, 0, 77);
                int[] tx = new int[17];
                System.arraycopy(auth_t(t1,i), 0, tx, 0, 17);
                for (int k=1;k<77;k++)
                    if ((dwx[k]==3) & (db[k]==0))
                        b=b+1;
                for (int j = 1; j < 17; j++)
                    if (t1[j]!=4)
                    {
                        int[] dwx1 = new int[77];
                        System.arraycopy(auth_d(dwx, tx, j), 0, dwx1, 0, 77);
                        for (int k = 1; k < 77; k++)
                            if (dwx1[k] == 4) {
                                a = a + 1;
                                xm[j]=true;
                            }


                    }
                for (int j = 1; j < 17; j++)
                    if (((1 + c < b) & (fb1[i] == false) & (a >= 2)) | ((c < b) & (fb1[i] == true) & (fb1[j] == false) & (a >= 2) & (xm[j] == true) & (t1[j]!=4))) {
                        if ((1 + c < b) & (fb1[i] == false))
                        {

                            a=i;
                            return a;
                        }
                        else
                        {

                            a=j;
                            return a;
                        }
                    }
            }

        return 0;
    }

    public static int debut(int[] db,int[] dw,int[] t)
    {
        if (t[1]==0)
            return 1;
        if (t[4]==0)
            return 4;
        if (t[13]==0)
            return 13;
        if (t[16]==0)
            return 16;
        return 0;
    }

    public static int d3_a(int[] db,int[] dw,int[] t)
    {
        int[] db1 = new int[77];
        System.arraycopy(db,0,db1,0,db.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(lined_db(db1, t1),0,fw1,0,17);
        int a=0,b=0,c=0;
        for (int i=1;i<77;i++)
            if ((db[i]==3) & (dw[i]==0))
                c=c+1;
        for (int i=1;i<17;i++)
            if (t1[i]<4)
            {
                a=0; b=0;
                int[] dbx = new int[77];
                System.arraycopy(auth_d(db1, t1, i), 0, dbx, 0, 77);
                int[] tx = new int[17];
                System.arraycopy(auth_t(t1,i), 0, tx, 0, 17);
                for (int k=1;k<77;k++)
                    if ((dbx[k]==3) & (dw[k]==0))
                        b=b+1;
                for (int j = 1; j < 17; j++)
                    if (t1[j]!=4)
                    {
                        int[] dbx1 = new int[77];
                        System.arraycopy(auth_d(dbx, tx, j), 0, dbx1, 0, 77);
                        for (int k = 1; k < 77; k++)
                            if (dbx1[k] == 4)
                                a =a+1;


                    }
                if ((c < b) & (fw1[i] == false) & (a == 0)) {
                    return i;
                }
            }

        return 0;
    }

    public static int d3_d(int[] db,int[] dw,int[] t)
    {
        int[] dw1 = new int[77];
        System.arraycopy(dw,0,dw1,0,dw.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(lined_db(db, t1),0,fw1,0,17);
        int a=0,b=0,c=0;
        for (int i=1;i<77;i++)
            if ((dw[i]==3) & (db[i]==0))
                c=c+1;
        for (int i=1;i<17;i++)
            if (t1[i]<4)
            {
                a=0; b=0;
                int[] dwx = new int[77];
                System.arraycopy(auth_d(dw, t1, i), 0, dwx, 0, 77);
                int[] tx = new int[17];
                System.arraycopy(auth_t(t1,i), 0, tx, 0, 17);
                for (int k=1;k<77;k++)
                    if ((dwx[k]==3) & (db[k]==0))
                        b=b+1;
                for (int j = 1; j < 17; j++)
                    if (t1[j]!=4)
                    {
                        int[] dwx1 = new int[77];
                        System.arraycopy(auth_d(dwx, tx, j), 0, dwx1, 0, 77);
                        for (int k = 1; k < 77; k++)
                            if (dwx1[k] == 4)
                                a =a+1;


                    }
                if ((c < b) & (fw1[i] == false) & (a == 0)) {
                    return i;
                }
            }

        return 0;
    }

    public static int dc2(int[] db,int[] dw,int[] t)
    {
        int[] db1 = new int[77];
        System.arraycopy(db,0,db1,0,db.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(lined_db(db, t1),0,fw1,0,17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(lined_dw(dw, t1),0,fb1,0,17);
        int b=0,c=0;
        for (int i=41;i<61;i++)
            if ((db[i]==2) & (dw[i]==0))
                c=c+1;
        for (int i=1;i<17;i++)
            if ((t1[i]!=4) & (fw1[i]==false) & (fw1[i]==false))
            {
                b=0;
                int[] dbx= new int[77];
                System.arraycopy(auth_d(db1,t1,i),0,dbx,0,77);
                for (int k=41;k<61;k++)
                    if ((dbx[k]==2) & (dw[k]==0))
                        b=b+1;
                if (1+c<b) {
                    return i;
                }
            }

        return 0;
    }

    public static int dc1(int[] db,int[] dw,int[] t)
    {
        int[] db1 = new int[77];
        System.arraycopy(db,0,db1,0,db.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(lined_db(db, t1),0,fw1,0,17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(lined_dw(dw, t1),0,fb1,0,17);
        int b=0,c=0;
        for (int i=41;i<61;i++)
            if ((db[i]==2) & (dw[i]==0))
                c=c+1;
        for (int i=1;i<17;i++)
            if ((t1[i]!=4) & (fw1[i]==false) & (fb1[i]==false))
            {
                b=0;
                int[] dbx= new int[77];
                System.arraycopy(auth_d(db1,t1,i),0,dbx,0,77);
                for (int k=41;k<61;k++)
                    if ((dbx[k]==2) & (dw[k]==0))
                        b=b+1;
                if (c<b) {
                    return i;
                }
            }

        return 0;
    }

    public static int def_3(int[] db,int[] dw,int[] t)
    {
        int[] db1 = new int[77];
        System.arraycopy(dw,0,db1,0,dw.length);
        int[] t1 = new int[77];
        System.arraycopy(t,0,t1,0,t.length);
        boolean[] fw1 = new boolean[17];
        System.arraycopy(lined_db(db, t1),0,fw1,0,17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(lined_dw(dw, t1),0,fb1,0,17);
        int b=0,c=0;
        for (int i=1;i<77;i++)
            if ((dw[i]==3) & (db[i]==0))
                c=c+1;
        for (int i=1;i<17;i++)
            if ((t1[i]!=4) & (fw1[i]==false) & (fb1[i]==false))
            {
                b=0;
                int[] dbx= new int[77];
                System.arraycopy(auth_d(db1,t1,i),0,dbx,0,77);
                for (int k=1;k<77;k++)
                    if ((dbx[k]==3) & (db[k]==0))
                        b=b+1;
                if (c<b) {
                    return i;
                }
            }

        return 0;
    }

    public static int rand(int[] db,int[] dw,int[] t)
    {
        boolean[] fw1 = new boolean[17];
        System.arraycopy(lined_db(db, t),0,fw1,0,17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(lined_dw(dw, t),0,fb1,0,17);
        for (int i=1;i<17;i++)
            if ((t[i]!=4) & (fw1[i]==false) & (fb1[i]==false))
            {
                return i;
            }
        for (int i=1;i<17;i++)
            if (t[i]!=4)
            {
                return i;
            }
        return 0;
    }

    public static int random(int[] db,int[] dw,int[] t)
    {
        boolean[] fw1 = new boolean[17];
        System.arraycopy(lined_db(db, t),0,fw1,0,17);
        boolean[] fb1 = new boolean[17];
        System.arraycopy(lined_dw(dw, t),0,fb1,0,17);
        for (int i=1;i<17;i++) {
            int j=(int)(Math.random()*16+1);

            if ((t[j] != 4) & (fw1[j] == false) & (fb1[j] == false)) {
                return j;
            }
            else
            if (t[j] != 4) {
                return j;
            }
        }
        return 0;
    }
}