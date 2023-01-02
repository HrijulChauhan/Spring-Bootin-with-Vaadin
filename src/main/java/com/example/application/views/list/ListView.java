package com.example.application.views.list;

import com.example.application.data.entity.Contact;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.awt.*;
import java.util.Collections;

//@PWA(name = "Dababy", shortName = "Baby");

@Route(value = "")
public class ListView extends VerticalLayout {

    // need a grid and a text field
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField(); // this is just a text field
    ContactForm form;

    public  ListView() {
        addClassName("list-view"); // this is a CSS class name for CSS later
        setSizeFull(); // basically making the view the entire browser window

        configureGrid(); // calling the method for configuration
        configureForm(); // helper method for configuring grid
        add(
          getToolbar(),
          getContent() // creating another helper method instead of just "grid"
        );
    }

    // creating a wrapper layout for form and grid in a horizontal manner
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid,form);

        content.addClassName("content");
        // used for spacing that is allocated
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        // instantiating the object form of class ContactForm
        // Calling the parameterized constructor , no DB connected as of yet,  so just passing 2 empty lists
        form = new ContactForm(Collections.emptyList(),Collections.emptyList());
        form.setWidth("25em"); // going to play around with this
    }

    // this is for filtering and stuff
    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("Add contact");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton); // 2 components will be next to each other
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName","lastName","email"); // takes names for the columns sidenote: I dont get this
        // you can only add the names that are declared in the contact class (entity package);

        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company"); // getting the company name
        grid.getColumns().forEach( col -> col.setAutoWidth(true)); // making them all properly sized
    }

}
