package ${package}.${artifactIdUnhyphenated}.config;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import ${package}.${artifactIdUnhyphenated}.model.aws.Secret;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class ${artifactIdCamelCase}SpringConfig {

    @Autowired
    private AWSCredentialsProvider awsCredentialsProvider;

    @Value("${spring.datasource.secret-name}")
    private String secretName;

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {

        return new DefaultAWSCredentialsProviderChain();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {

        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() throws IOException {

        AWSSecretsManager client  = AWSSecretsManagerClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(Regions.US_EAST_1)
                .build();

        String secretString = "";
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        if (getSecretValueResult.getSecretString() != null) {
            secretString = getSecretValueResult.getSecretString();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Secret secret = objectMapper.readValue(secretString, Secret.class);

        return dataSourceProperties()
                .initializeDataSourceBuilder()
                .username(secret.getUsername())
                .password(secret.getPassword())
                .build();
    }
}

