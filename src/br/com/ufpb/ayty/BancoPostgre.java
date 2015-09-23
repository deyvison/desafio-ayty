package br.com.ufpb.ayty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoPostgre {
	
	private Connection conexao;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String driver = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost:5432/Autenticacao";
	private String usuario = "postgres";
	private String senha = "admin";
	
	public BancoPostgre(){
		this.conexao = null;
		this.pstmt = null;
		this.rs = null;
		
	}
	
	public void abrirConexao() throws ClassNotFoundException, SQLException{
		try {
			Class.forName(driver);
			this.conexao = DriverManager.getConnection(url,usuario,senha);
			System.out.println("Conectado ao banco!"); 
		} catch (Exception e) {
			System.out.println("Erro ao conectar ao banco!");
		}
	}
	
	public void fecharConexao() {
		try {
			if(this.conexao != null)
				conexao.close();
			
			if(this.pstmt != null)
				pstmt.close();
			
			if(rs != null)
				rs.close();
			System.out.println("Conexão com o banco fechada!");
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexão com o banco");
		}
		
		
	}
	
	public String validarUsuario(String nome,String senha){
		
		try {
			
			/** String sql = "SELECT * FROM suaTabela where suaColunaVarchar = ? and suaColunaInt = ? ";             
				PreparedStatement ps = c.prepareStatement(sql);       
				//Aqui você seta os valores dos ?     
				ps.setString(1, "string");     
				ps.setInt(2, 10);     
				ResultSet rs = ps.executeQuery(); */
			
			this.abrirConexao();
			String consulta = "select * from usuarios where login = ? and senha = ?";
			pstmt = this.conexao.prepareStatement(consulta);
			pstmt.setString(1, nome); 
			pstmt.setString(2, senha); 
			rs = pstmt.executeQuery();
			
			String nomeRetornado = null; 
			String senhaRetornada = null;
			
			
			while(rs.next()){
				nomeRetornado = rs.getString("login");
				senhaRetornada = rs.getString("senha");
			}
			this.fecharConexao();
			
		//	if(nomeRetornado.equals(nome) && senhaRetornada.equals(senha)){
			//	return "Usuário autenticado!";
			//}
			
			if(nomeRetornado != null && senhaRetornada != null)
				return "Usuário autenticado!";
			else
				return "Usuário e/ou senha inválidos";
			
		} catch (Exception e) {
			this.fecharConexao();
			return "Erro ao validar usuário: "+e.getMessage();
		}
	}

	public String cadastrarBeneficiario(String nome,String estado_civil,String data_de_nascimento,
			String nacionalidade,String estado_nasc,String cidade_nasc,String sexo,String cpf,String rg){
		
		try {
			if(this.validarBeneficiario(cpf, rg)){
				this.abrirConexao();
				String consulta = "insert into beneficiario values(?,?,?,?,?,?,?,?,?)";
				pstmt = this.conexao.prepareStatement(consulta);
				
				pstmt.setString(1, nome);
				pstmt.setString(2, estado_civil);
				pstmt.setString(3, data_de_nascimento);
				pstmt.setString(4, nacionalidade);
				pstmt.setString(5, estado_nasc);
				pstmt.setString(6, cidade_nasc);
				pstmt.setString(7, sexo);
				pstmt.setString(8, cpf);
				pstmt.setString(9, rg);
				
				int retorno = pstmt.executeUpdate();
				
				this.fecharConexao();
				
				if(retorno > 0){
					return "Beneficiario cadastrado com sucesso!";
				}
				return "Erro ao cadastrar beneficiario"; // adicionar chave primária e mudar mensagem para : beneficiario já existente
			}else{
				return "Já existe um beneficiário com esse cpf/rg cadastrado";
			}
			
			
			
		} catch (Exception e) {
			this.fecharConexao();
			return "Erro ao cadastrar beneficiario: "+e.getMessage();
			
		}
	}
	
	public boolean validarBeneficiario(String cpf, String rg){
		try {
			this.abrirConexao();
			String consulta = "select * from beneficiario where cpf = ?";
			pstmt = this.conexao.prepareStatement(consulta);
			pstmt.setString(1, cpf); 
			rs = pstmt.executeQuery();
			
			String cpfRetornado = null; 
			String rgRetornado = null;
			
			while(rs.next()){
				cpfRetornado = rs.getString("cpf");
			}
			
			consulta = "select * from beneficiario where rg = ?";
			pstmt = this.conexao.prepareStatement(consulta);
			pstmt.setString(1, rg); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				rgRetornado = rs.getString("rg");
			}
			
			this.fecharConexao();
			
			if(cpfRetornado != null){
				return false; // já existe cpf no banco
			}else if(rgRetornado != null){
				return false; // já existe rg no banco
			}else{
				return true; // nao existem nem rg nem cpf no banco
			}
			
		} catch (Exception e) {
			this.fecharConexao();
		}
		
		return false;
	}

	public String consultarBeneficiario(String cpfPesquisa) {
		try {
			this.abrirConexao();
			String consulta = "select * from beneficiario where cpf = ?";
			pstmt = this.conexao.prepareStatement(consulta);
			pstmt.setString(1, cpfPesquisa); 
			rs = pstmt.executeQuery();
			
			String nome = null, estado_civil = null, data_de_nasc = null, nacionalidade = null, 
			estado_nasc = null, cidade_nasc = null, sexo = null, rg = null, cpf = null;
			
			
			
			while(rs.next()){
				nome = rs.getString("nome");
				estado_civil = rs.getString("estado_civil");
				data_de_nasc = rs.getString("data_de_nasc");
				nacionalidade = rs.getString("nacionalidade");
				estado_nasc = rs.getString("estado_nasc");
				cidade_nasc = rs.getString("cidade_nasc");
				sexo = rs.getString("sexo");
				cpf = rs.getString("cpf");
				rg = rs.getString("rg");
			}
			this.fecharConexao();
			if(cpf == null){
				return "Cpf inexistente.";
			}else{
				return "Nome: "+nome+" - Estado civil: "+estado_civil+" - "+"Data de nascimento: "+data_de_nasc+" - "
			+"Nacionalidade: "+nacionalidade+" - "+"Estado de nascimento: "+estado_nasc+" - "+"Cidade de nascimento: "+cidade_nasc+" - "
			+"Sexo: "+sexo+" - "+" cpf: "+cpf+" - "+"Rg: "+rg;
						
			}
			
			
		} catch (Exception e) {
			this.fecharConexao();
			return "Erro ao pesquisar beneficiario";
		}
		
	}

	public String removerBeneficiario(String cpf) {
		try {
			this.abrirConexao();
			String consulta = "delete from beneficiario where cpf = ?";
			pstmt = this.conexao.prepareStatement(consulta);
			pstmt.setString(1, cpf);
			int retorno = pstmt.executeUpdate();
			this.fecharConexao();
			System.out.println(retorno);
			if(retorno > 0){
				return "Beneficiário removido!";
			}
			return "CPF não cadastrado no sistema"; // adicionar chave primária e mudar mensagem para : beneficiario já existente
		} catch (Exception e) {
			this.fecharConexao();
			return "Erro ao remover beneficiário";
		}
		
	
		
		
	}
}
