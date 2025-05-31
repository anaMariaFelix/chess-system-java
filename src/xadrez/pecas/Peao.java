package xadrez.pecas;

import java.awt.Point;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	private PartidaDeXadrez partidaDeXadrez;
	
	public Peao(Tabuleiro tabuleiro, Color corDaPeca, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, corDaPeca);
		this.partidaDeXadrez = partidaDeXadrez;
	}

	@Override
	public boolean[][] possívelMovimento() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);

		if (getCorDaPeca() == Color.WHITE) {
			
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePecaNessaPosicao(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePecaNessaPosicao(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePecaNessaPosicao(p2) && getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			
			if (getTabuleiro().existePosicao(p) && existeUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			
			if (getTabuleiro().existePosicao(p) && existeUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//en Pasant white
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				
				if(getTabuleiro().existePosicao(esquerda) && existeUmaPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassanVunerable()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().existePosicao(direita) && existeUmaPecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassanVunerable()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}
			
			
		}
		else {
			
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePecaNessaPosicao(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePecaNessaPosicao(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePecaNessaPosicao(p2) && getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			
			if (getTabuleiro().existePosicao(p) && existeUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			
			if (getTabuleiro().existePosicao(p) && existeUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//En Pansante BLACK
			if(posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				
				if(getTabuleiro().existePosicao(esquerda) && existeUmaPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassanVunerable()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().existePosicao(direita) && existeUmaPecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassanVunerable()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
			
		}
		return mat;
		
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
