package com.cpm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cpm.dto.CarTrackingInfo;
import com.cpm.sim.service.SimOrderSource;
import com.cpm.sim.service.SimService;

@RestController
@EnableBinding(SimOrderSource.class)
public class SimController {

	@Autowired
	private SimOrderSource simOrderSource;
	@Autowired
	private SimService simService;
	@Autowired
    private DiscoveryClient discoveryClient;
	 @Autowired
	    private LoadBalancerClient loadBalancer;
	
	static Logger logger = LoggerFactory.getLogger(SimController.class);

	@GetMapping(value = "/getDetail/{idSim}")
	public String getStudents(@PathVariable String idSim) {
	
		return simService.getSimDetails(idSim);
	}
	
	
	  @PostMapping("/order")
	    @ResponseBody
	    public String orderSim(@RequestBody CarTrackingInfo sim){
		  logger.info("Se procede a guardar en la cola la informacion de la sim {}",sim);
	    	try {
	    		simOrderSource.simSend().send(MessageBuilder.withPayload(sim).build());
	    	
	    	}catch (Exception e) {
	    		 logger.error("Error al guardar en la cola",e);
			}
	    	
	        return "informacion de la sim enviada";
	    }
	  
	  @ResponseBody
	    @RequestMapping(value = "/testCallAbcService", method = RequestMethod.GET)
	    public String showFirstService() {
	 
	        String serviceId = "producer-to-rabbit-simInformation".toLowerCase();
	 
	        // eureka.client.fetchRegistry=true
	        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
	 
	        if (instances == null || instances.isEmpty()) {
	            return "No instances for service: " + serviceId;
	        }
	        String html = "<h2>Instances for Service Id: " + serviceId + "</h2>";
	 
	        for (ServiceInstance serviceInstance : instances) {
	            html += "<h3>Instance :" + serviceInstance.getUri() + "</h3>";
	        }
	 
	      
	 
	        html += "<br><h4>Call /hello of service: " + serviceId + "</h4>";
	 
	        try {
	            // May be throw IllegalStateException (No instances available)
	            ServiceInstance serviceInstance = this.loadBalancer.choose(serviceId);
	 
	            html += "<br>===> Load Balancer choose: " + serviceInstance.getUri();
	 
	            String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hello";
	 
	            html += "<br>Make a Call: " + url;
	            html += "<br>";
	 
	           
	 
	            html += "<br>Result:  REsultado" ;
	        } catch (IllegalStateException e) {
	            html += "<br>loadBalancer.choose ERROR: " + e.getMessage();
	            e.printStackTrace();
	        } catch (Exception e) {
	            html += "<br>Other ERROR: " + e.getMessage();
	            e.printStackTrace();
	        }
	        return html;
	    }
}
