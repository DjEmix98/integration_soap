package it.emix.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;

@IntegrationComponentScan
public class QuequeChannel {

	public static void main(String[] args) {
		final QueueChannel queueChannel = new QueueChannel();
		queueChannel.send(MessageBuilder.withPayload("Hello world #1").build());
		queueChannel.send(MessageBuilder.withPayload("Hello world #2").build());
		queueChannel.send(MessageBuilder.withPayload("Hello world #3").build());
		System.out.println("queueChannel = " + queueChannel.receive(10).getHeaders());
	}
}
