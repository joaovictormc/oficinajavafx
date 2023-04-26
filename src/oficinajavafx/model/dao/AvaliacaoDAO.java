package oficinajavafx.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oficinajavafx.model.domain.Avaliacao;

public class AvaliacaoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacao (nota, comentario, id_agendamento, id_mecanico) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, avaliacao.getNota());
            stmt.setString(2, avaliacao.getComentario());
            stmt.setInt(3, avaliacao.getIdAgendamento());
            stmt.setInt(4, avaliacao.getIdMecanico());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Avaliacao avaliacao) {
        String sql = "UPDATE avaliacao SET nota = ?, comentario = ?, id_agendamento = ?, id_mecanico = ? WHERE id_avaliacao = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, avaliacao.getNota());
            stmt.setString(2, avaliacao.getComentario());
            stmt.setInt(3, avaliacao.getIdAgendamento());
            stmt.setInt(4, avaliacao.getIdMecanico());
            stmt.setInt(5, avaliacao.getIdAvaliacao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM avaliacao WHERE id_avaliacao = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Avaliacao> listar() {
        String sql = "SELECT * FROM avaliacao";
        List<Avaliacao> avaliacoes = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Avaliacao avaliacao = new Avaliacao(
                        rs.getInt("id_avaliacao"),
                        rs.getInt("nota"),
                        rs.getString("comentario"),
                        rs.getInt("id_agendamento"),
                        rs.getInt("id_mecanico"));
                avaliacoes.add(avaliacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avaliacoes;
    }

    public Avaliacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM avaliacao WHERE id_avaliacao=?";
        Avaliacao avaliacao = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id_avaliacao = resultSet.getInt("id_avaliacao");
                int nota = resultSet.getInt("nota");
                String comentario = resultSet.getString("comentario");
                int id_agendamento = resultSet.getInt("id_agendamento");
                int id_mecanico = resultSet.getInt("id_mecanico");
                avaliacao = new Avaliacao(id_avaliacao, nota, comentario, id_agendamento, id_mecanico);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avaliacao;
    }
}
