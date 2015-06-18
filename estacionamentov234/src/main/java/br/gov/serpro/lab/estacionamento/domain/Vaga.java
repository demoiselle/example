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
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="tb_vaga")
public class Vaga implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_vaga")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column (nullable=false)
	private boolean coberta;
	
	@Column (nullable=false, length=255)
	private String porte;
	
	@OneToOne
    @JoinColumn(name="id_automovel")
	private Automovel automovel;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraEntrada;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraSaida;
	
	@ManyToOne  
	@JoinColumn(name="patio_fk")  
	private Patio patio;  
	
	public Vaga() {
		super();
	}
	
	public Vaga(boolean coberta, String porte, Automovel automovel, Date dataHoraEntrada, Date dataHoraSaida) {
		this.coberta = coberta; 
		this.porte = porte;
		this.automovel = automovel;
		this.dataHoraEntrada = dataHoraEntrada;
		this.dataHoraSaida = dataHoraSaida;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCoberta(boolean coberta) {
		this.coberta = coberta;
	}

	public boolean isCoberta() {
		return coberta;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public String getPorte() {
		return porte;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setDataHoraEntrada(Date dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}

	public Date getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public void setDataHoraSaida(Date dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

	public Date getDataHoraSaida() {
		return dataHoraSaida;
	}

	public Patio getPatio() {
		return patio;
	}

	public void setPatio(Patio patio) {
		this.patio = patio;
	}
}
