package com.datacoper.locacaoequipamentos.common.model;



import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the endereco database table.
 * 
 */
@Embeddable
public class EnderecoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_pessoa", insertable=false, updatable=false)
	private Integer idPessoa;

	@Column(name="cd_endereco")
	private Integer cdEndereco;

	public EnderecoPK() {
	}
	public Integer getIdPessoa() {
		return this.idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	public Integer getCdEndereco() {
		return this.cdEndereco;
	}
	public void setCdEndereco(Integer cdEndereco) {
		this.cdEndereco = cdEndereco;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EnderecoPK)) {
			return false;
		}
		EnderecoPK castOther = (EnderecoPK)other;
		return 
			this.idPessoa.equals(castOther.idPessoa)
			&& this.cdEndereco.equals(castOther.cdEndereco);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPessoa.hashCode();
		hash = hash * prime + this.cdEndereco.hashCode();
		
		return hash;
	}
}