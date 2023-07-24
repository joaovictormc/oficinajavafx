package oficinajavafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oficinajavafx.model.domain.Avaliacao;

public class FXMLInserirAvaliacaoController implements Initializable {

    @FXML
    private TextField txtCadastroNota;
    @FXML
    private TextField txtCadastroComentario;
    @FXML
    private TextField txtCadastroIdAgendamento;
    @FXML
    private TextField txtCadastroIdMecanico;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnCancelar;

    private Stage dialogStage;
    private boolean btnConfirmarClick = false;
    private Avaliacao avaliacao;

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

    public Avaliacao getAvaliacao() {
        return this.avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
        this.txtCadastroNota.setText(String.valueOf(avaliacao.getNota()));
        this.txtCadastroComentario.setText(avaliacao.getComentario());
        this.txtCadastroIdAgendamento.setText(String.valueOf(avaliacao.getIdAgendamento()));
        this.txtCadastroIdMecanico.setText(String.valueOf(avaliacao.getIdMecanico()));
    }

    public boolean isBtnConfirmarClick() {
        return btnConfirmarClick;
    }

    @FXML
    public void btnCadastrarAvaliacao() {
        if (validarDadosAvaliacao()) {
            avaliacao.setNota((int) Double.parseDouble(txtCadastroNota.getText()));
            avaliacao.setComentario(txtCadastroComentario.getText());
            avaliacao.setIdAgendamento((int) Double.parseDouble(txtCadastroIdAgendamento.getText()));
            avaliacao.setIdMecanico(txtCadastroIdMecanico.getText());
            

            btnConfirmarClick = true;
            dialogStage.close();
        }
    }
    
    @FXML
    public void btnCancelarCadastro() {
        getDialogStage().close();
    }

    public boolean validarDadosAvaliacao() {
        String msgErro = "";

        if (txtCadastroNota.getText() == null || txtCadastroNota.getText().isEmpty() || txtCadastroNota.getText().length() > 2) {
        // Verifica se o campo está vazio, nulo ou tem mais de 2 caracteres
        msgErro += "Campo obrigatório, insira a nota da avaliação corretamente!\n";
        } else {
        try {
        int nota = Integer.parseInt(txtCadastroNota.getText());
        if (nota < 0 || nota > 10) {
            msgErro += "A nota deve estar entre 0 e 10!\n";
        }
            } catch (NumberFormatException e) {
        msgErro += "A nota deve ser um valor numérico válido!\n";
       }
       }
        if (txtCadastroComentario.getText() == null || txtCadastroComentario.getText().isEmpty() || txtCadastroComentario.getText().length() > 200) {
            // Verifica se o comentário está nulo, vazio ou maior que 200 caracteres
            msgErro += "Campo obrigatório, insira o comentário da avaliação corretamente!\n";
        }
        if (txtCadastroIdAgendamento.getText() == null || txtCadastroIdAgendamento.getText().isEmpty() || txtCadastroIdAgendamento.getText().length() > 10) {
            // verifica se o ID do agendamento está nulo, vazio ou maior que 10 caracteres. Também testa sua validez
            msgErro += "Campo obrigatório, insira um ID de Agendamento válido!\n";
        }
        if (txtCadastroIdMecanico.getText() == null || txtCadastroIdMecanico.getText().isEmpty() || txtCadastroIdMecanico.getText().length() > 10) {
            // verifica se o ID do mecânico está nulo, vazio ou maior que 10 caracteres
            msgErro += "Campo obrigatório, insira o ID do mecânico corretamente!\n";
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
