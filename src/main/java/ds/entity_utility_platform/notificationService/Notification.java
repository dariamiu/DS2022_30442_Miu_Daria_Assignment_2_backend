package ds.entity_utility_platform.notificationService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {

    private String message;

    private Integer device;

    private String user;

}
