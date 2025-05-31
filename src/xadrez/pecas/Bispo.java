package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Bispo  extends PecaXadrez{

	public Bispo(Tabuleiro tabuleiro, Color corDaPeca) {
		super(tabuleiro, corDaPeca);
		
	}

	@Override
	public String toString() {//essa letra vai representar a peça que se movimenta no tabuleiro
		return "B";
		
	}
	
	@Override
	public boolean[][] possívelMovimento() { 
		boolean[][] matrizPossibilidadesTorre = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];//matriz instanciada com as mesmas medidas do tabuleiro
		
		Posicao pecaAux = new Posicao(0,0);
		
		//Noroeste
		pecaAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		
		while(getTabuleiro().existePosicao(pecaAux) && !getTabuleiro().existePecaNessaPosicao(pecaAux)) { 
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true; 
			pecaAux.setValores(pecaAux.getLinha() - 1, pecaAux.getColuna()- 1);
		}
		
		if(getTabuleiro().existePosicao(pecaAux) && existeUmaPecaAdversaria(pecaAux) ) {
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		
		
		//NORDESTE
		pecaAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		
		while(getTabuleiro().existePosicao(pecaAux) && !getTabuleiro().existePecaNessaPosicao(pecaAux)) { //enquanto existe essa posição e enquanto n tiver peças nelas entao a torre pode andar
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true; //true significa que ela pode andar
			pecaAux.setValores(pecaAux.getLinha() - 1, pecaAux.getColuna() + 1);
		}
		
		if(getTabuleiro().existePosicao(pecaAux) && existeUmaPecaAdversaria(pecaAux) ) {//se existir pessas adversarias a torre tbm pode andar, sendo assim colocasse true.
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		
		
		//SUDESTE
		pecaAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);//vai subindo nas casas acima da peça
		
		while(getTabuleiro().existePosicao(pecaAux) && !getTabuleiro().existePecaNessaPosicao(pecaAux)) { //enquanto existe essa posição e enquanto n tiver peças nelas entao a torre pode andar
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true; //true significa que ela pode andar
			pecaAux.setValores(pecaAux.getLinha() + 1, pecaAux.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(pecaAux) && existeUmaPecaAdversaria(pecaAux) ) {//se existir pessas adversarias a torre tbm pode andar, sendo assim colocasse true.
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		
		
		//SUDOESTE
		pecaAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);//vai subindo nas casas acima da peça
		
		while(getTabuleiro().existePosicao(pecaAux) && !getTabuleiro().existePecaNessaPosicao(pecaAux)) { //enquanto existe essa posição e enquanto n tiver peças nelas entao a torre pode andar
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true; //true significa que ela pode andar
			pecaAux.setValores(pecaAux.getLinha() + 1, pecaAux.getColuna() - 1);
		}
		
		if(getTabuleiro().existePosicao(pecaAux) && existeUmaPecaAdversaria(pecaAux) ) {//se existir pessas adversarias a torre tbm pode andar, sendo assim colocasse true.
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		
		return matrizPossibilidadesTorre;
		
	}
		
}
