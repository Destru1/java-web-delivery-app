package com.example.application.views;


import com.example.application.data.Roles;
import com.example.application.data.Users;
import com.example.application.service.AuthService;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;


public class MainLayout extends AppLayout {
    private final AuthService authService;
    public MainLayout(AuthService authService) {
        this.authService = authService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Delivery APP");
        logo.addClassNames("text-l", "m-m");
        Button logout = new Button("Log out", e -> UI.getCurrent().navigate("/logout")) ;
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo,logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink listView = new RouterLink("Delivery", ListView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation());

        VerticalLayout sidebar = new VerticalLayout(
                listView
        );

        Users authenticatedUser = authService.getAuthenticatedUser();
        if (authenticatedUser.getRoleId() == Roles.ADMIN) {
            sidebar.add(new RouterLink("Dashboard", DashboardView.class));
        }

        addToDrawer(sidebar);

    }
}
