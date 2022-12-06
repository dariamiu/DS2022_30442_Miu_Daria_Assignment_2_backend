package ds.entity_utility_platform.repository;

import ds.entity_utility_platform.entity.Device;
import ds.entity_utility_platform.entity.HourlyConsumption;

import java.util.List;

public interface HourlyConsumptionRepository extends AbstractRepository<ds.entity_utility_platform.entity.HourlyConsumption>{

    List<HourlyConsumption> findAllByDevice(Device device);
}
