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

public interface InfoMessages {
	
    final Message AUTOMOVEL_DELETE_OK = new DefaultMessage("{automovel-delete-ok}");
    final Message AUTOMOVEL_INSERT_OK = new DefaultMessage("{automovel-insert-ok}");
    final Message AUTOMOVEL_UPDATE_OK = new DefaultMessage("{automovel-update-ok}");

    final Message CLIENTE_DELETE_OK = new DefaultMessage("{cliente-delete-ok}");
    final Message CLIENTE_INSERT_OK = new DefaultMessage("{cliente-insert-ok}");
    final Message CLIENTE_UPDATE_OK = new DefaultMessage("{cliente-update-ok}");

    final Message ESTACIONAMENTO_DELETE_OK = new DefaultMessage("{estacionamento-delete-ok}");
    final Message ESTACIONAMENTO_INSERT_OK = new DefaultMessage("{estacionamento-insert-ok}");
    final Message ESTACIONAMENTO_UPDATE_OK = new DefaultMessage("{estacionamento-update-ok}");

    final Message PATIO_DELETE_OK = new DefaultMessage("{patio-delete-ok}");
    final Message PATIO_INSERT_OK = new DefaultMessage("{patio-insert-ok}");
    final Message PATIO_UPDATE_OK = new DefaultMessage("{patio-update-ok}");

    final Message VAGA_DELETE_OK = new DefaultMessage("{vaga-delete-ok}");
    final Message VAGA_INSERT_OK = new DefaultMessage("{vaga-insert-ok}");
    final Message VAGA_UPDATE_OK = new DefaultMessage("{vaga-update-ok}");

}
