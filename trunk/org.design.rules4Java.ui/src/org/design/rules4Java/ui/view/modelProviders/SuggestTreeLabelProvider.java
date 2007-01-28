package org.design.rules4Java.ui.view.modelProviders;

import org.design.rules4Java.ui.UiPlugin;
import org.design.rules4Java.ui.view.modelProviders.model.TreeObject;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class SuggestTreeLabelProvider extends LabelProvider implements ITableLabelProvider{

	public static final String CHECKED_IMAGE 	= "checked";
	public static final String UNCHECKED_IMAGE  = "unchecked";
	//	 For the checkbox images
	private ImageRegistry imageRegistry;
	
	/**
	 * Note: An image registry owns all of the image objects registered with it,
	 * and automatically disposes of them the SWT Display is disposed.
	*/  
	
	public SuggestTreeLabelProvider(){
		super();
		init();
//		super();
	}
	public void init(){
	    imageRegistry= new ImageRegistry();
		Image check = UiPlugin.getImageDescriptor(CHECKED_IMAGE + ".gif").createImage();
		Image uncheck = UiPlugin.getImageDescriptor(UNCHECKED_IMAGE + ".gif").createImage();
		
		imageRegistry.put(CHECKED_IMAGE, check);
		imageRegistry.put(UNCHECKED_IMAGE, uncheck);
	}
	
	/**
	 * Returns the image with the given key, or <code>null</code> if not found.
	 */
	private Image getImage(boolean isSelected) {
		String key = isSelected ? CHECKED_IMAGE : UNCHECKED_IMAGE;
		return  imageRegistry.get(key);
	}
	
	/**
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		TreeObject elem = (TreeObject) element;
		switch (columnIndex) {
			case 0:  // COMPLETED_COLUMN
				result = elem.toString();
				break;
			default :
				break; 	
		}
		return result;
	}

	/**
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		return (columnIndex == 0) ?   // COMPLETED_COLUMN?
			getImage(((TreeObject)element).isSelected()) :
			null;
	}
}
