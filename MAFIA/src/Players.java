abstract class Players {
    protected int HP_BOOST;
    protected int ID;
    protected String type;
    protected String status;

    Players(){
        type=" ";
        status="ALIVE";
    }
    ///getters
    public int getID() {
        return ID;
    }

    public int getHP_BOOST() {
        return HP_BOOST;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }


    ///setters
    public void setHP_BOOST(int HP_BOOST) {
        this.HP_BOOST -= HP_BOOST;
        if(getHP_BOOST()<=0){
            this.HP_BOOST =0;
        }
    }
    public void increase_Boost(boolean x){
        if(x){
            this.HP_BOOST=500;
            this.status="Alive";
        }
        else{
            this.HP_BOOST +=500;
        }

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }
    public int total_HP(GenericList<? extends Players> a){
        int l=a.size();
        int sum=0;
        for (int i=0;i<l;i++){
            sum+=a.get(i).getHP_BOOST();
        }
        return sum;
    }



}
