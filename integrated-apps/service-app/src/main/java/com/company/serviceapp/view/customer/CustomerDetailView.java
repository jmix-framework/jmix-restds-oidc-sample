package com.company.serviceapp.view.customer;

import com.company.serviceapp.entity.Customer;
import com.company.serviceapp.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "customers/:id", layout = MainView.class)
@ViewController(id = "Customer.detail")
@ViewDescriptor(path = "customer-detail-view.xml")
@EditedEntityContainer("customerDc")
public class CustomerDetailView extends StandardDetailView<Customer> {
}