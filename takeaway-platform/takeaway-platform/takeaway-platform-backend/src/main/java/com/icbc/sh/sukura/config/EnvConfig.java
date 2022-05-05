package com.icbc.sh.sukura.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "env")
public class EnvConfig {
    private String imagePrefix;
}
