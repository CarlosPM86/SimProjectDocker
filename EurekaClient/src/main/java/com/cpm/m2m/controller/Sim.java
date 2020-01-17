package com.cpm.m2m.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cpm.m2m.dto.CarTrackingInfo;
import com.cpm.m2m.service.ManagementService;

import brave.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RefreshScope
@RestController
@Api(value = "Sim microservice", description = "Sim services")
public class Sim {
	@Value("${server.port}")
	private Integer port;
	@Autowired
    private DiscoveryClient discoveryClient;
	
	@Autowired
	private Tracer tracer;
 
    @Autowired
    private LoadBalancerClient loadBalancer;
	
	@Autowired
	private ManagementService managementService;
	static Logger logger = LoggerFactory.getLogger(Sim.class);
	
	@PostMapping(value = "/sendInfo/{gates}/{cars}")
	@ApiOperation(value = "sendInfo", notes = "sendInfo" )
    public String home(@RequestBody @Valid CarTrackingInfo params, @PathVariable int gates,  @PathVariable int cars) {
		tracer.currentSpan().tag("info.gates", "Simulacion de gestion de coches gates "+ gates + " cars  "+cars);
		logger.info("Simulacion de gestion de coches gates {} cars {} ",gates,cars);
return managementService.sendInfoSim(gates,cars);
    }
	
	
	 @ResponseBody
	    @RequestMapping(value = "/service-instances/balancer/{instance}", method = RequestMethod.GET)
	    public String showFirstService(@PathVariable String instance) {
	 
	        //String serviceId = "producer-to-rabbit-simInformation".toLowerCase();
	 
	        // eureka.client.fetchRegistry=true
	        List<ServiceInstance> instances = this.discoveryClient.getInstances(instance);
	 
	        if (instances == null || instances.isEmpty()) {
	            return "No instances for service: " + instance;
	        }
	        String html = "<h2>Instances for Service Id: " + instance + "</h2>";
	 
	        for (ServiceInstance serviceInstance : instances) {
	            html += "<h3>Instance :" + serviceInstance.getUri() + "</h3>";
	        }
	 
	      
	 
	        html += "<br><h4>Call /hello of service: " + instance + "</h4>";
	 
	        try {
	            // May be throw IllegalStateException (No instances available)
	            ServiceInstance serviceInstance = this.loadBalancer.choose(instance);
	 
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
	
	 
	 @RequestMapping("/service-instances/{applicationName}")
		public List<ServiceInstance> serverInstances (@PathVariable String applicationName) {
			return this.discoveryClient.getInstances(applicationName);
		}
	 
	 
	 @RequestMapping("/getPort")
		public Integer getport () {
			return port;
		}
	 
	
	
}
