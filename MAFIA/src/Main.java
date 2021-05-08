import java.io.BufferedInputStream;
import java.util.*;

public class Main {
    private static int  USER_ID;
   // protected GenericList<Mafia> active_Mafias;
   // protected GenericList<Detective> active_Detectives;
   // protected GenericList<Healer> active_Healers;
    //protected GenericList<Commoner> active_Commoners;
    public static void main(String[] args) {
        System.out.println("Welcome to Mafia Game!! ");
        Main obj= new Main();
        obj.Menu();
    }
    public int getUSER_ID() {
        return USER_ID;
    }

    public void Menu(){
        try {
            Scanner sc = new Scanner(new BufferedInputStream(System.in));
            System.out.println("Enter no of players in the game");
            int N = sc.nextInt();
            if(N>=6) {
                System.out.println("Choose a Character in the options mentioned below:");
                System.out.println("1. Mafia");
                System.out.println("2. Detective");
                System.out.println("3. Healer");
                System.out.println("4. Commoner");
                System.out.println("5. Assign Randomly");
                int x = sc.nextInt();
                create_players(N, x);
            }
            else{
                while(N <6){
                    System.out.println("Please enter no of players >=6 in the game ");
                    N = sc.nextInt();
                }
                System.out.println("Choose a Character in the options mentioned below:");
                System.out.println("1. Mafia");
                System.out.println("2. Detective");
                System.out.println("3. Healer");
                System.out.println("4. Commoner");
                System.out.println("5. Assign Randomly");
                int x = sc.nextInt();
                create_players(N, x);
            }
        }
        catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }
    public void create_players(int N,int x) {
        Organiser obj2=new Organiser();
        /*ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<=N+1; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
         */
        try {
            int i;
            int l = N / 5;
            int c = N / 5;
            int h = Math.max(N / 10, 1);
            int s = N - (l + c + h);
            switch (x) {
                case 1:
                    USER_ID = 1;
                    break;
                case 2:
                    USER_ID = l + 1;
                    break;
                case 3:
                    USER_ID = l + c + 1;
                    break;
                case 4:
                    USER_ID = l + c + h + 1;
                    break;
                case 5:
                    Random r = new Random();
                    USER_ID = (r.nextInt(N) + 1);
            }

            for (i = 1; i <= l; i++) {
                Mafia obj = new Mafia(i);
                if(i==getUSER_ID()){
                    obj.setType("USER");
                }
                obj2.Mafias.add(obj);
            }
            for (i = i; i <= l + c; i++) {
                Detective obj = new Detective(i);
                if(i==getUSER_ID()){
                    obj.setType("USER");
                }
                obj2.Detectives.add(obj);
            }

            for (i = i; i <= l + c + h; i++) {
                Healer obj = new Healer(i);
                if(i==getUSER_ID()){
                    obj.setType("USER");
                }
                obj2.Healers.add(obj);
            }
            for (i = i; i <= N; i++) {
                Commoner obj = new Commoner(i);
                if(i==getUSER_ID()){
                    obj.setType("USER");
                }
                obj2.Commoners.add(obj);
            }
            start_game(obj2);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }
    public void start_game(Organiser obj){
        obj.Gameplay(getUSER_ID());

    }

}
