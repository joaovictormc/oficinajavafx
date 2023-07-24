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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import oficinajavafx.model.dao.AvaliacaoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Avaliacao;

/**
 * FXML Controller class
 *
 * @author Usuário
 */
public class FXMLReputacaoDeServicosPorMecanicoController implements Initializable {

    @FXML
    private TableView<Avaliacao> tblViewAvaliacao;
    @FXML
    private TableColumn<Avaliacao, Integer> tableColumnNota;
    @FXML
    private TableColumn<Avaliacao, String> tableColumnComentario;
    @FXML
    private TableColumn<Avaliacao, String> tableColumnDataAgendamento;
    @FXML
    private TableColumn<Avaliacao, Integer> tableColumnIdMecanico;
    @FXML
    private Button btnImprimir;
    
    private List<Avaliacao> listAvaliacao;
    private ObservableList<Avaliacao> observableListAvaliacao;

    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        avaliacaoDAO.setConnection(connection);
    }    
    public void carregarTableViewRDSPM() {
        tableColumnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        tableColumnComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        tableColumnDataAgendamento.setCellValueFactory(new PropertyValueFactory<>("id_agendamento"));
        tableColumnIdMecanico.setCellValueFactory(new PropertyValueFactory<>("id_mecanico"));

        listAvaliacao = avaliacaoDAO.listar();

        observableListAvaliacao = FXCollections.observableArrayList(listAvaliacao);
        tblViewAvaliacao.setItems(observableListAvaliacao);
    }
}
