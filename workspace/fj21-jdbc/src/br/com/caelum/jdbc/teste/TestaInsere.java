package br.com.caelum.jdbc.teste;

import java.io.ObjectInputStream.GetField;
import java.util.Calendar;

import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.modelo.Funcionario;
import br.com.caelum.jdbc.modelo.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.dao.FuncionarioDao;

public class TestaInsere {
	
	public static void main (String[] args) {	
		
		testaFuncionario(1000);
		
	}
	
	public static void testaFuncionario(int qantidadeRegistros){
		
		for(int i = 1 ;i<qantidadeRegistros;i++){
			
			Funcionario funcionario = new Funcionario();
			funcionario.setNome("funcionario"+i);
			funcionario.setUsuario("funcionario@mail.com"+i);
			funcionario.setSenha("Rua do funcionario,"+i);			
			
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			
			funcionarioDao.Adiciona(funcionario);
			
			System.out.println(i);				
		}		
	}
	
	public static void testaContato(int qantidadeRegistros){
		
		for(int i = 1 ;i<qantidadeRegistros;i++){
			
			Contato contato = new Contato();
			contato.setNome("contato"+i);
			contato.setEmail("contato@mail.com"+i);
			contato.setEndereco("Rua do contato,"+i);
			contato.setDataNascimento(Calendar.getInstance()); 
			
			ContatoDao contatoDao = new ContatoDao();
			
			contatoDao.Adiciona(contato);
			
			System.out.println(i);				
		}		
	}

}
