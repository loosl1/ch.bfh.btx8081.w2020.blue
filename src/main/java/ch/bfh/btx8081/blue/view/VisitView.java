package ch.bfh.btx8081.blue.view;

import ch.bfh.btx8081.blue.presenter.VisitPresenter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@SuppressWarnings("serial")
@CssImport("styles/lumo-custom-dark-theme.css")
@CssImport("styles/custom-formatting.css")
@Route("VisitView")
@Theme(value = Lumo.class, variant = Lumo.DARK)

public class VisitView extends VerticalLayout {

    //Elements
    String userInput;
    Binder binder;
    //Presenter
    private VisitPresenter presenter;
    //Layout
    private HorizontalLayout topBar;
    private HorizontalLayout content;
    private HorizontalLayout checklist;
    private HorizontalLayout buttonList;
    private Grid<VerticalLayout> layoutGrid;
    private VerticalLayout titlePanel;
    private VerticalLayout checklistPanel;
    //UI Objects
    private MultiSelectListBox<String> listBox;
    private Button btnConcludeVisit;
    private Button btnEditChecklist;
    private Button btnGotoReport;
    private Button btnGoals;
    private Button btnDailyPlanning;
    private Button btnConfirm;
    private Button btnCancel;
    private Label lblTitle;
    private Dialog dlgEditChecklist;
    private TextField txtEditChecklist;
    private Span msgEditChecklist;


    /**
     * Constructor empty
     */
    public VisitView() {
        addClassName("visit-view");
        loadUIElements();

        this.buttonList.add(
                this.btnConcludeVisit,
                this.btnEditChecklist,
                this.btnGotoReport,
                this.btnGoals,
                this.btnDailyPlanning
        );

        this.titlePanel.add(
                this.lblTitle
        );

        this.buttonList.setWidth("30%");
        this.titlePanel.setWidth("10%");

        this.checklist.add(this.titlePanel, this.checklistPanel);
        this.content.add(this.checklist, this.buttonList);
        this.listBox.setItems(this.presenter.setupChecklist());

        this.dlgEditChecklist.add(this.txtEditChecklist, this.msgEditChecklist, this.btnConfirm, this.btnCancel);

    }

    private void loadUIElements() {
        //  this.presenter = new VisitPresenter(this);

        //Layouts
        this.content = new HorizontalLayout();
        this.checklist = new HorizontalLayout();
        this.titlePanel = new VerticalLayout();
        this.buttonList = new HorizontalLayout();

        //Labels
        this.lblTitle = new Label();
        this.lblTitle.setClassName("title_label");

        //Buttons
        this.btnConcludeVisit = new Button("Besuch abschliessen",
                event -> this.presenter.concludeVisit(listBox.getSelectedItems())
        );
        this.btnEditChecklist = new Button("Checkliste bearbeiten", event -> {
            this.btnConfirm = new Button("Ok", event2 -> {
                this.userInput = this.txtEditChecklist.getValue().isEmpty() ? null : this.txtEditChecklist.getValue(); //toDo return a errormessage
                this.listBox.setValue(this.presenter.addChecklistItem(this.userInput));
                dlgEditChecklist.close();
            });

            this.btnCancel = new Button("Abbrechen", event2 -> {
                dlgEditChecklist.close();
            });
        });
        this.btnGotoReport = new Button();
        this.btnGoals = new Button();
        this.btnDailyPlanning = new Button();

        //Dialogs
        this.dlgEditChecklist = new Dialog();
        this.dlgEditChecklist.setCloseOnEsc(false);
        this.dlgEditChecklist.setCloseOnOutsideClick(false);

        //Textfields and Spans
        this.txtEditChecklist = new TextField();
        //toDo check if this is really the way to go
        binder = new Binder();
        binder.forField(txtEditChecklist)
                .withValidator(string -> string == null, "Bitte geben Sie einen Wert ein.");
        this.msgEditChecklist = new Span();

    }
}
