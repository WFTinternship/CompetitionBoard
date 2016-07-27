package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaDaoImpl extends GenericDao implements MediaDao {
    private static final Logger LOG = Logger.getLogger(MediaDaoImpl.class);

    public MediaDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets specific media(photo or video) by id
     */
    @Override
    public Media getMediaById(int id) throws FailedOperationException, ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Media media = null;

        String sql = "SELECT * FROM media WHERE media_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute statement
            rs = ps.executeQuery();
            if (rs.next()) {
                media = extractMediaFromResultSet(rs);
            } else {
                throw new ObjectNotFoundException(String.format("Media with id[%d] not found", id));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return media;
    }

    /**
     * Gets all media by specific manager
     */
    @Override
    public List<Media> getMediaListByManager(int id) throws FailedOperationException {
        List<Media> mediaByManagerList;
        String sql = "SELECT * FROM media WHERE manager_id=?";
        mediaByManagerList = getSpecificMediaList(sql, id);

        return mediaByManagerList;
    }

    /**
     * Gets all media by specific tournament
     */
    @Override
    public List<Media> getMediaListByTournament(int id) throws FailedOperationException {
        List<Media> mediaByTournamentList;
        String sql = "SELECT * FROM media WHERE tournament_id=?";
        mediaByTournamentList = getSpecificMediaList(sql, id);

        return mediaByTournamentList;
    }

    /**
     * Updates photo
     */
    @Override
    public void updatePhoto(int id, Media media) throws FailedOperationException {
        String sql = "UPDATE media SET photo=?, video=?, tournament_id=?, manager_id=? WHERE media_id=?";
        updateSpecificMedia(id, sql, media);
    }

    /**
     * Updates video
     */
    @Override
    public void updateVideo(int id, Media media) throws FailedOperationException {
        String sql = "UPDATE media SET photo=?, video=?, tournament_id=?, manager_id=? WHERE media_id=?";
        updateSpecificMedia(id, sql, media);
    }

    /**
     * Adding photo to db by specific manager and tournament
     */
    @Override
    public Media addPhoto(Media media) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO media(photo, video, tournament_id, manager_id) VALUES(?,?,?,?)";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, media.getPhoto());
            ps.setString(2, media.getVideo());
            ps.setInt(3, media.getTournamentId());
            ps.setInt(4, media.getManagerId());

            // Execute statement
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                media.setMediaId(rs.getInt(1));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return media;
    }

    /**
     * Adds video to db by specific manager and tournament
     */
    @Override
    public Media addVideo(Media media) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO media(photo, video, tournament_id, manager_id) VALUES(?, ?, ?, ?)";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, media.getPhoto());
            ps.setString(2, media.getVideo());
            ps.setInt(3, media.getTournamentId());
            ps.setInt(4, media.getManagerId());

            // Execute statement
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                media.setMediaId(rs.getInt(1));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return media;
    }


    /**
     * Deletes media by id
     */
    @Override
    public void deleteMediaById(int id) throws ObjectNotFoundException, FailedOperationException {
        String sql = "DELETE FROM media WHERE media_id=?";
        deleteEntry(sql, id);
    }

    /**
     * Deletes all media
     */
    @Override
    public void deleteAll() throws FailedOperationException {
        String sql = "DELETE FROM media";
        deleteAllEntries(sql);
    }

    /**
     * Gets specific data list of deleteMediaById from sql query
     */
    private List<Media> getSpecificMediaList(String sql, int id) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Media media;
        List<Media> mediaList = new ArrayList<>();

        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute statement
            rs = ps.executeQuery();
            while (rs.next()) {
                media = extractMediaFromResultSet(rs);
                mediaList.add(media);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return mediaList;
    }

    /**
     * Extracting specific data of deleteMediaById from ResultSet
     */
    private static Media extractMediaFromResultSet(ResultSet rs) throws FailedOperationException {
        Media media = new Media();
        try {
            media.setMediaId(rs.getInt("media_id"));
            media.setPhoto(rs.getString("photo"));
            media.setVideo(rs.getString("video"));
            media.setTournamentId(rs.getInt("tournament_id"));
            media.setManagerId(rs.getInt("manager_id"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        }
        return media;
    }

    /**
     * Updates media(photo or video) use specific sql query
     */
    private Media updateSpecificMedia(int id, String sql, Media media) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, media.getPhoto());
            ps.setString(2, media.getVideo());
            ps.setInt(3, media.getTournamentId());
            ps.setInt(4, media.getManagerId());
            ps.setInt(5, id);

            // Execute statement
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }

        return media;
    }
}