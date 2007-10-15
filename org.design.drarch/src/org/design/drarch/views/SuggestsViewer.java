package org.design.drarch.views;

import java.util.List;

import org.apache.log4j.Logger;
import org.design.drarch.DrarchApplication;
import org.design.drarch.DrarchPlugin;
import org.design.drarch.diagram.trace.uiAction.AnaliceLogTraceAction;
import org.design.drarch.views.action.ExecuteStepAction;
import org.design.drarch.views.action.LoadRuleFileAction;
import org.design.drarch.views.action.SelectFlabotFileAction;
import org.design.rules4Java.engine.coreEngine.StepsManager;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
import org.design.rules4Java.ui.view.modelProviders.SuggestTreeContentProvider;
import org.design.rules4Java.ui.view.modelProviders.SuggestTreeLabelProvider;
import org.design.rules4Java.ui.view.modelProviders.model.TreeObject;
import org.design.rules4Java.ui.view.modelProviders.model.TreeParent;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class SuggestsViewer {

	static Logger	     logger	= Logger.getLogger(DrarchPlugin.class.getName());
	private Shell 		 shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	private TreeViewer   treeViewer;
	private Tree	     tree;
	private Composite	 baseComposite;
	private TreeParent	 root	  = new TreeParent("");
	
	private Action	     selectFlabotFileAction;
	private Action	     nextStepAction;
	private Action	     importRuleAction;
	private Action	     chooseDefaultRulesFile;
	private Action	     analiceLog;
	private Action 		 resetEngine;
	
	private StepsManager	stepManager;

	public SuggestsViewer(Composite parent, IViewPart view) {
		logger.debug("SuggestViewer: creator");
		this.addChildControl(parent);
		this.createActions();
		this.createMenus(parent, view);
	}

	/**
	 * Return the parent composite
	 */
	public Control getControl() {
		return baseComposite.getParent();
	}

	private void createMenus(Composite parent, IViewPart view) {
		IMenuManager menuMgr = view.getViewSite().getActionBars().getMenuManager();

		MenuManager ucmMenu = new MenuManager("UCM");
		// ucmMenu.add(new GenerateUCM("Generate UCM"));
		menuMgr.add(ucmMenu);

		//TODO: leer textos de un .properties
		MenuManager rulesMenu = new MenuManager("Drarch Actions");

		// add actions to menu
		menuMgr.add(new Separator());
		rulesMenu.add(resetEngine);
		rulesMenu.add(selectFlabotFileAction);
		rulesMenu.add(new Separator());
		rulesMenu.add(nextStepAction);
		rulesMenu.add(analiceLog);
		menuMgr.add(rulesMenu);

		MenuManager importMenu = new MenuManager("Rules");
		importMenu.add(chooseDefaultRulesFile);
		importMenu.add(importRuleAction);

		menuMgr.add(new Separator());
		menuMgr.add(importMenu);

		// add actions to toolbar
		IToolBarManager toolMgr = view.getViewSite().getActionBars().getToolBarManager();
		toolMgr.add(importRuleAction);
		toolMgr.add(new Separator());
		toolMgr.add(selectFlabotFileAction);
		toolMgr.add(nextStepAction);

		logger.debug("SuggestsViewer: Actions added to the view menu");
	}

	private void createActions() {
		
		resetEngine = new Action("Reset Engine") {
			public void run() {
				logger.debug("Reset engine Action running");
				doResetEngine();
			}
		};
		
		selectFlabotFileAction = new Action("Select WorkingSet") {
			public void run() {
				doSelectFlabotFileAction();
			}
		};
		selectFlabotFileAction.setImageDescriptor(DrarchPlugin
		        .getImageDescriptor("projects.gif"));
		selectFlabotFileAction.setToolTipText("Select Working Set");
		nextStepAction = new Action("Next Steps") {
			public void run() {
				doNextStepAction();
			}
		};
		nextStepAction.setImageDescriptor(DrarchPlugin.getImageDescriptor("ff.gif"));
		nextStepAction.setToolTipText("Next Step");

		importRuleAction = new Action("Import Rules") {
			public void run() {
				doimportRuleAction();
			}
		};
		importRuleAction.setImageDescriptor(DrarchPlugin.getImageDescriptor("import_wiz.gif"));
		importRuleAction.setToolTipText("Import Rule XML");

		chooseDefaultRulesFile = new Action("Use default rules") {
			public void run() {
				//TODO: sacar
				DrarchApplication.INSTANCE.getCurrentSession().setPathtoSelectedRulesSource("");
			}
		};

		analiceLog = new Action(" analize LogTrace ") {
			public void run() {
				KnowledgeBase knowledgeBase = DrarchApplication.INSTANCE.getDrarchEngine().getKnowledgeBase();
				QueryEngine queryEngine = DrarchApplication.INSTANCE.getDrarchEngine().getQueryEngine();
				
				AnaliceLogTraceAction analiceLog = new AnaliceLogTraceAction(queryEngine, knowledgeBase);
				analiceLog.run();
			}
		};
	}

    protected void doResetEngine() {
    	DrarchApplication.INSTANCE.resetEngine();
    	root = new TreeParent("");
    	treeViewer.setInput(root);
    	treeViewer.refresh();
    }

	protected void doimportRuleAction() {
		LoadRuleFileAction load = new LoadRuleFileAction();
		load.run();
		String fileName = load.getFileName();
		logger.debug("SuggestViewer: doImportRuleAction() file imported: " + fileName);
	}

	protected void doNextStepAction() {
		// TODO: Esto se podria mejorar
		stepManager = DrarchApplication.INSTANCE.getDrarchEngine().getStepsManager();
		if (stepManager.hasNext()) {
			try {
				stepManager.startStep();
				List currentStepSuggests = stepManager.getStepSuggests();

				ExecuteStepAction execStep = new ExecuteStepAction(
				        currentStepSuggests, stepManager.getNumberStep());
				execStep.run();
				setInput(execStep.getInPut());
				stepManager.nextStep();
			} catch (DrarchEngineModelException e) {
				logger.error("DrarchEngine Exception when executing startStep method of stepsManager", e);
				ErrorDialog.openError(shell, "ERROR", "DrarchEngine Exception when executing startStep method of stepsManager", null);
			}
		} else {
			MessageDialog.openWarning(shell, "No more Steps", "Warning! \n\nNo more iterations.");
			logger.debug("SuggestsViewer: no more iterations");
		}
	}
	protected void doSelectFlabotFileAction() {
		SelectFlabotFileAction action = new SelectFlabotFileAction();
		action.run();
	}

	private void addChildControl(Composite parent) {
		baseComposite = new Composite(parent, SWT.NONE);
		GridLayout composite1Layout = new GridLayout();
		composite1Layout.makeColumnsEqualWidth = true;
		baseComposite.setLayout(composite1Layout);

		GridData treeViewerLData = new GridData();
		treeViewerLData.grabExcessHorizontalSpace = true;
		treeViewerLData.grabExcessVerticalSpace = true;
		treeViewerLData.horizontalAlignment = GridData.FILL;
		treeViewerLData.verticalAlignment = GridData.FILL;
		tree = new Tree(baseComposite, SWT.BORDER | SWT.V_SCROLL);
		tree.setLayoutData(treeViewerLData);
		tree.setLayoutDeferred(true);
		createTree();
		createTreeViewer();
		treeViewer.setContentProvider(new SuggestTreeContentProvider());
		treeViewer.setLabelProvider(new SuggestTreeLabelProvider());
		treeViewer.setInput(root);
	}

	private void createTree() {
		TreeColumn column = new TreeColumn(tree, SWT.LEFT, 0);
		column.setText("Suggests");
		column.setWidth(800);
	}

	private void createTreeViewer() {
		treeViewer = new TreeViewer(tree);
		treeViewer.setUseHashlookup(true);
		CellEditor[] editors = new CellEditor[2];
		editors[0] = new CheckboxCellEditor(tree);
		treeViewer.setCellEditors(editors);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event
				        .getSelection();
				TreeObject node = (TreeObject) selection.getFirstElement();
				if (node != null) {
					node.setSelected(!node.isSelected());
					treeViewer.update(node, null);
					treeViewer.refresh();
				}
			}
		});
	}

	public void setInput(TreeParent input) {
		root.addChild(input);
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				treeViewer.setInput(root);
				treeViewer.refresh();
			}
		});
	}
}
