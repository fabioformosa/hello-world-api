package it.fabioformosa.helloworldapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@Slf4j
public class HelloWorldController {

    @Value("${envName: undefined}")
    private String envName;

    @GetMapping("/")
    public String sayHello() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        String greetingMessage = String.format("hello world, by the host %s running in the %s env", hostname, envName);
        log.info("Saying... {}", greetingMessage);
        return greetingMessage;
    }

}
