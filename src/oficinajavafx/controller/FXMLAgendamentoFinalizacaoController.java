/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficinajavafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Wesley
 */
public class FXMLAgendamentoFinalizacaoController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button bntAgendamento;
    @FXML
    private Button btnFinalizacao;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void handleBtnAgendamento() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLAgendamento.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    public void handleBtnFinalizacao() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLFinalizacao.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
}
