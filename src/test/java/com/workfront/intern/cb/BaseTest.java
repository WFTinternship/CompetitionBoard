package com.workfront.intern.cb;

import com.workfront.intern.cb.common.Manager;

public class BaseTest {

    protected final int NON_EXISTING_ID = 9999;

    protected final String NON_EXISTING_LOGIN = "vJRLG8Z523sajdsad02";

    protected final String GENERIC_PASSWORD = "123456";

    protected final String MESSAGE_TEST_COMPLETED_OK = "Test completed successfully!";

    protected final String MESSAGE_TEST_COMPLETED_ERROR = "Test completed with errors :(";


    protected static Manager createRandomManager() {
        Manager testManager = new Manager();
        String MANAGER_LOGIN = "user_test";
        String MANAGER_PASSWORD = "123456";

        testManager = new Manager();
        testManager.setLogin(MANAGER_LOGIN);
        testManager.setPassword(MANAGER_PASSWORD);

        return testManager;
    }


}
