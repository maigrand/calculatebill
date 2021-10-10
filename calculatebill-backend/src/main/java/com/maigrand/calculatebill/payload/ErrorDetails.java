package com.maigrand.calculatebill.payload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Collection;

@Getter
public class ErrorDetails {

    @ApiModelProperty("${model.timestamp}")
    protected final String timestamp;

    @ApiModelProperty("${model.status}")
    protected final int status;

    @ApiModelProperty("${model.error}")
    protected final String error;

    @ApiModelProperty("${model.message}")
    protected final Object message;

    @ApiModelProperty("${model.path}")
    protected final String path;

    public ErrorDetails(HttpStatus status, Collection<String> messages, String path) {
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = messages.size() == 1
                ? messages.stream().findFirst().get()
                : messages;
        this.path = path;
    }
}
