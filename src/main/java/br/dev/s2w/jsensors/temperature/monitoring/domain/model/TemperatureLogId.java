package br.dev.s2w.jsensors.temperature.monitoring.domain.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class TemperatureLogId implements Serializable {

    private UUID value;

    public TemperatureLogId(UUID value) {
        this.value = value;
    }

    public TemperatureLogId(String value) {
        this.value = UUID.fromString(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
