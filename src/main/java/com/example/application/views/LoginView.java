package com.example.application.views;

import com.example.application.service.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@RouteAlias(value = "")
@PageTitle("Login")
@AnonymousAllowed

public class LoginView extends VerticalLayout {

    private final AuthService authService;

    public LoginView(AuthService authService) {
        this.authService = authService;
        confLogin();
    }

    private void confLogin() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);


        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");


        RouterLink registerLink = new RouterLink("Register", RegisterView.class);
        add(
                new H1("Delivery App"), new H2("Login"),
                username,
                password,
                new Button("Send", event -> {
                    try {
                        login(
                                username.getValue(),
                                password.getValue()
                        );
                    } catch (Exception e) {
                        Notification notification = Notification.show("Username or Password are not correct!");
                        notification.setPosition(Notification.Position.TOP_END);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }
                }),
                registerLink
        );
    }

    private void login(String username, String password) throws AuthService.AuthException {
        if (username.trim().isEmpty()) {
            Notification.show("Enter an username");
        } else if (password.isEmpty()) {
            Notification.show("Enter a password");
        } else {
            authService.authenticate(username, password);
            UI.getCurrent().navigate("/delivery");
        }
    }
}
