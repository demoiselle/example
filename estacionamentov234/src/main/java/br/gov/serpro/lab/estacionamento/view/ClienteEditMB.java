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

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.lab.estacionamento.business.*;
import br.gov.serpro.lab.estacionamento.domain.*;

@ViewController
@PreviousView("./cliente_list.xhtml")
public class ClienteEditMB extends AbstractEditPageBean<Cliente, Long> {

	private static final long serialVersionUID = 1L;

	private DataModel<Automovel> automoveis;

	private DualListModel<Endereco> enderecoList = null;

	@Inject
	private ClienteBC clienteBC;

	@Inject
	private AutomovelBC automovelBC;

	@Inject
	private EnderecoBC enderecoBC;

	public List<SelectItem> getAutomovelTipos() {
		return this.automovelBC.getAutomovelTipos();
	}

	public List<SelectItem> getAutomovelTamanhos() {
		return this.automovelBC.getAutomovelTamanhos();
	}

	@Override
	@Transactional
	public String delete() {
		this.clienteBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.clienteBC.insert(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		this.clienteBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(this.clienteBC.load(getId()));
	}

	public void addAutomovel() {
		getBean().addAutomovel(new Automovel());
	}

	public void deleteAutomovel() {
		getBean().getAutomoveis().remove(getAutomoveis().getRowData());
	}

	public DataModel<Automovel> getAutomoveis() {
		if (this.automoveis == null) {
			this.automoveis = new ListDataModel<Automovel>(getBean()
					.getAutomoveis());
		}

		return this.automoveis;
	}

	public void addEnderecoList(List<Endereco> enderecoList) {
		getBean().getEnderecos().addAll(enderecoList);
	}

	public void deleteEnderecoList(List<Endereco> enderecoList) {
		getBean().getEnderecos().removeAll(enderecoList);
	}

	public DualListModel<Endereco> getEnderecoList() {
		if (this.enderecoList == null) {

			List<Endereco> source = enderecoBC.findAll();
			List<Endereco> target = getBean().getEnderecos();

			if (source == null) {
				source = new ArrayList<Endereco>();
			}
			if (target == null) {
				target = new ArrayList<Endereco>();
			}else{
				source.removeAll(target);
			}
			this.enderecoList = new DualListModel<Endereco>(source, target);

		}
		return this.enderecoList;
	}

	public void setEnderecoList(DualListModel<Endereco> enderecoList) {
		this.enderecoList = enderecoList;
	}
	
	@SuppressWarnings("unchecked")
	public void onTransfer(TransferEvent event) {
		
		if (event.isAdd()){
			this.addEnderecoList((List<Endereco>) event.getItems());
		}
		if (event.isRemove()) {
			this.deleteEnderecoList((List<Endereco>) event.getItems());
		 }		

	}	

}