package com.example.application.views.list;

import com.example.application.data.Delivery;
import com.example.application.service.CourierService;
import com.example.application.service.DeliveryService;
import com.example.application.service.StatusesService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.awt.*;
import java.util.Collections;

@PageTitle("Deliveries")
@Route(value = "")
public class ListView extends VerticalLayout {

   private final DeliveryService deliveryService;
    private final CourierService courierService;
    private final StatusesService statusesService;
    Grid<Delivery> grid = new Grid<Delivery>(Delivery.class);
    TextField filterText = new TextField();

    DeliveryForm form;

    public ListView(DeliveryService deliveryService, CourierService courierService, StatusesService statusesService) {
        this.deliveryService = deliveryService;
        this.courierService = courierService;
        this.statusesService = statusesService;

        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()

        );

        updateList();
    }

    private void updateList() {
    grid.setItems(deliveryService.findAllDeliveries(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);

        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new DeliveryForm(courierService.findAllCouriers(), statusesService.findAllStatuses());
        form.setWidth("25em");

    }

    private Component getToolbar() {

        filterText.setPlaceholder("Filter by name: ");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addDeliveryButton = new Button("Add delivery");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addDeliveryButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("delivery-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "address", "phoneNumber");
        grid.addColumn(delivery -> delivery.getCourier().getFullName()).setHeader("Courier");
        grid.addColumn(delivery -> delivery.getStatus().getName()).setHeader("Status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

}
