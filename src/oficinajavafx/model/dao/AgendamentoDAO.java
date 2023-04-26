package oficinajavafx.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oficinajavafx.model.domain.Agendamento;


public class AgendamentoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Agendamento agendamento) {
        String sql = "INSERT INTO agendamento (data_inicio_servico, data_final_servico, pagamento, observacoes, id_mecanico, id_cliente, id_orcamentos) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setDate(1, new java.sql.Date(agendamento.getData_inicio_servico().getTime()));
            stmt.setDate(2, new java.sql.Date(agendamento.getData_final_servico().getTime()));
            stmt.setBoolean(3, agendamento.isPagamento());
            stmt.setString(4, agendamento.getObservacoes());
            stmt.setInt(5, agendamento.getId_mecanico());
            stmt.setInt(6, agendamento.getId_cliente());
            stmt.setInt(7, agendamento.getId_orcamentos());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean alterar(Agendamento agendamento) {
        String sql = "UPDATE agendamento SET data_inicio_servico=?, data_final_servico=?, pagamento=?, observacoes=?, id_mecanico=?, id_cliente=?, id_orcamentos=? WHERE id_agenda=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setDate(1, new java.sql.Date(agendamento.getData_inicio_servico().getTime()));
            stmt.setDate(2, new java.sql.Date(agendamento.getData_final_servico().getTime()));
            stmt.setBoolean(3, agendamento.isPagamento());
            stmt.setString(4, agendamento.getObservacoes());
            stmt.setInt(5, agendamento.getId_mecanico());
            stmt.setInt(6, agendamento.getId_cliente());
            stmt.setInt(7, agendamento.getId_orcamentos());
            stmt.setInt(8, agendamento.getId_agenda());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM agendamento WHERE id_agenda=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Agendamento> selectAll() throws SQLException {
        String sql = "SELECT * FROM agendamento";
        List<Agendamento> agendamentos = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int id_agenda = resultSet.getInt("id_agenda");
                Date data_inicio_servico = resultSet.getDate("data_inicio_servico");
                Date data_final_servico = resultSet.getDate("data_final_servico");
                boolean pagamento = resultSet.getBoolean("pagamento");
                String observacoes = resultSet.getString("observacoes");
                int id_mecanico = resultSet.getInt("id_mecanico");
                int id_cliente = resultSet.getInt("id_cliente");
                int id_orcamentos = resultSet.getInt("id_orcamentos");
                Agendamento agendamento = new Agendamento(id_agenda, data_inicio_servico, data_final_servico,
                        pagamento, observacoes, id_mecanico, id_cliente, id_orcamentos);
                agendamentos.add(agendamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agendamentos;
    }

    public Agendamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM agendamento WHERE id_agenda=?";
        Agendamento agendamento = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int id_agenda = resultSet.getInt("id_agenda");
                Date data_inicio_servico = resultSet.getDate("data_inicio_servico");
                Date data_final_servico = resultSet.getDate("data_final_servico");
                boolean pagamento = resultSet.getBoolean("pagamento");
                String observacoes = resultSet.getString("observacoes");
                int id_mecanico = resultSet.getInt("id_mecanico");
                int id_cliente = resultSet.getInt("id_cliente");
                int id_orcamentos = resultSet.getInt("id_orcamentos");
                agendamento = new Agendamento(id_agenda, data_inicio_servico, data_final_servico, pagamento,
                        observacoes, id_mecanico, id_cliente, id_orcamentos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agendamento;
    }
}
