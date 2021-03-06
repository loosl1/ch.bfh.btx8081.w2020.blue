package ch.bfh.btx8081.blue.view;

import ch.bfh.btx8081.blue.exceptions.AppointmentNotFoundException;
import ch.bfh.btx8081.blue.model.Item;
import ch.bfh.btx8081.blue.presenter.VisitPresenter;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.util.Objects;

@SuppressWarnings("serial")
@CssImport("styles/lumo-custom-dark-theme.css")
@CssImport("styles/custom-formatting.css")
@Route("VisitView")
@Theme(value = Lumo.class, variant = Lumo.DARK)

public class VisitView extends VerticalLayout implements HasUrlParameter<String> {

    //Elements
    String userInput;
    Binder<Item> binder;
    //Presenter
    private final VisitPresenter presenter;
    //Layout
    private HorizontalLayout content;
    private VerticalLayout checklist;
    private VerticalLayout buttonList;
    private VerticalLayout titlePanel;
    private VerticalLayout checklistPanel;
    //UI Objects
    private CheckboxGroup<String> checkBox;
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
    private String parameter;


    /**
     * Constructor empty
     */
    public VisitView() {

        this.presenter = new VisitPresenter(this, this.parameter);
        addClassName("visit-view");
        loadUIElements();

        this.buttonList.add(
                this.btnConcludeVisit,
                this.btnEditChecklist,
                this.btnGotoReport,
                this.btnGoals,
                this.btnDailyPlanning
        );

        this.titlePanel.add(this.lblTitle);
        this.buttonList.setWidth("30%");
        this.titlePanel.setWidth("100%");
        this.content.setWidth("100%");
        this.checkBox.setItems(this.presenter.setupChecklist());
        this.checklistPanel.add(checkBox);
        this.checklist.add(this.titlePanel, this.checklistPanel);
        this.content.add(this.checklist, this.buttonList);
        this.dlgEditChecklist.add(this.txtEditChecklist, this.msgEditChecklist, this.btnConfirm, this.btnCancel);
        add(this.content);
        System.out.println("--- Finish VisitView()");
    }

    private void loadUIElements() {
        //Layouts
        this.content = new HorizontalLayout();
        this.checklist = new VerticalLayout();
        this.checklist.setClassName("checklist-container");
        this.titlePanel = new VerticalLayout();
        this.buttonList = new VerticalLayout();
        this.buttonList.setClassName("buttonList-container");
        this.checklistPanel = new VerticalLayout();


        //Labels
        this.lblTitle = new Label();
        this.lblTitle.setClassName("title_label");
        this.lblTitle.setClassName("title_label_h1");
        this.lblTitle.setText(this.presenter.displayHeader());

        //Dialogs
        this.dlgEditChecklist = new Dialog();
        this.dlgEditChecklist.setCloseOnEsc(true);
        this.dlgEditChecklist.setCloseOnOutsideClick(true);

        //Buttons
        this.checkBox = new CheckboxGroup<>();
        this.checkBox.setClassName("checklist-items");
        this.checkBox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        this.btnConcludeVisit = new Button("Besuch abschliessen",
                event -> {
                    try {
                        this.presenter.concludeVisit(checkBox.getSelectedItems());
                    } catch (AppointmentNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );
        this.btnEditChecklist = new Button("Checkliste bearbeiten", event -> dlgEditChecklist.open());
        this.btnConfirm = new Button("Ok", event2 -> {
            this.userInput = this.txtEditChecklist.getValue().isEmpty() ? null : this.txtEditChecklist.getValue(); //toDo return a errormessage
            assert this.userInput != null;
            if (!this.userInput.isEmpty()) {
                this.checkBox.setItems(this.presenter.addChecklistItem(this.userInput));
                this.userInput = "";
            }
            dlgEditChecklist.close();
        });
        this.btnCancel = new Button("Abbrechen", event2 -> dlgEditChecklist.close());
        this.btnGotoReport = new Button("Zum Rapport");
        this.btnGoals = new Button("Ziele");
        this.btnDailyPlanning = new Button("Zur Tagesplanung", event3 -> UI.getCurrent().navigate(""));


        //Textfields and Spans
        this.txtEditChecklist = new TextField();

        binder = new Binder<>();
        binder.forField(txtEditChecklist)
                .withValidator(Objects::isNull, "Bitte geben Sie einen Wert ein.");
        this.msgEditChecklist = new Span();

    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        if (parameter.isEmpty()) {
            System.out.println("is Empty");
        } else {
            System.out.println("Load Parameter" + parameter);
            this.parameter = parameter;
        }
    }
}
