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

import oficinajavafx.model.domain.Mecanico;

public class MecanicoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Mecanico mecanico) throws SQLException {
        String sql = "INSERT INTO mecanico (nome_mec, telefone_mec, especialidade, email, salario, disponibilidade, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mecanico.getNome_mec());
            stmt.setString(2, mecanico.getTelefone_mec());
            stmt.setString(3, mecanico.getEspecialidade());
            stmt.setString(4, mecanico.getEmail());
            stmt.setBigDecimal(5, mecanico.getSalario());
            stmt.setBoolean(6, mecanico.isDisponibilidade());
            stmt.setString(7, mecanico.getStatus());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MecanicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Mecanico mecanico) throws SQLException {
        String sql = "UPDATE mecanico SET nome_mec=?, telefone_mec=?, especialidade=?, email=?, salario=?, disponibilidade=?, status=? WHERE id_mec=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mecanico.getNome_mec());
            stmt.setString(2, mecanico.getTelefone_mec());
            stmt.setString(3, mecanico.getEspecialidade());
            stmt.setString(4, mecanico.getEmail());
            stmt.setBigDecimal(5, mecanico.getSalario());
            stmt.setBoolean(6, mecanico.isDisponibilidade());
            stmt.setString(7, mecanico.getStatus());
            stmt.setInt(8, mecanico.getId_mec());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MecanicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM mecanico WHERE id_mec=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MecanicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Mecanico> listar() throws SQLException {
        String sql = "SELECT * FROM mecanico";
        List<Mecanico> mecanicos = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id_mec = resultSet.getInt("id_mec");
                String nome_mec = resultSet.getString("nome_mec");
                String telefone_mec = resultSet.getString("telefone_mec");
                String especialidade = resultSet.getString("especialidade");
                String email = resultSet.getString("email");
                BigDecimal salario = resultSet.getBigDecimal("salario");
                boolean disponibilidade = resultSet.getBoolean("disponibilidade");
                String status = resultSet.getString("status");
                Mecanico mecanico = new Mecanico(id_mec, nome_mec, telefone_mec, especialidade, email, salario,
                        disponibilidade, status);
                mecanicos.add(mecanico);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MecanicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mecanicos;
    }

    public Mecanico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM mecanico WHERE id_mec=?";
        Mecanico mecanico = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int id_mec = resultSet.getInt("id_mec");
                String nome_mec = resultSet.getString("nome_mec");
                String telefone_mec = resultSet.getString("telefone_mec");
                String especialidade = resultSet.getString("especialidade");
                String email = resultSet.getString("email");
                BigDecimal salario = resultSet.getBigDecimal("salario");
                boolean disponibilidade = resultSet.getBoolean("disponibilidade");
                String status = resultSet.getString("status");
                mecanico = new Mecanico(id_mec, nome_mec, telefone_mec, especialidade, email, salario, disponibilidade,
                        status);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MecanicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mecanico;
    }
}