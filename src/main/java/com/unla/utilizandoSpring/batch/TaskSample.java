package com.unla.utilizandoSpring.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSample {

	@Scheduled(fixedDelay=500) // solo dice "llamame este método en este horario"
	/* @Scheduled(fixedRate = 1000) // cada 1 segundo exacto

	 * @Scheduled(fixedDelay = 5000) // 5 seg después de terminar

	 * @Scheduled(cron = "0 0 2 * * ?") // todos los días a las 2:00 AM

	 * 
	 * */
	public void runjob() {
		
		System.out.println("Hello!");
	}
}
