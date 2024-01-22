package springexercise.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import springexercise.model.Speaker;
@Service
public class SpeakerService {
    @Bean("sony")
    @Primary
    public Speaker getSonySpeaker(){
        return new Speaker("Sony",4400.0);
    }
    @Bean("bose")
    public Speaker getBoseSpeaker(){
        return new Speaker("Bose",5600.0);
    }
}
