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

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.lab.estacionamento.business.EstacionamentoBC;
import br.gov.serpro.lab.estacionamento.business.PatioBC;
import br.gov.serpro.lab.estacionamento.domain.Estacionamento;
import br.gov.serpro.lab.estacionamento.domain.Patio;
import br.gov.serpro.lab.estacionamento.domain.Vaga;

@ViewController
@PreviousView("./patio_list.jsf")
public class PatioEditMB extends AbstractEditPageBean<Patio, Long> {

	private static final long serialVersionUID = 1L;
	
	private DataModel<Vaga> vagas;

	@Inject
	private PatioBC patioBC;
	
	@Inject
	private EstacionamentoBC estacionamentoBC;
	
	@Override
	@Transactional
	public String delete() {
		this.patioBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.patioBC.insert(getBean());
		return getPreviousView();
	}
	
	
	@Override
	@Transactional
	public String update() {
		this.patioBC.update(getBean());
		return getPreviousView();
	}
	
	
	
	
	public void addVaga() {
		getBean().addVaga(new Vaga());
	}

	public void deleteVaga() {
		getBean().getVagas().remove(getVagas().getRowData());
	}

	public DataModel<Vaga> getVagas() {
		if (vagas == null) {
			vagas = new ListDataModel<Vaga>(getBean().getVagas());
		}

		return vagas;
	}
	
	public List<Estacionamento> getEstacionamentos(){
		return estacionamentoBC.findAll();
	}

	@Override
	protected Patio handleLoad(Long id) {
		return this.patioBC.load(id);
	}

}