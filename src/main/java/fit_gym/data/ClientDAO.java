package fit_gym.data;

import fit_gym.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import static fit_gym.connection.DbConnection.getConnection;

public class ClientDAO implements IClientDAO {

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = getConnection();
        String query = "SELECT * FROM client ORDER BY id";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setLastName(resultSet.getString("lastName"));
                client.setMembership(resultSet.getInt("membership"));
                clients.add(client);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion a DB: " + e.getMessage());
            }
        }
        return clients;
    }

    @Override
    public Boolean searchClientById(Client client) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = getConnection();
        String query = "SELECT * FROM client WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, client.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client.setName(resultSet.getString("name"));
                client.setLastName(resultSet.getString("lastName"));
                client.setMembership(resultSet.getInt("membership"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por id: " + e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion a DB: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public Boolean addClient(Client client) {
        PreparedStatement preparedStatement;
        Connection connection = getConnection();
        String query = "INSERT INTO client (name, lastName, membership) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3, client.getMembership());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion a DB: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public Boolean updateClient(Client client) {
        PreparedStatement preparedStatement;
        Connection connection = getConnection();
        String query = "UPDATE client SET name = ?, lastName = ?, membership = ? WHERE id = ?";
        if (!searchClientById(client)) {
            return false;
        }
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3, client.getMembership());
            preparedStatement.setInt(4, client.getId());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion a DB: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public Boolean deleteClient(Client client) {
        PreparedStatement preparedStatement;
        Connection connection = getConnection();
        String query = "DELETE FROM client WHERE id = ?";
        if (!searchClientById(client)) {
            return false;
        }
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, client.getId());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion a DB: " + e.getMessage());
            }
        }
        return null;
    }
}
