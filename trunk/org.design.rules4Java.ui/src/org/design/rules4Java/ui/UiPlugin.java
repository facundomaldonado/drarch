package org.design.rules4Java.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The main plugin class to be used in the desktop.
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class UiPlugin extends AbstractUIPlugin {

  private static final String iconPath = "/icons/";

  // The shared instance.
  private static UiPlugin plugin;

  /**
   * The constructor.
   */
  public UiPlugin() {
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
  public static UiPlugin getDefault() {
    return plugin;
  }

  /**
   * Returns an image descriptor for the image file at the given plug-in
   * relative path.
   * 
   * @param name the name of the image descriptor.
   * @return the image descriptor.
   */
  public static ImageDescriptor getImageDescriptor(String name) {
    try {
      URL installURL = getDefault().getBundle().getEntry("/");
      URL url = new URL(installURL, iconPath + name);
      return ImageDescriptor.createFromURL(url);
    } catch (MalformedURLException e) {

      // Should not happen.
      return ImageDescriptor.getMissingImageDescriptor();
    }
  }
}
