package fit_gym.data;
import fit_gym.domain.Client;
import java.util.List;

public interface IClientDAO {
    List<Client> getAllClients();

    Boolean searchClientById(Client client);
    Boolean addClient(Client client);
    Boolean updateClient(Client client);
    Boolean deleteClient(Client client);


}
