package br.com.branetlogistica.msclient.core.security.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContextToken {

	private String tenantyId;
	private Long userId;
	private String userUUID;
	private String userName;
	private List<String> coastCenters;
	
}
	
	