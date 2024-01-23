package springexercise.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springexercise.model.Tyre;
@Configuration

public class TyreService {
    @Bean("mrf")
    @Primary
    public Tyre generateMRFTyre(){
        return new Tyre("MRF",360.0);
    }
    @Bean("bridgestone")
    public Tyre generateBridgestoneTyre(){
        return new Tyre("Bridgestone",4000.0);
    }
}