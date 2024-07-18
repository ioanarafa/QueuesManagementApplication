package gui;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Simulation extends JFrame {

    private JTextArea queueState;

    public Simulation() {

        setTitle("Queue simulation");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel queuePanel = new JPanel();
        queuePanel.setBorder(BorderFactory.createTitledBorder("Queue State"));
        queuePanel.setLayout(new BorderLayout());
        queueState = new JTextArea();
        queueState.setEditable(false);
        queueState.setBounds(50,100,700,555);
        JScrollPane scrollPane = new JScrollPane(queueState);
        queuePanel.add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(queuePanel);
        setVisible(true);
    }

    public void setSimulationArea(String filePath) {
        try {
            // Citeste
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            this.queueState.setText(fileContent);

        } catch (IOException e) {

            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
