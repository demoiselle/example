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
package org.demoiselle.estacionamento.business;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.demoiselle.estacionamento.domain.Automovel;
import org.demoiselle.estacionamento.domain.AutomovelTamanho;
import org.demoiselle.estacionamento.domain.AutomovelTipo;
import org.demoiselle.estacionamento.message.ErrorMessages;
import org.demoiselle.estacionamento.message.InfoMessages;
import org.demoiselle.estacionamento.persistence.AutomovelDAO;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.AuthorizationException;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class AutomovelBC extends DelegateCrud<Automovel, Long, AutomovelDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	private ResourceBundle defaultBundle;

	@ExceptionHandler
	public void tratador(NullPointerException cause) {
		messageContext.add(ErrorMessages.NULL_POINTER.getText(), cause.getMessage(), SeverityType.ERROR);
	}

	@ExceptionHandler
	public void seguranca(AuthorizationException cause) {
		messageContext.add(ErrorMessages.ACESSO_NOK.getText(), cause.getMessage());
	}

	public List<SelectItem> getAutomovelTipos() {
		List<SelectItem> autoTipos = new ArrayList<SelectItem>();

		for (AutomovelTipo automovelTipo : AutomovelTipo.values()) {
			autoTipos.add(new SelectItem(automovelTipo, defaultBundle.getString(automovelTipo.toString())));
		}

		return autoTipos;

	}

	public List<SelectItem> getAutomovelTamanhos() {
		List<SelectItem> autoTamanhos = new ArrayList<SelectItem>();

		for (AutomovelTamanho automovelTamanho : AutomovelTamanho.values()) {
			autoTamanhos.add(new SelectItem(automovelTamanho, defaultBundle.getString(automovelTamanho.toString())));
		}

		return autoTamanhos;
	}

	@Override
	@Transactional
	// Não é possível com JAAS.
	@RequiredPermission(resource = "automovel", operation = "insert")	
	//@RequiredRole({"gerente","atendente"})
	public Automovel insert(Automovel automovel) {
		try {
			super.insert(automovel);
			messageContext.add(InfoMessages.AUTOMOVEL_INSERT_OK.getText(), automovel.getPlaca());
		} catch (Exception te) {
			messageContext.add(ErrorMessages.AUTOMOVEL_INSERT_NOK.getText(), te.getMessage(), SeverityType.ERROR);
		}
		return automovel;
	}

	@Override
	@Transactional
	// Não é possível com JAAS.
	@RequiredPermission(resource = "automovel", operation = "update")
	//@RequiredRole({"gerente","atendente"})
	public Automovel update(Automovel automovel) {
		try {
			super.update(automovel);
			messageContext.add(InfoMessages.AUTOMOVEL_UPDATE_OK.getText(), automovel.getPlaca());
		} catch (Exception te) {
			messageContext.add(ErrorMessages.AUTOMOVEL_UPDATE_NOK.getText(), te.getMessage(), SeverityType.ERROR);
		}
		return automovel;
	}

	@Override
	@Transactional
	@RequiredRole("gerente")
	public void delete(Long id) {
		try {
			super.delete(id);
			messageContext.add(InfoMessages.AUTOMOVEL_DELETE_OK.getText(), id);
		} catch (Exception te) {
			messageContext.add(ErrorMessages.AUTOMOVEL_UPDATE_NOK.getText(), te.getMessage(), SeverityType.ERROR);
		}
	}
}
