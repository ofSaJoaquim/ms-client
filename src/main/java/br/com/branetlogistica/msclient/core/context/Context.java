package br.com.branetlogistica.msclient.core.context;

import br.com.branetlogistica.msclient.core.security.dto.ContextToken;

public class Context {

	private static ThreadLocal<ContextToken> contextToken = new InheritableThreadLocal<>();

	public static ContextToken getContextToken() {
		return contextToken.get();
	}

	public static void setContextToken(ContextToken contextToken) {
		Context.contextToken.set(contextToken);
	}

	public static void clear() {		
		contextToken.set(null);
	}
}