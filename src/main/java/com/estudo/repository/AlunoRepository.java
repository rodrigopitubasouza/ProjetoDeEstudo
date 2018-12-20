package com.estudo.repository;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.estudo.entity.Aluno;
import com.estudo.service.AlunoService;
import com.estudo.service.exception.AlunoInexistenteException;

@Path("/alunos")
public class AlunoRepository {
	
	@Inject
	private AlunoService alunoService;
	
	@GET
	@Produces("application/json")
	public List<Aluno> findAllAluno(){
		return alunoService.findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Aluno findAluno(@PathParam("id") Integer id) throws AlunoInexistenteException {
		return alunoService.find(id);
	
	}
	
	@POST
	@Consumes("application/json")
	public Response insertAluno(Aluno aluno) {
		alunoService.insert(aluno);
		return Response.status(201).entity("foi").build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public void updateAluno(@PathParam("id") Integer id,Aluno aluno) throws AlunoInexistenteException {
		aluno.setId(id);
		alunoService.update(aluno);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteAluno(@PathParam("id") Integer id) throws AlunoInexistenteException {
		alunoService.remove(id);
	}
}
