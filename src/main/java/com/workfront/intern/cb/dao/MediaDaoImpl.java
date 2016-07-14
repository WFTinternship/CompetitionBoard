package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.Tournament;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MediaDaoImpl extends GenericDao implements MediaDao {
    private static final Logger LOG = Logger.getLogger(MediaDaoImpl.class);

    /**
     * Gets all media by specific manager
     */
    @Override
    public List<Media> getMediaByManager(int id) {
        List<Media> mediaByManagerList;
        String sql = "SELECT * FROM media WHERE manager_id=?";

        mediaByManagerList = new MediaDaoImpl().getSpecificMediaList(sql, id);
        return mediaByManagerList;
    }

    /**
     * Gets all media by specific tournament
     */
    @Override
    public List<Media> getMediaByTournament(int id) {
        List<Media> mediaByTournamentList;
        String sql = "SELECT * FROM media WHERE tournament_id=?";

        mediaByTournamentList = new MediaDaoImpl().getSpecificMediaList(sql, id);
        return mediaByTournamentList;
    }

    @Override
    public boolean updatePhoto(Media media) {
        return false;
    }

    @Override
    public boolean updateVideo(Media media) {
        return false;
    }

    // Adding photo to db by specific manager and tournament
    @Override
    public boolean addPhoto(Media media) {
        boolean inserted = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO media(photo, tournament_id, manager_id) VALUES(?, ?, ?)";

        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, media.getPhoto());
            ps.setInt(2, media.getTournamentId());
            ps.setInt(3, media.getManagerId());

            // Execute statement
            ps.executeUpdate();

            inserted = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return inserted;
    }

    /**
     * Adds video to db by specific manager and tournament
     */
    @Override
    public boolean addVideo(Media media) {
        boolean inserted = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO media(video, tournament_id, manager_id) VALUES(?, ?, ?)";
        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, media.getVideo());
            ps.setInt(2, media.getTournamentId());
            ps.setInt(3, media.getManagerId());

            // Execute statement
            ps.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return inserted;
    }

    /**
     * Deletes media by id
     */
    @Override
    public boolean deleteMedia(int id) {
        boolean deleted;
        String sql = "DELETE FROM media WHERE media_id=?";

        deleted = deleteEntity(sql, id);
        return deleted;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    /**
     * Gets specific data list of deleteMedia from sql query
     */
    private List<Media> getSpecificMediaList(String sql, int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Media media;
        List<Media> mediaList = new ArrayList<>();
        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

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
        } finally {
            closeResources(conn, ps, rs);
        }
        return mediaList;
    }

    /**
     * Extracting specific data of deleteMedia from ResultSet
     */
    private static Media extractMediaFromResultSet(ResultSet rs) {
        Media media = new Media();
        try {
            media.setMediaId(rs.getInt("media_id"));
            media.setPhoto(rs.getString("photo"));
            media.setVideo(rs.getString("video"));
            media.setTournamentId(rs.getInt("tournament_id"));
            media.setManagerId(rs.getInt("manager_id"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return media;
    }
}
