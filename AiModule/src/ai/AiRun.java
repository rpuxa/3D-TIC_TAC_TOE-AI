package ai;


import java.util.HashMap;
import java.util.Map;

  public class AiRun {

    static Map<StateInfo,Integer> states = new HashMap<>();

      public static int engine(int[] db,int[] dw,int[] t)
      {
          //Атака 4 в ряд
          int a=0;
          int b =Secondary.attack(db,t);
          if (b!=0)
              a = b;

          //Защита 4 в ряд
          if (a==0) {
              b = Secondary.attack(dw, t);
              if (b != 0)
                  a = b;
          }

          // Создание узлов
          if (a==0) {
              b = Secondary.lip_a(db, dw, t);
              if (b != 0)
                  a = b;
          }

          // Защита от узлов
          if (a==0) {
              b = Secondary.lip_d(db, dw, t);
              if (b != 0)
                  a = b;
          }

          // Создание двойных линий
          if (a==0) {
              b = Secondary.double_(db, dw, t);
              if (b != 0)
                  a = b;
          }

          // Защита от двойных линий
          if (a==0) {
              b = Secondary.double_(dw, db, t);
              if (b != 0)
                  a = b;
          }


          // Атака на 3 недостижимых в ряд
          if (a==0) {
              b = Secondary.d3_a(db, dw, t);
              if (b != 0)
                  a = b;
          }

          // Защита от 3 недостижимых в ряд
          if (a==0) {
              b = Secondary.d3_d(db, dw, t);
              if (b != 0)
                  a = b;
          }

          // Дебют
          if (a==0) {
              b = Secondary.debut(t);
              if (b != 0)
                  a = b;
          }

          // Захват 2 диагоналей
          if (a==0) {
              b = Secondary.dc2(db, dw, t);
              if (b != 0)
                  a = b;
          }

          // Захват 1 диагонали
          if (a==0) {
              b = Secondary.dc1(db, dw, t);
              if (b != 0)
                  a = b;
          }

          // Защита от 3 в ряд
          if (a==0) {
              b = Secondary.def_3(db, dw, t);
              if (b != 0)
                  a = b;
          }

          // Ранддом
          if (a==0) {
              b = Secondary.rand(db, dw, t);
              if (b != 0)
                  a = b;
          }

          return a;
      }



      public static int analyzeMaxDepth(int[] db,int[] dw,int[] t)
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
            if (((t1[j]<3) && (i==1)) || ((t1[j]<2) && (i==2)) || ((t1[j]==0) && (i==3)))
            {
                int[] tx = new int[77];
                System.arraycopy(t1, 0, tx, 0, 17);
                tx[j]+=i;
                int[] dwx = new int[77];
                System.arraycopy(Secondary.auth_d(dw1.clone(), tx, j), 0, dwx, 0, 77);
                for (int k = 1; k < 77; k++)
                    if (dwx[k] == 4)
                    {
                        fw[j]=i;
                    }
            }

        for (int j = 1; j < 17; j++)
            for (int i = 1; i < 4; i++)
                if (((t1[j]<3) && (i==1)) || ((t1[j]<2) && (i==2)) || ((t1[j]==0) && (i==3)))
                {
                    int[] tx = new int[77];
                    System.arraycopy(t1, 0, tx, 0, 17);
                    tx[j]+=i;
                    int[] dwx = new int[77];
                    System.arraycopy(Secondary.auth_d(db1.clone(), tx, j), 0, dwx, 0, 77);
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
        return anl;
    }

    public static int win(int[] db,int[] dw)
    {
        for (int k=1;k<77;k++)
            if (dw[k] == 4)
            return 1;
        for (int k=1;k<77;k++)
            if (db[k] == 4)
            return -1;
        return 0;
    }

    public static int analyze(int[] db, int[] dw, int[] t, int depth, int maxDepth,int[] move) {
        int result;
        int lastMove = 0;
        if (depth == 0) {
            int min = 10000;
            int resultMove = 0;
            for (int i = 1; i < 17; i++)
            if (t[i]<4)
            {
               System.out.print(i+" ");
                if (lastMove!=0){
                db=InfAuth(db, t, lastMove);
                t[lastMove]+=-1;
                }
                lastMove=i;
                db = Secondary.auth_d(db.clone(), t, i);
                move[depth]=i;
                for (int k = 1; k < 77; k++)
                    if (db[k] == 4)
                        return i;
                t[i]++;
                StateInfo state = new StateInfo(db, dw, depth);
                if (states.get(state)==null) {
                    result = analyze(db.clone(), dw.clone(), t.clone(), depth + 1, maxDepth, move);
                    states.put(state, result);
                }
                else
                    result = states.get(state);
                if (result < min) {
                    min = result;
                    resultMove = i;
                }
            }
            return resultMove;
        }

        if (depth == maxDepth)
            return analyzeMaxDepth(db, dw, t);



        if ((depth == 1) || (depth == 3) || (depth == 5)) {
            int max=-10000;
            for (int i = 1; i < 17; i++)
                if (t[i]<4)
            {
                if (lastMove!=0) {
                    dw = InfAuth(dw, t, lastMove);
                    t[lastMove] += -1;
                }
                lastMove=i;
                dw = Secondary.auth_d(dw.clone(), t, i);
                move[depth]=i;
                for (int k = 1; k < 77; k++)
                    if (dw[k] == 4)
                        return 1000-depth;
                t[i]++;
                StateInfo state = new StateInfo(db, dw, depth);
                if (states.get(state)==null) {
                    result = analyze(db.clone(), dw.clone(), t.clone(), depth + 1, maxDepth, move);
                    states.put(state, result);
                }
                else
                    result = states.get(state);
                if (result > max)
                    max = result;
            }
            return max;
        }


            if ((depth == 2) || (depth == 4)) {
                int i = engine(db.clone(), dw.clone(), t.clone());
                db = Secondary.auth_d(db.clone(), t, i);
                move[depth]=i;
                for (int k = 1; k < 77; k++)
                    if (db[k]==4)
                        return -1000-depth;
                t[i]++;
                StateInfo state = new StateInfo(db, dw, depth);
                if (states.get(state)==null) {
                    result = analyze(db.clone(), dw.clone(), t.clone(), depth + 1, maxDepth, move);
                    states.put(state, result);
                }
                else
                    result = states.get(state);
                return result;
            }

            return 0;

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

   private static int[] InfAuth(int[] db, int[] t, int l) {
        int[] db1 = new int[77];
        System.arraycopy(db, 0, db1, 0, 77);
        int[] t1 = new int[77];
        System.arraycopy(t, 0, t1, 0, 17);
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
        z = t1[l];

        for (int i = 1; i < 5; i++)
            if ((y == i) & (z == 1)) {
                db1[i] += -1;
            }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 1))
                db1[i + 4] += -1;
        }
        if ((5 - x == y) & (z == 1))
            db1[9] += -1;

        if ((x == y) & (z == 1))
            db1[10] += -1;

        for (int i = 1; i < 5; i++) {
            if ((y == i) & (z == 2))
                db1[10 + i] = db1[10 + i] - 1;
        }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 2))
                db1[i + 14] = db1[i + 14] - 1;
        }
        if ((5 - x == y) & (z == 2))
            db1[19] = db1[19] - 1;

        if ((x == y) & (z == 2))
            db1[20] = db1[20] - 1;

        for (int i = 1; i < 5; i++)
            if ((y == i) & (z == 3))
                db1[i + 20] = db1[i + 20] - 1;

        for (int i = 1; i < 5; i++)
            if ((x == i) & (z == 3))
                db1[i + 24] = db1[i + 24] - 1;

        if ((5 - x == y) & (z == 3))
            db1[29] = db1[29] - 1;

        if ((x == y) & (z == 3))
            db1[30] = db1[30] - 1;

        for (int i = 1; i < 5; i++) {
            if ((y == i) & (z == 4))
                db1[i + 30] = db1[i + 30] - 1;
        }
        for (int i = 1; i < 5; i++) {
            if ((x == i) & (z == 4))
                db1[i + 34] = db1[i + 34] - 1;
        }
        if ((5 - x == y) & (z == 4))
            db1[39] = db1[39] - 1;

        if ((x == y) & (z == 4))
            db1[40] = db1[40] - 1;

        for (int i = 1; i < 5; i++) {
            if ((x == i) & (5 - y == z))
                db1[40 + i] = db1[40 + i] - 1;
            if ((x == i) & (y == z))
                db1[44 + i] = db1[44 + i] - 1;
            if ((y == 5 - i) & (x == z))
                db1[48 + i] = db1[48 + i] - 1;
            if ((y == 5 - i) & (5 - x == z))
                db1[52 + i] = db1[52 + i] - 1;
        }

        if ((x == z) & (x == 5 - y))
            db1[57] = db1[57] - 1;

        if ((x == 5 - z) & (x == y))
            db1[58] = db1[58] - 1;

        if ((y == z) & (x == 5 - y))
            db1[59] = db1[59] - 1;

        if ((x == z) & (x == y))
            db1[60] = db1[60] - 1;

        db1[l + 60] = db1[l + 60] - 1;



        return db1;
    }
}

  class StateInfo {

    int[] db, dw;
    int depth;

   StateInfo(int[] db, int[] dw, int depth) {
        this.db = db;
        this.dw = dw;
        this.depth = depth;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;

        StateInfo other = (StateInfo) obj;

        for (int i = 0; i < db.length; ++i) {
            if (db[i] != other.db[i]) return false;
            if (dw[i] != other.dw[i]) return false;
        }

        if (depth != other.depth) return false;

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
        hash += depth*19;
        return hash;
    }
}