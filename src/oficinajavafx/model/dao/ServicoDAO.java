package oficinajavafx.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oficinajavafx.model.domain.Mecanico;

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
            stmt.setDouble(3, servicos.getValor());
            stmt.setInt(4, servicos.getMecanico().getId_mec());
            stmt.setString(5, servicos.getComplexidade());
            stmt.execute();
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
            stmt.setDouble(3, servicos.getValor());
            stmt.setInt(4, servicos.getMecanico().getId_mec());
            stmt.setString(5, servicos.getComplexidade());
            stmt.setInt(6, servicos.getId_servicos());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(Servico servico) {
        String sql = "DELETE FROM servicos WHERE id_servicos=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getId_servicos());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Servico> listar(){
        String sql = "SELECT * FROM servicos";
        List<Servico> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Servico servico = new Servico();
                Mecanico mecanico = new Mecanico();
                
                servico.setId_serveicos(rs.getInt("id_servico"));
                servico.setTipo_servico(rs.getString("tipo_servico"));
                servico.setTempo_estimado(rs.getString("tempo_estimado"));
                servico.setValor(rs.getDouble("valor"));
                mecanico.setId_mec(rs.getInt("id_mec"));
                servico.setComplexidade(rs.getString("complexidade"));
                
                //pegando dados da tabela mecanico
                MecanicoDAO mecanicoDAO = new MecanicoDAO();
                mecanicoDAO.setConnection(connection);
                mecanico = mecanicoDAO.buscar(mecanico);
                
                servico.setMecanico(mecanico);
                
                retorno.add(servico);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Servico buscar(Servico servico) {
        String sql = "SELECT * FROM servicos WHERE id_servicos=?";
        Servico retorno = new Servico();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getId_servicos());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Mecanico mecanico = new Mecanico();
                
                servico.setId_serveicos(rs.getInt("id_servico"));
                servico.setTipo_servico(rs.getString("tipo_servico"));
                servico.setTempo_estimado(rs.getString("tempo_estimado"));
                servico.setValor(rs.getDouble("valor"));
                mecanico.setId_mec(rs.getInt("id_mec"));
                servico.setComplexidade(rs.getString("complexidade"));
                
                //pegando dados da tabela mecanico
                MecanicoDAO mecanicoDAO = new MecanicoDAO();
                mecanicoDAO.setConnection(connection);
                mecanico = mecanicoDAO.buscar(mecanico);
                
                servico.setMecanico(mecanico);
                retorno = servico;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    List<Servico> buscar(List<Servico> servico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
