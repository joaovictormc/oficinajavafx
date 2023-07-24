package oficinajavafx.controller;

import java.net.URL;
import java.sql.*;
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
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import oficinajavafx.model.dao.OrcamentoDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Orcamento;

public class FXMLRelatorioOSPorMesController implements Initializable {
    
    @FXML
    private AnchorPane anchorPaneRelatorioOSPorMes;
    @FXML
    private TableView<Orcamento> tblViewOSPorMes;
    @FXML
    private TableColumn<Orcamento, Integer> tblColumnID;
    @FXML
    private TableColumn<Orcamento, String> tblColumnCliente;
    @FXML
    private TableColumn<Orcamento, String> tblColumnModelo;
    @FXML
    private TableColumn<Orcamento, String> tblColumnServico;
    @FXML
    private TableColumn<Orcamento, Double> tblColumnValor;
    @FXML
    private TableColumn<Orcamento, Date> tblColumnDataEntrada;
    @FXML
    private Button btnImprimir;

    private List<Orcamento> listOrcamento;
    private ObservableList<Orcamento> observableListOS;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final OrcamentoDAO orcamentoDAO = new OrcamentoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orcamentoDAO.setConnection(connection);
        carregarTableViewOS();        
    }

    public void carregarTableViewOS() {
        tblColumnID.setCellValueFactory(new PropertyValueFactory<>("id_os"));
        tblColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tblColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo_veiculo"));
        tblColumnServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tblColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor_final"));
        tblColumnDataEntrada.setCellValueFactory(new PropertyValueFactory<>("data_entrada"));
        

        listOrcamento = orcamentoDAO.listarRelatorio();

        observableListOS = FXCollections.observableArrayList(listOrcamento);
        tblViewOSPorMes.setItems(observableListOS);
    }

    @FXML
    public void buttonImprimir() throws JRException {
        URL url = getClass().getResource("/oficinajavafx/relatorios/RelatorioOSAprovadoNoMes.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }
}
