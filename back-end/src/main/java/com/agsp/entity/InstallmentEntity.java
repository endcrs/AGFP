package com.agsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.agsp.enumerator.StatusEnum;

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
@Setter
@Getter
@Entity
@Table(name = "TB_AGFP_PARCELAS")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
public class InstallmentEntity implements Serializable {
	
	private static final long serialVersionUID = -4131922305601547724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	@Include
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSACAO_CARTAO_CREDITO_ID", referencedColumnName = "IDENT", nullable = false)
	private CreditCardTansationEntity creditCardTransation;
	
	@Column(name = "VALOR_PARCELA", nullable = false)
	private BigDecimal valorParcela;
	
	@Column(name = "DATA_VENCIMENTO", nullable = false)
	private LocalDate dataVencimento;
	
	@Column(name = "NUMERO_PARCELAS", nullable = false)
	private Integer numeroParcelas;
	
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	

}
