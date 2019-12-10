package br.com.ameridata.lojinha.model;

public enum Unidade {

	AMPOLA("Ampola"), BALDE("Balde"), BANDEJ("Bandeja"), BARRA("Barra"), BISNAG("Bisnaga"), BLOCO("Bloco"),
	BOBINA("Bobina"), BOMB("Bombona"), CAPS("Capsula"), CART("Cartela"), CENTO("Cento"), CJ("Conjunto"),
	CM("Centímetro"), CM2("Centímetro quadrado"), CX("Caixa"), CX2("Caixa com 2 unidades"), CX3("Caixa com 3 unidades"),
	CX5("Caixa com 5 unidades"), CX10("Caixa com 10 unidades"), CX15("Caixa com 15 unidades"),
	CX20("Caixa com 20 unidades"), CX25("Caixa com 25 unidades"), CX50("Caixa com 50 unidades"),
	CX100("Caixa com 100 unidades"), DISP("Display"), DUZIA("Duzia"), EMBAL("Embalagem"), FARDO("Fardo"),
	FOLHA("Folha"), FRASCO("Frasco"), GALAO("Galão"), GF("Garrafa"), GRAMAS("Gramas"), JOGO("Jogo"), KG("Quilograma"),
	KIT("Kit"), LATA("Lata"), LITRO("Litro"), M("Metro"), M2("Metro quadrado"), M3("Metro cúbico"), MILHEI("Milheiro"),
	ML("Mililitro"), MWH("Megawatt hora"), PACOTE("Pacote"), PALETE("Palete"), PARES("Pares"), PC("Peça"), POTE("Pote"),
	K("Quilate"), RESMA("Resma"), ROLO("Rolo"), SACO("Saco"), SACOLA("Sacola"), TAMBOR("Tambor"), TANQUE("Tanque"),
	TON("Tonelada"), TUBO("Tubo"), UNID("Unidade"), VASIL("Vasilhame"), VIDRO("Vidro");

	private String descricao;

	Unidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
