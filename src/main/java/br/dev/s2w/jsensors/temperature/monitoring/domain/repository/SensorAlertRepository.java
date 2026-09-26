package br.dev.s2w.jsensors.temperature.monitoring.domain.repository;

import br.dev.s2w.jsensors.temperature.monitoring.domain.model.SensorAlert;
import br.dev.s2w.jsensors.temperature.monitoring.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorAlertRepository extends JpaRepository<SensorAlert, SensorId> {
}
