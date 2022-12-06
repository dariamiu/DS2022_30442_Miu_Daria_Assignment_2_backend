package ds.entity_utility_platform.notificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    public void sendMessage(Notification notification) {
        simpMessagingTemplate.convertAndSend("/topic/notification/"+ notification.getUser(), notification);
    }
}
