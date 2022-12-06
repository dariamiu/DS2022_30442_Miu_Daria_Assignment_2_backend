package ds.entity_utility_platform;

import ds.entity_utility_platform.notificationService.Notification;
import ds.entity_utility_platform.notificationService.WebSocketController;
import ds.entity_utility_platform.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EntityUtilityPlatformApplication {

    public static void main(String[] args) {

        SpringApplication.run(EntityUtilityPlatformApplication.class, args);

    }

}
