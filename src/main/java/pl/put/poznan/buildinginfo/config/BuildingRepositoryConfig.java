package pl.put.poznan.buildinginfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.put.poznan.buildinginfo.logic.BuildingRepository;

@Configuration
public class BuildingRepositoryConfig {
    @Bean
    public BuildingRepository buildingRepository() {
        return BuildingRepository.getInstance();
    }
}
