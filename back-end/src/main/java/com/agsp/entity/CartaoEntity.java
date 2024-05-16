package com.agsp.entity;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.agsp.enumerator.BancoEnum;
import com.agsp.enumerator.TipoBancoEnum;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
@Table(name = "TB_AGSP_CARTAO")
@NoArgsConstructor @AllArgsConstructor
public class CartaoEntity implements Serializable {
	
	private static final long serialVersionUID = -4131922305601547724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	@Include
	private Long id;
	
	@Column(name = "NOME_CARTAO", nullable = false)
	private String nome;
	
	@Column(name = "NUMERO", nullable = false, length = 20, unique =  true)
	private String numero;
	
	@Column(name = "CVV", nullable = false, length = 3, unique =  true)
	private String cvv;
	
	@Column(name = "BANCO")
	@Enumerated(EnumType.STRING)
	private BancoEnum banco;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_BANCO", nullable = false)
	private TipoBancoEnum tipobanco;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_BANDEIRA", nullable = false)
	private TipoBandeiraEnum bandeira;
	
	@Column(name = "DATA_HORA")
	private ZonedDateTime dataHora;
	
	@Column(name = "LIMITE", nullable = false)
	private BigDecimal limiteTotal;
	
	@Column(name = "LIMITE_DISPONIVEL")
	private BigDecimal limiteDisponivel;
	
	@Column(name = "VENCIMENTO", nullable = false)
	private String vencimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO_PROPRIETARIO", referencedColumnName = "IDENT", nullable = false)
	private UsuarioEntity usuario;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cartao")
	private List<TransacaoEntity> transacoes;
	
	@PrePersist
	private void onCreate() {
		dataHora = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

}
