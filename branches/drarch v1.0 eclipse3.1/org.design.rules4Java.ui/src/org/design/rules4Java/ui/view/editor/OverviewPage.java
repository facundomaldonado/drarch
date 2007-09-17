package org.design.rules4Java.ui.view.editor;

import org.design.rules4Java.engine.ruleModel.DrarchFileModel;
import org.design.rules4Java.engine.ruleModel.Fact;
import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.Query;
import org.design.rules4Java.engine.ruleModel.Rule;
import org.design.rules4Java.engine.ruleModel.RuleModelFactory;
import org.design.rules4Java.engine.ruleModel.Var;
import org.design.rules4Java.ui.UiPlugin;
import org.design.rules4Java.ui.view.dialogs.NewFactDialog;
import org.design.rules4Java.ui.view.editor.dialogs.QueryVarsDialog;
import org.design.rules4Java.ui.view.editor.dialogs.RuleDescriptionDialog;
import org.design.rules4Java.ui.view.modelProviders.SuggestTreeContentProvider;
import org.design.rules4Java.ui.view.modelProviders.SuggestTreeLabelProvider;
import org.design.rules4Java.ui.view.modelProviders.model.TreeObject;
import org.design.rules4Java.ui.view.modelProviders.model.TreeParent;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import java.util.Iterator;
import java.util.List;

public class OverviewPage extends DrarchFormPage {
  private static final String PAGE_TITLE = "Overview";
  private static final String PAGE_ID = "Overview Page";
  private FormToolkit toolkit;
  private SectionPart spart;
  private Text queryText;
  private org.eclipse.swt.widgets.List varsText;
  private Text suggestTemplateText;
  private org.eclipse.swt.widgets.List factsTemplateText;
  private Button deleteFactButton;
  private Composite ruleSectionClient;
  private Rule currentRule;
  private TreeViewer treeViewer;

  public OverviewPage(FormEditor editor, DrarchFileModel model) {
    super(editor, PAGE_ID, PAGE_TITLE, model);
  }

