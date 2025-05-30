package aplication;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;


public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while(true) {
			UI.printTabuleiro(partidaDeXadrez.getPecas());
			System.out.println();
			System.out.println("Origem: ");
			PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
			
			System.out.println();
			System.out.println("Alvo: ");
			PosicaoXadrez alvo = UI.lerPosicaoXadrez(sc);
			
			PecaXadrez pecaCapturada = partidaDeXadrez.perfomaceParaMoverAPeca(origem, alvo);
		}
		

	}

}
