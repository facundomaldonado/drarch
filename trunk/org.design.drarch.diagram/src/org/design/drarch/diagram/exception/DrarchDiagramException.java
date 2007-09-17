/**
 * 
 */
package org.design.drarch.diagram.exception;

/**
 * @author pelado
 *
 */
public class DrarchDiagramException extends Exception {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	/**
     */
    public DrarchDiagramException(String message, Exception e) {
    	super(message, e);
    }
	
}
