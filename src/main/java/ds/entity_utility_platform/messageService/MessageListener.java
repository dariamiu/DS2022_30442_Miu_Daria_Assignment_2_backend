package ds.entity_utility_platform.messageService;

import ds.entity_utility_platform.dto.DeviceDTO;
import ds.entity_utility_platform.dto.builders.ObjectDTOBuilder;
import ds.entity_utility_platform.entity.Client;
import ds.entity_utility_platform.entity.Device;
import ds.entity_utility_platform.entity.HourlyConsumption;
import ds.entity_utility_platform.notificationService.Notification;
import ds.entity_utility_platform.notificationService.WebSocketController;
import ds.entity_utility_platform.repository.ClientRepository;
import ds.entity_utility_platform.repository.DeviceRepository;
import ds.entity_utility_platform.repository.HourlyConsumptionRepository;
import ds.entity_utility_platform.service.DeviceService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class MessageListener {

    HashMap<Integer,List<CustomMessage>> devices = new HashMap<>();
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    HourlyConsumptionRepository hourlyConsumptionRepository;

    @Autowired
    WebSocketController webSocketController;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DeviceService deviceService;

    public void addHourlyConsumption( Integer deviceId, List<CustomMessage> customMessages ) {
        Float sum = 0.0f;
        for (CustomMessage customMessage : customMessages) {
            sum += customMessage.getConsumption();
        }

        ObjectDTOBuilder objectDTOBuilder = new ObjectDTOBuilder();
        Device device = deviceRepository.findDeviceByMyId(deviceId);
        DeviceDTO deviceDTO = (DeviceDTO) objectDTOBuilder.entityToDTO(device);

        HourlyConsumption hourlyConsumption = new HourlyConsumption();
        hourlyConsumption.setConsumption(sum);
        hourlyConsumption.setDevice(device);
        hourlyConsumption.setTimestamp(customMessages.get(5).getTimestamp().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        hourlyConsumptionRepository.save(hourlyConsumption);

        if( sum > device.getMax_h_cons()) {
            Client client = clientRepository.findByUsername(deviceDTO.getClientName());
            Notification notification = new Notification();
            notification.setMessage("Device " + device.getDescription() + " exceeded the hourly maximum of " + deviceDTO.getMax_h_cons() +"!!"
                + " The consumption was " + sum + "!");
            notification.setDevice(device.getId_device());
            notification.setUser(client.getUsername());
            webSocketController.sendMessage(notification);
        }
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message) {
        System.out.println("message id :" + message.getDevice());
        if (devices.containsKey(message.getDevice())) {
            System.out.println(devices.get(message.getDevice()).size());
            if(devices.get(message.getDevice()).size() == 6) {
                addHourlyConsumption(message.getDevice(), devices.get(message.getDevice()));
                List<CustomMessage> c = new ArrayList<>();
                devices.put(message.getDevice(),c);
            } else {
                List<CustomMessage> newMessageList = devices.get(message.getDevice());
                newMessageList.add(message);
                devices.put(message.getDevice(),newMessageList);
            }
        } else {
            List<CustomMessage> newMessageList = new ArrayList<>();
            newMessageList.add(message);
            devices.put(message.getDevice(), newMessageList);
        }

    }

}
