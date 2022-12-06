package ds.entity_utility_platform.repository;

import ds.entity_utility_platform.entity.Client;

public interface ClientRepository extends AbstractRepository<Client>{
    Client findByUsername(String username);

}
