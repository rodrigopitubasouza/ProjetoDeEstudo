package com.estudo.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InexistenteExceptionMapper implements ExceptionMapper<InexistenteException> {

	@Override
	public Response toResponse(InexistenteException exception) {
		return Response.status(Response.Status.NOT_FOUND)
                .header("Motivo: ", exception.getMessage())
                .build();
	}
	
}
