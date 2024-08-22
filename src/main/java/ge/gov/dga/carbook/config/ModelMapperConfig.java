package ge.gov.dga.carbook.config;

import ge.gov.dga.carbook.entity.ServiceRecord;
import ge.gov.dga.carbook.model.dto.serviceRecord.ServiceRecordResponse;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull())
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(ServiceRecord.class, ServiceRecordResponse.class)
                .addMappings(mapper -> mapper.map(src -> src.getCar().getCarId(), ServiceRecordResponse::setCarId));

        return modelMapper;
    }
}
