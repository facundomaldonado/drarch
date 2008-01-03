package org.drarch.diagram.DiagramModel.ucmModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface Path extends EObject {

  /**
   * @model type="PathNode" opposite="path"
   * @return the PathNode list.
   */
  EList getNodes();

  /**
   * @model type="PathNode"
   * @return the PathNode list.
   */
  EList getStartNodes();

  /**
   * @model type="PathNode"
   * @return the Path Node list.
   */
  EList getEndNodes();
}
