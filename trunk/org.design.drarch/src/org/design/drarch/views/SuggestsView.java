package org.design.drarch.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class SuggestsView extends ViewPart {
  public static final String ID_VIEW ="org.design.drarch.views.SuggestsView"; 
  private SuggestsViewer viewer;

  public void createPartControl(Composite parent) {
    viewer= new SuggestsViewer(parent,this);
  }

  public void setFocus() {
    viewer.getControl().setFocus();
  }

  /**
   * Cleans up all resources created by this ViewPart.
   */
  public void dispose() {
    super.dispose();
  }
}