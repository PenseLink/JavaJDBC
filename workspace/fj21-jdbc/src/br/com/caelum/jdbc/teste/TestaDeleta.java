package br.com.caelum.jdbc.teste;

import java.util.Random;

import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.modelo.dao.ContatoDao;

public class TestaDeleta {
	public static void main (String [] args) {
		ContatoDao contatoDao = new ContatoDao();
		Random random = new Random();
		Contato contato = new Contato();
		
		for (int i = 0;i<5;i++){
			Long numeroBuscado = Long.parseLong(String.valueOf(random.nextInt(2000)));
			contato.setId(numeroBuscado);
			contatoDao.DeletarContato(contato);
			System.out.println("Número deletado:"+numeroBuscado);
		}
	}
}
