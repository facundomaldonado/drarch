package org.design.drarch;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.ui.plugin.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class DrarchPlugin extends AbstractUIPlugin {

  private static final String iconPath = "/icons/";

  // The shared instance.
  private static DrarchPlugin plugin;

  /**
   * The constructor.
   */
  public DrarchPlugin() {
    plugin = this;
  }

  /**
   * This method is called upon plug-in activation
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
  }

  /**
   * This method is called when the plug-in is stopped
   */
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
    plugin = null;
  }

  /**
   * Returns the shared instance.
   */
  public static DrarchPlugin getDefault() {
    return plugin;
  }

  /**
   * Returns an image descriptor for the image file at the given plug-in
   * relative path.
   * 
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(String name) {

    try {
      URL installURL = getDefault().getBundle().getEntry("/");
      URL url = new URL(installURL, iconPath + name);
      return ImageDescriptor.createFromURL(url);
    } catch (MalformedURLException e) {
      // should not happen
      return ImageDescriptor.getMissingImageDescriptor();
    }
  }
}
