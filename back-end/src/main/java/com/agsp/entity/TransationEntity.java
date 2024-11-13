package com.agsp.entity;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.agsp.enumerator.CategoriaEnum;
import com.agsp.enumerator.StatusEnum;
import com.agsp.enumerator.TipoTransacaoEnum;

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
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TB_AGFP_TRANSACAO")
public class TransationEntity implements Serializable {
	
	private static final long serialVersionUID = -4131922305601547724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	@Include
	private Long id;
	
	@Column(name = "CATEGORIA", nullable = false)
	@Enumerated(EnumType.STRING)
	private CategoriaEnum categoria;
	
	@Column(name = "DATA_TRANSACAO", nullable = false)
	private ZonedDateTime dataTransacao;
	
	@Column(name = "VALOR_COMPRA", nullable = false)
	private BigDecimal valorCompra;
	
	@Column(name = "ATIVO", nullable = false)
	private Boolean ativo;
	
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@Column(name = "TIPO_TRANSACAO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoTransacaoEnum tipo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTA_CORRENTE_ID", referencedColumnName = "IDENT", nullable = false)
	private CurrentAccountEntity currentAccount;
	
	@Column(name = "TRANSACAO", nullable = false)
	private String transacao;
	
	@Column(name = "TITULO", nullable = false, length = 15)
	private String titulo;
	
	@PrePersist
	private void onCreate() {
		dataTransacao = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}
	
}
