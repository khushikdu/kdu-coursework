package springexercise.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import springexercise.model.Tyre;
@Service

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
