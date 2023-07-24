package oficinajavafx.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import oficinajavafx.model.domain.Cliente;

import oficinajavafx.model.domain.Orcamento;
import oficinajavafx.model.domain.Servico;

public class OrcamentoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Orcamento orcamento) throws SQLException {
        String sql = "INSERT INTO orcamentos (nome_cliente, cpf_cliente, modelo_veiculo, ano_veiculo, marca_veiculo, data_entrada, defeito_relatado, descontos, valor_final, or_finalizado, servico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, orcamento.getNome_cliente());
            stmt.setString(2, orcamento.getCpf_cliente());
            stmt.setString(3, orcamento.getModelo_veiculo());
            stmt.setString(4, orcamento.getAno_veiculo());
            stmt.setString(5, orcamento.getMarca_veiculo());
            stmt.setDate(6, Date.valueOf(orcamento.getData_entrada()));
            stmt.setString(7, orcamento.getDefeito_relatado());
            stmt.setInt(8, orcamento.getDescontos());
            stmt.setDouble(9, orcamento.getValor_final());
            stmt.setBoolean(10, orcamento.getOrfinalizado());
            stmt.setString(11, orcamento.getServico());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Orcamento orcamento) {
        String sql = "UPDATE orcamentos SET nome_cliente=?, cpf_cliente=?, modelo_veiculo=?, ano_veiculo=?, marca_veiculo=?, data_entrada=?, defeito_relatado=?, descontos=?, valor_final=?, or_finalizado=?, servico=? WHERE id_os=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, orcamento.getNome_cliente());
            stmt.setString(2, orcamento.getCpf_cliente());
            stmt.setString(3, orcamento.getModelo_veiculo());
            stmt.setString(4, orcamento.getAno_veiculo());
            stmt.setString(5, orcamento.getMarca_veiculo());
            stmt.setDate(6, Date.valueOf(orcamento.getData_entrada()));
            stmt.setString(7, orcamento.getDefeito_relatado());
            stmt.setInt(8, orcamento.getDescontos());
            stmt.setDouble(9, orcamento.getValor_final());
            stmt.setBoolean(10, orcamento.getOrfinalizado());
            stmt.setString(11, orcamento.getServico());
            stmt.setInt(12, orcamento.getId_os());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id) {
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

    public List<Orcamento> listar() {
        String sql = "SELECT * FROM orcamentos";
        List<Orcamento> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Orcamento orcamento = new Orcamento();

                orcamento.setId_os(rs.getInt("id_os"));
                orcamento.setNome_cliente(rs.getString("nome_cliente"));
                orcamento.setCpf_cliente(rs.getString("cpf_cliente"));
                orcamento.setModelo_veiculo(rs.getString("modelo_veiculo"));
                orcamento.setMarca_veiculo(rs.getString("marca_veiculo"));
                orcamento.setAno_veiculo(rs.getString("ano_veiculo"));
                orcamento.setData_entrada(rs.getDate("data_entrada").toLocalDate());
                orcamento.setDefeito_relatado(rs.getString("defeito_relatado"));
                orcamento.setDescontos(rs.getInt("descontos"));
                orcamento.setValor_final(rs.getDouble("valor_final"));
                orcamento.setOrfinalizado(rs.getBoolean("or_finalizado"));
                orcamento.setServico(rs.getString("servico"));
                
                retorno.add(orcamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Orcamento> listarRelatorio() {
        String sql = "SELECT id_os, nome_cliente, modelo_veiculo, servico, valor_final, data_entrada FROM orcamentos";
        List<Orcamento> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Orcamento orcamento = new Orcamento();
                Cliente cliente = new Cliente();
                Servico servico = new Servico();

                orcamento.setId_os(rs.getInt("id_os"));
                orcamento.setModelo_veiculo(rs.getString("modelo_veiculo"));
                orcamento.setData_entrada(rs.getDate("data_entrada").toLocalDate());
                orcamento.setValor_final(rs.getDouble("valor_final"));

                //pegando os dados da tabela cliente
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                cliente = clienteDAO.buscarPorNome(rs.getString("nome_cliente"));

                //pegando os dados da tabela servico
                ServicoDAO servicoDAO = new ServicoDAO();
                servicoDAO.setConnection(connection);
                servico = servicoDAO.buscarPorTipoServico(rs.getString("servico"));

                orcamento.setNome_cliente(cliente.getNome());
                orcamento.setServico(servico.getTipo_Servico());
                retorno.add(orcamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Orcamento> listarPorSituacao() {
        String sql = "SELECT * FROM orcamentos WHERE or_finalizado=false";
        List<Orcamento> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Orcamento orcamento = new Orcamento();

                orcamento.setId_os(rs.getInt("id_os"));
                orcamento.setNome_cliente(rs.getString("nome_cliente"));
                orcamento.setCpf_cliente(rs.getString("cpf_cliente"));
                orcamento.setModelo_veiculo(rs.getString("modelo_veiculo"));
                orcamento.setMarca_veiculo(rs.getString("marca_veiculo"));
                orcamento.setAno_veiculo(rs.getString("ano_veiculo"));
                orcamento.setData_entrada(rs.getDate("data_entrada").toLocalDate());
                orcamento.setDefeito_relatado(rs.getString("defeito_relatado"));
                orcamento.setDescontos(rs.getInt("descontos"));
                orcamento.setValor_final(rs.getDouble("valor_final"));
                orcamento.setOrfinalizado(rs.getBoolean("or_finalizado"));
                orcamento.setServico(rs.getString("servico"));
                
                retorno.add(orcamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Orcamento buscarPorId(int id) {
        String sql = "SELECT * FROM orcamentos WHERE id_os=?";
        Orcamento retorno = new Orcamento();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Orcamento orcamento = new Orcamento();

                orcamento.setId_os(rs.getInt("id_os"));
                orcamento.setNome_cliente(rs.getString("nome_cliente"));
                orcamento.setCpf_cliente(rs.getString("cpf_cliente"));
                orcamento.setModelo_veiculo(rs.getString("modelo-veiculo"));
                orcamento.setAno_veiculo(rs.getString("ano_veiculo"));
                orcamento.setMarca_veiculo(rs.getString("marca_veiculo"));
                orcamento.setData_entrada(rs.getDate("data_entrada").toLocalDate());
                orcamento.setDefeito_relatado(rs.getString("defeito_relatado"));
                orcamento.setDescontos(rs.getInt("descontos"));
                orcamento.setValor_final(rs.getDouble("valor_final"));
                orcamento.setOrfinalizado(rs.getBoolean("or_finalizado"));
                orcamento.setServico(rs.getString("servico"));
                
                retorno = orcamento;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Map<Integer, ArrayList> listarOrcamentosPorMes() {
        String sql = "SELECT COUNT(id_os), EXTRACT(year from data_entrada) AS ano, EXTRACT(month from data_entrada) AS mes FROM orcamentos GROUP BY ano, mes ORDER BY ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(rs.getInt("ano"))) {
                    linha.add(rs.getInt("mes"));
                    linha.add(rs.getInt("count"));
                    retorno.put(rs.getInt("ano"), linha);
                } else {
                    ArrayList linhaNova = retorno.get(rs.getInt("ano"));
                    linhaNova.add(rs.getInt("mes"));
                    linhaNova.add(rs.getInt("count"));
                }
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
