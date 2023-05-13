package oficinajavafx.model.dao;

import java.math.BigDecimal;
import java.sql.*;
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

    public boolean inserir(Mecanico mecanico) {
        String sql = "INSERT INTO mecanico (nome_mec, telefone_mec, especialidade, email, salario, disponibilidade, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mecanico.getNome_mec());
            stmt.setString(2, mecanico.getTelefone_mec());
            stmt.setString(3, mecanico.getEspecialidade());
            stmt.setString(4, mecanico.getEmail());
            stmt.setDouble(5, mecanico.getSalario());
            stmt.setBoolean(6, mecanico.isDisponibilidade());
            stmt.setString(7, mecanico.getStatus());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MecanicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Mecanico mecanico) {
        String sql = "UPDATE mecanico SET nome_mec=?, telefone_mec=?, especialidade=?, email=?, salario=?, disponibilidade=?, status=? WHERE id_mec=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mecanico.getNome_mec());
            stmt.setString(2, mecanico.getTelefone_mec());
            stmt.setString(3, mecanico.getEspecialidade());
            stmt.setString(4, mecanico.getEmail());
            stmt.setDouble(5, mecanico.getSalario());
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

    public boolean excluir(Mecanico mecanico){
        String sql = "DELETE FROM mecanico WHERE id_mec=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, mecanico.getId_mec());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MecanicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Mecanico> listar(){
        String sql = "SELECT * FROM mecanico";
        List<Mecanico> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mecanico mecanico = new Mecanico();
                mecanico.setId_mec(rs.getInt("id_mec"));
                mecanico.setNome_mec(rs.getString("nome_mec"));
                mecanico.setTelefone_mec(rs.getString("telefone_mec"));
                mecanico.setEspecialidade(rs.getString("especialidade"));
                mecanico.setEmail(rs.getString("email"));
                mecanico.setSalario(rs.getDouble("salario"));
                mecanico.setDisponibilidade(rs.getBoolean("disponibilidade"));
                mecanico.setStatus(rs.getString("status"));
                retorno.add(mecanico);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MecanicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Mecanico buscar(Mecanico mecanico){
        String sql = "SELECT * FROM mecanico WHERE id_mec=?";
        Mecanico retorno = new Mecanico();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, mecanico.getId_mec());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                mecanico.setNome_mec(rs.getString("nome_mec"));
                mecanico.setTelefone_mec(rs.getString("telefone_mec"));
                mecanico.setEspecialidade(rs.getString("especialidade"));
                mecanico.setEmail(rs.getString("email"));
                mecanico.setSalario(rs.getDouble("salario"));
                mecanico.setDisponibilidade(rs.getBoolean("disponibilidade"));
                mecanico.setStatus(rs.getString("status"));
                retorno = mecanico;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MecanicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}