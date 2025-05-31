package aplication;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Color;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class UI {

	//cores para o console
	
	//cores para os textos
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	
	//cores para o fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static void limpaTela() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	
	public static PosicaoXadrez lerPosicaoXadrez(Scanner scanner) {
		try {
			String s = scanner.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			
			return new PosicaoXadrez(coluna, linha);
			
		}catch(RuntimeException e) {
			throw new InputMismatchException("ERRO: Na instancia da Posição do xadrez. forneca valores entre a1 e h8");
		}
	}
	
	public static void printPartida(PartidaDeXadrez partidaDeXadrez, List<PecaXadrez> listaPecasCapturadas) {
		printTabuleiro(partidaDeXadrez.getPecas());
		System.out.println();
		printPecasCapturadas(listaPecasCapturadas);
		System.out.println();
		System.out.println("Turno: "+ partidaDeXadrez.getTurno());
		
		
		if(!partidaDeXadrez.getCheckMate()) {
			
			System.out.println("Aguardando o jogador: " + partidaDeXadrez.getJogadorAtual());
			
			if(partidaDeXadrez.getCheck()) {
				System.out.println("CHECK!!!");
			}
		}else {
			System.out.println("CHECKMATE!!!");
			System.out.println("Vencedor: "+ partidaDeXadrez.getJogadorAtual());
		}
		
		
	}
	
	public static void printTabuleiro(PecaXadrez[][] pecas) {
		
		for(int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			
			for(int j = 0; j < pecas.length; j++) {
				printPecas(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printTabuleiro(PecaXadrez[][] pecas, boolean[][] possivelMovimento) {
		for(int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			
			for(int j = 0; j < pecas.length; j++) {
				printPecas(pecas[i][j], possivelMovimento[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
		
	}
	
	private static void printPecas(PecaXadrez peca, boolean background) {
		if(background) {
			
			System.out.print(ANSI_BLUE_BACKGROUND);//COLORE o fundo da peça
		}
		
    	if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
    	
        else { //Verifica se as peças são brancas ou pretas, se for brancas ele deixa branco se for pratas ele deixa amarelas para poder destacar no fundo do console que é preto
            if (peca.getCorDaPeca() == Color.WHITE) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);//depois reseta a cor
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}

	private static void printPecasCapturadas(List<PecaXadrez> pecaCapturada) {//guarda as peças que foram capturadas em listas de acordo com a cor da peça
		List<PecaXadrez> white = pecaCapturada.stream().filter(x -> x.getCorDaPeca() == Color.WHITE).collect(Collectors.toList());//filtragem das peças brancas da lista de pecaCapturada usando uma função lambda
		List<PecaXadrez> black = pecaCapturada.stream().filter(x -> x.getCorDaPeca() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("----PEÇAS CAPTURADAS----");
		System.out.print("WHITE: ");
		System.out.print(ANSI_WHITE);
		System.out.print(Arrays.toString(white.toArray()));//forma de imprimir um array/lista em java 
		System.out.println(ANSI_RESET);
		
		System.out.print("BLACK: ");
		System.out.print(ANSI_YELLOW);
		System.out.print(Arrays.toString(black.toArray()));
		System.out.println(ANSI_RESET);
		
	}
	
	
}
