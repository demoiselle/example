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
package org.demoiselle.estacionamento.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.demoiselle.estacionamento.business.ClienteBC;
import org.demoiselle.estacionamento.domain.Cliente;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.FileRenderer;

@ViewController
@NextView("./cliente_edit.jsf")
@PreviousView("./cliente_list.jsf")
public class ClienteListMB extends AbstractListPageBean<Cliente, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	@Path("reports/clientes.jasper")
	private Report relatorio;

	@Inject
	private FileRenderer renderer;

	@Inject
	private ClienteBC clienteBC;
	
	@Inject
	private MessageContext messageContext;

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				this.clienteBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

	
	public String exibirRelatorioGeral() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("T\u00EDtulo", "Listagem de Clientes");
		try {
			byte[] buffer = this.relatorio.export(getResultList(), param, Type.PDF);
			this.renderer.render(buffer, FileRenderer.ContentType.PDF, "relatorio.pdf");
		} catch (Exception e) {
			messageContext.add(e.getMessage(), e);
		}
		return getNextView();
	}

	@Override
	protected List<Cliente> handleResultList() {
		return this.clienteBC.findAll();
	}
}
