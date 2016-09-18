package com.workfront.intern.cb.web.beans;

import com.workfront.intern.cb.common.util.ClassHelper;
import com.workfront.intern.cb.service.*;
import com.workfront.intern.cb.web.Initializer;

/**
 * Initialization of service business logic's
 */
public class BeanProvider {

    /**
     * Gets manager service
     */
    public static ManagerService getManagerService() {
        return (ManagerService) Initializer.getApplicationContext()
                .getBean(ClassHelper.getBeanName(ManagerServiceImpl.class));
    }

    /**
     * Gets tournament service
     */
    public static TournamentService getTournamentService() {
        return (TournamentService) Initializer.getApplicationContext()
                .getBean(ClassHelper.getBeanName(TournamentServiceImpl.class));
    }

    /**
     * Gets group service
     */
    public static GroupService getGroupService() {
        return (GroupService) Initializer.getApplicationContext()
                .getBean(ClassHelper.getBeanName(GroupServiceImpl.class));
    }

    /**
     * Gets match service
     */
    public static MatchService getMatchService() {
        return (MatchService) Initializer.getApplicationContext()
                .getBean(ClassHelper.getBeanName(MatchServiceImpl.class));
    }

    /**
     * Gets media service
     */
    public static MediaService getMediaService() {
        return (MediaService) Initializer.getApplicationContext().
                getBean(ClassHelper.getBeanName(MediaServiceImpl.class));
    }

    /**
     * Gets participant service
     */
    public static ParticipantService getParticipantService() {
        return (ParticipantService) Initializer.getApplicationContext()
                .getBean(ClassHelper.getBeanName(ParticipantServiceImpl.class));
    }
}