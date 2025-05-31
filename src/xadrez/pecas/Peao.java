package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Color corDaPeca) {
		super(tabuleiro, corDaPeca);
		
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
			
		}
		return mat;
		
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
