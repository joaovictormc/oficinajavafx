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
import oficinajavafx.model.dao.MecanicoDAO;
import oficinajavafx.model.dao.ServicoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Mecanico;
import oficinajavafx.model.domain.Servico;

public class FXMLCadastroMecanicosController implements Initializable {

    @FXML
    private TableView<Mecanico> tblViewMecanicos;
    @FXML
    private TableColumn<Mecanico, String> tblColumnNome;
    @FXML
    private TableColumn<Mecanico, String> tblColumnTelefone;
    @FXML
    private TableColumn<Mecanico, String> tblColumnEspecialidade;
    @FXML
    private Button btnMecanicoInserir;
    @FXML
    private Button btnMecanicoAlterar;
    @FXML
    private Button btnMecanicoRemover;
    @FXML
    private Label lblMecanicoId;
    @FXML
    private Label lblMecanicoNome;
    @FXML
    private Label lblMecanicoTelefone;
    @FXML
    private Label lblMecanicoEspecialidade;
    @FXML
    private Label lblMecanicoEmail;
    @FXML
    private Label lblMecanicoSalario;

    private List<Mecanico> listMecanicos;
    private ObservableList<Mecanico> observableListMecanicos;
    
    
    

    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final MecanicoDAO mecanicoDAO = new MecanicoDAO();
    private final ServicoDAO servicoDAO = new ServicoDAO();
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mecanicoDAO.setConnection(connection);
        servicoDAO.setConnection(connection);
        carregarTableViewMecanicos();
        selectItemTblViewMecanicos(null);
        tblViewMecanicos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemTblViewMecanicos(newValue));
    }

    public void carregarTableViewMecanicos() {
        tblColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome_mec"));
        tblColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone_mec"));
        tblColumnEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));

        listMecanicos = mecanicoDAO.listar();

        observableListMecanicos = FXCollections.observableArrayList(listMecanicos);
        tblViewMecanicos.setItems(observableListMecanicos);
    }
    
    
    public void selectItemTblViewMecanicos(Mecanico mecanico) {
        if(mecanico != null){
            lblMecanicoId.setText(String.valueOf(mecanico.getId_mec()));
            lblMecanicoNome.setText(mecanico.getNome_mec());
            lblMecanicoTelefone.setText(mecanico.getTelefone_mec());
            lblMecanicoEspecialidade.setText(mecanico.getEspecialidade());
            lblMecanicoEmail.setText(mecanico.getEmail());
            lblMecanicoSalario.setText(String.valueOf(mecanico.getSalario()));
        } else {
            lblMecanicoId.setText("");
            lblMecanicoNome.setText("");
            lblMecanicoTelefone.setText("");
            lblMecanicoEspecialidade.setText("");
            lblMecanicoEmail.setText("");
            lblMecanicoSalario.setText("");
        }
    }
    
  
@FXML
    public void handleBtnInserir() throws IOException {
        Mecanico mecanico = new Mecanico();
        boolean bntConfirmarClick = abrirTelaInserirMecanicos(mecanico);
        if (bntConfirmarClick) {
            mecanicoDAO.inserir(mecanico);
            carregarTableViewMecanicos();
        }
    }
    
    @FXML
    public void handleBtnRemover() {
        Mecanico mecanico = tblViewMecanicos.getSelectionModel().getSelectedItem();
        if (mecanico != null) {
            mecanicoDAO.deletar(mecanico);
            carregarTableViewMecanicos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, Selecione um mecânico na lista ao lado");
            alert.show();
        }
    }
    
      @FXML
    public void handleBtnAlterar() throws IOException {
        Mecanico mecanico = tblViewMecanicos.getSelectionModel().getSelectedItem();
        if (mecanico != null) {
            boolean btnConfirmarClick = abrirTelaInserirMecanicos(mecanico);
            if (btnConfirmarClick) {
                mecanicoDAO.alterar(mecanico);
                carregarTableViewMecanicos();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, Selecione um cliente na lista ao lado");
            alert.show();
        }
    }
    
    
    
    public boolean abrirTelaInserirMecanicos(Mecanico mecanico) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLInserirMecanicosController.class.getResource("/oficinajavafx/view/FXMLInserirMecanicos.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastrar Mecânicos");
        
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLInserirMecanicosController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMecanico(mecanico);
        dialogStage.setResizable(false);
        dialogStage.setFocused(true);
        dialogStage.showAndWait();
        return controller.isBtnConfirmarClick();
    }
}
