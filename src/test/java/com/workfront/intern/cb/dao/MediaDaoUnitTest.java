package com.workfront.intern.cb.dao;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class MediaDaoUnitTest extends BaseTest {
    DataSource dataSource;
    MediaDao mediaDao;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        dataSource = Mockito.mock(DataSource.class);
        Connection conn = Mockito.mock(Connection.class);

        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        when(conn.prepareStatement(any(String.class), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(SQLException.class);

        mediaDao = new MediaDaoImpl(dataSource);
    }

    @After
    public void afterTest() {
    }

    @Test
    public void addPhoto_dbError() throws Exception {
        mediaDao.addPhoto(new Media());
    }

    @Test
    public void addVideo_dbError() throws Exception {
        mediaDao.addVideo(new Media());
    }

    @Test
    public void getMedia_dbError() throws Exception {
        mediaDao.getMediaById(NON_EXISTING_ID);
    }

    @Test
    public void getMediaListByManager_dbError() throws Exception {
        mediaDao.getMediaListByManager(NON_EXISTING_ID);
    }

    @Test
    public void getMediaListByTournament_dbError() throws Exception {
        mediaDao.getMediaListByTournament(NON_EXISTING_ID);
    }

    @Test
    public void updatePhoto_dbError() throws Exception {
        mediaDao.updatePhoto(NON_EXISTING_ID, new Media());
    }

    @Test
    public void updateVideo_dbError() throws Exception {
        mediaDao.updateVideo(NON_EXISTING_ID, new Media());
    }

    @Test
    public void deleteMedia() throws Exception {
        mediaDao.deleteMediaById(NON_EXISTING_ID);
    }

    @Test
    public void deleteAll_dbError() throws Exception {
        mediaDao.deleteAll();
    }
}
