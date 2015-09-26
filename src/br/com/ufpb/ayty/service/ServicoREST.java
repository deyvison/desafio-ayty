package br.com.ufpb.ayty.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import br.com.ufpb.ayty.GerenciarBD;
import br.com.ufpb.ayty.controllers.BeneficiarioController;
import br.com.ufpb.ayty.controllers.UsuarioController;
import br.com.ufpb.ayty.entity.Beneficiario;
 
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
			String login = jsonobj.getString("login");
			String senha = jsonobj.getString("senha");
			
			if (UsuarioController.getInstance().validarUsuario(login, senha)){
				return "Usuário autenticado!";
			}
			
			return "Login e senha não conferem!";
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
			data_de_nascimento = jsonobj.getString("data_de_nasc");
			nacionalidade = jsonobj.getString("nacionalidade");
			estado_nasc = jsonobj.getString("estado_nasc");
			cidade_nasc = jsonobj.getString("cidade_nasc");
			sexo = jsonobj.getString("sexo");
			cpf = jsonobj.getString("cpf");
			rg = jsonobj.getString("rg");
			
			
			
			BeneficiarioController.getInstance().cadastrarBeneficiario(new Beneficiario(nome, estado_civil, data_de_nascimento, 
					nacionalidade, estado_nasc, cidade_nasc, sexo, cpf, rg));
			
			return "Beneficiario cadastrado com sucesso!";
			
		} catch (Exception e) {
			return "Erro ao tentar cadastrar beneficiario: "+ e.getMessage();
		}
		
	}
	
	
	@POST
	@Path("/consultarbeneficiario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String consultarBeneficiario(String dados){
		try {
			JSONObject jsonobj = new JSONObject(dados);
			String cpf = jsonobj.getString("pesquisa");
			return this.gerenciador.consultarBeneficiario(cpf);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		
	}
	
	@POST
	@Path("/removerbeneficiario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerBeneficiario(String dados){
		try {
			JSONObject jsonobj = new JSONObject(dados);
			String cpf = jsonobj.getString("cpf");
			return this.gerenciador.removerBeneficiario(cpf);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
}