/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficinajavafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import oficinajavafx.model.dao.AgendamentoDAO;
import oficinajavafx.model.dao.ClienteDAO;
import oficinajavafx.model.dao.MecanicoDAO;
import oficinajavafx.model.dao.OrcamentoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Agendamento;
import oficinajavafx.model.domain.Cliente;
import oficinajavafx.model.domain.Mecanico;
import oficinajavafx.model.domain.Orcamento;

/**
 * FXML Controller class
 *
 * @author Wesley
 */
public class FXMLAgendamentoController implements Initializable {
    
    @FXML
    private TableView<Orcamento> tableViewOrcamentos;
    @FXML
    private TableColumn<Orcamento, String> tableColumnServico;
    @FXML
    private TableColumn<Orcamento, DatePicker> tableColumnDataEntrada;
    @FXML
    private TableColumn<Orcamento, String> tableColumnCarro;
    @FXML
    private Label labelCliente;
    @FXML
    private Label labelCpf;
    @FXML
    private DatePicker dateInicial;
    @FXML
    private DatePicker dataFinal;
    @FXML
    private ComboBox comboBoxMecanico;
    @FXML
    private Button btnAgendar;
    @FXML
    private Button btnCancelar;
    @FXML
    private AnchorPane anchorPane;
    
    private Orcamento orcamento = new Orcamento();
    private Cliente cliente = new Cliente();
    
    private List<Orcamento> listOrcamento = new ArrayList<>();
    private ObservableList<Orcamento> obsListOrcamento;
    
    private List<Mecanico> listMecanico = new ArrayList<>();
    private ObservableList<Mecanico> obsListMecanico;
    
    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    private final MecanicoDAO mecanicoDAO = new MecanicoDAO();
    private final AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orcamentoDAO.setConnection(connection);
        mecanicoDAO.setConnection(connection);
        agendamentoDAO.setConnection(connection);
        clienteDAO.setConnection(connection);
        carregarTableViewOrcamentos();
        carregarComboBoxMecanico();
        tableViewOrcamentos.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> selectItemTblViewOrcamentos(newValue));
    }

    public void carregarTableViewOrcamentos() {
        tableColumnCarro.setCellValueFactory(new PropertyValueFactory<>("modelo_veiculo"));
        tableColumnServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tableColumnDataEntrada.setCellValueFactory(new PropertyValueFactory<>("data_entrada"));
        listOrcamento = orcamentoDAO.listarPorSituacao();
        obsListOrcamento = FXCollections.observableArrayList(listOrcamento);
        tableViewOrcamentos.setItems(obsListOrcamento);
    }
    
    public void carregarComboBoxMecanico() {
        listMecanico = mecanicoDAO.listarPorDisponibilidade();
        obsListMecanico = FXCollections.observableArrayList(listMecanico);
        comboBoxMecanico.setItems(obsListMecanico);
    }
    
    public void selectItemTblViewOrcamentos(Orcamento or) {
        if (or != null) {
            Cliente cl = clienteDAO.buscarPorNome(or.getNome_cliente());
            labelCliente.setText(cl.getNome());
            labelCpf.setText(cl.getCpf());
        }
    }
    
    public void limparCampos() {
        labelCliente.setText("");
        labelCpf.setText("");
        dataFinal.setValue(null);
        dateInicial.setValue(null);
    }
    
    @FXML
    public void realizarAgendamentoDeServiço() {
        Orcamento or = tableViewOrcamentos.getSelectionModel().getSelectedItem();
        if (or != null) {
            try {
                connection.setAutoCommit(false);
                Mecanico mc = (Mecanico) comboBoxMecanico.getSelectionModel().getSelectedItem();
                mc.addServicosSendoFeitos(1);
                mecanicoDAO.alterar(mc);
                or.setOrfinalizado(true);
                orcamentoDAO.alterar(or);
                Agendamento ag = new Agendamento();
                ag.setNome_cliente(or.getNome_cliente());
                ag.setCpf_cliente(or.getCpf_cliente());
                ag.setData_inicio_servico(dateInicial.getValue());
                ag.setData_final_servico(dataFinal.getValue());
                agendamentoDAO.inserir(ag);
                connection.commit();
                carregarTableViewOrcamentos();
                carregarComboBoxMecanico();
                limparCampos();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(FXMLAgendamentoController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(FXMLAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
