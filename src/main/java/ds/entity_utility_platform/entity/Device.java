package ds.entity_utility_platform.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_device;

    @Column
    private String description;

    @Column
    private float max_h_cons;

    @Column
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Client client;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HourlyConsumption> hourlyConsumptions;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMax_h_cons() {
        return max_h_cons;
    }

    public void setMax_h_cons(float max_h_cons) {
        this.max_h_cons = max_h_cons;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Device(String description, float max_h_cons, String address, Client client) {
        this.description = description;
        this.max_h_cons = max_h_cons;
        this.address = address;
        this.client = client;
    }

    public Device(){

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getId_device() {
        return id_device;
    }
}
