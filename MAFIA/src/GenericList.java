import java.util.ArrayList;
import java.util.HashMap;

public class GenericList<T>  {
   private ArrayList<T> temp;
   public GenericList(){
       temp=new ArrayList<>();
   }

   public void add(T obj){
       temp.add(obj);
   }
   public void add(int index,T obj){
       temp.add(index,obj);
   }
   public T get(int index){
       return temp.get(index);
   }
   public int size(){
       return temp.size();
   }
   public T remove(int index){
       return temp.remove(index);
   }

}
