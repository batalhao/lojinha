package br.com.ameridata.lojinha.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {

	private static final long serialVersionUID = -1182010512331746884L;

}
