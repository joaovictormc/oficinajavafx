/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficinajavafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import oficinajavafx.model.dao.MecanicoDAO;
import oficinajavafx.model.dao.ServicoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Mecanico;
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
    private Button btnLimpar;
    @FXML
    private TextField textFieldTipoDeServico;
    @FXML
    private TextField textFieldTempoEstimado;
    @FXML
    private TextField textFieldValor;
    @FXML
    private ComboBox comboBoxMecanico;
    @FXML
    private TextField textFieldComplexidade;
    @FXML
    private Label labelByWesley;

    private List<Servico> listServicos;
    private List<Mecanico> listMecanicos;
    private ObservableList<Servico> observableListServicos;
    private ObservableList<Mecanico> observableListMecanicos;
    
    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ServicoDAO servicoDAO = new ServicoDAO();
    private final MecanicoDAO mecanicoDAO = new MecanicoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicoDAO.setConnection(connection);
        mecanicoDAO.setConnection(connection);
        carregarTableViewServicos();
        carregarComboBoxMecanicos();
        selectItemTblViewServicos(null);
        listViewServicos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemTblViewServicos(newValue));
    }

    public void carregarTableViewServicos() {
        listServicos = servicoDAO.listar();
        observableListServicos = FXCollections.observableArrayList(listServicos);
        listViewServicos.setItems(observableListServicos);
    }
    
    public void carregarComboBoxMecanicos() {
        listMecanicos = mecanicoDAO.listar();
        observableListMecanicos = FXCollections.observableArrayList(listMecanicos);
        comboBoxMecanico.setItems(observableListMecanicos);
    }
    
    
    public void selectItemTblViewServicos(Servico servico) {
        if(servico != null){
            textFieldTipoDeServico.setText(servico.getTipo_Servico());
            textFieldTempoEstimado.setText(servico.getTempo_Estimado());
            textFieldValor.setText(String.valueOf(servico.getValor()));
            textFieldComplexidade.setText(servico.getComplexidade());
            comboBoxMecanico.getSelectionModel().select(servico.getMecanico());
        } else {
            textFieldComplexidade.setText("");
            textFieldTempoEstimado.setText("");
            textFieldTipoDeServico.setText("");
            textFieldValor.setText("");
            comboBoxMecanico.getSelectionModel().select(null);
        }
    }
    
    public void limparCampos(){
        textFieldComplexidade.setText("");
        textFieldTempoEstimado.setText("");
        textFieldTipoDeServico.setText("");
        textFieldValor.setText("");
        comboBoxMecanico.getSelectionModel().select(null);
    }
    
    
    @FXML
    public void handleBtnInserir() {
        Servico servico = new Servico();
        Mecanico mecanico = (Mecanico)comboBoxMecanico.getSelectionModel().getSelectedItem();
        
        if (validarDadosDeEntrada()) {
            servico.setTipo_Servico(textFieldTipoDeServico.getText());
            servico.setTempo_Estimado(textFieldTempoEstimado.getText());
            servico.setValor(Double.valueOf(textFieldValor.getText()));
            servico.setMecanico(mecanico.getNome_mec());
            servico.setComplexidade(textFieldComplexidade.getText());
            servicoDAO.inserir(servico);
            carregarTableViewServicos();
            limparCampos();
        }
    }
    
        
    @FXML
    public void handleBtnAlterar() {
        Servico servico = listViewServicos.getSelectionModel().getSelectedItem();
        Mecanico mecanico = (Mecanico)comboBoxMecanico.getSelectionModel().getSelectedItem();
        
        if (servico != null) {
            if (validarDadosDeEntrada()) {
                servico.setTipo_Servico(textFieldTipoDeServico.getText());
                servico.setTempo_Estimado(textFieldTempoEstimado.getText());
                servico.setValor(Double.valueOf(textFieldValor.getText()));
                servico.setMecanico(mecanico.getNome_mec());
                servico.setComplexidade(textFieldComplexidade.getText());
                servicoDAO.alterar(servico);
                carregarTableViewServicos();
                limparCampos();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setContentText("Por favor, Selecione um serviço na lista ao lado.");
            alert.show();
        }
    }   
    
    @FXML
    public void handleBtnRemover() {
        Servico servico = listViewServicos.getSelectionModel().getSelectedItem();
        if (servico != null) {
            servicoDAO.deletar(servico);
            carregarTableViewServicos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setContentText("Por favor, Selecione um cliente na lista ao lado.");
            alert.show();
        }
    }
    
    public boolean validarDadosDeEntrada() {
        String errorMessage = "";
        if (textFieldComplexidade.getText() == null || textFieldComplexidade.getText().length() == 0) {
            errorMessage += "Complexidade inválido!\n";
        }
        if (textFieldTempoEstimado.getText() == null || textFieldTempoEstimado.getText().length() == 0) {
            errorMessage += "Tempo Estimado inválido!\n";
        }
        if (textFieldTipoDeServico.getText() == null || textFieldTipoDeServico.getText().length() == 0) {
            errorMessage += "Tipo de serviço inválido!\n";
        }
        if (textFieldValor.getText() == null || textFieldValor.getText().length() == 0) {
            errorMessage += "Valor inválido!\n";
        }
        if (comboBoxMecanico.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Mecanico inválido!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
}
