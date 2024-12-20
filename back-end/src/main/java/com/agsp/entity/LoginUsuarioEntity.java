package com.agsp.entity;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "TB_AGFP_LOGIN_USUARIO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
public class LoginUsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = -4131922305601547724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	@Include
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIO_ID", referencedColumnName = "IDENT")
	private UserEntity usuario;
	
	@Column(name = "TOKEN")
	private String token;
	
	@Column(name = "DATA_HORA")
	private ZonedDateTime dataHora;
	
	@PrePersist
	private void onCreate() {
		dataHora = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

}
