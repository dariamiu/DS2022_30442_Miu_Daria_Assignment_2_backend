package ds.entity_utility_platform.messageService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageCounter {

    private Integer deviceId;
    private Integer count;

}
