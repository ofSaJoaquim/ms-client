package br.com.branetlogistica.msclient.core.context;

public class Context {

	private static ThreadLocal<ContextData> contextData = new InheritableThreadLocal<>();

	public static ContextData getContextData() {
		return contextData.get();
	}

	public static void setContextData(ContextData contextToken) {
		Context.contextData.set(contextToken);
	}

	public static void clear() {		
		contextData.set(null);
	}
}