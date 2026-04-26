package compulsory;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    //jFrame- o fereastra
    //MainFrame va fi o fereastra java; fereastra principala

    //declar cele 3 zone ale aplicatiei
    ConfigPanel configPanel;
    DrawingPanel drawingPanel;
    ControlPanel controlPanel;

    //constructor
    public MainFrame() {
        super("Labyrinth");//setez titlu fereastra

        init();
        //apelez metoda unde construiesc interior fereastra

        setDefaultCloseOperation(EXIT_ON_CLOSE);//programul se inchide la x
        pack();//fereastra calculeaza dimensiunea in functie de ce contine
        setLocationRelativeTo(null);//pune fereastra pe mijlocul ecranului
        setVisible(true);//afiseaza fereastra
    }

    private void init() {
        setLayout(new BorderLayout());
        //BorderLayout imparte fereastra in zone

        configPanel = new ConfigPanel(this);
        drawingPanel = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);

        add(configPanel, BorderLayout.NORTH);//pune panoul de configurare sus
        add(drawingPanel, BorderLayout.CENTER);//pune zona de desen in centru
        add(controlPanel, BorderLayout.SOUTH);//panou de control- butoane jos
    }
}