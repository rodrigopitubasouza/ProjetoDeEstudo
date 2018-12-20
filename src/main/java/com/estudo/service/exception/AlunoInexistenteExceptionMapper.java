package com.estudo.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AlunoInexistenteExceptionMapper implements ExceptionMapper<AlunoInexistenteException> {

	@Override
	public Response toResponse(AlunoInexistenteException exception) {
		return Response.status(Response.Status.NOT_FOUND)
                .header("Motivo: ", exception.getMessage())
                .build();
	}
	
}
