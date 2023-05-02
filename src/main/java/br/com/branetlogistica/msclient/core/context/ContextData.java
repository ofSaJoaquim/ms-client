package br.com.branetlogistica.msclient.core.context;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ContextData {

	private final String centerCoastId;
	private final Long userId;
	private final String sub; 
	private final String tenantId;
	private final String transactionId;	
	private final String method;
	private final String className;
	private final String url;
	
	private final List<String>centerCoasts;
	
	
	
}
