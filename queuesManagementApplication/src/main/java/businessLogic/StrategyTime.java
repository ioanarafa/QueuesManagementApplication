package businessLogic;
import model.*;
import java.lang.*;
import java.util.List;

public class StrategyTime implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task task) {
        int minim = Integer.MAX_VALUE;
        Server fastestServer = null;
        for(Server server:servers){
            if( minim > server.getServiceTime()){
                minim = server.getServiceTime();
                fastestServer = server;
            }
        }
        fastestServer.addTask(task);
    }
}
