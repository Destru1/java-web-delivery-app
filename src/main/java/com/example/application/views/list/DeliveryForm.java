package com.example.application.views.list;

import com.example.application.data.Courier;
import com.example.application.data.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;


public class DeliveryForm extends FormLayout {
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField address = new TextField("Address");
    TextField phoneNumber = new TextField("Phone number");

    ComboBox<Courier> courier = new ComboBox<>("Courier");
    ComboBox<Status> status = new ComboBox<>("Status");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public DeliveryForm(List<Courier> couriers, List<Status> statuses) {

        addClassNames("delivery-form");

        courier.setItems(couriers);
        courier.setItemLabelGenerator(Courier::getFullName);

        status.setItems(statuses);
        status.setItemLabelGenerator(Status::getName);

        add(
                firstName,
                lastName,
                address,
                phoneNumber,
                courier,
                status,
                createButtonLayout()
        );
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save,delete,cancel);
    }
}
