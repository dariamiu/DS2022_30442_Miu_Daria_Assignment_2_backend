package ds.entity_utility_platform.dto;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DailyConsumptionDTO {

    private Integer idDevice;

    private String date;

    public Integer getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(Integer idDevice) {
        this.idDevice = idDevice;
    }

    public DailyConsumptionDTO() {
    }

    public DailyConsumptionDTO(Integer idDevice, String date) {
        this.idDevice = idDevice;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
