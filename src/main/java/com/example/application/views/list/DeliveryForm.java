package com.example.application.views.list;

import com.example.application.data.Courier;
import com.example.application.data.Delivery;
import com.example.application.data.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.events.ChartLoadEvent;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.shared.Registration;

import java.util.List;


public class DeliveryForm extends FormLayout {
    Binder<Delivery> binder = new BeanValidationBinder<>(Delivery.class);
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField city = new TextField("City");
    TextField address = new TextField("Address");
    TextField phoneNumber = new TextField("Phone number");

    ComboBox<Courier> courier = new ComboBox<>("Courier");
    ComboBox<Status> status = new ComboBox<>("Status");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Delivery delivery;

    public DeliveryForm(List<Courier> couriers, List<Status> statuses) {

        addClassNames("delivery-form");
        binder.bindInstanceFields(this);

        courier.setItems(couriers);
        courier.setItemLabelGenerator(Courier::getFullName);

        status.setItems(statuses);
        status.setItemLabelGenerator(Status::getName);

        add(
                firstName,
                lastName,
                city,
                address,
                phoneNumber,
                courier,
                status,
                createButtonLayout()
        );
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        binder.readBean(delivery);

    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this,delivery)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(delivery);
            fireEvent(new SaveEvent(this, delivery));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class DeliveryFormEvent extends ComponentEvent<DeliveryForm> {
        private Delivery delivery;


        protected DeliveryFormEvent(DeliveryForm source, Delivery delivery) {
            super(source, false);
            this.delivery = delivery;
        }

        public Delivery getDelivery() {
            return delivery;
        }
    }

    public static class SaveEvent extends DeliveryFormEvent {
        SaveEvent(DeliveryForm source, Delivery delivery) {
            super(source, delivery);
        }
    }

    public static class DeleteEvent extends DeliveryFormEvent {
        DeleteEvent(DeliveryForm source, Delivery delivery) {
            super(source, delivery);
        }
    }

    public static class CloseEvent extends DeliveryFormEvent {
        CloseEvent(DeliveryForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}

