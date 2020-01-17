package com.cpm.sim.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SimOrderSource {
	
	@Output("simChannel")
    MessageChannel simSend();
}
