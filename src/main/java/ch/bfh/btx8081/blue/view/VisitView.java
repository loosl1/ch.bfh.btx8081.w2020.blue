package ch.bfh.btx8081.blue.view;

import ch.bfh.btx8081.blue.presenter.VisitPresenter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
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
    private Button concludeVisit;
    private Button editChecklist;
    private Button gotoReport;
    private Button Goals;
    private Button dailyPlanning;

    /**
     * Constructor
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



        this.buttonList.setWidth("30%");
        this.titlePanel.setWidth("10%");
        this.checklist.add(this.titlePanel, this.checklistPanel);
        this.content.add(this.checklist, this.buttonList);

    }

    private void loadUIElements() {
    }
}
