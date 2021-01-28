package kata6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Block {
    
    public static final int MAX = 7;
    private final Timer timer;
    private int x;
    private int y;
    private final List<Observer> observers;
    
    public Block(){
        this.observers = new ArrayList();
        this.x=4;
        this.y=4;
        this.timer= new Timer();
        this.timer.schedule(task(), 0,5000);
    }
    
    public int x(){
        return x;
    }
    
    public int y(){
        return y;
    }
    
    public void left(){
        if(this.x == 1)return;
        this.x--;
        changed();
    }
    
    public void right(){
        if(this.x == MAX)return;
        this.x++;
        changed();
    }
    
    public void up(){
        if(this.y==MAX)return;
        this.y++;
        changed();
    }
    
    public void down(){
        if(this.y == 1)return;
        this.y--;
        changed();
    }

    private TimerTask task() {
        return new TimerTask(){
            @Override
            public void run(){
                double r = Math.random();
                if(r >= 0.20)return;
                if(r>0.15)left();
                else if (r >= 0.10)right();
                else if(r >= 0.05) up();
                else if(r >= 0.00)down();
            }
        };
    }

    private void changed() {
        for(Observer observer : observers) observer.changed();
    }
    
    public void register(Observer observer){
        observers.add(observer);
    }
    public interface Observer{
        void changed();
    }
    
}
