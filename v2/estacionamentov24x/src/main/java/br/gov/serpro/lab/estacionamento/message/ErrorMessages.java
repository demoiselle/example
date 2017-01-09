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
package br.gov.serpro.lab.estacionamento.message;

import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.Message;

public interface ErrorMessages {
	
    final Message NULL_POINTER = new DefaultMessage("{error-null-pointer}");
    final Message ACESSO_NOK = new DefaultMessage("{acesso-nok}");
	
    final Message AUTOMOVEL_DELETE_NOK = new DefaultMessage("{automovel-delete-nok}");
    final Message AUTOMOVEL_INSERT_NOK = new DefaultMessage("{automovel-insert-nok}");
    final Message AUTOMOVEL_UPDATE_NOK = new DefaultMessage("{automovel-update-nok}");

    final Message CLIENTE_DELETE_NOK = new DefaultMessage("{cliente-delete-nok}");
    final Message CLIENTE_INSERT_NOK = new DefaultMessage("{cliente-insert-nok}");
    final Message CLIENTE_UPDATE_NOK = new DefaultMessage("{cliente-update-nok}");

    final Message ESTACIONAMENTO_DELETE_NOK = new DefaultMessage("{estacionamento-delete-nok}");
    final Message ESTACIONAMENTO_INSERT_NOK = new DefaultMessage("{estacionamento-insert-nok}");
    final Message ESTACIONAMENTO_UPDATE_NOK = new DefaultMessage("{estacionamento-update-nok}");

    final Message PATIO_DELETE_NOK = new DefaultMessage("{patio-delete-nok}");
    final Message PATIO_INSERT_NOK = new DefaultMessage("{patio-insert-nok}");
    final Message PATIO_UPDATE_NOK = new DefaultMessage("{patio-update-nok}");

    final Message VAGA_DELETE_NOK = new DefaultMessage("{vaga-delete-nok}");
    final Message VAGA_INSERT_NOK = new DefaultMessage("{vaga-insert-nok}");
    final Message VAGA_UPDATE_NOK = new DefaultMessage("{vaga-update-nok}");
	

}
