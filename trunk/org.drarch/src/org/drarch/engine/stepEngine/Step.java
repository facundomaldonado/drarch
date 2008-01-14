package org.drarch.engine.stepEngine;


/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public abstract class Step implements IStep {

	private IStep NEXT = null;
	private IStep BEFORE = null;
	
	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.coreEngine.IStepAction#next()
	 */
	public IStep next() {
		return NEXT;
	}

	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.stepEngine.IStep#before()
	 */
	public IStep before() {
	    return BEFORE;
	}
	
	public void addNext(IStep nextStep) {
	   NEXT = nextStep;
    }
	
	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.stepEngine.IStep#addBefore(org.design.rules4Java.engine.stepEngine.IStep)
	 */
	public void addBefore(IStep beforeStep) {
		BEFORE = beforeStep;
	}
	
}
