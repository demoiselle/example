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
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.lab.estacionamento.config.EstacionamentoConfig;
import br.gov.serpro.lab.estacionamento.domain.Estacionamento;
import br.gov.serpro.lab.estacionamento.message.ErrorMessages;
import br.gov.serpro.lab.estacionamento.message.InfoMessages;
import br.gov.serpro.lab.estacionamento.persistence.EstacionamentoDAO;

@BusinessController
public class EstacionamentoBC extends DelegateCrud<Estacionamento, Long, EstacionamentoDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstacionamentoConfig config;

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

	@Startup
	@Transactional
	public void startup() {

		// Para ativar essa configuração modifique o valor em estacionamento.properties -> general.loadInitialData =
		// true
		if (config.isLoadInitialData()) {
			if (findAll().isEmpty()) {
				insert(new Estacionamento("Demoiselle Park", "42070027000109", " 0775037176397"));
			}
		}
	}

	@Override
	@Transactional
	// Não é possível com JAAS.
	@RequiredPermission(resource = "estacionamento", operation = "insert")
	//@RequiredRole({"gerente","atendente"})
	public Estacionamento insert(Estacionamento estacionamento) {
		try {
			super.insert(estacionamento);
			messageContext.add(InfoMessages.ESTACIONAMENTO_INSERT_OK.getText(), estacionamento.getNome());
		} catch (Exception te) {
			te.printStackTrace();
			messageContext.add(ErrorMessages.ESTACIONAMENTO_INSERT_NOK.getText(), te.getMessage(), SeverityType.ERROR);			
		}
		return estacionamento;
	}

	@Override
	@Transactional
	// Não é possível com JAAS.
	@RequiredPermission(resource = "estacionamento", operation = "update")
	//@RequiredRole({"gerente","atendente"})
	public Estacionamento update(Estacionamento estacionamento) {
		try {
			super.update(estacionamento);
			messageContext.add(InfoMessages.ESTACIONAMENTO_UPDATE_OK.getText(), estacionamento.getNome());
		} catch (Exception te) {
			messageContext.add(ErrorMessages.ESTACIONAMENTO_UPDATE_NOK.getText(), te.getMessage(), SeverityType.ERROR);
		}
		return estacionamento;
	}

	@Override
	@Transactional
	@RequiredRole("gerente")
	public void delete(Long id) {
		try {
			super.delete(id);
			messageContext.add(InfoMessages.ESTACIONAMENTO_DELETE_OK.getText(), id);
		} catch (Exception te) {
			messageContext.add(ErrorMessages.ESTACIONAMENTO_DELETE_NOK.getText(), te.getMessage(), SeverityType.ERROR);
		}
	}
}
