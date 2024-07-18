package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import businessLogic.*;
public class SimulationF extends JFrame {

    private JFrame frame;
    private JLabel labelClients;
    private JTextField txtClients;
    private JLabel labelQueues;
    private JTextField txtQueues;
    private JLabel labelSimulationInterval;
    private JTextField txtSimulationInterval;

    private JLabel labelMinArrivalTime;
    private JTextField txtMinArrivalTime;
    private JLabel labelMaxArrivalTime;
    private JTextField txtMaxArrivalTime;

    private JLabel labelMinServiceTime;
    private JTextField txtMinServiceTime;
    private JLabel labelMaxServiceTime;
    private JTextField txtMaxServiceTime;
    private JButton startButton;

    private int queue;
    private int N;
    private int simulationTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int maxServiceTime;
    private int minServiceTime;

    public SimulationF() {

        frame = new JFrame("Input Data");

        labelClients = new JLabel("Number of clients (N):");
        txtClients = new JTextField(10);
        labelQueues = new JLabel("Number of queues (Q):");
        txtQueues = new JTextField(10);
        labelSimulationInterval = new JLabel("Simulation interval (MAX):");
        txtSimulationInterval = new JTextField(10);
        labelMinArrivalTime = new JLabel("Minimum arrival time:");
        txtMinArrivalTime = new JTextField(10);
        labelMaxArrivalTime = new JLabel("Maximum arrival time:");
        txtMaxArrivalTime = new JTextField(10);
        labelMinServiceTime = new JLabel("Minimum service time:");
        txtMinServiceTime = new JTextField(10);
        labelMaxServiceTime = new JLabel("Maximum service time:");
        txtMaxServiceTime = new JTextField(10);
        startButton = new JButton("START");


        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(labelClients);
        panel.add(txtClients);
        panel.add(labelQueues);
        panel.add(txtQueues);
        panel.add(labelSimulationInterval);
        panel.add(txtSimulationInterval);
        panel.add(labelMinArrivalTime);
        panel.add(txtMinArrivalTime);
        panel.add(labelMaxArrivalTime);
        panel.add(txtMaxArrivalTime);
        panel.add(labelMinServiceTime);
        panel.add(txtMinServiceTime);
        panel.add(labelMaxServiceTime);
        panel.add(txtMaxServiceTime);
        panel.add(startButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 300);
        frame.setLocation(700, 300);


        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                N = Integer.parseInt(txtClients.getText());
                queue = Integer.parseInt(txtQueues.getText());
                simulationTime=Integer.parseInt(txtSimulationInterval.getText());
                maxArrivalTime=Integer.parseInt(txtMaxArrivalTime.getText());
                minArrivalTime=Integer.parseInt(txtMinArrivalTime.getText());
                maxServiceTime=Integer.parseInt(txtMaxServiceTime.getText());
                minServiceTime=Integer.parseInt(txtMinServiceTime.getText());

                txtClients.setEnabled(false);
                txtQueues.setEnabled(false);
                txtSimulationInterval.setEnabled(false);
                txtMinArrivalTime.setEnabled(false);
                txtMaxArrivalTime.setEnabled(false);
                txtMinServiceTime.setEnabled(false);
                txtMaxServiceTime.setEnabled(false);
                startButton.setEnabled(false);

                Simulation simulation =  new Simulation();
                SimulationManager gen = new SimulationManager(SimulationF.this, simulation);
                Thread t = new Thread(gen);
                t.start();
            }
        });

    }


     public int getN(){return this.N;}
     public int getQueue(){return this.queue;}
     public int getSimulationTime(){return this.simulationTime;}
     public int getMaxArrivalTime(){return this.maxArrivalTime;}
     public int getMinArrivalTime(){return this.minArrivalTime;}
     public int getMaxServiceTime(){return this.maxServiceTime;}
     public int getMinServiceTime(){return this.minServiceTime;}

    public static void main(String[] args) {
        SimulationF inputView = new SimulationF();

    }
}