package br.com.caelum.jdbc.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.modelo.Funcionario;

public class FuncionarioDao {
	private Connection connection;
	
	public FuncionarioDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
		public void Adiciona(Funcionario funcionario){
			String insertQuery = "insert into funcionarios"+
								"(nome,usuario,senha)"+
								"values(?,?,?)";
			
			try	{
				PreparedStatement stmt = connection.prepareStatement(insertQuery);
				stmt.setString(1,funcionario.getNome());
				stmt.setString(2,funcionario.getUsuario());
				stmt.setString(3,funcionario.getSenha());				
				stmt.execute();
				stmt.close();
				
			}catch(SQLException e){
				throw new RuntimeException(e);			
			}		
		}
		
		public ArrayList<Funcionario> Listar(){
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			
			try{
				PreparedStatement stmt = this.connection.prepareStatement("select * from funcionarios");			
				ResultSet resultSet = stmt.executeQuery();
				
				while(resultSet.next()){
					Funcionario funcionario = new Funcionario();
					
					funcionario.setNome(resultSet.getString("nome"));
					funcionario.setUsuario(resultSet.getString("usuario"));
					funcionario.setSenha(resultSet.getString("senha"));
					
					
					funcionarios.add(funcionario);				
				}
				
				resultSet.close();
				stmt.close();
				
				return funcionarios;
				
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		}
		
		public Funcionario BuscaPorId(int id){
			try{
				PreparedStatement stmt = this.connection.prepareStatement("select * from funcionarios where id = "+id);			
				ResultSet resultSet = stmt.executeQuery();
				
				if (resultSet.next()){
					Funcionario funcionario = new Funcionario();
					
					funcionario.setNome(resultSet.getString("nome"));
					funcionario.setUsuario(resultSet.getString("usuario"));
					funcionario.setSenha(resultSet.getString("senha"));					
					
					resultSet.close();
					stmt.close();
					
					return funcionario;
				}
				else
					return null;
				
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		}
		
		public void Alterar(Funcionario funcionario){
			String sql = "update contatos set nome=?, usuario=?, senha=? where id=?";
			
			try{
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				stmt.setString(1,funcionario.getNome());
				stmt.setString(2,funcionario.getUsuario());
				stmt.setString(3,funcionario.getSenha());
				
				stmt.execute();
				stmt.close();
			}catch(SQLException e){
				throw new RuntimeException(e);
			}		
		}
		
		public void Deletar(Funcionario funcionario){
			String sql = "delete from contatos where id=?";
			try{
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				stmt.setLong(1,funcionario.getId());
				stmt.execute();
				stmt.close();
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		}
				
	
}
