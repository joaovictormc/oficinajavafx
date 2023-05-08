package oficinajavafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oficinajavafx.model.dao.OrcamentoDAO;
import oficinajavafx.model.dao.ServicoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Orcamento;
import oficinajavafx.model.domain.Servico;

public class FXMLOrcamentosController implements Initializable {

    @FXML
    private TableView<Orcamento> tblViewOS;
    @FXML
    private TableColumn<Orcamento, Integer> tblColumnID;
    @FXML
    private TableColumn<Orcamento, Date> tblColumnEntrada;
    @FXML
    private TableColumn<Orcamento, Orcamento> tblColumnCliente;

    @FXML
    private Label lblOSID;
    @FXML
    private Label lblOSTipoVeiculo;
    @FXML
    private Label lblVeiculoModelo;
    @FXML
    private Label lblVeiculoAno;
    @FXML
    private Label lblVeiculoMarca;
    @FXML
    private Label lblOSAvaria;
    @FXML
    private Label lblOSEntrada;
    @FXML
    private Label lblOSSaida;
    @FXML
    private Label lblOSDefeitoRelatado;
    @FXML
    private Label lblOSDefeitoConstatado;
    @FXML
    private Label lblOSCliente;
    @FXML
    private Label lblOSSituacao;
    @FXML
    private Label lblOSServico;
    @FXML
    private Label lblOSDesconto;
    @FXML
    private Label lblOSValor;

    @FXML
    private Button bntOSInserir;
    @FXML
    private Button bntOSAlterar;
    @FXML
    private Button bntOSRemover;

    private List<Orcamento> listOrcamento;
    private ObservableList<Orcamento> observableListOS;

    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    private final ServicoDAO servicoDAO = new ServicoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orcamentoDAO.setConnection(connection);
        carregarTblViewOS();
        selectItemTblViewOS(null);
        tblViewOS.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemTblViewOS(newValue));
    }

    public void carregarTblViewOS() {
        tblColumnID.setCellValueFactory(new PropertyValueFactory<>("id_os"));
        tblColumnEntrada.setCellValueFactory(new PropertyValueFactory<>("data_entrada"));
        tblColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        listOrcamento = orcamentoDAO.listar();

        observableListOS = FXCollections.observableArrayList(listOrcamento);
        tblViewOS.setItems(observableListOS);
    }

    public void selectItemTblViewOS(Orcamento orcamento) {
        if (orcamento != null) {
            lblOSID.setText(String.valueOf(orcamento.getId_os()));
            lblOSTipoVeiculo.setText(orcamento.getTipo_veiculo());
            lblVeiculoModelo.setText(orcamento.getModelo_veiculo());
            lblVeiculoAno.setText(orcamento.getAno_veiculo());
            lblVeiculoMarca.setText(orcamento.getMarca_veiculo());
            lblOSAvaria.setText(orcamento.getAvarias());
            lblOSEntrada.setText(String.valueOf(orcamento.getData_entrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            lblOSSaida.setText(String.valueOf(orcamento.getData_saida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            lblOSDefeitoRelatado.setText(orcamento.getDefeito_relatado());
            lblOSDefeitoConstatado.setText(orcamento.getDefeito_constatado());
            lblOSCliente.setText(orcamento.getCliente().toString());
            lblOSSituacao.setText(String.valueOf(orcamento.getSituacao()));
            lblOSDesconto.setText(String.valueOf(orcamento.getDescontos()));
            lblOSValor.setText(String.format("%.2f", orcamento.getValor_final()));
        } else {
            lblOSID.setText("");
            lblOSTipoVeiculo.setText("");
            lblVeiculoModelo.setText("");
            lblVeiculoAno.setText("");
            lblVeiculoMarca.setText("");
            lblOSAvaria.setText("");
            lblOSEntrada.setText("");
            lblOSSaida.setText("");
            lblOSDefeitoRelatado.setText("");
            lblOSDefeitoConstatado.setText("");
            lblOSCliente.setText("");
            lblOSSituacao.setText("");
            lblOSDesconto.setText("");
            lblOSValor.setText("");
        }
    }

    @FXML
    public void buttonInserir() throws IOException {
        Orcamento os = new Orcamento();
        List<Servico> listServico = new ArrayList<>();
        os.setServico(listServico);
        boolean btnConfirmarClick = showFXMLInserirOrcamento(os);
        if (btnConfirmarClick) {
            try {
                connection.setAutoCommit(false);
                orcamentoDAO.setConnection(connection);
                orcamentoDAO.inserir(os);
                servicoDAO.setConnection(connection);
                for (Servico listServicos : os.getServico()) {
                    listServicos.setOrcamento(os);
                    servicoDAO.inserir(listServicos);
                }
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(FXMLOrcamentosController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(FXMLOrcamentosController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    public boolean showFXMLInserirOrcamento(Orcamento orcamento) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLInserirOrcamentoController.class.getResource("/oficinajavafx/view/FXMLInserirOrcamento.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Criar Orcamento");
        Scene scene = new Scene(page);
        dialogStage.setResizable(false);
        dialogStage.setScene(scene);

        FXMLInserirOrcamentoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setOrcamento(orcamento);

        dialogStage.showAndWait();

        return controller.isBtnConfirmarClick();
    }
}
