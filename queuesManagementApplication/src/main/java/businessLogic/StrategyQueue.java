package businessLogic;
import model.Server;
import model.Task;
import java.util.List;

public class StrategyQueue implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t){
        int minim = Integer.MAX_VALUE;
        Server fastestServer = null;

        for(Server server:servers){
            if(minim > server.getTasks().length){
                minim = server.getTasks().length;
                fastestServer = server;
            }
        }
        fastestServer.addTask(t);
    }

}


