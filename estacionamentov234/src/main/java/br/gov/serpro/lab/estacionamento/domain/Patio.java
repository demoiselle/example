/*
 Demoiselle Framework
 Copyright (C) 2013 SERPRO
 ============================================================================
 This file is part of Demoiselle Framework.
 Demoiselle Framework is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License version 3
 as published by the Free Software Foundation.
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 You should have received a copy of the GNU Lesser General Public License version 3
 along with this program; if not,  see <http://www.gnu.org/licenses/>
 or write to the Free Software Foundation, Inc., 51 Franklin Street,
 Fifth Floor, Boston, MA  02110-1301, USA.
 ============================================================================
 Este arquivo é parte do Framework Demoiselle.
 O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 do Software Livre (FSF).
 Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 para maiores detalhes.
 Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 ou escreva para a Fundação do Software Livre (FSF) Inc.,
 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.serpro.lab.estacionamento.domain;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import br.gov.frameworkdemoiselle.validation.annotation.Cep;

@Entity
@Table(name = "tb_patio")
public class Patio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_patio")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String local;

	@Column
	@Cep
	private String cep;

	@ManyToOne
	@JoinColumn(name = "estacionamento_fk")
	private Estacionamento estacionamento;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "patio_fk")
	private List<Vaga> vagas = new ArrayList<Vaga>();

	public Patio() {
		super();
	}

	public Patio(final String local) {
		this.local = local;
	}

	public Patio(final String local, final List<Vaga> vagas) {
		this.local = local;
		this.vagas = vagas;
	}

	public void addVaga(final Vaga vaga) {
		this.vagas.add(vaga);
	}

	public String getCep() {
		return this.cep;
	}

	public Estacionamento getEstacionamento() {
		return this.estacionamento;
	}

	public Long getId() {
		return this.id;
	}

	public String getLocal() {
		return this.local;
	}

	public List<Vaga> getVagas() {
		return this.vagas;
	}

	public void setCep(final String cep) {
		this.cep = cep;
	}

	public void setEstacionamento(final Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setLocal(final String local) {
		this.local = local;
	}

	public void setVagas(final List<Vaga> vagas) {
		this.vagas = vagas;
	}
}
