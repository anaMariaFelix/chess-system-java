package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

			//chessMatch
public class PartidaDeXadrez { 

	private int turno;
	private Color jogadorAtual; //currentPlay
	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Color.WHITE;
		
		configuracaoInicial();
		
	}
	
	public int getTurno() {
		return turno;
	}

	public Color getJogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] possivelMovimento(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.toPosicao();
		validaPosiçãoDeorigem(posicao);
		
		return tabuleiro.peca(posicao).possívelMovimento();
	}
	
	public PecaXadrez perfomaceParaMoverAPeca(PosicaoXadrez posiçãoDeorigem, PosicaoXadrez posiçãoAlvo) {
		
		Posicao origem = posiçãoDeorigem.toPosicao();
		Posicao alvo = posiçãoAlvo.toPosicao();
		
		validaPosiçãoDeorigem(origem);
		validaPosiçãoDeAlvo(origem,alvo);
		
		Peca capturaPeca = facaMovimento(origem, alvo);
		
		nextTurno();
		return (PecaXadrez)capturaPeca;
	}
	
	

	private Peca facaMovimento(Posicao origem, Posicao alvo) {
		Peca p = tabuleiro.removePeca(origem);
		Peca pecaCapturada = tabuleiro.removePeca(alvo);
		
		tabuleiro.colocaPecaNaPosicao(p, alvo);
		return pecaCapturada;
	}

	private void validaPosiçãoDeorigem(Posicao origem) {
		if(!tabuleiro.existePecaNessaPosicao(origem)) {
			throw new XadrezException("Não há nenhuma peça na posição: ");
		}
							//Dwon Cast
		if(jogadorAtual != ((PecaXadrez) tabuleiro.peca(origem)).getCorDaPeca()) {//se a cor do jogador atual for deiferente da cor do jogador que estaa na posição de origem
			throw new XadrezException("Essa peça não é sua!");
		}
		
		if(!tabuleiro.peca(origem).existeAlgumaMovimentaçãoPossível()) {
			throw new XadrezException("Não existe movimentos possiveis para a peça escolhida! ");
		}
		
	}
	
	private void validaPosiçãoDeAlvo(Posicao origem, Posicao alvo) {
		if(!tabuleiro.peca(origem).possívelMovimento(alvo)) {//se a posição de destino não é um movimento possivel eu não posso mover a peça para la
			throw new XadrezException("A peça não pode se mover para a posição de destino!");
		}
			
	}
	
	private void nextTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Color.WHITE)? Color.BLACK : Color.WHITE; //if ternario para mudar a cor do proximo jogador
	}
	
	private void coloqueNovaPeça(char coluna, int linha, PecaXadrez peca) {
		PosicaoXadrez posicaoXadrez = new PosicaoXadrez(coluna, linha);
		tabuleiro.colocaPecaNaPosicao(peca, posicaoXadrez.toPosicao());
	}
	
	private void configuracaoInicial() {
		coloqueNovaPeça('c', 1, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('c', 2, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('d', 2, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('e', 2, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('e', 1, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('d', 1, new Rei(tabuleiro, Color.BLACK));

		coloqueNovaPeça('c', 7, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('c', 8, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('d', 7, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('e', 7, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('e', 8, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('d', 8, new Rei(tabuleiro, Color.BLACK));
		
	}
	
	
}
