package oficinajavafx.model.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import oficinajavafx.model.domain.Orcamento;
import oficinajavafx.model.domain.Servico;

public class Orcamento2DAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Orcamento orcamento) throws SQLException {
        String sql = "INSERT INTO orcamentos (tipo_veiculo, modelo_veiculo, ano_veiculo, marca_veiculo, avarias, data_entrada, data_saida, defeito_relatado, defeito_constatado, valor_final, descontos, situacao, id_cliente, id_servico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        stmt.setInt(13, orcamento.getId_cli());
        stmt.execute();
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
            stmt.setInt(13, orcamento.getId_cli());
            stmt.setInt(14, orcamento.getId_os());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void excluir(Orcamento orcamento) throws SQLException {
        String sql = "DELETE FROM orcamentos WHERE id_os";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, orcamento.getId_os());
        stmt.execute();
    }

    public List<Orcamento> listar() throws SQLException {
        List<Orcamento> orcamentos = new ArrayList<>();

        String sql = "SELECT * FROM orcamento";
        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Orcamento orcamento = new Orcamento();
            orcamento.setId_os(rs.getInt("id_os"));
            orcamento.setTipo_veiculo(rs.getString("tipo_veiculo"));
            orcamento.setModelo_veiculo(rs.getString("modelo_veiculo"));
            orcamento.setAno_veiculo(rs.getString("ano_veiculo"));
            orcamento.setMarca_veiculo(rs.getString("marca_veiculo"));
            orcamento.setAvarias(rs.getString("avarias"));
            orcamento.setData_entrada(rs.getObject("data_entrada", LocalDate.class));
            orcamento.setData_saida(rs.getObject("data_saida", LocalDate.class));
            orcamento.setDefeito_relatado(rs.getString("defeito_relatado"));
            orcamento.setDefeito_constatado(rs.getString("defeito_constatado"));
            orcamento.setDescontos(rs.getInt("descontos"));
            orcamento.setValor_final(rs.getDouble("valor_final"));
            orcamento.setSituacao(rs.getBoolean("situacao"));
            orcamento.setTipo_servico(getTipoServicoPorIdOrcamento(orcamento.getId_os()));
            orcamento.setId_cli(rs.getInt("id_cli"));

            orcamentos.add(orcamento);
        }

        stmt.close();
        rs.close();

        return orcamentos;
    }

    private List<String> getTipoServicoPorIdOrcamento(int idOrcamento) throws SQLException {
        List<String> tipoServicos = new ArrayList<>();

        String sql = "SELECT tipo_servico FROM orcamento_servico WHERE id_os = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idOrcamento);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            tipoServicos.add(rs.getString("tipo_servico"));
        }

        stmt.close();
        rs.close();

        return tipoServicos;
    }

    public void removerServicoOrcamento(Orcamento orcamento, Servico servico) throws SQLException {
        String sql = "DELETE FROM orcamento_servico WHERE id_orcamento = ? AND id_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orcamento.getId_os());
            stmt.setInt(2, servico.getId_servicos());
            stmt.executeUpdate();
        }
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

    public Map<Integer, Integer> selecionarOSPorMes() {
        Map<Integer, Integer> osPorMes = new HashMap<>();
        String sql = "SELECT \n"
                + "    EXTRACT(MONTH FROM data_saida) AS mes, \n"
                + "    COUNT(*) AS quantidade,\n"
                + "    o.id_os AS id_orcamento,\n"
                + "    c.nome AS nome_cliente,\n"
                + "    s.tipo_servico AS tipo_servico,\n"
                + "    o.valor_final AS valor_final\n"
                + "FROM \n"
                + "    orcamentos o \n"
                + "    JOIN clientes c ON o.id_cliente = c.id_cli\n"
                + "    JOIN servicos s ON o.id_servicos = s.id_servicos\n"
                + "WHERE \n"
                + "    o.situacao = true\n"
                + "GROUP BY \n"
                + "    mes, id_orcamento, nome_cliente, tipo_servico, valor_final\n"
                + "ORDER BY \n"
                + "    mes, id_orcamento;";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int mes = rs.getInt("mes");
                int qtd_orcamentos = rs.getInt("qtd_orcamentos");
                osPorMes.put(mes, qtd_orcamentos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return osPorMes;
    }
}
