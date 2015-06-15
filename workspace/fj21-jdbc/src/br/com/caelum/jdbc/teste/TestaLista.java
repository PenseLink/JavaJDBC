package br.com.caelum.jdbc.teste;

import java.text.DateFormat;
import java.util.ArrayList;

import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.modelo.dao.ContatoDao;

public class TestaLista {
	 public static void main (String [] args){
		 ContatoDao contatoDao = new ContatoDao();
		 ArrayList<Contato> contatos = contatoDao.ListarContatos();
		 
		 for (Contato contato : contatos){
			 System.out.println("Nome "+contato.getNome());
			 System.out.println("Email "+contato.getEmail());
			 System.out.println("Endereço "+contato.getEndereco());
			 DateFormat dataFormatada = DateFormat.getDateInstance();
			 System.out.println("Data de Nascimento "+dataFormatada.format(contato.getDataNascimento().getTime())+"\n");
		 }
	 }
}
