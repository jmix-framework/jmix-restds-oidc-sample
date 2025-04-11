package com.company.frontendapp.view.customer;

import com.company.frontendapp.entity.Customer;
import com.company.frontendapp.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "customers", layout = MainView.class)
@ViewController(id = "Customer.list")
@ViewDescriptor(path = "customer-list-view.xml")
@LookupComponent("customersDataGrid")
@DialogMode(width = "50em")
public class CustomerListView extends StandardListView<Customer> {

}
