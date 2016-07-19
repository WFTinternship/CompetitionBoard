package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Media;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaDaoImpl extends GenericDao implements MediaDao {
    private static final Logger LOG = Logger.getLogger(MediaDaoImpl.class);

    private DataSource dataSource;

    public MediaDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets all media by specific manager
     */
    @Override
    public List<Media> getMediaListByManager(int id) {
        List<Media> mediaByManagerList;
        String sql = "SELECT * FROM media WHERE manager_id=?";

        mediaByManagerList = getSpecificMediaList(sql, id);
        return mediaByManagerList;
    }

    /**
     * Gets all media by specific tournament
     */
    @Override
    public List<Media> getMediaListByTournament(int id) {
        List<Media> mediaByTournamentList;
        String sql = "SELECT * FROM media WHERE tournament_id=?";

        mediaByTournamentList = getSpecificMediaList(sql, id);
        return mediaByTournamentList;
    }

    /**
     * Updates photo
     */
    @Override
    public boolean updatePhoto(int id, Media media) {
        String sql = "UPDATE media SET photo=?, video=?, tournament_id=?, manager_id=? WHERE media_id=?";

        return updateSpecificMedia(id, sql, media);
    }


    /**
     * Updates video
     */
    @Override
    public boolean updateVideo(int id, Media media) {
        String sql = "UPDATE media SET photo=?, video=?, tournament_id=?, manager_id=? WHERE media_id=?";

        return updateSpecificMedia(id, sql, media);
    }


    /**
     * Adding photo to db by specific manager and tournament
     */
    @Override
    public boolean addPhoto(Media media) {
        boolean inserted = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO media(photo, tournament_id, manager_id) VALUES(?, ?, ?)";
        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, media.getPhoto());
            ps.setInt(2, media.getTournamentId());
            ps.setInt(3, media.getManagerId());

            // Execute statement
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                media.setMediaId(rs.getInt(1));
            }
            inserted = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
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
        ResultSet rs = null;

        String sql = "INSERT INTO media(video, tournament_id, manager_id) VALUES(?, ?, ?)";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, media.getVideo());
            ps.setInt(2, media.getTournamentId());
            ps.setInt(3, media.getManagerId());

            // Execute statement
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                media.setMediaId(rs.getInt(1));
            }
            inserted = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
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

        deleted = deleteEntries(sql, id);
        return deleted;
    }

    @Override
    public boolean deleteAll() {
        boolean deleted;
        String sql = "DELETE FROM media";

        deleted = deleteEntries(sql);
        return deleted;
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

    /**
     * Updates media(photo or video) use specific sql query
     */
    private boolean updateSpecificMedia(int id, String sql, Media media) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean updated = false;
        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, media.getPhoto());
            ps.setString(2, media.getVideo());
            ps.setInt(3, media.getTournamentId());
            ps.setInt(4, media.getManagerId());
            ps.setInt(5, id);

            // Execute statement
            ps.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return updated;
    }
}