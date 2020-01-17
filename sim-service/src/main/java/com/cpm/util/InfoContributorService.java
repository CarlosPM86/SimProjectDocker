package com.cpm.util;

import java.util.Collections;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class InfoContributorService implements InfoContributor {

	@Override
	public void contribute(Info.Builder builder) {
		builder.withDetail("Information",
				Collections.singletonMap("description", "Publica la informacion de las sim a la cola rabbit MQ"));
	}

}
