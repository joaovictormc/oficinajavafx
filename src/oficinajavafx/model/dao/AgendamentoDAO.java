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
        String sql = "INSERT INTO agendamento (nome_cliente, cpf_cliente, data_inicio_servico, data_final_servico, tipo_pagamento, valor_pago, pagamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, agendamento.getNome_cliente());
            stmt.setString(2, agendamento.getCpf_cliente());
            stmt.setDate(3, Date.valueOf(agendamento.getData_inicio_servico()));
            stmt.setDate(4, Date.valueOf(agendamento.getData_final_servico()));
            stmt.setString(5, agendamento.getTipo_pagamento());
            stmt.setDouble(6, agendamento.getValor_pago());
            stmt.setBoolean(7, agendamento.isPagamento());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean alterar(Agendamento agendamento) {
        String sql = "UPDATE agendamento SET nome_cliente=?, cpf_cliente=?, data_inicio_servico=?, data_final_servico=?, tipo_pagamento=?, valor_pago=?, pagamento=? WHERE id_agenda=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, agendamento.getNome_cliente());
            stmt.setString(2, agendamento.getCpf_cliente());
            stmt.setDate(3, Date.valueOf(agendamento.getData_inicio_servico()));
            stmt.setDate(4, Date.valueOf(agendamento.getData_final_servico()));
            stmt.setString(5, agendamento.getTipo_pagamento());
            stmt.setDouble(6, agendamento.getValor_pago());
            stmt.setBoolean(7, agendamento.isPagamento());
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
        Agendamento agendamento = new Agendamento();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                agendamento.setId_agenda(resultSet.getInt("id_agenda"));
                agendamento.setNome_cliente(resultSet.getString("nome_cliente"));
                agendamento.setCpf_cliente(resultSet.getString("cpf_cliente"));
                agendamento.setData_inicio_servico(resultSet.getDate("data_inicio_servico").toLocalDate());
                agendamento.setData_final_servico(resultSet.getDate("data_final_servico").toLocalDate());
                agendamento.setTipo_pagamento(resultSet.getString("tipo_pagamento"));
                agendamento.setValor_pago(resultSet.getDouble("valor_pago"));
                agendamento.setPagamento(resultSet.getBoolean("pagamento"));
                
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
                agendamento.setId_agenda(resultSet.getInt("id_agenda"));
                agendamento.setNome_cliente(resultSet.getString("nome_cliente"));
                agendamento.setCpf_cliente(resultSet.getString("cpf_cliente"));
                agendamento.setData_inicio_servico(resultSet.getDate("data_inicio_servico").toLocalDate());
                agendamento.setData_final_servico(resultSet.getDate("data_final_servico").toLocalDate());
                agendamento.setTipo_pagamento(resultSet.getString("tipo_pagamento"));
                agendamento.setValor_pago(resultSet.getDouble("valor_pago"));
                agendamento.setPagamento(resultSet.getBoolean("pagamento"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agendamento;
    }
}
