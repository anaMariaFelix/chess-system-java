package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	private PartidaDeXadrez  PartidaDeXadrez;
	
	public Rei(Tabuleiro tabuleiro, Color corDaPeca, PartidaDeXadrez  PartidaDeXadrez) {
		super(tabuleiro, corDaPeca);
		this.PartidaDeXadrez = PartidaDeXadrez;
	}
	
	@Override
	public String toString() {
		return "R";
	}

	public boolean podeMover(Posicao posicao) {//diz se o rei pode mover para uma determinada posição
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCorDaPeca() != getCorDaPeca(); //se a posição for nula e se tiver alguma peça for diferente o rei pode se mover
	}
	
					//testRookCastling
	private boolean testeDeRoqueTorre(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		
		return p != null && p instanceof Torre && p.getCorDaPeca() == getCorDaPeca() && p.getContagemMovimentos() == 0;
	}
	
	@Override
	public boolean[][] possívelMovimento() {
		boolean[][] matrizPossibilidadesRei = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];//matriz instanciada com as mesmas medidas do tabuleiro
		
		Posicao p = new Posicao(0,0);
		
		//posições ACIMA do rei
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//ABAIXO da peça rei
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//ESQUERDA do rei
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//DIREITA do rei
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//NOROESTE do rei(a quina da esquerda)
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//NORDESTE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//SUDOESTE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//SUDESTE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			matrizPossibilidadesRei[p.getLinha()][p.getColuna()] = true;
		}
		
		//ESPECIAL ROCK LADO DO REI/LADO DIREITO
		if(getContagemMovimentos() == 0 && !PartidaDeXadrez.getCheck()) {
			Posicao posT1 = new Posicao(posicao.getLinha(),posicao.getColuna() + 3);//3 casas a direita do dei, la vai esta a torre
			//rock pequeno
			if(testeDeRoqueTorre(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(),posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna() + 2);
				
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null){
					matrizPossibilidadesRei[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			
			//rock grande
			Posicao posT2 = new Posicao(posicao.getLinha(),posicao.getColuna() - 4);
			if(testeDeRoqueTorre(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(),posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(),posicao.getColuna() - 3);
				
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null){
					matrizPossibilidadesRei[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		
		return matrizPossibilidadesRei;
	}

}
