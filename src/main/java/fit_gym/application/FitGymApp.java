package fit_gym.application;

import fit_gym.data.ClientDAO;
import fit_gym.domain.Client;

import java.util.Scanner;

public class FitGymApp {
    public static void main(String[] args) {
        fitGymApp();
    }

    public static void fitGymApp() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        ClientDAO clientDAO = new ClientDAO();

        while (isRunning) {
            try {
                int option = printMenu(scanner);
                isRunning = executeOption(scanner, option, clientDAO);
            }
            catch (Exception e) {
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    public static int printMenu(Scanner scanner) {
        System.out.print("""
                ***** FitGym App *****
                1. Listar clientes
                2. Buscar cliente por ID
                3. Agregar cliente
                4. Actualizar cliente
                5. Eliminar cliente
                6. Salir
                Ingrese una opción:\s""");
        return Integer.parseInt(scanner.nextLine());
    }

    public static Boolean executeOption(Scanner scanner, int option, ClientDAO clientDAO) {
        switch (option) {
            case 1 -> {
                System.out.println("\t*** Listado de clientes ***");
                for (Client client : clientDAO.getAllClients()) {
                    System.out.println("\t" + client);
                }
            }
            case 2 -> {
                System.out.println("\t*** Buscar cliente por ID ***");
                System.out.print("\tIngrese el ID del cliente: ");
                int id = Integer.parseInt(scanner.nextLine());
                Client client = new Client(id);
                System.out.println(clientDAO.searchClientById(client) ? "\tCliente encontrado: " + client :
                        "\tCliente no encontrado: " + client);
            }
            case 3 -> {
                System.out.println("\t*** Agregar cliente ***");
                System.out.print("\tIngrese el nombre del cliente: ");
                String name = scanner.nextLine();
                System.out.print("\tIngrese el apellido del cliente: ");
                String lastName = scanner.nextLine();
                System.out.print("\tIngrese el membresia del cliente: ");
                int membership = Integer.parseInt(scanner.nextLine());
                Client newClient = new Client(name, lastName, membership);
                System.out.println(clientDAO.addClient(newClient) ? "\tCliente agregado: " + newClient : "\tError al " +
                        "agregar cliente: " + newClient);
            }
            case 4 -> {
                System.out.println("\t*** Actualizar cliente ***");
                System.out.print("\tIngrese el ID del cliente a actualizar: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("\tIngrese el nombre del cliente: ");
                String name = scanner.nextLine();
                System.out.print("\tIngrese el apellido del cliente: ");
                String lastName = scanner.nextLine();
                System.out.print("\tIngrese el membresia del cliente: ");
                int membership = Integer.parseInt(scanner.nextLine());
                Client clientToUpdate = new Client(id, name, lastName, membership);
                System.out.println(clientDAO.updateClient(clientToUpdate) ?
                        "\tCliente actualizado: " + clientToUpdate :
                        "\tError al actualizar cliente: " + clientToUpdate);
            }
            case 5 -> {
                System.out.println("\t*** Eliminar cliente ***");
                System.out.print("\tIngrese el ID del cliente a eliminar: ");
                int id = Integer.parseInt(scanner.nextLine());
                Client clientToDelete = new Client(id);
                System.out.println(clientDAO.deleteClient(clientToDelete) ?
                        "\tCliente eliminado: " + clientToDelete :
                        "\tError al eliminar cliente: " + clientToDelete);
            }
            case 6 -> {
                System.out.println("\t*** Saliendo de FitGym App ***");
                return false;
            }
            default -> System.out.println("\tOpción no válida");
        }
        return true;
    }
}
