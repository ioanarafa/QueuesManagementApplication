package businessLogic;
import model.*;
import gui.*;

import java.io.*;
import java.util.*;

public class SimulationManager implements Runnable {

    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler;
    private List<Task> generatedTasks;

    private String outputName = "output.txt";

    private int queue;
    private int nrN;
    private int simulationTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int maxServiceTime;
    private int minServiceTime;
    private Simulation simulation;

    public SimulationManager(SimulationF frame, Simulation simulation) {
        this.simulation = simulation;
        this.queue = frame.getQueue();
        this.nrN = frame.getN();
        this.simulationTime = frame.getSimulationTime();
        this.maxArrivalTime = frame.getMaxArrivalTime();
        this.minArrivalTime = frame.getMinArrivalTime();
        this.maxServiceTime = frame.getMaxServiceTime();
        this.minServiceTime = frame.getMinServiceTime();
        this.generatedTasks = new ArrayList<>();

        generateNRandomTasks();

        for (int i = 0; i < queue; i++) {
            Server server = new Server();
            Thread t = new Thread(server);
            t.start();
        }

        scheduler = new Scheduler(frame.getQueue());
        scheduler.changeStrategy(selectionPolicy);
    }

    private void generateNRandomTasks() {

        for (int i = 0; i < nrN; i++) {
            int serviceTime = (int) (Math.random() * (maxServiceTime - minServiceTime) + minServiceTime);
            int arrivalTime = (int) (Math.random() * (maxArrivalTime - minArrivalTime) + minArrivalTime);
            Task task = new Task(i + 1, arrivalTime, serviceTime);
            generatedTasks.add(task);
        }
        Collections.sort(generatedTasks);
    }

    public void run() {
        int currentTime = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))) {

            // Creaza un BufferedReader pt citire
            BufferedReader reader = new BufferedReader(new FileReader(outputName));
            String line;
            while ((line = reader.readLine()) != null) {

                simulation.setSimulationArea(line);
            }
            reader.close();

            while (currentTime < simulationTime) {
                List<Task> arrivedTasks = new ArrayList<>();
                for (Task task : generatedTasks) {

                    if (task.getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(task);
                        arrivedTasks.add(task);
                    }
                }
                generatedTasks.removeAll(arrivedTasks);

                BufferedWriter currentWriter = new BufferedWriter(new FileWriter(outputName, true));

                currentWriter.write("\nCurrent Time:" + currentTime + "\n");
                currentWriter.write("    Waiting Tasks:");
                currentWriter.newLine();
                currentWriter.write("    ");

                for (Task task : generatedTasks)
                    currentWriter.write(task.toString() + " ");
                currentWriter.newLine();


                List<Server> servers = scheduler.getServers();
                for (int indiceCozi = 0; indiceCozi < servers.size(); indiceCozi++) {

                    currentWriter.write("    Queue:" + (indiceCozi + 1));
                    currentWriter.newLine();
                    Task[] tasks = servers.get(indiceCozi).getTasks();
                    currentWriter.write("        Tasks: ");

                    for (int indiceSarcini = 0; indiceSarcini < tasks.length; indiceSarcini++)
                        currentWriter.write(tasks[indiceSarcini].toString());
                    currentWriter.newLine();
                }

                currentWriter.close();
                simulation.setSimulationArea("output.txt");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread: " + e.getMessage());
                }

                currentTime++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


