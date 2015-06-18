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
package br.gov.serpro.lab.estacionamento.view;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.lab.estacionamento.business.EstacionamentoBC;
import br.gov.serpro.lab.estacionamento.domain.Estacionamento;
import br.gov.serpro.lab.estacionamento.domain.Patio;

@ViewController
@PreviousView("./estacionamento_list.jsf")
public class EstacionamentoEditMB extends AbstractEditPageBean<Estacionamento, Long> {

	private static final long serialVersionUID = 1L;
	
	private DataModel<Patio> patios;

	
	@Inject
	private EstacionamentoBC estacionamentoBC;
	
	@Override
	@Transactional
	public String delete() {
		this.estacionamentoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.estacionamentoBC.insert(getBean());
		return getPreviousView();
	}
	
	
	@Override
	@Transactional
	public String update() {
		this.estacionamentoBC.update(getBean());
		return getPreviousView();
	}
	
	public void addPatio() {
		getBean().getPatios().add(new Patio());
	}

	public void deletePatio() {
		getBean().getPatios().remove(getPatios().getRowData());
	}

	public DataModel<Patio> getPatios() {
		if (patios == null) {
			patios = new ListDataModel<Patio>(getBean().getPatios());
		}

		return patios;
	}

	@Override
	protected Estacionamento handleLoad(Long id) {
		return this.estacionamentoBC.load(id);
	}
		
}