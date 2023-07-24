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
    private MenuItem menuItemSobreNos;
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
    
    @FXML
    public void handleMenuCadastrarServicos() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLCadastroServicos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuCadastrarMecanicos() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLCadastroMecanicos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuOrcamentos() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLOrcamentos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuAgendamentoFinalizacao() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLAgendamentoFinalizacao.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuGraficoOSAprovado() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLGraficoOSAprovado.fxml"));
        anchorPane.getChildren().setAll(a);
    }
        
    @FXML
    public void handleMenuRelatorioOSAprovadoPorMes() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLRelatorioOSPorMes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuAvaliacao() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLAvaliacaoDeServico.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuRelatorioAvaliacaoPorMecanico() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLReputacaoDeServicosPorMecanico.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuGraficoMediaAvaliacao() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLGraficoMediaAvaliacoesPorMes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuSobreNos() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLSobreNos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
}
