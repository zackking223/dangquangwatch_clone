package edu.it10.dangquangwatch.spring.notification;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class NotificationRestController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationBody notificationRequest) {
        notificationRequest.setTimestamp(LocalDateTime.now());
        messagingTemplate.convertAndSend("/topic/notifications", notificationRequest);
        return ResponseEntity.ok("Message sent to WebSocket!");
    }
}
