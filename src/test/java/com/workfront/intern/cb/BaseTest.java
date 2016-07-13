package com.workfront.intern.cb;

import com.workfront.intern.cb.common.Manager;

public class BaseTest {

    protected final int NON_EXISTING_ID = 9999;

    protected final String NON_EXISTING_LOGIN = "vJRLG8Z523sajdsad02";

    protected final String GENERIC_PASSWORD = "123456";

    protected final String MESSAGE_TEST_COMPLETED_OK = "Test completed successfully!";

    protected final String MESSAGE_TEST_COMPLETED_ERROR = "Test completed with errors :(";

    /**
     * Creates manager
     */
    protected Manager createRandomManager() {
        String managerLogin = "user_test";
        String managerPassword = "123456";

        Manager testManager = new Manager();
        testManager.setLogin(managerLogin);
        testManager.setPassword(managerPassword);

        return testManager;
    }
}
