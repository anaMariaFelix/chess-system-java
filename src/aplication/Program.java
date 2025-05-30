package aplication;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import tabuleiro.TabuleiroException;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;


public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaXadrez> listaPecasCapturadas = new ArrayList<>();
		
		while(true) {
			try {
				UI.limpaTela();
				UI.printPartida(partidaDeXadrez, listaPecasCapturadas);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
				
				boolean[][] possivelMovimento = partidaDeXadrez.possivelMovimento(origem);
				UI.limpaTela();
				UI.printTabuleiro(partidaDeXadrez.getPecas(), possivelMovimento);
								
				System.out.println();
				System.out.print("Alvo: ");
				PosicaoXadrez alvo = UI.lerPosicaoXadrez(sc);
				
				PecaXadrez pecaCapturada = partidaDeXadrez.perfomaceParaMoverAPeca(origem, alvo);
				
				if(pecaCapturada != null) {
					listaPecasCapturadas.add(pecaCapturada);
				}
				
			}catch(TabuleiroException e) {
				System.out.println(e.getMessage());
				sc.nextLine();//vai continuar pedindo as peças, pq o try catch esta dentro de uma estrutura de repetição entao vai mostrar o erro e deixar o usuario continuar jogando
				
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
		}
		

	}

}
