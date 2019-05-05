
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.login2",lazyInit = true)
@SpringBootApplication
public class Application {
  public static void main(String[] args) {

    SpringApplication.run(Application.class,args);
  }
}
