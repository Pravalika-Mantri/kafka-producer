package com.kafka.producer.demo;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


@Service
public class SpringKafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendMessagge(String message) {
		CompletableFuture<SendResult<String,Object>> f = kafkaTemplate.send("Topic7",message);
		f.whenComplete((res,ex)->{
			if(ex == null) {
				System.out.println("Message "+message+""
						+ "published to offset "+res.getRecordMetadata().offset()
						+"and partition "+res.getRecordMetadata().partition()
						);
				
			}else {
				System.out.println("unable to send message due to "+ex.getMessage());
			}
		});
	}

	public void sendCustomer(Customer customer) {
		try {
		CompletableFuture<SendResult<String,Object>> f = kafkaTemplate.send("Customer-Topic11",3,null,customer);
		f.whenComplete((res,ex)->{
			if(ex == null) {
				System.out.println("Customer "+customer.toString()+""
						+ "published to offset "+res.getRecordMetadata().offset()
						+"and partition "+res.getRecordMetadata().partition()
						);
				
			}else {
				System.out.println("unable to send message due to "+ex.getMessage());
			}
		});
		}catch(Exception ex) {
			System.out.println("Error occuered while sending customer "+ex.getMessage());
		}
		
	}

}
