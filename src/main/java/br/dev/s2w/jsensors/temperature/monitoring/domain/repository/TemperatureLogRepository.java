package br.dev.s2w.jsensors.temperature.monitoring.domain.repository;

import br.dev.s2w.jsensors.temperature.monitoring.domain.model.SensorId;
import br.dev.s2w.jsensors.temperature.monitoring.domain.model.TemperatureLog;
import br.dev.s2w.jsensors.temperature.monitoring.domain.model.TemperatureLogId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureLogRepository extends JpaRepository<TemperatureLog, TemperatureLogId> {

    Page<TemperatureLog> findAllBySensorId(SensorId sensorId, Pageable pageable);

}
