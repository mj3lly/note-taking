package com.ota.notetaking.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private int responseCode;

    private String errorMessage;

}
