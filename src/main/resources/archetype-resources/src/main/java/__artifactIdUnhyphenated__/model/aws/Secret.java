package ${package}.${artifactIdUnhyphenated}.model.aws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class Secret {

    private String username;

    private String password;
}
