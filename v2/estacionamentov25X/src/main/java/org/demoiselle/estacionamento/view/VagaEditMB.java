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

import java.util.*;

import javax.inject.Inject;

import org.demoiselle.estacionamento.business.*;
import org.demoiselle.estacionamento.domain.*;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./vaga_list.jsf")
public class VagaEditMB extends AbstractEditPageBean<Vaga, Long> {

	private static final long serialVersionUID = 1L;
	
	private boolean editMode=false;
	
	@Inject 
	private AutomovelBC automovelBC;

	@Inject
	private VagaBC vagaBC;
	
	@Inject
	
	private PatioBC patioBC; 
	
	@Override
	@Transactional
	public String delete() {
		this.vagaBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.vagaBC.insert(getBean());
		return getPreviousView();
	}
	
	
	@Override
	@Transactional
	public String update() {		
		this.vagaBC.update(getBean());
		return getPreviousView();
	}
	
	
	public boolean isEditMode() {  
	    return editMode;  
    }  
		  
    public void setEditMode(boolean editMode) {  
		   this.editMode = editMode;  
	}
		
	public List<Patio> getPatios (){
		return patioBC.findAll();
	}

	@Override
	protected Vaga handleLoad(Long id) {
		return this.vagaBC.load(id);
	}
	
	public List<Automovel> getAutomovelList (){
		return this.automovelBC.findAll();
	}
}