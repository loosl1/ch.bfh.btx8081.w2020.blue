package ch.bfh.btx8081.blue.view;

import ch.bfh.btx8081.blue.model.Appointment;
import ch.bfh.btx8081.blue.presenter.CalendarPresenter;
import ch.bfh.btx8081.blue.presenter.VisitPresenter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@SuppressWarnings("serial")
@CssImport("styles/lumo-custom-dark-theme.css")
@CssImport("styles/custom-formatting.css")
@Route("VisitView")
@Theme(value = Lumo.class, variant = Lumo.DARK)

public class VisitView extends VerticalLayout {
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
    private Button bttEditChecklist;
    private Button btnGotoReport;
    private Button btnGoals;
    private Button btnDailyPlanning;
    private Label lblTitle;

    /**
     * Constructor empty
     */
    public VisitView() {
        addClassName("visit-view");
        loadUIElements();


        //topBar
        this.topBar.setAlignItems(Alignment.END);
        this.topBar.setPadding(true);
        this.topBar.add(
                //toDO add the elements for the topbar
        );
        this.buttonList.add(
        		this.btnConcludeVisit,
        	    this.bttEditChecklist,
        	    this.btnGotoReport,
        	    this.btnGoals,
        	    this.btnDailyPlanning
        		);
        
        this.titlePanel.add(
        		this.lblTitle);

        this.buttonList.setWidth("30%");
        this.titlePanel.setWidth("10%");

        this.checklist.add(this.titlePanel, this.checklistPanel);
        this.content.add(this.checklist, this.buttonList);
        this.listBox.setItems(this.presenter.setupChecklist());

    }

    private void loadUIElements() {
      //  this.presenter = new VisitPresenter(this);
		this.content = new HorizontalLayout();
		this.checklist = new HorizontalLayout();
		this.titlePanel = new VerticalLayout();
		this.buttonList = new HorizontalLayout();

		this.lblTitle = new Label();
		this.btnConcludeVisit = new Button();
	    this.bttEditChecklist = new Button();
	    this.btnGotoReport = new Button();
	    this.btnGoals = new Button();
	    this.btnDailyPlanning = new Button();
    }
}
