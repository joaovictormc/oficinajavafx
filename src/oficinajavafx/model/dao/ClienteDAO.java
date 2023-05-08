package oficinajavafx.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oficinajavafx.model.domain.Cliente;



public class ClienteDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes(nome, endereco, telefone, cpf, email) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEmail());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, endereco = ?, telefone = ?, cpf = ?, email = ? WHERE id_cli = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEmail());
            stmt.setInt(6, cliente.getId_Cli());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean excluir(Cliente cliente) {
        String sql = "DELETE FROM clientes WHERE id_cli = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId_Cli());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Cliente> listar() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> retorno = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_Cli(rs.getInt("id_cli"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                retorno.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public Cliente buscar(Cliente cliente) {
        String sql = "SELECT * FROM clientes WHERE id_cli = ?";
        Cliente retorno = new Cliente();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId_Cli());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                retorno = cliente;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