  protected void createFormContent(IManagedForm managedForm) {
    final ScrolledForm form = managedForm.getForm();
    toolkit = managedForm.getToolkit();
    form.setText("Overview");
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    layout.makeColumnsEqualWidth = true;
    form.getBody().setLayout(layout);
    Label label;
    GridData gd;

    // First section: General information.
    Section section = toolkit.createSection(form.getBody(), Section.DESCRIPTION
        | Section.EXPANDED | Section.FOCUS_TITLE);
    ImageHyperlink helpImage = new ImageHyperlink(section, SWT.PUSH);
    helpImage.setBackground(new Color(Display.getCurrent(), 255, 255, 255));
    Image helpIcon = UiPlugin.getImageDescriptor("help.gif").createImage();
    helpImage.setImage(helpIcon);
    final String helpId = "org.design.rules4Java.ui.buttonId";
    PlatformUI.getWorkbench().getHelpSystem().setHelp(helpImage, helpId);
    helpImage.addHyperlinkListener(new IHyperlinkListener() {
      public void linkActivated(HyperlinkEvent e) {
        PlatformUI.getWorkbench().getHelpSystem().displayHelp(helpId);
        // TODO: ??? no se por que no muestra la ayuda
      }

      public void linkExited(HyperlinkEvent e) {
      }

      public void linkEntered(HyperlinkEvent e) {
      }
    });
    section.setTextClient(helpImage);
    section.setToolTipText("Rules file information");
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.grabExcessHorizontalSpace = true;
    section.setLayoutData(gd);
    section.addExpansionListener(new ExpansionAdapter() {
      public void expansionStateChanged(ExpansionEvent e) {
        form.reflow(true);
      }
    });
    section.setText("General Information");
    toolkit.createCompositeSeparator(section);
    section.setDescription("This section describes general information "
        + "about this Rules file:");
    Composite sectionClient = toolkit.createComposite(section);
    TableWrapLayout clientLayout = new TableWrapLayout();
    clientLayout.numColumns = 3;
    clientLayout.makeColumnsEqualWidth = true;
    sectionClient.setLayout(clientLayout);
    spart = new SectionPart(section);
    managedForm.addPart(spart);
    label = toolkit.createLabel(sectionClient, "File Name :");
    TableWrapData td = new TableWrapData(TableWrapData.LEFT);
    label.setLayoutData(td);
    Text text = toolkit.createText(sectionClient, getModel().getFileName());
    td = new TableWrapData(TableWrapData.FILL_GRAB);
    td.grabHorizontal = true;
    td.colspan = 2;
    text.setLayoutData(td);
    label = toolkit.createLabel(sectionClient, "Rules count:");
    td = new TableWrapData(TableWrapData.LEFT);
    label.setLayoutData(td);
    DrarchFileModel model = getModel();
    if (model != null) {
      text = toolkit.createText(sectionClient, String.valueOf(model.getRules()
          .size()));
    } else {
      text = toolkit.createText(sectionClient, String.valueOf(0));
    }
    td = new TableWrapData(TableWrapData.FILL_GRAB);
    td.grabHorizontal = true;
    td.colspan = 2;
    text.setLayoutData(td);
    section.setClient(sectionClient);

    // Second section: Rule content.
    section = toolkit.createSection(form.getBody(), Section.DESCRIPTION
        | Section.EXPANDED | Section.FOCUS_TITLE);
    section.setToolTipText("Show selected rule information");
    gd = new GridData(GridData.FILL_BOTH);
    gd.grabExcessVerticalSpace = true;
    gd.grabExcessHorizontalSpace = true;
    gd.verticalSpan = 2;
    section.setLayoutData(gd);
    section.addExpansionListener(new ExpansionAdapter() {
      public void expansionStateChanged(ExpansionEvent e) {
        form.reflow(true);
      }
    });
    section.setText("Selected Rule content");
    toolkit.createCompositeSeparator(section);
    section.setDescription("The content is made up of three sections:");
    ruleSectionClient = toolkit.createComposite(section);
    GridLayout clientGridLayout = new GridLayout(1, true);
    ruleSectionClient.setLayout(clientGridLayout);

    // Query Section.
    Section subSection = toolkit.createSection(ruleSectionClient,
        Section.EXPANDED | Section.DESCRIPTION | Section.TREE_NODE);
    subSection.setToolTipText("Show the query and the selected variables");
    gd = new GridData(GridData.FILL_BOTH);
    gd.horizontalSpan = 2;
    subSection.setLayoutData(gd);
    subSection.setText("Query Description");
    toolkit.createCompositeSeparator(subSection);
    subSection.setDescription("Represent the query that will be executed");
    Composite querySectionClient = toolkit.createComposite(subSection);
    GridLayout queryClientGridLayout = new GridLayout();
    queryClientGridLayout.numColumns = 2;
    querySectionClient.setLayout(queryClientGridLayout);
    GridData LabelLData = new GridData();
    LabelLData.verticalAlignment = GridData.FILL;
    GridData TextLData = new GridData();
    TextLData.verticalSpan = 15;
    TextLData.verticalAlignment = GridData.FILL;
    TextLData.horizontalAlignment = GridData.FILL;
    TextLData.grabExcessHorizontalSpace = true;
    TextLData.grabExcessVerticalSpace = true;
    toolkit.createLabel(querySectionClient, "Query Text : ");
    queryText = toolkit.createText(querySectionClient, "", SWT.MULTI
        | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
    TextLData.verticalSpan = 5;
    queryText.setLayoutData(TextLData);
    queryText.setEditable(false);
    final Button setQueryButton;
    Button editQueryButton = toolkit.createButton(querySectionClient, "edit",
        SWT.NONE);
    setQueryButton = toolkit.createButton(querySectionClient, "set ", SWT.NONE);
    setQueryButton.setEnabled(false);
    setQueryButton.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent e) {
      }

      public void widgetSelected(SelectionEvent e) {
        String newQueryString = queryText.getText();
        Query q = currentRule.getQuery();
        if (q == null) {
          q = RuleModelFactory.eINSTANCE.createQuery();
          currentRule.setQuery(q);
        }
        q.setQueryString(newQueryString);
        spart.markDirty();
        queryText.setEditable(false);
        setQueryButton.setEnabled(false);
      }
    });
    editQueryButton.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent e) {
      }

      public void widgetSelected(SelectionEvent e) {
        queryText.setEditable(true);
        setQueryButton.setEnabled(true);
      }
    });
    Section varSubSection = toolkit.createSection(querySectionClient,
        Section.EXPANDED | Section.DESCRIPTION | Section.TREE_NODE);
    varSubSection.setToolTipText("get vars from query");
    gd = new GridData(GridData.FILL_BOTH);
    gd.horizontalSpan = 2;
    varSubSection.setLayoutData(gd);
    varSubSection.setText("Query Vars");
    toolkit.createCompositeSeparator(varSubSection);
    varSubSection.setDescription("Vars from query");
    Composite varsSectionClient = toolkit.createComposite(varSubSection);
    GridLayout varsClientGridLayout = new GridLayout();
    varsClientGridLayout.numColumns = 2;
    varsSectionClient.setLayout(varsClientGridLayout);

    toolkit.createLabel(varsSectionClient, "Choosen Vars : ");

    varsText = new org.eclipse.swt.widgets.List(varsSectionClient, SWT.BORDER
        | SWT.V_SCROLL);
    TextLData.verticalSpan = 15;
    varsText.setLayoutData(TextLData);

    Button varsButton = toolkit.createButton(varsSectionClient, "get Vars",
        SWT.NONE);
    varsButton.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        QueryVarsDialog queryVarsDialog = new QueryVarsDialog(currentRule
            .getQuery().getQueryString());
        queryVarsDialog.open();
        List vars = queryVarsDialog.getVars();
        if (vars.size() > 0) {
          String[] values = {};
          varsText.setItems(values);
          Query q = currentRule.getQuery();
          if (q == null) {
            q = RuleModelFactory.eINSTANCE.createQuery();
            currentRule.setQuery(q);
          }
          q.getChosenVars().clear();
          Iterator varI = vars.iterator();
          while (varI.hasNext()) {
            String var = (String) varI.next();
            varsText.add(var);
            Var v = RuleModelFactory.eINSTANCE.createVar();
            v.setVarText(var);
            q.getChosenVars().add(v);
          }
          spart.markDirty();
        }
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });
    varSubSection.setClient(varsSectionClient);
    subSection.setClient(querySectionClient);

    // Suggest Section.
    subSection = toolkit.createSection(ruleSectionClient, Section.EXPANDED
        | Section.DESCRIPTION | Section.TREE_NODE);
    subSection.setToolTipText("Show the suggest Template for the current rule");
    gd = new GridData(GridData.FILL_BOTH);
    gd.horizontalSpan = 2;
    subSection.setLayoutData(gd);
    subSection.setText("Suggest Description");
    toolkit.createCompositeSeparator(subSection);
    subSection.setDescription("Suggest Template Description");
    Composite suggestSectionClient = toolkit.createComposite(subSection);
    GridLayout suggestClientGridLayout = new GridLayout();
    suggestClientGridLayout.numColumns = 2;
    suggestSectionClient.setLayout(suggestClientGridLayout);
    toolkit.createLabel(suggestSectionClient, "Suggest Template : ");
    suggestTemplateText = toolkit.createText(suggestSectionClient, "",
        SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
    suggestTemplateText.setLayoutData(TextLData);
    suggestTemplateText.setEditable(false);
    final Button setSuggestButton;
    Button editSuggestButton = toolkit.createButton(suggestSectionClient,
        "edit", SWT.NONE);
    setSuggestButton = toolkit.createButton(suggestSectionClient, "set ",
        SWT.NONE);
    setSuggestButton.setEnabled(false);
    setSuggestButton.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent e) {
      }

      public void widgetSelected(SelectionEvent e) {
        String newSuggest = suggestTemplateText.getText();
        currentRule.setSuggestTemplate(newSuggest);
        spart.markDirty();
        setSuggestButton.setEnabled(false);
      }
    });
    editSuggestButton.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent e) {
      }

      public void widgetSelected(SelectionEvent e) {
        suggestTemplateText.setEditable(true);
        setSuggestButton.setEnabled(true);
      }
    });
    subSection.setClient(suggestSectionClient);

    // Facts Section.
    subSection = toolkit.createSection(ruleSectionClient, Section.EXPANDED
        | Section.DESCRIPTION | Section.TREE_NODE);
    subSection.setToolTipText("Show the facts templates in order of execution");
    gd = new GridData(GridData.FILL_BOTH);
    gd.horizontalSpan = 2;
    subSection.setLayoutData(gd);
    subSection.setText("Facts Description");
    toolkit.createCompositeSeparator(subSection);
    subSection.setDescription("Facts Templates Description");
    Composite factsSectionClient = toolkit.createComposite(subSection);
    GridLayout factsClientGridLayout = new GridLayout();
    factsClientGridLayout.numColumns = 2;
    factsSectionClient.setLayout(factsClientGridLayout);
    toolkit.createLabel(factsSectionClient, "Facts Templates : ");
    factsTemplateText = new org.eclipse.swt.widgets.List(factsSectionClient,
        SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
    factsTemplateText.setLayoutData(TextLData);
    factsTemplateText.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        deleteFactButton.setEnabled(true);
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });
    Button addFactButton = toolkit.createButton(factsSectionClient, " New  ",
        SWT.NONE);
    addFactButton.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        NewFactDialog factDialog = new NewFactDialog();
        factDialog.open();
        String factText = factDialog.getFact();
        Fact fact = RuleModelFactory.eINSTANCE.createFact();
        fact.setFactText(factText);
        FactSet factSet = currentRule.getFactSet();
        if (factSet == null) {
          FactSet newFactSet = RuleModelFactory.eINSTANCE.createFactSet();
          currentRule.setFactSet(newFactSet);
        }
        currentRule.getFactSet().getFactTemplates().add(fact);
        factsTemplateText.add(factText);
        spart.markDirty();
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });
    deleteFactButton = toolkit.createButton(factsSectionClient, "Remove",
        SWT.NONE);
    deleteFactButton.setEnabled(false);
    deleteFactButton.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        String[] selectedFact = factsTemplateText.getSelection();
        for (int i = 0; i < selectedFact.length; i++) {
          currentRule.getFactSet().getFactTemplates().remove(i);
          factsTemplateText.remove(i);
        }
        spart.markDirty();
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });
    subSection.setClient(factsSectionClient);
    section.setClient(ruleSectionClient);

    // Rules information.
    section = toolkit.createSection(form.getBody(), Section.DESCRIPTION
        | Section.EXPANDED | Section.FOCUS_TITLE);
    section.setToolTipText("Show the availables rules in the selected file");
    gd = new GridData(GridData.FILL_BOTH);
    gd.widthHint = 200;
    gd.grabExcessHorizontalSpace = true;
    section.setLayoutData(gd);
    section.addExpansionListener(new ExpansionAdapter() {
      public void expansionStateChanged(ExpansionEvent e) {
        form.reflow(true);
      }
    });
    section.setText("Rules Information");
    toolkit.createCompositeSeparator(section);
    section
        .setDescription("This section describes general information about this Rules file:");
    sectionClient = toolkit.createComposite(section);
    GridLayout rulesClientGridLayout = new GridLayout(3, true);
    sectionClient.setLayout(rulesClientGridLayout);
    spart = new SectionPart(section);
    managedForm.addPart(spart);
    label = toolkit.createLabel(sectionClient, "Rules Available:");
    gd = new GridData(GridData.BEGINNING);
    gd.horizontalSpan = 3;
    label.setLayoutData(gd);
    spart = new SectionPart(section);
    managedForm.addPart(spart);
    Tree tree = toolkit.createTree(sectionClient, SWT.BORDER | SWT.V_SCROLL);
    gd = new GridData(GridData.FILL_BOTH);
    gd.horizontalSpan = 2;
    gd.verticalSpan = 3;
    tree.setLayoutData(gd);
    treeViewer = new TreeViewer(tree);
    treeViewer.setUseHashlookup(true);
    treeViewer.setAutoExpandLevel(TreeViewer.ALL_LEVELS);
    treeViewer.setContentProvider(new SuggestTreeContentProvider());
    treeViewer.setLabelProvider(new SuggestTreeLabelProvider());
    treeViewer.setInput(getRoot());
    treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      public void selectionChanged(SelectionChangedEvent event) {
        IStructuredSelection selection = (IStructuredSelection) event
            .getSelection();
        TreeObject node = (TreeObject) selection.getFirstElement();
        if (node != null) {
          if ((!node.getName().equals("Rules"))
              && (node.getName().startsWith("Rule :"))) {
            currentRule = (Rule) node.getValue();
            setValues();
          }
        }
      }
    });

    Button addRule = toolkit.createButton(sectionClient, "Add Rule", SWT.PUSH);
    gd = new GridData(GridData.CENTER);
    addRule.setLayoutData(gd);
    addRule.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent e) {
      }

      public void widgetSelected(SelectionEvent e) {
        RuleDescriptionDialog descriptionDialog = new RuleDescriptionDialog();
        descriptionDialog.open();
        String description = descriptionDialog.getdescription();
        if (!description.equals("")) {
          Rule newRule = RuleModelFactory.eINSTANCE.createRule();
          newRule.setDescription(description);
          DrarchFileModel curr_model = getModel();
          if (curr_model != null) {
            getModel().getRules().add(newRule);
            treeViewer.setInput(getRoot());
            spart.markDirty();
          }
        }
      }
    });
    Button editRule = toolkit
        .createButton(sectionClient, "Edit Rule", SWT.PUSH);
    gd = new GridData(GridData.CENTER);
    editRule.setLayoutData(gd);
    editRule.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        ruleSectionClient.setEnabled(true);
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });

    // Remove button.
    Button removeRule = toolkit
        .createButton(sectionClient, "Remove ", SWT.PUSH);
    gd = new GridData(GridData.CENTER);
    removeRule.setLayoutData(gd);
    removeRule.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent e) {
      }

      public void widgetSelected(SelectionEvent e) {
        IStructuredSelection selection = (IStructuredSelection) treeViewer
            .getSelection();
        TreeObject node = (TreeObject) selection.getFirstElement();
        if (node.getValue() instanceof Rule) {
          Rule currentRule = (Rule) node.getValue();
          getModel().getRules().remove(currentRule);
          treeViewer.setInput(getRoot());
          spart.markDirty();
        } else {
          MessageDialog.openError(form.getShell(), "Error: Deleting Rule",
              "Select a rule please");
        }
      }
    });
    section.setClient(sectionClient);
  }

  protected void setValues() {
    clearValues();
    Query query = currentRule.getQuery();
    if (query != null) {
      queryText.setText(currentRule.getQuery().getQueryString());
      List<Var> varList = query.getChosenVars();
      if (varList != null) {
        for (Iterator<Var> varI = currentRule.getQuery().getChosenVars()
            .iterator(); varI.hasNext();) {
          varsText.add(varI.next().getVarText());
        }
      }
    }
    suggestTemplateText.setText(currentRule.getSuggestTemplate());
    FactSet currentRuleFactSet = currentRule.getFactSet();
    if (currentRuleFactSet != null) {
      List<Fact> factList = currentRule.getFactSet().getFactTemplates();
      if (factList != null) {
        for (Iterator<Fact> factI = factList.iterator(); factI.hasNext();) {
          factsTemplateText.add(factI.next().getFactText());
        }
      }
    }
  }

  private void clearValues() {
    queryText.setText("".trim());
    String[] values = {};
    varsText.setItems(values);
    suggestTemplateText.setText("".trim());
    factsTemplateText.setItems(values);
  }

  private TreeParent getRoot() {
    TreeParent root = new TreeParent("");
    if (getModel() != null) {
      for (Iterator<Rule> i = getModel().getRules().iterator(); i.hasNext();) {
        int index = 0;
        Rule r = i.next();
        TreeParent node = new TreeParent("Rule : " + index++);
        node.setValue(r);
        TreeObject descriptionNode = new TreeObject(r.getDescription());
        node.addChild(descriptionNode);
        root.addChild(node);
      }
    }
    return root;
  }
}
