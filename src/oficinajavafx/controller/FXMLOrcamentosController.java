/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficinajavafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import oficinajavafx.model.dao.ClienteDAO;
import oficinajavafx.model.dao.OrcamentoDAO;
import oficinajavafx.model.dao.ServicoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Cliente;
import oficinajavafx.model.domain.Orcamento;
import oficinajavafx.model.domain.Servico;

public class FXMLOrcamentosController implements Initializable {

    @FXML
    private TableView<Cliente> tblViewOS;
    @FXML
    private TableColumn<Cliente, String> tableColumnNomeCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnCPFCliente;
    @FXML
    private TextField textFieldModelo;
    @FXML
    private TextField textFieldMarca;
    @FXML
    private TextField textFieldAno;
    @FXML
    private TextField textFieldDescontos;
    @FXML
    private DatePicker dataPickerDataDeEntrada;
    @FXML
    private ComboBox comboBoxServico;
    @FXML
    private TextArea textAreaDefeitoRelatado;
    @FXML
    private Label labelValor;
    @FXML
    private Button btnOSInserir;
    
    private List<Servico> listServicos;
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    private ObservableList<Servico> observableListServicos;
    
    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ServicoDAO servicoDAO = new ServicoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicoDAO.setConnection(connection);
        clienteDAO.setConnection(connection);
        orcamentoDAO.setConnection(connection);
        carregarTableViewClientes();
        cerregarComboBoxServicos();
    }
    
    public void limparCamposSelecionador() {
        textAreaDefeitoRelatado.setText("");
        textFieldAno.setText("");
        textFieldMarca.setText("");
        textFieldModelo.setText("");
        textFieldDescontos.setText("");
    }

    public void carregarTableViewClientes() {
        tableColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCPFCliente.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        listClientes = clienteDAO.listarPorSituacao();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tblViewOS.setItems(observableListClientes);
    }
    
    public void cerregarComboBoxServicos() {
        listServicos = servicoDAO.listar();
        observableListServicos = FXCollections.observableArrayList(listServicos);
        comboBoxServico.setItems(observableListServicos);
    }
    
    @FXML
    public void insirirUmAgendamento() {
        Cliente cliente = tblViewOS.getSelectionModel().getSelectedItem();
        Servico servico = (Servico) comboBoxServico.getSelectionModel().getSelectedItem();
        if (cliente != null && servico != null) {
            Orcamento orcamento = new Orcamento();
            try {
                connection.setAutoCommit(false);
                orcamento.setNome_cliente(cliente.getNome());
                orcamento.setCpf_cliente(cliente.getCpf());
                orcamento.setAno_veiculo(textFieldAno.getText());
                orcamento.setMarca_veiculo(textFieldMarca.getText());
                orcamento.setModelo_veiculo(textFieldModelo.getText());
                orcamento.setData_entrada(dataPickerDataDeEntrada.getValue());
                orcamento.setDescontos(Integer.valueOf(textFieldDescontos.getText()));
                orcamento.setDefeito_relatado(textAreaDefeitoRelatado.getText());
                orcamento.setValor_final(servico.getValor() - (servico.getValor() * (Integer.valueOf(textFieldDescontos.getText())/100.0)));
                orcamento.setServico(servico.getTipo_Servico());
                orcamentoDAO.inserir(orcamento);
                cliente.setOsFeitos(1);
                clienteDAO.alterar(cliente);
                carregarTableViewClientes();
                limparCamposSelecionador();
                connection.commit();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(FXMLOrcamentosController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(FXMLOrcamentosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
}
