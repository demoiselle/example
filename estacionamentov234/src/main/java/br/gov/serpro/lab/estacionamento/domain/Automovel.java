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

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_automovel")
public class Automovel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_automovel")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column (length=255)
	private String marca;
	
	@Column (length=255)
	private String modelo;
	
	@Column (nullable=false, length=9)
	private String placa;
	
	@Enumerated(EnumType.STRING)
	@Column (nullable=false, length=10)
	private AutomovelTamanho tamanho;
	
	
	@Enumerated(EnumType.STRING)
	@Column (nullable=false, length=10)
	private AutomovelTipo tipo;

	@ManyToOne
	@JoinColumn(name="cliente_fk")
	private Cliente cliente;
	
	public Automovel() {
		super();
	}
	
	public Automovel(String marca, String modelo, String placa, AutomovelTamanho tamanho, AutomovelTipo  tipo) {
		this.marca = marca;
		this.modelo =  modelo;
		this.placa = placa;
		this.tamanho = tamanho;
		this.tipo = tipo;		
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMarca() {
		return marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getPlaca() {
		return placa;
	}

	public void setTamanho(AutomovelTamanho tamanho) {
		this.tamanho = tamanho;
	}

	public AutomovelTamanho getTamanho() {
		return tamanho;
	}

	public void setTipo(AutomovelTipo  tipo) {
		this.tipo = tipo;
	}

	public AutomovelTipo  getTipo() {
		return tipo;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
}
