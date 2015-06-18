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

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@SessionScoped
public class EstacionamentoAuthorizer  implements Authorizer {

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SecurityContext context;
	
	@Inject
	private ResourceBundle bundle;	
	
	@Override
	public boolean hasRole(String role)  throws Exception {
		
		boolean authorized = false;
		if (context != null)
		{
			if (context.isLoggedIn()){
				try {
					String usr = context.getUser().getName();						
					if (usr.equals("gerente") && role.equals("gerente")) {
						authorized = true;
					}
				}catch (Exception e) {
					// throw new AuthorizationException(e.getMessage());
					throw new AuthenticationException(
							bundle.getString("usuarioNaoAutenticado"));
				}
			}else {
				throw new AuthenticationException(
						bundle.getString("usuarioNaoAutenticado"));
			}
		}
		else{
			throw new AuthenticationException(
					bundle.getString("usuarioNaoAutenticado"));
		}
		return authorized;
	}

	@Override
	public boolean hasPermission(String res, String op) throws Exception {
		
		boolean authorized = false;
		if (context != null)
		{
		  try{
			if (context.isLoggedIn()){
				String usr = context.getUser().getName();
			
				if (context.hasRole("gerente")) {
					authorized = true;
				}else{
					if (usr.equals("atendente") && res.equals("automovel") && op.equals("insert")) {
						authorized = true;
					}
				
					if (usr.equals("atendente") && res.equals("automovel") && op.equals("update")) {
						authorized = true;
					}
				
					if (usr.equals("atendente") && res.equals("estacionamento") && op.equals("insert")) {
						authorized = true;
					}
				
					if (usr.equals("atendente") && res.equals("estacionamento") && op.equals("update")) {
						authorized = true;
					}
					
					if (usr.equals("atendente") && res.equals("patio") && op.equals("insert")) {
						authorized = true;
					}
				
					if (usr.equals("atendente") && res.equals("patio") && op.equals("update")) {
						authorized = true;
					}
					if (usr.equals("atendente") && res.equals("endereco") && op.equals("insert")) {
						authorized = true;
					}
				
					if (usr.equals("atendente") && res.equals("endereco") && op.equals("update")) {
						authorized = true;
					}
					if (usr.equals("atendente") && res.equals("cliente") && op.equals("insert")) {
						authorized = true;
					}
				
					if (usr.equals("atendente") && res.equals("cliente") && op.equals("update")) {
						authorized = true;
					}
				}
			}
			else {
				throw new AuthenticationException(
						bundle.getString("usuarioNaoAutenticado"));
			}
		  }catch (Exception e) {
			  throw new AuthenticationException(
						bundle.getString("usuarioNaoAutenticado"));
			  //throw new AuthorizationException(e.getMessage());
		  }
		}else{
			throw new AuthenticationException(
					bundle.getString("usuarioNaoAutenticado"));
		}
		

		return authorized;
	}

}