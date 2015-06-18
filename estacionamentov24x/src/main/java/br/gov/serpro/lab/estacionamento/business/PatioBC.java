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
package br.gov.serpro.lab.estacionamento.business;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.lab.estacionamento.domain.Patio;
import br.gov.serpro.lab.estacionamento.message.ErrorMessages;
import br.gov.serpro.lab.estacionamento.message.InfoMessages;
import br.gov.serpro.lab.estacionamento.persistence.PatioDAO;

@BusinessController
public class PatioBC extends DelegateCrud<Patio, Long, PatioDAO> {

	private static final long serialVersionUID = 1L;


	@Inject
	private MessageContext messageContext;
	
	@ExceptionHandler
	public void tratador(NullPointerException cause) {
		messageContext.add(ErrorMessages.NULL_POINTER.getText(), cause.getMessage(), SeverityType.ERROR);
	}

	@ExceptionHandler
	public void seguranca(SecurityException cause) {
		messageContext.add(ErrorMessages.ACESSO_NOK.getText(), cause.getMessage());
	}

	@Override
	@Transactional
	// Não é possível com JAAS
	@RequiredPermission(resource = "patio", operation = "insert")
	//@RequiredRole({"gerente","atendente"})
	public Patio insert(Patio patio) {
		try {
			super.insert(patio);
			messageContext.add(InfoMessages.PATIO_INSERT_OK.getText(), patio.getLocal());
		} catch (Exception te) {
			messageContext.add(ErrorMessages.PATIO_INSERT_NOK.getText(), te.getMessage(), SeverityType.ERROR);
		}
		return patio;
	}

	@Override
	@Transactional
	// Não é possível com JAAS
	 @RequiredPermission(resource = "patio", operation = "update")
	//@RequiredRole({"gerente","atendente"})
	public Patio update(Patio patio) {
		try {
			super.update(patio);
			messageContext.add(InfoMessages.PATIO_UPDATE_OK.getText(), patio.getLocal());
		} catch (Exception te) {
			messageContext.add(ErrorMessages.PATIO_UPDATE_NOK.getText(), te.getMessage(), SeverityType.ERROR);
		}
		return patio;
	}

	@Override
	@Transactional
	@RequiredRole("gerente")
	public void delete(Long id) {
		try {
			super.delete(id);
			messageContext.add(InfoMessages.PATIO_DELETE_OK.getText(), id);
		} catch (Exception te) {
			messageContext.add(ErrorMessages.PATIO_DELETE_NOK.getText(), te.getMessage(), SeverityType.ERROR);
		}
	}
}
