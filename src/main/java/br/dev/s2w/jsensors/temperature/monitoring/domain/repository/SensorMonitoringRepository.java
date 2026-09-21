package br.dev.s2w.jsensors.temperature.monitoring.domain.repository;

import br.dev.s2w.jsensors.temperature.monitoring.domain.model.SensorId;
import br.dev.s2w.jsensors.temperature.monitoring.domain.model.SensorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorMonitoringRepository extends JpaRepository<SensorMonitoring, SensorId> {
}
