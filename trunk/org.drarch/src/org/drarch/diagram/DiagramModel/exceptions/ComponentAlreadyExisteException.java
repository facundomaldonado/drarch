/**
 * 
 */
package org.drarch.diagram.DiagramModel.exceptions;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class ComponentAlreadyExisteException
        extends DrarchDiagramModelException {

	/**
     * 
     */
    private static final long serialVersionUID = 8830734959108354398L;

	/**
     * 
     */
    public ComponentAlreadyExisteException(String message, Throwable exception) {
	    super(message, exception);
    }
}
