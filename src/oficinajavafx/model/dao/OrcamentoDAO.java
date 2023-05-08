package oficinajavafx.model.dao;

import java.math.BigDecimal;
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
        String sql = "INSERT INTO orcamentos (tipo_veiculo, modelo_veiculo, ano_veiculo, avarias, data_entrada, data_saida, defeito_relatado, defeito_constatado, descontos, valor_final, situacao, id_cliente, id_servicos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, orcamento.getTipo_veiculo());
            stmt.setString(2, orcamento.getModelo_veiculo());
            stmt.setString(3, orcamento.getAno_veiculo());
            stmt.setString(4, orcamento.getMarca_veiculo());
            stmt.setString(5, orcamento.getAvarias());
            stmt.setDate(6, Date.valueOf(orcamento.getData_entrada()));
            stmt.setDate(7, Date.valueOf(orcamento.getData_saida()));
            stmt.setString(8, orcamento.getDefeito_relatado());
            stmt.setString(9, orcamento.getDefeito_constatado());
            stmt.setInt(10, orcamento.getDescontos());
            stmt.setDouble(11, orcamento.getValor_final());
            stmt.setBoolean(12, orcamento.getSituacao());
            stmt.setInt(13, orcamento.getCliente().getId_Cli());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Orcamento orcamento) {
        String sql = "UPDATE orcamentos SET tipo_veiculo=?, modelo_veiculo=?, ano_veiculo=?, avarias=?, data_entrada=?, data_saida=?, defeito_relatado=?, defeito_constatado=?, descontos=?, valor_final=?, situacao=?, id_cliente=? WHERE id_os=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, orcamento.getTipo_veiculo());
            stmt.setString(2, orcamento.getModelo_veiculo());
            stmt.setString(3, orcamento.getAno_veiculo());
            stmt.setString(4, orcamento.getMarca_veiculo());
            stmt.setString(5, orcamento.getAvarias());
            stmt.setDate(6, Date.valueOf(orcamento.getData_entrada()));
            stmt.setDate(7, Date.valueOf(orcamento.getData_saida()));
            stmt.setString(8, orcamento.getDefeito_relatado());
            stmt.setString(9, orcamento.getDefeito_constatado());
            stmt.setInt(10, orcamento.getDescontos());
            stmt.setDouble(11, orcamento.getValor_final());
            stmt.setBoolean(12, orcamento.getSituacao());
            stmt.setInt(13, orcamento.getCliente().getId_Cli());
            stmt.setInt(14, orcamento.getId_os());
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
                Cliente cliente = new Cliente();
                List<Servico> servico = new ArrayList<>();

                orcamento.setId_os(rs.getInt("id_os"));
                orcamento.setTipo_veiculo(rs.getString("tipo_veiculo"));
                orcamento.setModelo_veiculo(rs.getString("modelo-veiculo"));
                orcamento.setMarca_veiculo(rs.getString("marca_veiculo"));
                orcamento.setAno_veiculo(rs.getString("ano_veiculo"));
                orcamento.setData_entrada(rs.getDate("data_entrada").toLocalDate());
                orcamento.setData_saida(rs.getDate("data-saida").toLocalDate());
                orcamento.setDefeito_relatado(rs.getString("defeito_relatado"));
                orcamento.setDefeito_constatado(rs.getString("defeito_constatado"));
                orcamento.setDescontos(rs.getInt("descontos"));
                orcamento.setValor_final(rs.getDouble("valor_final"));
                orcamento.setSituacao(rs.getBoolean("situacao"));

                //pegando os dados da tabela cliente
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                cliente = clienteDAO.buscar(cliente);

                //pegando os dados da tabela servico
                ServicoDAO servicoDAO = new ServicoDAO();
                servicoDAO.setConnection(connection);
                servico = servicoDAO.buscar(servico);

                orcamento.setCliente(cliente);
                orcamento.setServico(servico);
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
                Cliente cliente = new Cliente();
                List<Servico> servico = new ArrayList<>();

                orcamento.setId_os(rs.getInt("id_os"));
                orcamento.setTipo_veiculo(rs.getString("tipo_veiculo"));
                orcamento.setModelo_veiculo(rs.getString("modelo-veiculo"));
                orcamento.setAno_veiculo(rs.getString("ano_veiculo"));
                orcamento.setMarca_veiculo(rs.getString("marca_veiculo"));
                orcamento.setData_entrada(rs.getDate("data_entrada").toLocalDate());
                orcamento.setData_saida(rs.getDate("data-saida").toLocalDate());
                orcamento.setDefeito_relatado(rs.getString("defeito_relatado"));
                orcamento.setDefeito_constatado(rs.getString("defeito_constatado"));
                orcamento.setDescontos(rs.getInt("descontos"));
                orcamento.setValor_final(rs.getDouble("valor_final"));
                orcamento.setSituacao(rs.getBoolean("situacao"));

                //pegando os dados da tabela cliente
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                cliente = clienteDAO.buscar(cliente);

                //pegando os dados da tabela servico
                ServicoDAO servicoDAO = new ServicoDAO();
                servicoDAO.setConnection(connection);
                servico = servicoDAO.buscar(servico);

                orcamento.setCliente(cliente);
                orcamento.setServico(servico);

                retorno = orcamento;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Map<Integer, ArrayList> listarOrcamentosPorMes() {
        String sql = "SELECT COUNT(id_os), EXTRACT(year from data) AS ano, EXTRACT(month from data) AS mes FROM orcamento GROUP BY ano, mes ORDER BY ano, mes";
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
