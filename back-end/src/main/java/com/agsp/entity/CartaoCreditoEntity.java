package com.agsp.entity;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.agsp.enumerator.TipoBandeiraEnum;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "TB_AGFP_CARTAO_CREDITO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
public class CartaoCreditoEntity implements Serializable {
	
	private static final long serialVersionUID = -4131922305601547724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	@Include
	private Long id;
	
	@Column(name = "NUMERO", nullable = false, length = 20, unique =  true)
	private String numero;
	
	@Column(name = "LIMITE", nullable = false)
	private BigDecimal limite;
	
	@Column(name = "VALIDADE_CARTAO")
	private LocalDate validade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_BANDEIRA", nullable = false)
	private TipoBandeiraEnum bandeira;
	
	@Column(name = "FATURA_ATUAL", nullable = false)
	private BigDecimal facturaAtual;
	
	@Column(name = "VENCIMENTO")
	private LocalDate vencimento;
	
	@Column(name = "FECHAMENTO")
	private LocalDate fechamento;
	
	@Column(name = "DATA_HORA")
	private ZonedDateTime dataCadastro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIO_ID", referencedColumnName = "IDENT", nullable = true)
	private UsuarioEntity usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTA_ID", referencedColumnName = "IDENT", nullable = true)
	private ContaEntity conta;
	
	@PrePersist
	private void onCreate() {
		dataCadastro = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

}
