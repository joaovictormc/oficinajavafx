/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficinajavafx.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author Wesley
 */
public class RunnableSobreNos implements Runnable {
    
    private Socket socket;
    private Label labelMembrosEquipe;
    
    public RunnableSobreNos(Socket socket, Label labelMembrosEquipe) {
        this.socket = socket;
        this.labelMembrosEquipe = labelMembrosEquipe;
    }
    
    @Override
    public void run() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            List<String> listMembrosEquipe = (List<String>) entrada.readObject();
            while (true) {
                Platform.runLater(() -> labelMembrosEquipe.setText(listMembrosEquipe.get(0)));
                Thread.sleep(3000);
                Platform.runLater(() -> labelMembrosEquipe.setText(listMembrosEquipe.get(1)));
                Thread.sleep(3000);
                Platform.runLater(() -> labelMembrosEquipe.setText(listMembrosEquipe.get(2)));
                Thread.sleep(3000);
            }
        } catch (IOException ex) {
            Logger.getLogger(RunnableSobreNos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RunnableSobreNos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(RunnableSobreNos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
