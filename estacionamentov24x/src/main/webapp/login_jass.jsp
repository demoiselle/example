 <!-- 
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
 -->
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="pt-br">
<head>
	<title>escola - Login</title>
	<meta http-equiv="PRAGMA" content="NO-CACHE">
	<meta http-equiv="CACHE-CONTROL" content="NO-CACHE">
	<meta http-equiv="Expires" content="-1">
</head>
<body marginheight="0" marginwidth="0" bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
	<form method="post" action="j_security_check">
		<table align="center">
			<tr>
				<td>
					<label for="txt_username">Login:</label>
				</td>
				<td>
					<input id="txt_username" type="text" name="j_username" tabindex="1" title="Informe o usuário"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="txt_password">Senha:</label>
				</td>
				<td>
					<input id="txt_password" type="password" name= "j_password" tabindex="2" title="Informe a senha do usuário"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="login" tabindex="3" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>