package com.example.application.views;

import com.example.application.views.categoriatarefa.CategoriaTarefaView;
import com.example.application.views.listadetarefas.ListadeTarefasView;
import com.example.application.views.prioridade.PrioridadeView;
import com.example.application.views.responsavel.ResponsavelView;
import com.example.application.views.status.StatusView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Span appName = new Span("Gerenciador de Tarefas");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Prioridade", PrioridadeView.class, LineAwesomeIcon.ALLERGIES_SOLID.create()));
        nav.addItem(new SideNavItem("Responsavel", ResponsavelView.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new SideNavItem("Status", StatusView.class, LineAwesomeIcon.BABY_SOLID.create()));
        nav.addItem(
                new SideNavItem("Categoria Tarefa", CategoriaTarefaView.class, LineAwesomeIcon.ARCHIVE_SOLID.create()));
        nav.addItem(new SideNavItem("Lista de Tarefas", ListadeTarefasView.class,
                LineAwesomeIcon.BOOK_OPEN_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}