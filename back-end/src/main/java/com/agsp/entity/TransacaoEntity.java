package com.agsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.agsp.enumerator.CategoriaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_AGSP_TRANSACAO")
@NoArgsConstructor @AllArgsConstructor
public class TransacaoEntity implements Serializable {
	
	private static final long serialVersionUID = -4131922305601547724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	@Include
	private Long id;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "CATEGORIA")
	@Enumerated(EnumType.STRING)
	private CategoriaEnum categoria;
	
	@Column(name = "DATA_TRANSACAO")
	private LocalDate dataTransacao;
	
	@Column(name = "VALOR_COMPRA", nullable = false)
	private BigDecimal valorCompra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CARTAO", referencedColumnName = "IDENT", nullable = false)
	private CartaoEntity cartao;
	
}
