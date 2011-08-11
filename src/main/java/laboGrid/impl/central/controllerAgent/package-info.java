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
/**
 * Contains the implementation of the central Controller. The Controller,
 * whose logic is mainly implemented by class
 * {@link laboGrid.impl.central.controllerAgent.LBControllerAgent LBControllerAgent},
 * implies 2 main components: the Experimenter and the Resource Graph Builder.
 * <p>
 * The {@link laboGrid.impl.central.controllerAgent.experimenter.Experimenter Experimenter}
 * leads all operations related to simulations' execution.
 * The {@link laboGrid.impl.central.controllerAgent.resourceGraphHandler.ResourceGraphBuilder Resource Graph Bluider}
 * handles Resource Graph's changes.
 */
package laboGrid.impl.central.controllerAgent;
