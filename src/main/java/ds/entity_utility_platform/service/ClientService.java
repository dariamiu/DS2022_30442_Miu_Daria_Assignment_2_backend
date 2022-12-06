package ds.entity_utility_platform.service;

import ds.entity_utility_platform.dto.ClientDTO;
import ds.entity_utility_platform.dto.DeviceDTO;
import ds.entity_utility_platform.dto.UpdateClientDTO;
import ds.entity_utility_platform.dto.builders.ObjectDTOBuilder;
import ds.entity_utility_platform.entity.Client;
import ds.entity_utility_platform.entity.Device;
import ds.entity_utility_platform.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository customerRepository;

    Logger logger = LoggerFactory.getLogger(ClientService.class);


    public HashMap<String,Object> saveCustomer(ClientDTO client){

        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            Client validCustomer = customerRepository.findByUsername(client.getUsername());
            if (validCustomer != null) {
                response.put("message", "The username "+ client.getUsername() +" is taken ");
                response.put("success", false);
                return response;
            } else {
                String encodedPassword = BCrypt.hashpw(client.getPassword(), BCrypt.gensalt());
                customerRepository.save(new Client(client.getUsername(),encodedPassword));
                response.put("message", "Successful save");
                response.put("success", true);
                logger.info("A new customer has been created with the username : {}",client.getUsername());
                return response;
            }


        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }

    }


    public HashMap<String, Object> loginCustomer(ClientDTO client){

        Client customerFromDB = customerRepository.findByUsername(client.getUsername());
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {

            if (customerFromDB == null) {
                response.put("message", "No user with this username");
                response.put("success", false);
                return response;
            }
            else if (!BCrypt.checkpw(client.getPassword(),customerFromDB.getPassword())) {
                response.put("message", "Wrong password");
                response.put("success", false);
                return response;
            } else {
                response.put("message", "Successful login");
                response.put("success", true);
                logger.info("Admin with username {} logged in", client.getUsername());
                return response;
            }

        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }

    }

    public List<ClientDTO> findAll() {
        List<Client> clients = customerRepository.findAll();
        if(clients.isEmpty()){
            logger.warn("There are no clients in the database");
        }

        List<ClientDTO> clientDTOS = new ArrayList<>();

        ObjectDTOBuilder objectDTOBuilder = new ObjectDTOBuilder();

        for (Client client : clients) {
            clientDTOS.add((ClientDTO) objectDTOBuilder.entityToDTO(client));
        }

        return clientDTOS;
    }

    public HashMap<String,Object> updateClient(UpdateClientDTO clientDTO){
        HashMap<String,Object> response = new HashMap<>();

        try {
            Client client = customerRepository.findByUsername(clientDTO.getOldUsername());
            Client client1 = customerRepository.findByUsername(clientDTO.getNewUsername());
            if(client1 != null) {
                response.put("message","Username taken");
                response.put("success", false);
            } else {
                client.setUsername(clientDTO.getNewUsername());
                if(clientDTO.getPasswordChanged() == 1 ){
                    System.out.println("parolaaaaaaaaaaaa" + clientDTO.getPassword());
                    String encodedPassword = BCrypt.hashpw(clientDTO.getPassword(), BCrypt.gensalt());
                    client.setPassword(encodedPassword);
                }
                customerRepository.save(client);
                response.put("message","Client Changed");
                response.put("success", true);
            }

        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }
        return response;
    }

    public HashMap<String,Object> deleteClient( String username){
        HashMap<String,Object> response = new HashMap<>();
        try{
            Client client = customerRepository.findByUsername(username);
            customerRepository.delete(client);
            response.put("message","Client Deleted");
            response.put("success", true);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }
        return response;
    }
}
