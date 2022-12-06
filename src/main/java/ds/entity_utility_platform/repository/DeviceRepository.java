package ds.entity_utility_platform.repository;

import ds.entity_utility_platform.entity.Client;
import ds.entity_utility_platform.entity.Device;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepository extends  AbstractRepository<Device>{
    @Query("SELECT o FROM Device o WHERE o.id_device = ?1")
    Device findDeviceByMyId(Integer myId);

    List<Device> findAllByClient(Client client);
}
