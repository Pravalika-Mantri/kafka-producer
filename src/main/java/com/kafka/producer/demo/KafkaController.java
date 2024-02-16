package com.kafka.producer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
	
	@Autowired
	private SpringKafkaProducer producer;
	
	@GetMapping("send/{msg}")
	public ResponseEntity<?> send(@PathVariable("msg") String message){
		for(int i=0;i<10000;i++) {
		producer.sendMessagge(message+""+i);
		}
		return ResponseEntity.ok("Message Sent");
	}
	
	
	@PostMapping("customer")
	public ResponseEntity<?> customer(@RequestBody Customer customer){
		producer.sendCustomer(customer);
		return ResponseEntity.ok("Customer saved"); 
	}

}
