/*
 * #%L
 * LaBoGrid
 * %%
 * Copyright (C) 2011 LaBoGrid Team
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
package laboGrid.procChain.operators;

import laboGrid.lb.LBException;
import laboGrid.procChain.ProcessingChainElement;

public abstract class LBOperator extends ProcessingChainElement {

	public void waitOperatorReady() throws InterruptedException {
		return;
	}

	/**
	 * Passes the parameters from the configuration file to
	 * the LBOperator.
	 * @param parameters The arguments
	 * @throws LBException
	 */
	public abstract void setParameters(String[] parameters) throws LBException;

}