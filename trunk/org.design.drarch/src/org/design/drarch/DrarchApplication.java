/**
 * 
 */
package org.design.drarch;

import org.design.drarch.diagram.flabot.DiagramManager;
import org.design.rules4Java.engine.DrarchEngine;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class DrarchApplication {

	public static DrarchApplication INSTANCE = new DrarchApplication();
	private DrarchEngine engine = null;
	private RecoverySession session = null;
	
	private DrarchApplication() {
		/**
		 * default contructor
		 */
	}
	
	public DrarchEngine getDrarchEngine() {
		if(engine == null) {
			engine = new DrarchEngineImpl();
		}
		return engine;
	}
	
	public void startEngine() {
		engine.startEngine();
		//TODO revisar la inicializacion del DiagramManager
		DiagramManager.getInstance().restart();
	}
	
	public RecoverySession createSession() {
		return new RecoverySession();
	}
	
	public void registerRecoverySession(RecoverySession session) {
		this.session = session;
		((DrarchEngineImpl)engine).setRecoverySession(session);
	}

	public RecoverySession getCurrentSession() {
    	return this.session;
    }
}
