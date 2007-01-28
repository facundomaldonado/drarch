package org.design.drarch.views;

import java.util.List;

import org.design.drarch.Application;
import org.design.drarch.DrarchPlugin;
import org.design.drarch.diagram.trace.action.AnaliceLogTraceAction;
import org.design.drarch.manager.StepsManagerImpl;
import org.design.drarch.views.action.ExecuteStepAction;
import org.design.drarch.views.action.SelectWorkingSetAction;
import org.design.rules4Java.engine.engine.StepsManager;
import org.design.rules4Java.ui.view.action.LoadRuleFileAction;
import org.design.rules4Java.ui.view.modelProviders.SuggestTreeContentProvider;
import org.design.rules4Java.ui.view.modelProviders.SuggestTreeLabelProvider;
import org.design.rules4Java.ui.view.modelProviders.model.TreeObject;
import org.design.rules4Java.ui.view.modelProviders.model.TreeParent;
import org.design.rules4Java.util.Util;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;



public class SuggestsViewer {

	private TreeViewer treeViewer;
	private Tree tree;
	private Composite baseComposite;
	private TreeParent root=new TreeParent("");
	private IWorkingSetManager workingSetManager;
	private Action selectWorkingSetAction;
	private Action nextStepAction;
	private Action importRuleAction;
	private Action chooseDefaultRulesFile;
	private Action analiceLog;

	public Application app = Application.getInstance();
	
	private StepsManager stepManager;
	

	public SuggestsViewer(Composite parent, IViewPart view) {
		this.workingSetManager= Application.getInstance().getWorkingSetManager();
		this.addChildControl(parent);
		this.createActions();
		this.createMenus(parent,view);
	}
	/**
	 * Return the parent composite
	 */
	public Control getControl() {
		return baseComposite.getParent();
	}
	
	private void createMenus(Composite parent, IViewPart view){
		IMenuManager menuMgr= view.getViewSite().getActionBars().getMenuManager();
		
		MenuManager ucmMenu = new MenuManager("UCM");
		//ucmMenu.add(new GenerateUCM("Generate UCM"));
		menuMgr.add(ucmMenu);
		
		MenuManager rulesMenu = new MenuManager("Drarch Actions");
		
		// add actions to menu
		//menuMgr.add(importRuleAction);
		menuMgr.add(new Separator());
		rulesMenu.add(selectWorkingSetAction);
		rulesMenu.add(new Separator());
		rulesMenu.add(nextStepAction);
		rulesMenu.add(analiceLog);
		menuMgr.add(rulesMenu);
		
		MenuManager importMenu= new MenuManager("Rules");
		importMenu.add(chooseDefaultRulesFile);
		importMenu.add(importRuleAction);
		
		menuMgr.add(new Separator());
		menuMgr.add(importMenu);
		
		//add actions to toolbar
		IToolBarManager toolMgr = view.getViewSite().getActionBars().getToolBarManager();
		toolMgr.add(importRuleAction);
		toolMgr.add(new Separator());
		toolMgr.add(selectWorkingSetAction);
		toolMgr.add(nextStepAction);
		
	}
	

	private void createActions(){
		
		selectWorkingSetAction = new Action("Select WorkingSet"){
			public void run() {
				doSelectWorkingSetAction();
			}

		};
		selectWorkingSetAction.setImageDescriptor(DrarchPlugin.getImageDescriptor("projects.gif"));
		selectWorkingSetAction.setToolTipText("Select Working Set");
		nextStepAction= new Action("Next Steps"){
			public void run(){
				doNextStepAction();
			}
		};
		nextStepAction.setImageDescriptor(DrarchPlugin.getImageDescriptor("ff.gif"));
		nextStepAction.setToolTipText("Next Step");
		
		
		importRuleAction = new Action("Import Rules"){
			public void run() {
				doimportRuleAction();
			}
		};
		importRuleAction.setImageDescriptor(DrarchPlugin.getImageDescriptor("import_wiz.gif"));
		importRuleAction.setToolTipText("Import Rule XML");
		
		chooseDefaultRulesFile= new Action("Use default rules"){
			public void run(){
				Util.getInstance().setExternalFilePath("");
			}
		};
		
		analiceLog= new Action(" analize LogTrace "){
			public void run(){
				AnaliceLogTraceAction analiceLog=new AnaliceLogTraceAction();
				analiceLog.run();
			}
		};
	}
	
	protected void doimportRuleAction() {
		LoadRuleFileAction load= new LoadRuleFileAction();
		load.run();
		String fileName=load.getFileName();
		System.out.println(fileName);
	}
	
	protected void doNextStepAction() {
		//TODO: Esto se podira mejorar
		stepManager= StepsManagerImpl.getInstance();
		if (stepManager.hasNext()){
			
			stepManager.startStep();
			List currentStepSuggests = stepManager.getStepSuggests();
			
			ExecuteStepAction execStep=new ExecuteStepAction(currentStepSuggests, stepManager.getNumberStep());
			execStep.run();
			setInput(execStep.getInPut());
			stepManager.nextStep();
		}
		else{
			System.out.println("No more steps: SuggestsViewer.java");
		}
	}
	
	protected void doSelectWorkingSetAction() {
		String[] buttons = {"OK"};
		MessageDialog message = new MessageDialog( 	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
													"Information Message" , null, "Important! \n\nOnly select the .java files." , 0, buttons, 0);
		message.open();
		
		SelectWorkingSetAction action= new SelectWorkingSetAction(workingSetManager);
		action.run();
		if (action.getWorkingSet() != null){
			root= new TreeParent("");
			treeViewer.setInput(root);
		  	treeViewer.refresh();
		}		
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
		tree = new Tree(baseComposite,SWT.BORDER | SWT.V_SCROLL);
		tree.setLayoutData(treeViewerLData);
		tree.setLayoutDeferred(true);
		createTree();
		createTreeViewer();
		treeViewer.setContentProvider(new SuggestTreeContentProvider());
		treeViewer.setLabelProvider(new SuggestTreeLabelProvider());
		treeViewer.setInput(root);
	}
	
	private void createTree(){
		TreeColumn column=new TreeColumn(tree,SWT.LEFT,0);
		column.setText("Suggests");
		column.setWidth(800);
	}
	
	private void createTreeViewer() {
		treeViewer= new TreeViewer(tree);
		treeViewer.setUseHashlookup(true);
		CellEditor[] editors = new CellEditor[2];
		editors[0] = new CheckboxCellEditor(tree);
		treeViewer.setCellEditors(editors);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener(){

			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				TreeObject node=(TreeObject)selection.getFirstElement();
				if (node!=null){
					if (node instanceof TreeParent) {
						node = (TreeParent) node;
					}
					node.setSelected(!node.isSelected());
					treeViewer.update(node,null);
					treeViewer.refresh();
				}
			}
			
		});
	}
	
	public void setInput(TreeParent input){
		root.addChild(input);
		PlatformUI.getWorkbench().getDisplay().asyncExec( new Runnable(){
		      public void run () {
                treeViewer.setInput(root);
		  		treeViewer.refresh();
		      }
		   });
	}
}
