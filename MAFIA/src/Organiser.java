import java.io.BufferedInputStream;
import java.util.*;
public class Organiser {
    protected GenericList<Mafia> Mafias;
    protected GenericList<Detective> Detectives;
    protected GenericList<Healer> Healers;
    protected GenericList<Commoner> Commoners;

    Organiser(){
        Mafias =new GenericList();
        Detectives =new GenericList();
        Healers =new GenericList();
        Commoners=new GenericList();
    }

    public void Gameplay(int user_id){

        int count=1;
        while(!((alive_count(Mafias)==0) || (alive_count(Mafias)==
                alive_count(Detectives)+ alive_count(Healers)+ alive_count(Commoners)))){
            System.out.println("Round:"+count);
            print_alive();
            int proceed=-1;
            int dead;
            int vote_out=-1;
            Scanner sc = new Scanner(new BufferedInputStream(System.in));
            Players user=get_element(user_id);
///Mafia
            if(user.getClass().getName()=="Mafia" && user.getStatus()=="ALIVE"){
                System.out.println("Choose whom to kill");
                int temp=sc.nextInt();
                Players target=get_element(temp);
                if(target.getStatus()=="ALIVE") {
                    choose_target( target);
                    dead=temp;
                }
                else{
                    while(target.getStatus()!="ALIVE") {
                        System.out.println("PLAYER ALREADY DEAD,Choose another one");
                        temp = sc.nextInt();
                        target = get_element(temp);
                    }
                    choose_target(target);
                    dead=temp;
                }
            }
            else{
                int temp=generate_list(Detectives,Commoners, Healers);
                Players target=get_element(temp);
                choose_target(target);
                dead=temp;
                System.out.println("Mafias have chosen their target");
            }
//Detective
            if(user.getClass().getName()=="Detective" && user.getStatus()=="ALIVE"){
                System.out.println("Choose your target");
                int temp=sc.nextInt();
                Players target=get_element(temp);
                if(target.getStatus()=="ALIVE" && target.getClass().getName() !="Detective") {
                    proceed=check_player(target);
                }
                else{
                    while(target.getStatus()!="ALIVE" ) {
                        System.out.println("PLAYER ALREADY DEAD or DETECTIVE,Choose another one");
                        temp = sc.nextInt();
                        target = get_element(temp);
                    }
                   proceed=check_player(target);
                }
                if(proceed==1){
                    System.out.println("Player"+target.getID()+" is Mafia");
                    target.setStatus("DEAD");
                    vote_out=target.getID();

                }
                if(proceed==2) {
                    System.out.println("Player"+target.getID()+" is not Mafia");
                }

            }
            else{
                int temp=generate_list(Mafias,Commoners, Healers);
                Players target=get_element(temp);
                //assert equals(target,null);
                proceed = check_player(target);
                System.out.println("Detectives have tested their target");

            }
///healer
            if(user.getClass().getName()=="Healer" && user.getStatus()=="ALIVE"){
                System.out.println("Choose a person to heal");
                int temp=sc.nextInt();
                Players target=get_element(temp);
                if(target.getStatus()=="ALIVE") {
                    heal_player(target,dead);
                }
                else{
                    while(target.getStatus()!="ALIVE" ) {
                        System.out.println("PLAYER ALREADY DEAD,Choose another one");
                        temp = sc.nextInt();
                        target = get_element(temp);
                    }
                    heal_player(target,dead);
                }
            }
            else{
                heal(dead);
                System.out.println("Healers have healed their target");
            }
//Commoner
            if(user.getClass().getName()=="Commoner" && user.getStatus()=="ALIVE"){
                System.out.println("Choose a person to vote");
                int temp=sc.nextInt();
            }
            else{
               System.out.println("Voting over");
            }
 //vote out
            if(proceed == 2){
                vote_out=vote();
                get_element(vote_out).setStatus("DEAD");
            }
            print_end_of_round(dead,vote_out,count);
            count++;
        }
        print_end_game();
    }
    public void print_end_game(){
        System.out.println("--------------Game Over--------------");
        if (alive_count(Mafias)==0) {
            System.out.println("Mafias Lost");
        }
        else{
            System.out.println("Mafias Won");
        }
        print_roles(Mafias);
        print_roles(Detectives);
        print_roles(Healers);
        print_roles(Commoners);
    }
    public void print_end_of_round(int dead,int vote_out,int count){
        System.out.println("--------------Round "+count+" Summary--------------");
        if(dead != -1)
        System.out.println("Player"+dead +" died");
        else{
            System.out.println("No one died");
        }
        if(vote_out !=-1)
            System.out.println("Player"+vote_out +" voted out");

        System.out.println("--------------End of Round "+count+"--------------");
    }
    public void heal(int dead){
        ArrayList<Integer> list = new ArrayList<>();
        int l=Mafias.size();
        for (int i=0;i<l;i++){
            if(Mafias.get(i).getStatus()=="ALIVE")
                list.add(Mafias.get(i).getID());
        }
        l=Detectives.size();
        for (int i=0;i<l;i++){
            if(Detectives.get(i).getStatus()=="ALIVE")
                list.add(Detectives.get(i).getID());
        }
        l=Healers.size();
        for (int i=0;i<l;i++){
            if(Healers.get(i).getStatus()=="ALIVE")
                list.add(Healers.get(i).getID());
        }
        l=Commoners.size();
        for (int i=0;i<l;i++){
            if(Commoners.get(i).getStatus()=="ALIVE")
                list.add(Commoners.get(i).getID());
        }
        list.add(dead);
        Random rand = new Random();
        int x = list.get(rand.nextInt(list.size()));
        Players target=get_element(x);
        heal_player(target,dead);
    }
    public void heal_player(Players target ,int dead){

        if(target.getID()==dead) {
            target.increase_Boost(true);
            dead=-1;
        }
        else{
            target.increase_Boost(false);
        }
    }
    public int check_player(Players target){
        if(target.getClass().getName()=="Detective"){
            System.out.println("You cant choose another detective");
            return 0;
        }
        else if(target.getClass().getName()=="Mafia"){
           // System.out.println("Player"+target.getID()+" is Mafia");
            return 1;
        }
        //System.out.println("Player"+target.getID()+" is not Mafia");
        return 2;
    }
    public void print_alive(){
        System.out.println("Players alive are:");
        print(Mafias);
        print(Detectives);
        print(Healers);
        print(Commoners);
    }
    public  void print(GenericList<? extends Players> list){
        int l=list.size();
        for (int i=0;i<l;i++){
            if(list.get(i).getStatus()=="ALIVE")
            System.out.println("Player"+list.get(i).getID() +list.get(i).getType());
        }
    }
    public void print_roles(GenericList<? extends Players> list){
        int l=list.size();
        //System.out.println("Size:"+l);
        for (int i=0;i<l;i++){
            System.out.println("Player"+list.get(i).getID()+
                        " "+list.get(i).getClass().getName()+" "+list.get(i).getType());
        }
    }
    public Players get_element(int id){
        int l=Mafias.size();
        for (int i=0;i<l;i++){
            if(Mafias.get(i).getID()==id){
                return Mafias.get(i);
            }
        }
        l= Detectives.size();
        for (int i=0;i<l;i++){
            if(Detectives.get(i).getID()==id){
                return Detectives.get(i);
            }
        }
        l= Healers.size();
        for (int i=0;i<l;i++){
            if(Healers.get(i).getID()==id){
                return Healers.get(i);
            }
        }
        l=Commoners.size();
        for (int i=0;i<l;i++){
            if(Commoners.get(i).getID()==id){
                return Commoners.get(i);
            }
        }
        return null;

    }
    public int vote(){
        ArrayList<Integer> list = new ArrayList<>();
        int l=Mafias.size();
        for (int i=0;i<l;i++){
            if(Mafias.get(i).getStatus()=="ALIVE")
                list.add(Mafias.get(i).getID());
        }
        l=Detectives.size();
        for (int i=0;i<l;i++){
            if(Detectives.get(i).getStatus()=="ALIVE")
                list.add(Detectives.get(i).getID());
        }
        l=Healers.size();
        for (int i=0;i<l;i++){
            if(Healers.get(i).getStatus()=="ALIVE")
                list.add(Healers.get(i).getID());
        }
        l=Commoners.size();
        for (int i=0;i<l;i++){
            if(Commoners.get(i).getStatus()=="ALIVE")
                list.add(Commoners.get(i).getID());
        }
        Random rand = new Random();
        int x = list.get(rand.nextInt(list.size()));
        return x;
    }

