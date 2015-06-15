package br.com.caelum.jdbc.teste;

import java.util.Random;

import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.modelo.dao.ContatoDao;





public class TestaBuscaId {
	public static void main (String [] args) {
		ContatoDao contatoDao = new ContatoDao();
		Random random = new Random();
		
		for (int i = 0;i<30;i++){
			int numeroBuscado = random.nextInt(1000);
			Contato contato = contatoDao.BuscaPorId(numeroBuscado);
			System.out.println("Número buscado:"+numeroBuscado);
			if (contato==null){
				System.out.println("Não encontrado");
			}
			else			
				System.out.println(contato.getNome());
		}
	}

}
