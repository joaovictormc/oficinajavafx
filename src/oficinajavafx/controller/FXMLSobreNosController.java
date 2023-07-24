/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficinajavafx.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import oficinajavafx.thread.RunnableSobreNos;

/**
 * FXML Controller class
 *
 * @author Wesley
 */
public class FXMLSobreNosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label labelMembrosEquipe;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Socket socket = new Socket("127.0.0.1", 12345);
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            saida.writeInt(9);
            RunnableSobreNos runSobreNos = new RunnableSobreNos(socket, labelMembrosEquipe);
            Thread thread = new Thread(runSobreNos);
            thread.start();
        } catch (IOException ex) {
            Logger.getLogger(FXMLSobreNosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
