package oficinajavafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oficinajavafx.model.domain.Mecanico;

public class FXMLInserirMecanicosController implements Initializable {

    @FXML
    private TextField txtCadastroNome;
    @FXML
    private TextField txtCadastroTelefone;
    @FXML
    private TextField txtCadastroEspecialidade;
    @FXML
    private TextField txtCadastroEmail;
    @FXML
    private TextField txtCadastroSalario;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnCancelar;

    private Stage dialogStage;
    private boolean btnConfirmarClick = false;
    private Mecanico mecanico;

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

    public Mecanico getMecanico() {
        return this.mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
        this.txtCadastroNome.setText(mecanico.getNome_mec());
        this.txtCadastroTelefone.setText(mecanico.getTelefone_mec());
        this.txtCadastroEspecialidade.setText(mecanico.getEspecialidade());
        this.txtCadastroEmail.setText(mecanico.getEmail());
        this.txtCadastroSalario.setText(String.valueOf(mecanico.getSalario()));
    }

    public boolean isBtnConfirmarClick() {
        return btnConfirmarClick;
    }

    @FXML
    public void btnCadastrarMecanico() {
        if (validarDadosMecanico()) {
            mecanico.setNome_mec(txtCadastroNome.getText());
            mecanico.setTelefone_mec(txtCadastroTelefone.getText());
            mecanico.setEspecialidade(txtCadastroEspecialidade.getText());
            mecanico.setEmail(txtCadastroEmail.getText());
            mecanico.setSalario(Double.parseDouble(txtCadastroSalario.getText()));

            btnConfirmarClick = true;
            dialogStage.close();
        }
    }
    
    @FXML
    public void btnCancelarCadastro() {
        getDialogStage().close();
    }

    public boolean validarDadosMecanico() {
        String msgErro = "";

        if (txtCadastroNome.getText() == null || txtCadastroNome.getText().isEmpty() || txtCadastroNome.getText().length() > 100) {
            // verifica se o nome está nulo, vazio ou maior que 100 caracteres
            msgErro += "Campo obrigatório, insira o nome do mecânico corretamente!\n";
        }
        if (txtCadastroTelefone.getText() == null || txtCadastroTelefone.getText().isEmpty() || txtCadastroTelefone.getText().length() > 20) {
            // verifica se o telefone está nulo, vazio ou maior que 20 caracteres
            msgErro += "Campo obrigatório, insira o telefone do mecânico corretamente!\n";
        }
        if (txtCadastroEspecialidade.getText() == null || txtCadastroEspecialidade.getText().isEmpty() || txtCadastroEspecialidade.getText().length() > 50) {
            // verifica se a especialidade está nula, vazia ou maior que 50 caracteres
            msgErro += "Campo obrigatório, insira a especialidade do mecânico corretamente!\n";
        }
        if (txtCadastroEmail.getText() == null || txtCadastroEmail.getText().isEmpty() || txtCadastroEmail.getText().length() > 50) {
            // verifica se o email está nulo, vazio ou maior que 50 caracteres
            msgErro += "Campo obrigatório, insira o email do mecânico corretamente!\n";
        }
        if (txtCadastroSalario.getText() == null || txtCadastroSalario.getText().isEmpty() || txtCadastroSalario.getText().length() > 50) {
            // verifica se o email está nulo, vazio ou maior que 50 caracteres
            msgErro += "Campo obrigatório, insira o salário do mecânico corretamente!\n";
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
