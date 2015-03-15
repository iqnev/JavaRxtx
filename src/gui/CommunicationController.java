package gui;

import gnu.io.NoSuchPortException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import testcomunication.Command;
import testcomunication.GetSensorData;
import testcomunication.SerialClassConnection;
import testcomunication.TestComunication;

public class CommunicationController implements ActionListener {

    private JFrame frame;
    private CommunicationView view;

    private TestComunication communication;

    public CommunicationController() throws IOException, TooManyListenersException {
        this.frame = new JFrame("Test Communication");
        this.frame.setSize(new Dimension(500, 300));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.view = new CommunicationView();
        this.view.addActionListener(this);

        // initialize connection here
        this.communication = new TestComunication();
        this.communication.addCannectionStatusListener(this.view);
        
        this.frame.getContentPane().add(this.view);
        this.frame.setVisible(true);
    }

    private void sendCommand() throws IOException {
        Command cmd = new GetSensorData("hellow");
        this.communication.sendComand(cmd);

    }

    @Override
    public void actionPerformed(ActionEvent _event) {
        String actionCommand;

        actionCommand = _event.getActionCommand();

        if (actionCommand.equals(ViewConstants.BUTTON_SEND)) {
            try {
                this.sendCommand();
            } catch (IOException ex) {
                Logger.getLogger(CommunicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (actionCommand.equals(ViewConstants.CONNECT_ACTION_COMAND)) {
            String portName = this.view.getPortName();
          
            try {
                this.communication.openPort(portName);
            } catch (NoSuchPortException e) {
                    System.out.println("Error");
            } catch (TooManyListenersException e) {
                    System.out.println("Error");
            }
            
        } else if(actionCommand.equals(ViewConstants.DISCONNECT_ACTION_COMAND)) {
            SerialClassConnection.getInstance().close();
        }

    }

}
