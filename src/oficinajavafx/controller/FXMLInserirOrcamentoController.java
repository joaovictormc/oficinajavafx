package oficinajavafx.controller;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import oficinajavafx.model.dao.ClienteDAO;
import oficinajavafx.model.database.Database;
import oficinajavafx.model.database.DatabaseFactory;
import oficinajavafx.model.domain.Cliente;
import oficinajavafx.model.domain.Orcamento;
import oficinajavafx.model.domain.Servico;


public class FXMLInserirOrcamentoController implements Initializable {

    
    
    
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    private ObservableList<Servico> observableListServico;
    
    
    //conexão com o banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    
    private Stage dialogStage;
    private boolean btnConfirmarClick = false;
    private Orcamento orcamento;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmarClick() {
        return btnConfirmarClick;
    }

    public void setBtnConfirmarClick(boolean btnConfirmarClick) {
        this.btnConfirmarClick = btnConfirmarClick;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
