package oficinajavafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;


public class FXMLVboxMainController implements Initializable {

    @FXML
    private MenuItem menuCliente;
    @FXML
    private MenuItem menuMecanico;
    @FXML
    private MenuItem menuServico;
    @FXML
    private MenuItem menuOrcamento;
    @FXML
    private MenuItem menuAgendamento;
    @FXML
    private MenuItem menuAvaliacao;
    @FXML
    private MenuItem menuOS;
    @FXML
    private MenuItem menuAgendamentoServico;
    @FXML
    private MenuItem menuAvaliacaoCliente;
    @FXML
    private AnchorPane anchorPane;
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    public void handleMenuCadastrarCliente() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLCadastroClientes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
}
