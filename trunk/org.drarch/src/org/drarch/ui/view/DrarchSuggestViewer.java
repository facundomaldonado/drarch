/**
 * 
 */
package org.drarch.ui.view;

import org.apache.log4j.Logger;
import org.drarch.engine.stepEngine.IPhase;
import org.drarch.engine.stepEngine.Phase;
import org.drarch.engine.stepEngine.Step;
import org.drarch.ui.modelProviders.model.TreeObject;
import org.drarch.ui.modelProviders.model.TreeParent;
import org.drarch.ui.view.actions.CleanSuggestViewAction;
import org.drarch.ui.view.actions.ExecuteStepAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 * 
 */
public class DrarchSuggestViewer {

	private static Logger logger = Logger.getLogger(DrarchSuggestViewer.class
			.getName());

	private TreeViewer treeViewer;

	private Tree tree;

	private Composite baseComposite;

	private TreeParent root = new TreeParent("");

	private TreeParent currentParentNode = root;

	private Phase currentPhase = null;

	Action executeStepAction;

	Action cleanSuggestViewAction;

	private Text phaseNameText;

	private Label phaseNameLabel;

	/**
	 * 
	 */
	public DrarchSuggestViewer(Composite parent, IViewPart view) {
		logger.debug("SuggestViewer: creator");
		this.addChildControl(parent);
		this.createActions();
		this.createMenus(parent, view);
		currentParentNode.setValue(null);
	}

	/**
	 * Return the parent composite
	 */
	public Control getControl() {
		return baseComposite.getParent();
	}

	private void createMenus(Composite parent, IViewPart view) {
		IMenuManager menuMgr = view.getViewSite().getActionBars()
				.getMenuManager();

		MenuManager ucmMenu = new MenuManager("UCM");
		menuMgr.add(ucmMenu);

		// TODO: leer textos de un .properties
		MenuManager rulesMenu = new MenuManager("Drarch Actions");

		// add actions to menu
		menuMgr.add(new Separator());
		rulesMenu.add(executeStepAction);
		menuMgr.add(new Separator());
		rulesMenu.add(cleanSuggestViewAction);
		menuMgr.add(rulesMenu);

		// add actions to toolbar
		IToolBarManager toolMgr = view.getViewSite().getActionBars()
				.getToolBarManager();
		toolMgr.add(executeStepAction);
		toolMgr.add(cleanSuggestViewAction);
	}

	private void createActions() {
		executeStepAction = new ExecuteStepAction("Execute Step", this);
		cleanSuggestViewAction = new CleanSuggestViewAction(
				"Clean Suggest View", this);
	}

	private void addChildControl(Composite parent) {
		baseComposite = new Composite(parent, SWT.NONE);
		GridLayout baseCompositeLayout = new GridLayout();
		baseCompositeLayout.makeColumnsEqualWidth = true;
		baseCompositeLayout.numColumns = 3;
		baseComposite.setLayout(baseCompositeLayout);
		{
			Composite phaseComposite = new Composite(baseComposite, SWT.NONE);
			GridLayout phaseCompositeLayout = new GridLayout();
			phaseCompositeLayout.makeColumnsEqualWidth = true;
			phaseComposite.setLayout(phaseCompositeLayout);

			phaseNameLabel = new Label(phaseComposite, SWT.NONE);
			phaseNameText = new Text(phaseComposite, SWT.None);
			if (null != currentPhase) {
				updatePhaseFields(currentPhase);
			} else {
				phaseNameText.setVisible(false);
				phaseNameLabel.setText("Please Select a Phase");
			}
		}
		{
			GridData treeViewerLData = new GridData();
			treeViewerLData.grabExcessHorizontalSpace = true;
			treeViewerLData.grabExcessVerticalSpace = true;
			treeViewerLData.horizontalSpan = 2;
			treeViewerLData.horizontalAlignment = GridData.FILL;
			treeViewerLData.verticalAlignment = GridData.FILL;

			tree = new Tree(baseComposite, SWT.BORDER | SWT.V_SCROLL);
			tree.setLayoutData(treeViewerLData);
			tree.setLayoutDeferred(true);
			TreeColumn column = new TreeColumn(tree, SWT.LEFT, 0);
			column.setText("Suggests");
			column.setWidth(800);
			createTreeViewer();
			treeViewer.setContentProvider(new SuggestTreeContentProvider());
			treeViewer.setLabelProvider(new SuggestTreeLabelProvider());
			treeViewer.setInput(root);
		}
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

	/**
	 * parent node must be currentParentNode
	 */
	public void addChild(TreeObject child, TreeParent parent) {
		parent.addChild(child);
		if (child.getValue() instanceof IPhase) {
			currentParentNode = (TreeParent) child;
			currentPhase = (Phase) child.getValue();
		}
		if (child.getValue() instanceof Step) {
			currentParentNode = (TreeParent) child;
		}
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				treeViewer.refresh();
			}
		});
	}

	public TreeParent getCurrentParent() {
		return currentParentNode;
	}

	/**
	 * @param currentParentNode
	 *            the currentParentNode to set
	 */
	public void setCurrentParentNode(TreeParent currentParentNode) {
		this.currentParentNode = currentParentNode;
	}

	public void setActivePhase(Phase phase) {
		if (null != phase) {
			currentPhase = phase;
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					updatePhaseFields(currentPhase);
				}
			});
		} else {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					phaseNameText.setVisible(false);
					phaseNameLabel.setText("Please Select a Phase");
					root = new TreeParent("");
			    	treeViewer.setInput(root);
			    	treeViewer.refresh();
				}
			});
		}
	}

	private void updatePhaseFields(Phase phase) {
		phaseNameLabel.setText("Current phase: ");
		phaseNameText.setText(phase.getName());
		phaseNameText.setVisible(true);
	}
}
