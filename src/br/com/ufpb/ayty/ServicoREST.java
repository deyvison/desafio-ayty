package br.com.ufpb.ayty;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
 
@Path("/app")
public class ServicoREST {
	
	private GerenciarBD gerenciador = new GerenciarBD();
	/**
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}*/
	
	
	@GET
	@Path("/helloworld")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMsg() {
 
		return "Hello World!";
	}
	
	
	@POST
	@Path("/consultarlogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String verificaUsuario(String dados){
		
		try {
			
			JSONObject jsonobj = new JSONObject(dados);
			String nome = jsonobj.getString("login");
			String senha = jsonobj.getString("senha");
			
			return this.gerenciador.validarUsuario(nome, senha);
		} catch (Exception e) {
			return "Erro ao tentar validar usuário: "+ e.getMessage();
		}
	}
	
	@POST
	@Path("/cadastrarbeneficiario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String cadastrarBeneficiario(String dados){
		
		try {
			JSONObject jsonobj = new JSONObject(dados);
			String nome, estado_civil,data_de_nascimento,nacionalidade,estado_nasc,cidade_nasc,sexo,cpf,rg;
			
			nome = jsonobj.getString("nome");
			estado_civil = jsonobj.getString("estado_civil");
			data_de_nascimento = jsonobj.getString("data_de_nascimento");
			nacionalidade = jsonobj.getString("nacionalidade");
			estado_nasc = jsonobj.getString("estado_nasc");
			cidade_nasc = jsonobj.getString("cidade_nasc");
			sexo = jsonobj.getString("sexo");
			cpf = jsonobj.getString("cpf");
			rg = jsonobj.getString("rg");
			
			return gerenciador.cadastrarBeneficiario(nome, estado_civil, data_de_nascimento, nacionalidade, estado_nasc, cidade_nasc, sexo, cpf, rg);

		} catch (Exception e) {
			return "Erro ao tentar cadastrar beneficiario: "+ e.getMessage();
		}
		
	}
	
}