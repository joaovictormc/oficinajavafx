package oficinajavafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oficinajavafx.model.dao.ClienteDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Cliente;

public class FXMLCadastroClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tblViewClientes;
    @FXML
    private TableColumn<Cliente, String> tblColumnNome;
    @FXML
    private TableColumn<Cliente, String> tblColumnTelefone;
    @FXML
    private TableColumn<Cliente, String> tblColumnCPF;
    @FXML
    private Button bntClienteInserir;
    @FXML
    private Button bntClienteAlterar;
    @FXML
    private Button bntClienteRemover;
    @FXML
    private Label lblClienteId;
    @FXML
    private Label lblClienteNome;
    @FXML
    private Label lblClienteEndereco;
    @FXML
    private Label lblClienteTelefone;
    @FXML
    private Label lblClienteCPF;
    @FXML
    private Label lblClienteEmail;

    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    
    
    

    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        carregarTableViewClientes();
        selectItemTblViewCliente(null);
        tblViewClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemTblViewCliente(newValue));
    }

    public void carregarTableViewClientes() {
        tblColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tblColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listClientes = clienteDAO.listar();

        observableListClientes = FXCollections.observableArrayList(listClientes);
        tblViewClientes.setItems(observableListClientes);
    }
    
    
    public void selectItemTblViewCliente(Cliente cliente) {
        if(cliente != null){
            lblClienteId.setText(String.valueOf(cliente.getId_Cli()));
            lblClienteNome.setText(cliente.getNome());
            lblClienteEndereco.setText(cliente.getEndereco());
            lblClienteTelefone.setText(cliente.getTelefone());
            lblClienteCPF.setText(cliente.getCpf());
            lblClienteEmail.setText(cliente.getEmail());
        } else {
            lblClienteId.setText("");
            lblClienteNome.setText("");
            lblClienteEndereco.setText("");
            lblClienteTelefone.setText("");
            lblClienteCPF.setText("");
            lblClienteEmail.setText("");
        }
    }
    
    
    @FXML
    public void handleBtnInserir() throws IOException {
        Cliente cliente = new Cliente();
        boolean bntConfirmarClick = abrirTelaInserirClientes(cliente);
        if (bntConfirmarClick) {
            clienteDAO.inserir(cliente);
            carregarTableViewClientes();
        }
    }
    
        
    @FXML
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
    }
       
    
    @FXML
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
    }
    
    
    
    public boolean abrirTelaInserirClientes(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLInserirClientesController.class.getResource("/oficinajavafx/view/FXMLInserirClientes.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastrar Clientes");
        
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLInserirClientesController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);
        dialogStage.setResizable(false);
        dialogStage.setFocused(true);
        dialogStage.showAndWait();
        return controller.isBtnConfirmarClick();
    }
}
