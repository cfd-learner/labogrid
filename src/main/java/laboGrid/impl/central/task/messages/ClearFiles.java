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
package laboGrid.impl.central.task.messages;

import java.util.regex.Pattern;

import dimawo.WorkerMessage;
import dimawo.middleware.distributedAgent.DAId;


public class ClearFiles extends WorkerMessage {

	protected Pattern p;
	
	public ClearFiles(DAId t, Pattern p) {
		super(t);
		this.p = p;
	}
	
	public Pattern getPattern() {
		return p;
	}

}
