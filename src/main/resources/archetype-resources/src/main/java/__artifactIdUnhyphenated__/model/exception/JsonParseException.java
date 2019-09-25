package ${package}.${artifactIdUnhyphenated}.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class JsonParseException extends RuntimeException {

    public JsonParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
