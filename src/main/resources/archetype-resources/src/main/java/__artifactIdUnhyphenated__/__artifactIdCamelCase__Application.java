#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.${artifactIdUnhyphenated};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ${artifactIdCamelCase}Application {

    public static void main(String[] args) {
        SpringApplication.run(${artifactIdCamelCase}Application.class, args);
    }

}
