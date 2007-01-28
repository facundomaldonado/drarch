package org.design.rules4Java.ui.view.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.design.rules4Java.engine.ruleModel.DrarchFileModel;
import org.design.rules4Java.engine.ruleModel.RuleModelFactory;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;


public class Rules4JavaNewWizard extends Wizard implements INewWizard {

	private Rules4JavaNewWizardPage page;
	private ISelection selection;
	
	
	public Rules4JavaNewWizard(){
		super();
		setNeedsProgressMonitor(true);
		setWindowTitle("New Drarch File Wizard"); //$NON-NLS-1$
	}
	
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		page = new Rules4JavaNewWizardPage(selection);
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			System.out.println("Interrupted Exception");
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "error", realException.getMessage()); //$NON-NLS-1$
			return false;
		}
		return true;
	}

	private void doFinish(String containerName,	String fileName,IProgressMonitor monitor)
	throws CoreException {
		// create a sample file
		monitor.beginTask("beginTaskName" + fileName, 2); //$NON-NLS-1$
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist."); //$NON-NLS-1$ //$NON-NLS-2$
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		try {
			InputStream stream = openContentStream(fileName);
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();			
		} catch (IOException e) {
		}
		monitor.worked(1);
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(1);
	}
	
	@SuppressWarnings("unchecked")
	private InputStream openContentStream(String fileName) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Resource resource = new XMIResourceImpl();
		DrarchFileModel model= RuleModelFactory.eINSTANCE.createDrarchFileModel();
		model.setFileName(fileName);
		resource.getContents().add(model);
		try {
			resource.save(out, Collections.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "drarch", IStatus.OK, message, null);
		throw new CoreException(status);
	}
}
