package lk.dialog.mou.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

//@EnableEurekaClient
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class})
@ComponentScan(basePackages = {"lk.dialog.mou"})
public class MOUBudgetServiceApplication {

	private final static Logger logger = LoggerFactory.getLogger(MOUBudgetServiceApplication.class);

	@PostConstruct
	public void postConstruct() {
		Runtime.getRuntime().addShutdownHook(new Thread(() ->
				logger.debug("!!!!!!!!!!! application shutdown !!!!!!!!!!!")
		));
	}

	public static void main(String[] args) {
		SpringApplication.run(MOUBudgetServiceApplication.class, args);
	}
}
