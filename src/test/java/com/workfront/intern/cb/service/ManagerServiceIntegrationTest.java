package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.DBManager;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.ManagerDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ManagerServiceIntegrationTest extends BaseTest {
    private ManagerService managerService;
    private ManagerDao managerDao;

    // Test helper objects
    private Manager testManager;

    @Before
    public void beforeTest() throws Exception {
        DataSource dataSource = DBManager.getDataSource();
        managerDao = new ManagerDaoImpl(dataSource);
        Whitebox.setInternalState(managerDao, "dataSource", dataSource);

        managerService = new ManagerServiceImpl();
        Whitebox.setInternalState(managerService, "managerDao", managerDao);

        // Delete all remaining objects
        cleanUp();

        // Initialize random manager instance
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to database
        managerService.addManager(testManager);

        // Validate ID
        assertTrue(testManager.getId() > 0);
    }

    @After()
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        managerService.deleteAll();
    }

    // region <TEST CASES>

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void getManagerById_notFound() throws Exception {
    }

    @Test
    public void getManagerById_found() throws Exception {
        int targetId = testManager.getId();

        // Testing method
        Manager manager = managerService.getManagerById(targetId);
        assertNotNull(manager);
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void getManagerByLogin_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void getManagerByLogin_found() throws Exception {
    }

    @Ignore
    @Test
    public void getManagerList_emptyList() throws Exception {
    }

    @Ignore
    @Test
    public void getManagerList_found() throws Exception {
    }

    @Ignore
    @Test
    public void addManager_created() throws Exception {
    }

    @Ignore
    @Test
    public void updateManager() throws Exception {
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void deleteManagerById_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void deleteManagerById_deleted() throws Exception {
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
    }

    // endregion
}