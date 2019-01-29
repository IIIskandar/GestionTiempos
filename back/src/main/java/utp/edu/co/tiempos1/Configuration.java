
package utp.edu.co.tiempos1;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author C-Lug
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
