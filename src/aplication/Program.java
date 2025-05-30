package aplication;

import java.util.InputMismatchException;
import java.util.Scanner;

import tabuleiro.TabuleiroException;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;


public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while(true) {
			try {
				UI.limpaTela();
				UI.printTabuleiro(partidaDeXadrez.getPecas());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
				
				System.out.println();
				System.out.print("Alvo: ");
				PosicaoXadrez alvo = UI.lerPosicaoXadrez(sc);
				
				PecaXadrez pecaCapturada = partidaDeXadrez.perfomaceParaMoverAPeca(origem, alvo);
				
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
