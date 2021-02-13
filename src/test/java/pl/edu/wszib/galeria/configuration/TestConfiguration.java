
package pl.edu.wszib.galeria.configuration;



import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.galeria.dao.IArtPieceDAO;
import pl.edu.wszib.galeria.dao.IOrderDAO;
import pl.edu.wszib.galeria.dao.IUserDAO;



@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.galeria.controllers",
        "pl.edu.wszib.galeria.services.impl",
        "pl.edu.wszib.galeria.session"
})
public class TestConfiguration {


}
