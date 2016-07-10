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

    @Override
    public List<Media> getMediaByManager(int id) {
        List<Media> mediaByManagerList;
        String sql = "SELECT * FROM media WHERE manager_id=?";

        mediaByManagerList = new MediaDaoImpl().getSpecificMediaList(sql, id);
        return mediaByManagerList;
    }

    @Override
    public List<Media> getMediaByTournament(int id) {
        List<Media> mediaByTournamentList;
        String sql = "SELECT * FROM media WHERE tournament_id=?";

        mediaByTournamentList = new MediaDaoImpl().getSpecificMediaList(sql, id);
        return mediaByTournamentList;
    }

    @Override
    public boolean addPhoto(Media media) {
        return false;
    }

    @Override
    public boolean addVideo(Media media) {
        return false;
    }

    @Override
    public boolean deletePhoto(Media media) {
        return false;
    }

    @Override
    public boolean deleteVideo(Media media) {
        return false;
    }

    //Extracting specific data of Media from ResultSet
    private static Media extractMediaFromResultSet(ResultSet rs) {
        Media media = new Media();
        try {
            media.setMediaId(rs.getInt("media_id"));
            media.setPhoto(rs.getString("photo"));
            media.setVideo(rs.getString("video"));
            media.setTournament(new Tournament().getTournamentByid(rs.getInt("tournament_id")));
            media.setManager(new Manager().getManagerById(rs.getInt("manager_id")));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return media;
    }

    // Gets specific data list of Media from sql query
    private List<Media> getSpecificMediaList(String sql, int id){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Media media;
        List<Media> mediaList = new ArrayList<>();
        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                media = extractMediaFromResultSet(rs);
                mediaList.add(media);
            }
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return mediaList;
    }

    public static void main(String[] args) {
        List<Media> mediaByManagerList = new MediaDaoImpl().getMediaByManager(1);
        System.out.println(mediaByManagerList);

//        List<Media> mediaByTournamentList = new MediaDaoImpl().getMediaByTournament(1);
//        System.out.println(mediaByTournamentList);

    }
}
