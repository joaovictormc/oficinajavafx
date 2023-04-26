package oficinajavafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnEntrar;

    @FXML
    public void entrar(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        String usuario = txtUser.getText();
        String senha = txtPassword.getText();
        // Conexão com o banco de dados
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Substitua os valores abaixo com as informações do seu banco de dados
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://127.0.0.1/oficina";
            String user = "postgres";
            String password = "1234";

            connection = DriverManager.getConnection(url, user, password);
            // Aqui você pode adicionar a lógica para validar o usuário e senha no banco de dados
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario);
            pstmt.setString(2, senha);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Usuário e senha válidos
                Parent root = FXMLLoader.load(getClass().getResource("/oficinajavafx/view/FXMLTelaPrincipal.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Problemas no login!");
                alert.setContentText("Login e senha inválidos! Tente novamente!");
                alert.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
