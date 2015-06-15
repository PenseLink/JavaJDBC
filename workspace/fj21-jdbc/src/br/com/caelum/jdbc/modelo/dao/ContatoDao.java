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

public class ContatoDao {
	
	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void Adiciona(Contato contato){
		String insertQuery = "insert into contatos"+
							"(nome,email,endereco,dataNascimento)"+
							"values(?,?,?,?)";
		
		try	{
			PreparedStatement stmt = connection.prepareStatement(insertQuery);
			stmt.setString(1,contato.getNome());
			stmt.setString(2,contato.getEmail());
			stmt.setString(3,contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.execute();
			stmt.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);			
		}		
	}
	
	public ArrayList<Contato> ListarContatos(){
		ArrayList<Contato> contatos = new ArrayList<Contato>();
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos");			
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()){
				Contato contato = new Contato();
				
				contato.setNome(resultSet.getString("nome"));
				contato.setEmail(resultSet.getString("email"));
				contato.setEndereco(resultSet.getString("endereco"));
				Calendar data =  Calendar.getInstance();
				data.setTime(resultSet.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				
				contatos.add(contato);				
			}
			
			resultSet.close();
			stmt.close();
			
			return contatos;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public Contato BuscaPorId(int id){
		try{
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos where id = "+id);			
			ResultSet resultSet = stmt.executeQuery();
			
			if (resultSet.next()){
				Contato contato = new Contato();
				
				contato.setNome(resultSet.getString("nome"));
				contato.setEmail(resultSet.getString("email"));
				contato.setEndereco(resultSet.getString("endereco"));
				Calendar data =  Calendar.getInstance();
				data.setTime(resultSet.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				
				resultSet.close();
				stmt.close();
				
				return contato;
			}
			else
				return null;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void AlterarContato(Contato contato){
		String sql = "update contatos set nome=?, email=?, endereco=?, dataNascimento=? where id=?";
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1,contato.getNome());
			stmt.setString(2,contato.getEmail());
			stmt.setString(3,contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}
	
	public void DeletarContato(Contato contato){
		String sql = "delete from contatos where id=?";
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1,contato.getId());
			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
}
