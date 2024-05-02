import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Lift {

    static int liftCounter = 1;

    static int totalPath = 10;



    String name ;
    int position;
    int capacity;
    HashSet<Integer> path ;
    Lift(){
        this.name = "L"+liftCounter;
        this.position = 0;
        initializePath(liftCounter);
        liftCounter++;
    }

    void initializePath(int liftCounter){
        path = new HashSet<>();
        path.add(0);
        for(int i=1;i<=totalPath;i++){
            if((liftCounter==1 || liftCounter ==2)&&i<=5){
                this.path.add(i);
            }
            else if((liftCounter==3 || liftCounter ==4)&&i>5){
                this.path.add(i);
            }else if(liftCounter==5){
                path.add(i);
            }
        }
    }
}