    public int alive_count(GenericList<? extends Players> a){
        int l=a.size();
        int sum=0;
        for (int i=0;i<l;i++){
            if(a.get(i).getStatus()=="ALIVE")
                sum+=1;
        }
        return sum;
    }
    public int generate_list(GenericList<? extends Players> a,GenericList<? extends Players> b,GenericList<? extends Players>  c){
        ArrayList<Integer> list = new ArrayList<>();
        int l=a.size();
        for (int i=0;i<l;i++){
            if(a.get(i).getStatus()=="ALIVE")
            list.add(a.get(i).getID());
        }
         l=b.size();
        for (int i=0;i<l;i++){
            if(b.get(i).getStatus()=="ALIVE")
            list.add(b.get(i).getID());
        }
         l=c.size();
        for (int i=0;i<l;i++){
            if(c.get(i).getStatus()=="ALIVE")
            list.add(c.get(i).getID());
        }
        Random rand = new Random();
        int x = list.get(rand.nextInt(list.size()));
        return x;

    }
    public void choose_target(Players target){
            int X = target.getHP_BOOST();
            if(target.total_HP(Mafias) !=0) {
                if (target.total_HP(Mafias) >= target.getHP_BOOST()) {
                    target.setHP_BOOST(target.getHP_BOOST());
                    target.setStatus("DEAD");
                } else {
                    target.setHP_BOOST(target.total_HP(Mafias));
                }
                int Y = Mafias.size();
                while (X > 0 && Y > 0) {
                    for (int i = 0; i < Mafias.size(); i++) {
                        if (Y > 0 && (Mafias.get(i).getHP_BOOST() != 0) && (X / Y) > Mafias.get(i).getHP_BOOST()) {
                            X -= Mafias.get(i).getHP_BOOST();
                            Y -= 1;
                            Mafias.get(i).setHP_BOOST(Mafias.get(i).getHP_BOOST());
                            // System.out.println("3 "+active_Mafias.get(i).getHP_BOOST());
                        }
                    }
                    int temp = X / Y;
                    for (int i = 0; i < Mafias.size(); i++) {
                        if (X > 0) {
                            Mafias.get(i).setHP_BOOST(temp);
                            X -= temp;
                        } else {
                            break;
                        }
                    }
                }
            }
    }


}
