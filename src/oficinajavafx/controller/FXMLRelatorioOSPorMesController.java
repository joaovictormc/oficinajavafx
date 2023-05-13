package oficinajavafx.controller;

import java.awt.print.PageFormat;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import oficinajavafx.model.dao.Orcamento2DAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Orcamento;

public class FXMLRelatorioOSPorMesController implements Initializable {

    @FXML
    private TableView<Orcamento> tblViewOSPorMes;
    @FXML
    private TableColumn<Orcamento, Integer> tblColumnID;
    @FXML
    private TableColumn<Orcamento, String> tblColumnNome;
    @FXML
    private TableColumn<Orcamento, String> tblColumnModelo;
    @FXML
    private TableColumn<Orcamento, String> tblColumnServico;
    @FXML
    private TableColumn<Orcamento, Double> tblColumnValor;
    @FXML
    private TableColumn<Orcamento, Boolean> tblColumnSituacao;
    @FXML
    private Button btnImprimir;

    private List<Orcamento> listOrcamento;
    private ObservableList<Orcamento> observableListOS;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final Orcamento2DAO orcamento2DAO = new Orcamento2DAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orcamento2DAO.setConnection(connection);

        try {
            carregarTableViewOS();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLRelatorioOSPorMesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewOS() throws SQLException {
        tblColumnID.setCellValueFactory(new PropertyValueFactory<>("id_os"));
        tblColumnNome.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        tblColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo_veiculo"));
        tblColumnServico.setCellValueFactory(new PropertyValueFactory<>("id_servico"));
        tblColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor_final"));
        tblColumnSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));

        listOrcamento = orcamento2DAO.listar();

        observableListOS = FXCollections.observableArrayList(listOrcamento);
        tblViewOSPorMes.setItems(observableListOS);
    }

    @FXML
    public void buttonImprimir() throws JRException {
        URL url = getClass().getResource("/oficinajavafx/relatorios/");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }
}
