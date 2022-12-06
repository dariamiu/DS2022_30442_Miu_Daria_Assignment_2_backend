package ds.entity_utility_platform.dto;

import java.time.LocalDateTime;

public class HourlyConsumptionDTO {

    private LocalDateTime timestamp;

    private float consumption;

    public HourlyConsumptionDTO(LocalDateTime timestamp, float consumption) {
        this.timestamp = timestamp;
        this.consumption = consumption;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }
}
