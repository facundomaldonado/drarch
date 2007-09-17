package org.design.rules4Java.ui.view.modelProviders.vars;

import org.design.rules4Java.ui.UiPlugin;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class VarListLabelProvider extends LabelProvider implements
    ITableLabelProvider {

  public static final String CHECKED_IMAGE = "checked";
  public static final String UNCHECKED_IMAGE = "unchecked";

  // For the checkbox images.
  private ImageRegistry imageRegistry;

  /**
   * Note: An image registry owns all of the image objects registered with it,
   * and automatically disposes of them the SWT Display is disposed.
   */
  public VarListLabelProvider() {
    super();
    imageRegistry = new ImageRegistry();
    Image check = UiPlugin.getImageDescriptor(CHECKED_IMAGE + ".gif")
        .createImage();
    Image uncheck = UiPlugin.getImageDescriptor(UNCHECKED_IMAGE + ".gif")
        .createImage();
    imageRegistry.put(CHECKED_IMAGE, check);
    imageRegistry.put(UNCHECKED_IMAGE, uncheck);
  }

  public Image getImage(boolean isSelected) {
    String key = isSelected ? CHECKED_IMAGE : UNCHECKED_IMAGE;
    return imageRegistry.get(key);
  }

  public String getText(Object element) {
    return ((Var) element).getVar();
  }

  public void addListener(ILabelProviderListener listener) {
  }

  public void dispose() {
  }

  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  public void removeListener(ILabelProviderListener listener) {
  }

  public Image getColumnImage(Object element, int columnIndex) {
    return (columnIndex == 0) ? getImage(((Var) element).isSelected()) : null;
  }

  public String getColumnText(Object element, int columnIndex) {
    String result = "";
    Var elem = (Var) element;
    switch (columnIndex) {
    case 0:
      result = elem.getVar();
      break;
    default:
      break;
    }
    return result;
  }
}
