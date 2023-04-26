package br.com.branetlogistica.msclient.modules.database.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseRequest {

	@NotNull
	@NotEmpty
	private String login;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	private String defaultDataBase;

	@NotNull
	@NotEmpty
	private String driver;

	@NotNull
	@NotEmpty
	private String url;

}
