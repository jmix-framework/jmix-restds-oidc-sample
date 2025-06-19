package com.company.serviceapp.user;

import com.company.serviceapp.ServiceAppApplication;
import com.company.serviceapp.entity.User;
import com.company.serviceapp.test_support.BaseIntegrationTest;
import com.company.serviceapp.view.user.UserDetailView;
import com.company.serviceapp.view.user.UserListView;
import com.vaadin.flow.component.Component;
import io.jmix.core.DataManager;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.textfield.JmixPasswordField;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.data.grid.DataGridItems;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.testassist.FlowuiTestAssistConfiguration;
import io.jmix.flowui.testassist.UiTest;
import io.jmix.flowui.testassist.UiTestUtils;
import io.jmix.flowui.view.View;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Sample UI integration test for the User entity.
 */
@UiTest
@SpringBootTest(classes = {ServiceAppApplication.class, FlowuiTestAssistConfiguration.class})
public class UserUiTest extends BaseIntegrationTest {

    @Autowired
    ViewNavigators viewNavigators;

    @Test
    void test_createUser() {
        // Navigate to user list view
        viewNavigators.view(UiTestUtils.getCurrentView(), UserListView.class).navigate();

        UserListView userListView = UiTestUtils.getCurrentView();

        // Select a user in the table
        DataGrid<User> usersDataGrid = UiTestUtils.getComponent(userListView, "usersDataGrid");
        DataGridItems<User> usersDataGridItems = usersDataGrid.getItems();
        Assertions.assertNotNull(usersDataGridItems);
        usersDataGrid.select(usersDataGridItems.getItems().iterator().next());

        // click "Read" button
        JmixButton createBtn = UiTestUtils.getComponent(userListView, "readButton");
        createBtn.click();


        // Get detail view
        UserDetailView userDetailView = UiTestUtils.getCurrentView();

        // Click "OK"
        JmixButton commitAndCloseBtn = UiTestUtils.getComponent(userDetailView, "saveAndCloseButton");
        commitAndCloseBtn.click();

        // Get navigated user list view
        assertThat(UiTestUtils.getCurrentView()).isInstanceOf(UserListView.class);
    }
}
