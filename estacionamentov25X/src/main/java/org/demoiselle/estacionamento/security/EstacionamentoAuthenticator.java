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
package org.demoiselle.estacionamento.security;

import java.security.Principal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@SessionScoped
public class EstacionamentoAuthenticator implements Authenticator {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstacionamentoCredentials credentials;

	@Inject
	private ResourceBundle bundle;

	private static boolean authenticated=false;

	@Override
	public void authenticate() throws Exception {

		String username = credentials.getUsername();
		String password = credentials.getPassword();

		if (username.equals("gerente") && password.equals("gerente")) {
			authenticated = true;
		} else if (username.equals("atendente") && password.equals("atendente")) {
			authenticated = true;
		}
		if (!authenticated) {
			throw new AuthenticationException(
					bundle.getString("usuarioNaoAutenticado"));
		}

	}

	@Override
	public  Principal getUser() {

		if (authenticated) {

			return new Principal() {

				@Override
				public String getName() {
					return credentials.getUsername();
				}

			};
		}else{
			return null;
		}

	}

	@Override
	public void unauthenticate() throws Exception {
		credentials.clear();
		authenticated = false;
	}
}
