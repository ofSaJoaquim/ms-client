package br.com.branetlogistica.msclient.core.context.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.method.HandlerMethod;

import br.com.branetlogistica.msclient.core.context.ContextData;
import br.com.branetlogistica.msclient.core.exceptions.ApiException;
import feign.Util;

public class ContextUtil {

	public static ContextData toContextData(Jwt jwt) {
		boolean isService = jwt.getClaimAsBoolean("is_service");
		return ContextData.builder().tenantId(jwt.getClaimAsString("x-tenant-id"))		
				.userId(Long.parseLong(jwt.getClaimAsString("user-id"))).sub(jwt.getClaimAsString("sub"))
				.centerCoasts(isService?null:jwt.getClaimAsStringList("coast-centers"))
				.service(isService)
				.build();
	}

	public static ContextData toContextData(ContextData contextData, HttpServletRequest request, Object object) {
		String contollerClass = ((HandlerMethod) object).getBeanType().getSimpleName();
		String methodName = ((HandlerMethod) object).getMethod().getName();
		String uri = request.getRequestURL().toString();
		String tenantId = request.getHeader("x-tenant-id");
		String centerCoasId = request.getHeader("center-coast-id");

		if (contextData == null) {

			contextData = ContextData.builder().tenantId(tenantId).method(methodName).className(contollerClass).url(uri)
					.build();

		} else {
			if (!contextData.getTenantId().equals(tenantId))
				throw new ApiException(HttpStatus.BAD_REQUEST, "x-tenant-id invalid", null);

			contextData = ContextData.builder()
					.tenantId(tenantId)
					.method(methodName).className(contollerClass).url(uri)
					.centerCoasts(contextData.getCenterCoasts())
					.sub(contextData.getSub())
					.userId(contextData.getUserId())
					.service(contextData.isService())
					.build();
		}

		if (!Util.isBlank(centerCoasId) && !contextData.isService()) {

			if (contextData.getCenterCoasts() == null || contextData.getCenterCoasts().isEmpty()
					|| !contextData.getCenterCoasts().contains(centerCoasId))
				throw new ApiException(HttpStatus.BAD_REQUEST, "center coast not permited", null);

		

		}
		
		return contextData;

	}
}
