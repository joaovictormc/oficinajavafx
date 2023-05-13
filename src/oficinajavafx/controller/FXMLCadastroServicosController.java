/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficinajavafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oficinajavafx.model.dao.MecanicoDAO;
import oficinajavafx.model.dao.ServicoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Cliente;
import oficinajavafx.model.domain.Servico;

/**
 * FXML Controller class
 *
 * @author Wesley
 */
public class FXMLCadastroServicosController implements Initializable {

    @FXML
    private ListView<Servico> listViewServicos;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Label textLabelTipoDeServico;
    @FXML
    private Label textLabelTempoEstimado;
    @FXML
    private Label textLabelValor;
    @FXML
    private Label textLabelMecanico;
    @FXML
    private Label textLabelComplexidade;

    private List<Servico> listServicos;
    private ObservableList<Servico> observableListServicos;
    
    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ServicoDAO servicoDAO = new ServicoDAO();
    private final MecanicoDAO mecanicoDAO = new MecanicoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicoDAO.setConnection(connection);
        
        carregarTableViewServicos();
        selectItemTblViewServicos(null);
        listViewServicos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemTblViewServicos(newValue));
    }

    public void carregarTableViewServicos() {
        listServicos = servicoDAO.listar();
        observableListServicos = FXCollections.observableArrayList(listServicos);
        listViewServicos.setItems(observableListServicos);
    }
    
    
    public void selectItemTblViewServicos(Servico servico) {
        if(servico != null){
            textLabelTipoDeServico.setText(servico.getTipo_Servico());
            textLabelTempoEstimado.setText(servico.getTempo_Estimado());
            textLabelValor.setText(String.valueOf(servico.getValor()));
            textLabelMecanico.setText(String.valueOf(servico.getMecanico()));
            textLabelComplexidade.setText(servico.getComplexidade());
        } else {
            textLabelTipoDeServico.setText("");
            textLabelTempoEstimado.setText("");
            textLabelValor.setText("");
            textLabelMecanico.setText("");
            textLabelComplexidade.setText("");
        }
    }
    
    
    @FXML
    public void handleBtnInserir() throws IOException {
        Servico servico = listViewServicos.getSelectionModel().getSelectedItem();
        if (servico == null) {
            servico.setTipo_Servico(textFieldTipoDeServico.getText());
            servico.setTempo_Estimado(textFieldTempoEstimado.getText());
            servico.setValor(Double.valueOf(textFieldValor.getText()));
            servico.setMecanico(textFieldMecanico.getText());
            servico.setTipo_Servico(textFieldTipoDeServico.getText());
        }
    }
    
        
    /*@FXML
    public void handleBtnAlterar() throws IOException {
        Cliente cliente = tblViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            boolean btnConfirmarClick = abrirTelaInserirClientes(cliente);
            if (btnConfirmarClick) {
                clienteDAO.alterar(cliente);
                carregarTableViewClientes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, Selecione um cliente na lista ao lado");
            alert.show();
        }
    }*/
       
    
    /*@FXML
    public void handleBtnRemover() {
        Cliente cliente = tblViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            clienteDAO.excluir(cliente);
            carregarTableViewClientes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, Selecione um cliente na lista ao lado");
            alert.show();
        }
    }*/
    
}
