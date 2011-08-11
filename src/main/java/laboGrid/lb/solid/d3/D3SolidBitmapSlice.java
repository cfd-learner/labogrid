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
package laboGrid.lb.solid.d3;

import laboGrid.lb.solid.Solid;

public class D3SolidBitmapSlice implements D3SolidSlice {
	private int iSize, jSize;
	private byte[] data;

	public D3SolidBitmapSlice(int iSize, int jSize) {
		this.iSize = iSize;
		this.jSize = jSize;
		this.data = new byte[iSize * jSize];
	}

	public int getJSize() {
		return jSize;
	}

	public int getISize() {
		return iSize;
	}

	public void set(int i, int j, byte val) {
		data[i * jSize + j] = val;
	}

	@Override
	public boolean isFluid(int i, int j) {
		return data[i * jSize + j] == Solid.FLUID;
	}

}
