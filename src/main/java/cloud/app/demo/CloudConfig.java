package cloud.app.demo;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO @Profile("cloud")
@Configuration
public class CloudConfig extends AbstractCloudConfig {
	@Bean
	public DataSource dataSource() {
		return connectionFactory().dataSource();
	}
}