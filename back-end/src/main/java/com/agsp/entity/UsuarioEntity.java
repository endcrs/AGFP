package com.agsp.entity;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "TB_AGFP_USUARIO")
@NoArgsConstructor @AllArgsConstructor
public class UsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = -4131922305601547724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	@Include
	private Long id;
	
	@Column(name = "NOME_COMPLETO", nullable = false, length = 75)
	private String nomeCompleto;
	
	@Column(name = "SENHA", nullable = false, length = 16)
	private String senha;
	
	@Column(name = "SENHA_CONFIRMADA", nullable = false, length = 16)
	private String senhaConfirmada;
	
	@Column(name = "CPF", nullable = false, unique = true, /*, updatable = false*/ length = 11)
	private String cpf;
	
	@Column(name = "CELULAR", nullable = false,/*, updatable = false*/ length = 11)
	private String celular;
	
	@Column(name = "DATA_CADASTRO")
	private ZonedDateTime dataCadastro;
	
	@Column(name = "DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	
	@Column(name = "DATA_ATUALIZACAO")
	private ZonedDateTime dataAtualizacao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<CartaoEntity> cartoes;

	
	@PrePersist
	private void onCreate() {
		dataCadastro = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

	@PreUpdate
	private void onUpdate() {
		dataAtualizacao = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

}
