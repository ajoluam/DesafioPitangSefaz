package br.com.pitang.desafio.sefaz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.pitang.desafio.sefaz.util.TelefoneTipo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telefone")
@Getter
@Setter
@NoArgsConstructor
public class Telefone implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 2)
	private int ddd;

	@Column(length = 12)
	private String number;
	
	@Enumerated(EnumType.STRING)
	private TelefoneTipo iipo;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;


	}
