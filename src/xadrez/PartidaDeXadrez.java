package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

			//chessMatch
public class PartidaDeXadrez { 

	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		configuracaoInicial();
		
	}
	
	public PecaXadrez[][] getPecas() {//retornar a matriz com as peças do jogo
		
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for(int i = 0; i < tabuleiro.getLinhas(); i++) {
			
			for(int j = 0; j < tabuleiro.getColunas(); j++) {
				
				matriz[i][j] = (PecaXadrez)tabuleiro.peca(i, j);
			}
		}
		
		return matriz;
	}
	
	private void configuracaoInicial() {
		tabuleiro.colocaPecaNaPosicao(new Torre(tabuleiro, Color.BRANCO), new Posicao(2, 1));
		tabuleiro.colocaPecaNaPosicao(new Rei(tabuleiro, Color.BRANCO), new Posicao(0, 4));
		tabuleiro.colocaPecaNaPosicao(new Rei(tabuleiro, Color.PRETO), new Posicao(7, 4));
		
	}
	
	
}
