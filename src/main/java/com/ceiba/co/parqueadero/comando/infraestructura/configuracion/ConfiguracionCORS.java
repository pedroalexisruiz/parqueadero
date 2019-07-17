package com.ceiba.co.parqueadero.comando.infraestructura.configuracion;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ConfiguracionCORS implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST")
				.allowedOrigins("http://localhost:4200");
	}
}