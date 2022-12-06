package ds.entity_utility_platform.dto;


public class DeviceDTO {

    private String clientName;

    private String description;


    private float max_h_cons;


    private String address;

    private int id_device;

    public int getId_device() {
        return id_device;
    }

    public DeviceDTO(int id_device, String clientName, String description, float max_h_cons, String address) {
        this.id_device = id_device;
        this.clientName = clientName;
        this.description = description;
        this.max_h_cons = max_h_cons;
        this.address = address;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

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
}
