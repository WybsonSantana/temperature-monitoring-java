package br.dev.s2w.jsensors.temperature.monitoring.api.config.web;

import io.hypersistence.tsid.TSID;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;

public class StringToTSIDWebConverter implements Converter<String, TSID> {

    @Override
    public TSID convert(@Nonnull String source) {
        return TSID.from(source);
    }

}
