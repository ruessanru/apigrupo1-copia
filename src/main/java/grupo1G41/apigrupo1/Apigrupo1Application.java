package grupo1G41.apigrupo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.modelmapper.ModelMapper;
import grupo1G41.apigrupo1.utils.AppContexto;

@SpringBootApplication
public class Apigrupo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Apigrupo1Application.class, args);

		System.out.println("Api trabajando...");
	}

	@Bean

	   public ModelMapper  modelmapper(){

		  ModelMapper modelmapper = new ModelMapper();

		  return modelmapper;


	}

	@Bean

	   public BCryptPasswordEncoder bCryptPasswordEncoder(){
	       return new BCryptPasswordEncoder();

    }

	@Bean

		public AppContexto appContexto(){

			return new  AppContexto();


	}
}