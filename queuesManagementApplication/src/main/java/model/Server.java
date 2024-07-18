package model;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.LinkedBlockingQueue;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server(){
        this.tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task task){
        tasks.add(task);
        waitingPeriod.incrementAndGet();
    }

    @Override
    public void run() {
        while (true) {
            Task task = null;
            try {
                task = tasks.element();
            } catch (NoSuchElementException exception) {

            }
            if(task!=null) {
                int n = (int) task.getServiceTime();
                for (int i = 0; i < n; i++) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    waitingPeriod.decrementAndGet();
                    task.setServiceTime((int) task.getServiceTime() - 1);
                }
                tasks.remove(task);
            }
}}

    //ret. array cu sarc
    public Task[] getTasks(){

         int numTasks = tasks.size();
        Task[] taskArray = new Task[numTasks];
        tasks.toArray(taskArray);
        return taskArray;
    }

    //Calc perioada totala de serv ramasa pt toate sarcinile din coada
    public int getServiceTime(){

        int sum = 0;
        for(Task t:tasks){
            sum = sum+ (int)t.getServiceTime();
        }
        return sum;
    }
}


