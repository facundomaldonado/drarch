package org.design.rules4Java.engine.jqueryImpl;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 *
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class JqueryImplPlugin extends AbstractUIPlugin {

  //The shared instance.
  private static JqueryImplPlugin plugin;

  /**
   * The constructor.
   */
  public JqueryImplPlugin() {
    plugin = this;
  }

  /**
   * This method is called upon plug-in activation.
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
  }

  /**
   * This method is called when the plug-in is stopped.
   */
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
    plugin = null;
  }

  /**
   * Returns the shared instance.
   */
  public static JqueryImplPlugin getDefault() {
    return plugin;
  }

  /**
   * Returns an image descriptor for the image file at the given
   * plug-in relative path.
   *
   * @param path the path
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(String path) {
    return AbstractUIPlugin.imageDescriptorFromPlugin("rules4Java.engine" +
            ".jqueryImpl", path);
  }
}