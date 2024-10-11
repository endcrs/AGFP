package com.agsp.entity;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Setter
@Getter
@Entity
@Table(name = "TB_AGFP_USUARIO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = -4131922305601547724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	@Include
	private Long id;
	
	@Column(name = "NOME", nullable = false, length = 60)
	private String nome;
	
	@Column(name = "SOBRENOME", nullable = false, length = 75)
	private String sobrenome;
	
	@Column(name = "SENHA", nullable = false, length = 16)
	private String senha;
	
	@Column(name = "CELULAR", nullable = false, length = 11)
	private String celular;

	@CPF(message = "CPF inválido")
	@Column(name = "CPF", nullable = false, unique = true, updatable = false, length = 11)
	private String cpf;
	
	@Column(name = "DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	
	@Column(name = "ATIVO", nullable = false)
	private Boolean ativo;
	
	@Column(name = "DATA_CADASTRO")
	private ZonedDateTime dataCadastro;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<CurrentAccountEntity> accounts;
	
	@PrePersist
	private void onCreate() {
		dataCadastro = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

}
