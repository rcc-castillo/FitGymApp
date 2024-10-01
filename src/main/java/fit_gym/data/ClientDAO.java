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
        String query = "SELECT * FROM client ORDER BY id";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    clients.add(new Client(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("lastName"),
                            resultSet.getInt("membership")));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return clients;
    }

    @Override
    public Boolean searchClientById(Client client) {
        String query = "SELECT * FROM client WHERE id = ?";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, client.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        client.setName(resultSet.getString("name"));
                        client.setLastName(resultSet.getString("lastName"));
                        client.setMembership(resultSet.getInt("membership"));
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por id: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean addClient(Client client) {
        String query = "INSERT INTO client (name, lastName, membership) VALUES (?, ?, ?)";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, client.getName());
                preparedStatement.setString(2, client.getLastName());
                preparedStatement.setInt(3, client.getMembership());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean updateClient(Client client) {
        if (!searchClientById(client)) return false;
        String query = "UPDATE client SET name = ?, lastName = ?, membership = ? WHERE id = ?";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, client.getName());
                preparedStatement.setString(2, client.getLastName());
                preparedStatement.setInt(3, client.getMembership());
                preparedStatement.setInt(4, client.getId());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean deleteClient(Client client) {
        if (!searchClientById(client)) return false;
        String query = "DELETE FROM client WHERE id = ?";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, client.getId());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
        return false;
    }
}
