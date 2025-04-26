package br.dev.s2w.jsensors.temperature.monitoring.api.controller;

import br.dev.s2w.jsensors.temperature.monitoring.api.model.SensorAlertInput;
import br.dev.s2w.jsensors.temperature.monitoring.api.model.SensorAlertOutput;
import br.dev.s2w.jsensors.temperature.monitoring.domain.model.SensorAlert;
import br.dev.s2w.jsensors.temperature.monitoring.domain.model.SensorId;
import br.dev.s2w.jsensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensors/{sensorId}/alert")
@RequiredArgsConstructor
public class SensorAlertController {

    private final SensorAlertRepository sensorAlertRepository;

    @GetMapping
    public SensorAlertOutput getDetail(@PathVariable TSID sensorId) {
        SensorAlert sensorAlert = sensorAlertRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return SensorAlertOutput.builder()
                .id(sensorAlert.getId().getValue())
                .maxTemperature(sensorAlert.getMaxTemperature())
                .minTemperature(sensorAlert.getMinTemperature())
                .build();
    }

    @PutMapping
    public SensorAlertOutput createOrUpdate(@PathVariable TSID sensorId, @RequestBody SensorAlertInput input) {
        SensorAlert sensorAlert = findByIdOrDefault(sensorId);
        sensorAlert.setMaxTemperature(input.getMaxTemperature());
        sensorAlert.setMinTemperature(input.getMinTemperature());

        sensorAlertRepository.saveAndFlush(sensorAlert);

        return SensorAlertOutput.builder()
                .id(sensorAlert.getId().getValue())
                .maxTemperature(sensorAlert.getMaxTemperature())
                .minTemperature(sensorAlert.getMinTemperature())
                .build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable TSID sensorId) {
        SensorAlert sensorAlert = sensorAlertRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        sensorAlertRepository.delete(sensorAlert);
    }

    private SensorAlert findByIdOrDefault(TSID sensorId) {
        return sensorAlertRepository.findById(new SensorId(sensorId))
                .orElse(SensorAlert.builder()
                        .id(new SensorId(sensorId))
                        .maxTemperature(null)
                        .minTemperature(null)
                        .build());
    }

}
