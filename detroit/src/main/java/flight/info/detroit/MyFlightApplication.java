package flight.info.detroit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class MyFlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFlightApplication.class, args);


	}
}

