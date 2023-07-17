package vaccination;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ui.Menu;

@ComponentScan(basePackages = {
		"dao",
		"models",
		"services",
		"ui",
		"utility",
		})
@Configuration
public class VaccinationApplication {
	private static final ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(VaccinationApplication.class);
	public static void main(String[] args) {
		Menu menu = context.getBean(Menu.class);
		menu.menu();
		context.close();
	}
	public static ConfigurableApplicationContext getContext() {
		return context;
	}
}
