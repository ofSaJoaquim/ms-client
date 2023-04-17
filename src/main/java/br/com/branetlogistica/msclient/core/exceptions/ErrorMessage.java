package br.com.branetlogistica.msclient.core.exceptions;

import java.time.OffsetDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage {

  @JsonProperty("timestamp")  
  private OffsetDateTime timestamp;

  @JsonProperty("status") 
  private Integer status;

  @JsonProperty("error") 
  private String error;

  @JsonProperty("message")  
  private String message;

  @JsonProperty("path")
  private String path;
  
  @JsonProperty("erros")
  private Map<String,String> errors;

}

