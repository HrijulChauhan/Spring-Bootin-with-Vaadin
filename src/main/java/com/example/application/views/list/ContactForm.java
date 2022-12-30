package com.example.application.views.list;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class ContactForm extends FormLayout {
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField email = new TextField("Email");

    // Combo Box seems just to be a filterable list of options
    // name is same because of entity/contact. If same name, vaadin automatically binds them later
    ComboBox<Status> status = new ComboBox<Status>("Status");
    ComboBox<Company> company = new ComboBox<Company>("Company");
    // Company is in entity/Company

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    // parameterized constructor that takes 2 list as arguments
    public ContactForm(List<Company> companies, List<Status> statuses){
        addClassName("contact-form"); // css class name

        company.setItems(companies); // company is the combo box attribute it takes the List (that takes objects of class Company)
        company.setItemLabelGenerator(Company::getName); // setItems and the label generator are methods that exist in the ComboBox class

        status.setItems(statuses);
        status.setItemLabelGenerator(Status::getName);

        add( // this basically adds all the UI component to the specified layout
                firstName,
                lastName,
                email,
                company,
                status,
                createButtonsLayout() // just lines the button
        );
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // just some shortcuts
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        // returning three buttons in horizontal layout
        return new HorizontalLayout(save,delete,close);
    }
}
