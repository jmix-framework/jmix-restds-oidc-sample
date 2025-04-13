package com.company.clientapp.security;

import com.company.clientapp.entity.Customer;
import com.company.clientapp.entity.Order;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "EmployeeRole", code = EmployeeRole.CODE)
public interface EmployeeRole extends UiMinimalRole {
    String CODE = "employee";

    @EntityAttributePolicy(entityClass = Customer.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Customer.class, actions = EntityPolicyAction.ALL)
    void customer();

    @MenuPolicy(menuIds = {"Customer.list", "Order_.list"})
    @ViewPolicy(viewIds = {"Customer.list", "Customer.detail", "Order_.list", "Order_.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = Order.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Order.class, actions = EntityPolicyAction.ALL)
    void order();
}