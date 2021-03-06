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
package laboGrid.lb.collision.d3.q19;

import laboGrid.lb.LBException;
import laboGrid.lb.lattice.d3.q19.D3Q19LatticeDescriptor;

public class D3Q19SRTSmagoCollider extends D3Q19SRTCollider {

	// Parameter
	protected D3Q19Smagorinsky smago;
	
	// Variable
	protected double omegax;
	
	public void setParameters(String[] params) throws LBException {
		
		if(params.length != 5)
			throw new LBException("Wrong number of parameters: "+params.length);

		double omega = Double.parseDouble(params[0]);
		double xAccel = Double.parseDouble(params[1]);
		double yAccel = Double.parseDouble(params[2]);
		double zAccel = Double.parseDouble(params[3]);
		double csmago = Double.parseDouble(params[4]);

		setParameters(omega, csmago, xAccel, yAccel, zAccel);
	}
	
	public void setParameters(double omega, double csmago, double xAccel, double yAccel, double zAccel) {
		super.setParameters(omega, xAccel, yAccel, zAccel);
		smago = new D3Q19Smagorinsky(csmago, omega);
	}
	
	protected void collide(boolean fluid) {
		
		if(fluid) {

			dloc = 	getLocalDensity(site);
			inv_rho = 1. / dloc;

			ux = inv_rho*(site[D3Q19LatticeDescriptor.EAST] + 
					site[D3Q19LatticeDescriptor.NORTHEAST] + 
					site[D3Q19LatticeDescriptor.SOUTHEAST] + 
					site[D3Q19LatticeDescriptor.UPEAST] + 
					site[D3Q19LatticeDescriptor.DOWNEAST] -
					site[D3Q19LatticeDescriptor.WEST] -
					site[D3Q19LatticeDescriptor.NORTHWEST] -
					site[D3Q19LatticeDescriptor.SOUTHWEST] -
					site[D3Q19LatticeDescriptor.UPWEST] -
					site[D3Q19LatticeDescriptor.DOWNWEST])+xAccel;

			uy = inv_rho*(site[D3Q19LatticeDescriptor.NORTHEAST] +
					site[D3Q19LatticeDescriptor.NORTH] +
					site[D3Q19LatticeDescriptor.NORTHWEST] +
					site[D3Q19LatticeDescriptor.UPNORTH] +
					site[D3Q19LatticeDescriptor.DOWNNORTH] -
					site[D3Q19LatticeDescriptor.SOUTHWEST] -
					site[D3Q19LatticeDescriptor.SOUTH] -
					site[D3Q19LatticeDescriptor.SOUTHEAST] -
					site[D3Q19LatticeDescriptor.UPSOUTH] -
					site[D3Q19LatticeDescriptor.DOWNSOUTH])+yAccel;

			uz = inv_rho*(site[D3Q19LatticeDescriptor.UP] +
					site[D3Q19LatticeDescriptor.UPEAST] +
					site[D3Q19LatticeDescriptor.UPNORTH] +
					site[D3Q19LatticeDescriptor.UPWEST] +
					site[D3Q19LatticeDescriptor.UPSOUTH] -
					site[D3Q19LatticeDescriptor.DOWNEAST] -
					site[D3Q19LatticeDescriptor.DOWNNORTH] -
					site[D3Q19LatticeDescriptor.DOWNWEST] -
					site[D3Q19LatticeDescriptor.DOWNSOUTH] -
					site[D3Q19LatticeDescriptor.DOWN])+zAccel;

			uSqrd = ux*ux + uy*uy + uz*uz;
			u2const = 1.0f - 1.5f * uSqrd;

			mux = ux*3.0f; 
			muy = uy*3.0f;
			muz = uz*3.0f;

			cu1 = mux;
			cu2 = mux + muy;
			cu3 = muy;
			cu4 = muy - mux;
			cu5 = -mux;
			cu6 = -cu2;
			cu7 = -muy;
			cu8 = -cu4;
			cu9 = muz;
			cu10 = mux + muz;
			cu11 = muy + muz;
			cu12 = -mux + muz;
			cu13 = -muy + muz;
			cu14 = -cu12;
			cu15 = -cu13;
			cu16 = -cu10;
			cu17 = -cu11;
			cu18 = -muz;

			distEq[D3Q19LatticeDescriptor.REST] = dloc/3.0f*u2const;

			qian = dloc/18.0f;
			distEq[D3Q19LatticeDescriptor.EAST] = qian*(u2const + cu1 + 0.5f*cu1*cu1);
			distEq[D3Q19LatticeDescriptor.NORTH] = qian*(u2const + cu3 + 0.5f*cu3*cu3);
			distEq[D3Q19LatticeDescriptor.WEST] = qian*(u2const + cu5 + 0.5f*cu5*cu5);
			distEq[D3Q19LatticeDescriptor.SOUTH] = qian*(u2const + cu7 + 0.5f*cu7*cu7);
			distEq[D3Q19LatticeDescriptor.UP] = qian*(u2const + cu9 + 0.5f*cu9*cu9);
			distEq[D3Q19LatticeDescriptor.DOWN] = qian*(u2const + cu18 + 0.5f*cu18*cu18);

			qian=qian/2.0f;
			distEq[D3Q19LatticeDescriptor.NORTHEAST] = qian*(u2const + cu2 + 0.5f*cu2*cu2);
			distEq[D3Q19LatticeDescriptor.NORTHWEST] = qian*(u2const + cu4 + 0.5f*cu4*cu4);
			distEq[D3Q19LatticeDescriptor.SOUTHWEST] = qian*(u2const + cu6 + 0.5f*cu6*cu6);
			distEq[D3Q19LatticeDescriptor.SOUTHEAST] = qian*(u2const + cu8 + 0.5f*cu8*cu8);
			distEq[D3Q19LatticeDescriptor.UPEAST] = qian*(u2const + cu10 + 0.5f*cu10*cu10);
			distEq[D3Q19LatticeDescriptor.UPNORTH] = qian*(u2const + cu11 + 0.5f*cu11*cu11);
			distEq[D3Q19LatticeDescriptor.UPWEST] = qian*(u2const + cu12 + 0.5f*cu12*cu12);
			distEq[D3Q19LatticeDescriptor.UPSOUTH] = qian*(u2const + cu13 + 0.5f*cu13*cu13);
			distEq[D3Q19LatticeDescriptor.DOWNEAST] = qian*(u2const + cu14 + 0.5f*cu14*cu14);
			distEq[D3Q19LatticeDescriptor.DOWNNORTH] = qian*(u2const + cu15 + 0.5f*cu15*cu15);
			distEq[D3Q19LatticeDescriptor.DOWNWEST] = qian*(u2const + cu16 + 0.5f*cu16*cu16);
			distEq[D3Q19LatticeDescriptor.DOWNSOUTH] = qian*(u2const + cu17 + 0.5f*cu17*cu17);
			
			omegax = smago.getSRTCoeff(dloc, site, distEq);

			site[0] = site[0] + omegax * (distEq[0] - site[0]);
			site[1] = site[1] + omegax * (distEq[1] - site[1]);
			site[2] = site[2] + omegax * (distEq[2] - site[2]);
			site[3] = site[3] + omegax * (distEq[3] - site[3]);
			site[4] = site[4] + omegax * (distEq[4] - site[4]);
			site[5] = site[5] + omegax * (distEq[5] - site[5]);
			site[6] = site[6] + omegax * (distEq[6] - site[6]);
			site[7] = site[7] + omegax * (distEq[7] - site[7]);
			site[8] = site[8] + omegax * (distEq[8] - site[8]);
			site[9] = site[9] + omegax * (distEq[9] - site[9]);
			site[10] = site[10] + omegax * (distEq[10] - site[10]);
			site[11] = site[11] + omegax * (distEq[11] - site[11]);
			site[12] = site[12] + omegax * (distEq[12] - site[12]);
			site[13] = site[13] + omegax * (distEq[13] - site[13]);
			site[14] = site[14] + omegax * (distEq[14] - site[14]);
			site[15] = site[15] + omegax * (distEq[15] - site[15]);
			site[16] = site[16] + omegax * (distEq[16] - site[16]);
			site[17] = site[17] + omegax * (distEq[17] - site[17]);
			site[18] = site[18] + omegax * (distEq[18] - site[18]);

		} else { // Bounce back

			bounceBack.apply(site);

		}
		
	}
	
}
