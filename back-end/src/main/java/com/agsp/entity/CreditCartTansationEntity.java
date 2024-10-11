package com.agsp.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_AGFP_TRANSACAO_CARTAO_CREDITO")
class CreditCartTansationEntity implements Serializable {
	
	private static final long serialVersionUID = 432727693281136927L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	@Include
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSACAO_ID", referencedColumnName = "IDENT")
	private TransationEntity transation;
	
	@Column(name = "NUMERO_PARCELAS", nullable = false)
	private Integer numeroParcelas;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARTAO_CREDITO_ID", referencedColumnName = "IDENT")
	private CreditCardEntity creditCard;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creditCardTransation")
	private List<PortionEntity> portions;
	
	
}
