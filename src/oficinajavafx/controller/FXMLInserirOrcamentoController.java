package oficinajavafx.controller;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oficinajavafx.model.dao.ClienteDAO;
import oficinajavafx.model.dao.ServicoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Cliente;
import oficinajavafx.model.domain.Orcamento;
import oficinajavafx.model.domain.Servico;


public class FXMLInserirOrcamentoController implements Initializable {

    @FXML
    private ComboBox cbxServico;
    @FXML
    private ComboBox cbxClientes;
    @FXML
    private TableView<Servico> tblViewServicos;
    @FXML
    private TableColumn<Servico, String> tblColumnServico;
    @FXML
    private TableColumn<Servico, Double> tblColumnValor;
    @FXML
    private DatePicker dateEntrada;
    @FXML
    private DatePicker dateSaida;
    @FXML
    private TextField txtTipoVeiculo;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtAno;
    @FXML
    private TextArea txtAvarias;
    @FXML
    private TextArea txtDefeitoRelatado;
    @FXML
    private TextArea txtDefeitoConstatado;
    @FXML
    private TextField txtValorFinal;
    @FXML
    private TextField txtDescontos;
    @FXML
    private TextField txtValorServico;
    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    
    
    
    private List<Cliente> listClientes;
    private List<Servico> listServico;
    private ObservableList<Cliente> observableListClientes;
    private ObservableList<Servico> observableListServico;
    
    
    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ServicoDAO servicoDAO = new ServicoDAO();
    
    
    private Stage dialogStage;
    private boolean btnConfirmarClick = false;
    private Orcamento orcamento;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmarClick() {
        return btnConfirmarClick;
    }

    public void setBtnConfirmarClick(boolean btnConfirmarClick) {
        this.btnConfirmarClick = btnConfirmarClick;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        servicoDAO.setConnection(connection);
        
        carregarComboBoxServico();
        carregarComboBoxCliente();
        
        tblColumnServico.setCellValueFactory(new PropertyValueFactory<>("tipo_servico"));
        tblColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }   
    
    public void carregarComboBoxServico() {
        listServico = servicoDAO.listar();
        observableListServico = FXCollections.observableArrayList(listServico);
        cbxServico.setItems(observableListServico);
    }
    
    public void carregarComboBoxCliente() {
        listClientes = clienteDAO.listar();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        cbxClientes.setItems(observableListClientes);
    }
    
    
    @FXML
    public void handleButtonAdicionar() {
        Servico servico;
        Orcamento orcamento = new Orcamento();
        
        if(cbxServico.getSelectionModel().getSelectedItem() != null) {
            servico = (Servico) cbxServico.getSelectionModel().getSelectedItem();
            
            if(servico.getTipo_Servico() != null) {
                tblViewServicos.getItems().add(servico);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Problemas na escolha do Servico!");
                alert.setContentText("Produto não Selecionado!");
                alert.show();
            }
        }
    }
    
    
    @FXML
    public void handleButtonConfirmar() {
        if (validarDados()) {
            orcamento.setId_cli((int) cbxClientes.getSelectionModel().getSelectedItem());
            orcamento.setTipo_servico((List<String>) cbxServico.getSelectionModel().getSelectedItem());
            orcamento.setData_entrada(dateEntrada.getValue());
            
            
            btnConfirmarClick = true;
            dialogStage.close();
        }
    }
    
    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
    
    
    private boolean validarDados() {
        String errorMessage = "";
        
        if(cbxClientes.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cliente inválido!\n";
        }
        if (dateEntrada.getValue() == null) {
            errorMessage += "Data Inválida!\n";
        }
        if(observableListServico == null) {
            errorMessage += "Serviço inválido!\n";
        }
        if(errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, tente novamente!");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
}
