package com.agsp.entity;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.agsp.enumerator.BancoEnum;

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
@Table(name = "TB_AGFP_CONTA")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
public class ContaEntity implements Serializable {
	
	private static final long serialVersionUID = -4131922305601547724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	@Include
	private Long id;
	
	@Column(name = "BANCO")
	@Enumerated(EnumType.STRING)
	private BancoEnum banco;
	
	@Column(name = "SALDO", nullable = false)
	private BigDecimal saldo;
	
	@Column(name = "DATA_CADASTRO")
	private ZonedDateTime dataCadastro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIO_ID", referencedColumnName = "IDENT", nullable = false)
	private UsuarioEntity usuario;
	
	@PrePersist
	private void onCreate() {
		dataCadastro = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

}
