package ds.entity_utility_platform.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hourly_consumption")
public class HourlyConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_hc;

    @Column
    private LocalDateTime timestamp;

    @Column
    private float consumption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_device")
    private Device device;

    public Integer getId_hc() {
        return id_hc;
    }

    public void setId_hc(Integer id_hc) {
        this.id_hc = id_hc;
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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
