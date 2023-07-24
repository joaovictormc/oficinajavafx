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
import oficinajavafx.model.dao.AvaliacaoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Avaliacao;

public class FXMLAvaliacaoDeServicoController implements Initializable {

    @FXML
    private TableView<Avaliacao> tblViewAvaliacao;
    @FXML
    private TableColumn<Avaliacao, Integer> tblColumnIdMecanico;
    @FXML
    private TableColumn<Avaliacao, Integer> tblColumnAgendamento;
    @FXML
    private TableColumn<Avaliacao, Integer> tblColumnNota;
    @FXML
    private Button btnAvaliacaoInserir;
    @FXML
    private Button btnAvaliacaoAlterar;
    @FXML
    private Button btnAvaliacaoRemover;
    @FXML
    private Label lblIdAvaliacao;
    @FXML
    private Label lblNota;
    @FXML
    private Label lblComentário;
    @FXML
    private Label lblIdAgendamento;
    @FXML
    private Label lblIdMecanico;

    private List<Avaliacao> listAvaliacao;
    private ObservableList<Avaliacao> observableListAvaliacao;
    
    
    

    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        avaliacaoDAO.setConnection(connection);
        carregarTableViewAvaliacao();
        selectItemTblViewAvaliacao(null);
        tblViewAvaliacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemTblViewAvaliacao(newValue));
    }

    public void carregarTableViewAvaliacao() {
        tblColumnIdMecanico.setCellValueFactory(new PropertyValueFactory<>("id_mecanico"));
        tblColumnAgendamento.setCellValueFactory(new PropertyValueFactory<>("id_agendamento"));
        tblColumnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));

        listAvaliacao = avaliacaoDAO.listar();

        observableListAvaliacao = FXCollections.observableArrayList(listAvaliacao);
        tblViewAvaliacao.setItems(observableListAvaliacao);
    }
    
    
    public void selectItemTblViewAvaliacao(Avaliacao avaliacao) {
        if(avaliacao != null){
            lblIdAvaliacao.setText(String.valueOf(avaliacao.getIdAvaliacao()));
            lblNota.setText(String.valueOf(avaliacao.getNota()));
            lblComentário.setText(avaliacao.getComentario());
            lblIdAgendamento.setText(String.valueOf(avaliacao.getIdAgendamento()));
            lblIdMecanico.setText(String.valueOf(avaliacao.getIdMecanico()));
        } else {
            lblIdAvaliacao.setText("");
            lblNota.setText("");
            lblComentário.setText("");
            lblIdAgendamento.setText("");
            lblIdMecanico.setText("");
        }
    }
    
  
@FXML
    public void handleBtnInserirAvaliacao() throws IOException {
        Avaliacao avaliacao = new Avaliacao();
        boolean bntConfirmarClick = abrirTelaInserirAvaliacao(avaliacao);
        if (bntConfirmarClick) {
            avaliacaoDAO.inserir(avaliacao);
            carregarTableViewAvaliacao();
        }
    }
    
    @FXML
    public void handleBtnRemoverAvaliacao() {
        Avaliacao avaliacao = tblViewAvaliacao.getSelectionModel().getSelectedItem();
        if (avaliacao != null) {
            avaliacaoDAO.deletar(avaliacao);
            carregarTableViewAvaliacao();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, Selecione uma avaliação já feita na lista ao lado");
            alert.show();
        }
    }
    
      @FXML
    public void handleBtnAlterarAvaliacao() throws IOException {
        Avaliacao avaliacao = tblViewAvaliacao.getSelectionModel().getSelectedItem();
        if (avaliacao != null) {
            boolean btnConfirmarClick = abrirTelaInserirAvaliacao(avaliacao);
            if (btnConfirmarClick) {
                avaliacaoDAO.alterar(avaliacao);
                carregarTableViewAvaliacao();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, Selecione um cliente na lista ao lado");
            alert.show();
        }
    }
    
    
    
    public boolean abrirTelaInserirAvaliacao(Avaliacao avaliacao) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLInserirAvaliacaoController.class.getResource("/oficinajavafx/view/FXMLInserirAvaliacao.fxml")); //mudar o caminho
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Inserir Avaliação");
        
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLInserirAvaliacaoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAvaliacao(avaliacao);
        dialogStage.setResizable(false);
        dialogStage.setFocused(true);
        dialogStage.showAndWait();
        return controller.isBtnConfirmarClick();
    }
}
