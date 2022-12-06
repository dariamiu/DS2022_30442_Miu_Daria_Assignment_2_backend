package ds.entity_utility_platform.dto.builders;

import ds.entity_utility_platform.dto.ClientDTO;
import ds.entity_utility_platform.dto.DeviceDTO;
import ds.entity_utility_platform.dto.HourlyConsumptionDTO;
import ds.entity_utility_platform.entity.Client;
import ds.entity_utility_platform.entity.Device;
import ds.entity_utility_platform.entity.HourlyConsumption;

public class ObjectDTOBuilder {

    public Object entityToDTO (Object object){
        if (object instanceof Client){
            return new ClientDTO(((Client) object).getUsername(), ((Client) object).getPassword());
        }
        else if (object instanceof Device){
            return new DeviceDTO(((Device) object).getId_device(),((Device) object).getClient().getUsername(), ((Device) object).getDescription(),
                    ((Device) object).getMax_h_cons(),
                    ((Device) object).getAddress());
        }
        else if (object instanceof HourlyConsumption){
            return new HourlyConsumptionDTO(((HourlyConsumption) object).getTimestamp(),
                    ((HourlyConsumption) object).getConsumption()
                    );
        }
        return null;
    }

    public Device deviceDTOToEntity(Client client, String description,float max_h_cons, String address){
        return new Device(description,max_h_cons,address,client);
    }
}
