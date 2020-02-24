package it.emix.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import it.emix.integration.dao.TempConverter;

@Configuration
@SpringBootApplication
@IntegrationComponentScan
@EnableScheduling
public class IntegrationApplication {

	public static void main(String[] args) {
	    ConfigurableApplicationContext ctx = SpringApplication.run(IntegrationApplication.class, args);
	    System.out.println("Application is started");
	    TempConverter converter = ctx.getBean(TempConverter.class);
	    float f = converter.fahrenheitToCelcius(80.0f);
	    System.out.println("Response: " + f);
	  }
	
	@Scheduled(cron = "1 * * * * *")
	public void quequeChannel() {
		
		final QueueChannel queueChannel = new QueueChannel();
		queueChannel.send(MessageBuilder.withPayload("Hello world #1").build());
		queueChannel.send(MessageBuilder.withPayload("Hello world #2").build());
		queueChannel.send(MessageBuilder.withPayload("Hello world #3").build());
		System.out.println("queueChannel = " + queueChannel.receive().getPayload());
	}
}
