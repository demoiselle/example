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
import javax.validation.constraints.NotNull;
import br.gov.frameworkdemoiselle.validation.PatternInscricaoEstadual;
import br.gov.frameworkdemoiselle.validation.annotation.Cnpj;
import br.gov.frameworkdemoiselle.validation.annotation.InscricaoEstadual;

@Entity
@Table(name = "tb_estacionamento")
public class Estacionamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_estacionamento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(length = 255)
	@NotNull
	private String nome;

	@Column(length = 14)
	@Cnpj
	@NotNull
	private String cnpj;

	@Column
	@InscricaoEstadual(pattern = PatternInscricaoEstadual.DISTRITO_FEDERAL)
	private String inscricaoEstadual;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "estacionamento_fk")
	private List<Patio> patios = new ArrayList<Patio>();

	public Estacionamento() {
		super();
	}

	public Estacionamento(final String nome, final String cnpj) {
		this.nome = nome;
		this.cnpj = cnpj;
	}

	public Estacionamento(final String nome, final String cnpj, final List<Patio> patios) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.patios = patios;
	}

	public void addPatio(final Patio patio) {
		this.patios.add(patio);
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public Long getId() {
		return this.id;
	}

	public String getInscricaoEstadual() {
		return this.inscricaoEstadual;
	}

	public String getNome() {
		return this.nome;
	}

	public List<Patio> getPatios() {
		return this.patios;
	}

	public void setCnpj(final String cnpj) {
		this.cnpj = cnpj;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setInscricaoEstadual(final String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public void setPatios(final List<Patio> patios) {
		this.patios = patios;
	}

}
