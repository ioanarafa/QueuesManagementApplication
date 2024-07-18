package businessLogic;
import model.*;
import java.util.*;
import java.lang.*;

//resp. pt crearea cozilor si pornirea unui thread pt fiecare coada
public class Scheduler{

    private List<Server> servers; //nr de cozi
    private int maxNoServers;
    private Strategy strategy;

    public Scheduler(int maxNoServers){

        this.maxNoServers=maxNoServers;
        this.servers = new ArrayList<>();

        for (int i = 0; i < this.maxNoServers; i++) {
            Server server = new Server();
            servers.add(server);
        }

        // Creaza un thread pentru fiecare coada si porneste
        for (Server server : servers) {
            Thread thread = new Thread(server);
            thread.start();
        }
    }

    public List<Server> getServers(){
        return servers;
    }

    public void changeStrategy(SelectionPolicy policy) {

        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new StrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new StrategyTime();
        }
    }

    public void dispatchTask(Task t){

        strategy.addTask(servers,t);
    }

}

