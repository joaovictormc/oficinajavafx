package oficinajavafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oficinajavafx.model.domain.Cliente;

public class FXMLInserirClientesController implements Initializable {

    @FXML
    private TextField txtCadastroNome;
    @FXML
    private TextField txtCadastroEndereco;
    @FXML
    private TextField txtCadastroTelefone;
    @FXML
    private TextField txtCadastroCPF;
    @FXML
    private TextField txtCadastroEmail;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnCancelar;

    private Stage dialogStage;
    private boolean btnConfirmarClick = false;
    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.txtCadastroNome.setText(cliente.getNome());
        this.txtCadastroEndereco.setText(cliente.getEndereco());
        this.txtCadastroTelefone.setText(cliente.getTelefone());
        this.txtCadastroCPF.setText(cliente.getCpf());
        this.txtCadastroEmail.setText(cliente.getEmail());
    }

    public boolean isBtnConfirmarClick() {
        return btnConfirmarClick;
    }

    @FXML
    public void btnCadastrarCliente() {
        if (validarDadosCliente()) {
            cliente.setNome(txtCadastroNome.getText());
            cliente.setEndereco(txtCadastroEndereco.getText());
            cliente.setTelefone(txtCadastroTelefone.getText());
            cliente.setCpf(txtCadastroCPF.getText());
            cliente.setEmail(txtCadastroEmail.getText());

            btnConfirmarClick = true;
            dialogStage.close();
        }
    }
    
    @FXML
    public void btnCancelarCadastro() {
        getDialogStage().close();
    }

    public boolean validarDadosCliente() {
        String msgErro = "";

        if (txtCadastroNome.getText() == null || txtCadastroNome.getText().isEmpty() || txtCadastroNome.getText().length() > 100) {
            // verifica se o nome está nulo, vazio ou maior que 100 caracteres
            msgErro += "Campo obrigatório, insira o nome do cliente corretamente!\n";
        }
        if (txtCadastroTelefone.getText() == null || txtCadastroTelefone.getText().isEmpty() || txtCadastroTelefone.getText().length() > 20) {
            // verifica se o telefone está nulo, vazio ou maior que 20 caracteres
            msgErro += "Campo obrigatório, insira o telefone do cliente corretamente!\n";
        }
        if (txtCadastroCPF.getText() == null || txtCadastroCPF.getText().isEmpty() || txtCadastroCPF.getText().length() > 15) {
            // verifica se o CPF está nulo, vazio ou maior que 15 caracteres
            msgErro += "Campo obrigatório, insira o CPF do cliente corretamente!\n";
        }
        // se passar por todas as validações, retorna verdadeiro
        if (msgErro.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro!");
            alert.setHeaderText("Campos inválidos ou em branco, tente novmente!");
            alert.setContentText(msgErro);
            alert.show();
            return false;
        }
    }
    
    
    

}
