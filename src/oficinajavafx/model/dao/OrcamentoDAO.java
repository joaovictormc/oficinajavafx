package oficinajavafx.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oficinajavafx.model.domain.Orcamento;

public class OrcamentoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Orcamento orcamento) throws SQLException {
        String sql = "INSERT INTO orcamentos (tipo_veiculo, modelo_veiculo, ano_veiculo, avarias, data_entrada, data_saida, defeito_relatado, defeito_constatado, descontos, valor_final, situacao, id_cliente, id_servicos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, orcamento.getTipo_veiculo());
            stmt.setString(2, orcamento.getModelo_veiculo());
            stmt.setString(3, orcamento.getAno_veiculo());
            stmt.setString(4, orcamento.getAvarias());
            stmt.setDate(5, new java.sql.Date(orcamento.getData_entrada().getTime()));
            stmt.setDate(6, new java.sql.Date(orcamento.getData_saida().getTime()));
            stmt.setString(7, orcamento.getDefeito_relatado());
            stmt.setString(8, orcamento.getDefeito_constatado());
            stmt.setInt(9, orcamento.getDescontos());
            stmt.setBigDecimal(10, orcamento.getValor_final());
            stmt.setBoolean(11, orcamento.getSituacao());
            stmt.setInt(12, orcamento.getId_cliente());
            stmt.setInt(13, orcamento.getId_servicos());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Orcamento orcamento) throws SQLException {
        String sql = "UPDATE orcamentos SET tipo_veiculo=?, modelo_veiculo=?, ano_veiculo=?, avarias=?, data_entrada=?, data_saida=?, defeito_relatado=?, defeito_constatado=?, descontos=?, valor_final=?, situacao=?, id_cliente=?, id_servicos=? WHERE id_os=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, orcamento.getTipo_veiculo());
            stmt.setString(2, orcamento.getModelo_veiculo());
            stmt.setString(3, orcamento.getAno_veiculo());
            stmt.setString(4, orcamento.getAvarias());
            stmt.setDate(5, new java.sql.Date(orcamento.getData_entrada().getTime()));
            stmt.setDate(6, new java.sql.Date(orcamento.getData_saida().getTime()));
            stmt.setString(7, orcamento.getDefeito_relatado());
            stmt.setString(8, orcamento.getDefeito_constatado());
            stmt.setInt(9, orcamento.getDescontos());
            stmt.setBigDecimal(10, orcamento.getValor_final());
            stmt.setBoolean(11, orcamento.getSituacao());
            stmt.setInt(12, orcamento.getId_cliente());
            stmt.setInt(13, orcamento.getId_servicos());
            stmt.setInt(14, orcamento.getId_os());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM orcamentos WHERE id_os=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Orcamento> listar() throws SQLException {
        String sql = "SELECT * FROM orcamentos";
        List<Orcamento> orcamentos = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id_os = resultSet.getInt("id_os");
                String tipo_veiculo = resultSet.getString("tipo_veiculo");
                String modelo_veiculo = resultSet.getString("modelo_veiculo");
                String ano_veiculo = resultSet.getString("ano_veiculo");
                String avarias = resultSet.getString("avarias");
                Date data_entrada = resultSet.getDate("data_entrada");
                Date data_saida = resultSet.getDate("data_saida");
                String defeito_relatado = resultSet.getString("defeito_relatado");
                String defeito_constatado = resultSet.getString("defeito_constatado");
                int descontos = resultSet.getInt("descontos");
                BigDecimal valor_final = resultSet.getBigDecimal("valor_final");
                boolean situacao = resultSet.getBoolean("situacao");
                int id_cliente = resultSet.getInt("id_cliente");
                int id_servicos = resultSet.getInt("id_servicos");
                Orcamento orcamento = new Orcamento(id_os, tipo_veiculo, modelo_veiculo, ano_veiculo, avarias,
                        data_entrada, data_saida, defeito_relatado, defeito_constatado, descontos, valor_final,
                        situacao, id_cliente, id_servicos);
                orcamentos.add(orcamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orcamentos;
    }

    public Orcamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM orcamentos WHERE id_os=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String tipo_veiculo = resultSet.getString("tipo_veiculo");
                String modelo_veiculo = resultSet.getString("modelo_veiculo");
                String ano_veiculo = resultSet.getString("ano_veiculo");
                String avarias = resultSet.getString("avarias");
                Date data_entrada = resultSet.getDate("data_entrada");
                Date data_saida = resultSet.getDate("data_saida");
                String defeito_relatado = resultSet.getString("defeito_relatado");
                String defeito_constatado = resultSet.getString("defeito_constatado");
                int descontos = resultSet.getInt("descontos");
                BigDecimal valor_final = resultSet.getBigDecimal("valor_final");
                boolean situacao = resultSet.getBoolean("situacao");
                int id_cliente = resultSet.getInt("id_cliente");
                int id_servicos = resultSet.getInt("id_servicos");
                Orcamento orcamento = new Orcamento(id, tipo_veiculo, modelo_veiculo, ano_veiculo, avarias,
                        data_entrada, data_saida, defeito_relatado, defeito_constatado, descontos, valor_final,
                        situacao, id_cliente, id_servicos);
                return orcamento;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; // caso não encontre nenhum orcamento com o id especificado
    }

}
