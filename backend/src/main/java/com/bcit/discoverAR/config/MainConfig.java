package com.bcit.discoverAR.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AuditingConfig.class, DataSourceConfig.class, WebMvcConfig.class, WebSecurityConfig.class })
public class MainConfig {
}
