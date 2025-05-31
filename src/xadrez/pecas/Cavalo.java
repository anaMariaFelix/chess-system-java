package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, Color corDaPeca) {
		super(tabuleiro, corDaPeca);
		
	}
	
	@Override
	public String toString() {
		return "C";
	}
					//CANMOVE
	public boolean podeMover(Posicao posicao) {//diz se o rei pode mover para uma determinada posição
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCorDaPeca() != getCorDaPeca(); //se a posição for nula e se tiver alguma peça for diferente o rei pode se mover
	}
	
	@Override
	public boolean[][] possívelMovimento() {
		boolean[][] matrizPossibilidadesRei = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];//matriz instanciada com as mesmas medidas do tabuleiro
		
		Posicao p = new Posicao(0,0);
		
	
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		
		p.setValores(posicao.getLinha() - 2, posicao.getColuna()- 1);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//ESQUERDA do rei
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//DIREITA do rei
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//NOROESTE do rei(a quina da esquerda)
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//NORDESTE
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//SUDOESTE
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//SUDESTE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		return matrizPossibilidadesRei;
	}

}
