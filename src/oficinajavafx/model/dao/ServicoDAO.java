package oficinajavafx.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oficinajavafx.model.domain.Servico;

public class ServicoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Servico servicos) {
        String sql = "INSERT INTO servicos (tipo_servico, tempo_estimado, valor, id_mecanico, complexidade) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servicos.getTipo_servico());
            stmt.setString(2, servicos.getTempo_estimado());
            stmt.setBigDecimal(3, servicos.getValor());
            stmt.setInt(4, servicos.getId_mecanico());
            stmt.setString(5, servicos.getComplexidade());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Servico servicos) {
        String sql = "UPDATE servicos SET tipo_servico=?, tempo_estimado=?, valor=?, id_mecanico=?, complexidade=? WHERE id_servicos=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servicos.getTipo_servico());
            stmt.setString(2, servicos.getTempo_estimado());
            stmt.setBigDecimal(3, servicos.getValor());
            stmt.setInt(4, servicos.getId_mecanico());
            stmt.setString(5, servicos.getComplexidade());
            stmt.setInt(6, servicos.getId_servicos());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM servicos WHERE id_servicos=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Servico> listar() throws SQLException {
        List<Servico> servicosList = new ArrayList<>();
        String sql = "SELECT * FROM servicos";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id_servicos = resultSet.getInt("id_servicos");
                String tipo_servico = resultSet.getString("tipo_servico");
                String tempo_estimado = resultSet.getString("tempo_estimado");
                BigDecimal valor = resultSet.getBigDecimal("valor");
                int id_mecanico = resultSet.getInt("id_mecanico");
                String complexidade = resultSet.getString("complexidade");
                Servico servicos = new Servico(id_servicos, tipo_servico, tempo_estimado, valor, id_mecanico,
                        complexidade);
                servicosList.add(servicos);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servicosList;
    }

    public Servico buscarPorId(int id) throws SQLException {
        Servico servicos = null;
        String sql = "SELECT * FROM servicos WHERE id_servicos=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int id_servicos = resultSet.getInt("id_servicos");
                String tipo_servico = resultSet.getString("tipo_servico");
                String tempo_estimado = resultSet.getString("tempo_estimado");
                BigDecimal valor = resultSet.getBigDecimal("valor");
                int id_mecanico = resultSet.getInt("id_mecanico");
                String complexidade = resultSet.getString("complexidade");
                servicos = new Servico(id_servicos, tipo_servico, tempo_estimado, valor, id_mecanico,
                        complexidade);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servicos;
    }
}
