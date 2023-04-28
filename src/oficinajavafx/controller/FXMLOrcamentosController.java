package oficinajavafx.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import oficinajavafx.model.dao.ClienteDAO;
import oficinajavafx.model.dao.OrcamentoDAO;
import oficinajavafx.model.dao.ServicoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Cliente;
import oficinajavafx.model.domain.Orcamento;


public class FXMLOrcamentosController implements Initializable {

    @FXML
    private TableView<Cliente> tblViewOS;
    @FXML
    private TableColumn<Orcamento, Integer> tblColumnID;
    @FXML
    private TableColumn<Orcamento, String> tblColumnEntrada;
    @FXML
    private TableColumn<Orcamento, Orcamento> tblColumnCliente;
    @FXML
    private TableColumn<Orcamento, BigDecimal> tblColumnValor;

    
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
    
    private List<Orcamento> listOrcamento;
    private ObservableList<Orcamento> observableListOS;
    
    
    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
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
        tblColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor_final"));

        listOrcamento = orcamentoDAO.listar();

        observableListOS = FXCollections.observableArrayList(listOrcamento);
        tblViewOS.setItems(observableListOS);
    }
    
    public void selectItemTblViewOS(Orcamento orcamento) {
        if(orcamento != null){
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
            lblOSServico.setText(orcamento.getServico().toString());
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
            lblOSServico.setText("");
            lblOSDesconto.setText("");
            lblOSValor.setText("");
        }
    }
}
