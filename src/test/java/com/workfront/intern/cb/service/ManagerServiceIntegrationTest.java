package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.ServiceSpringConfigTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceSpringConfigTest.class)
public class ManagerServiceIntegrationTest extends BaseTest {

    @Autowired
    private ManagerService managerService;

//     private ManagerDao managerDao;

    // Test helper objects
    private Manager testManager;

    @Before
    public void beforeTest() throws Exception {
//        DataSource dataSource = DBManager.getDataSource();
//        managerService = new ManagerServiceImpl();
//        Whitebox.setInternalState(managerService, "dataSource", dataSource);

        managerService = new ManagerServiceImpl();
        Whitebox.setInternalState(testManager, "managerService", managerService);

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