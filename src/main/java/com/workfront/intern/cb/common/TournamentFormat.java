package com.workfront.intern.cb.common;

public enum TournamentFormat {
    ROUND_ROBBIN (1, "ROUND ROBIN"),
    OLYMPIC (2, "OLYMPIC");

    private final int formatId;
    private final String formatName;

    TournamentFormat(int formatId, String formatName) {
        this.formatId = formatId;
        this.formatName = formatName;
    }

    public int getFormatId() {
        return formatId;
    }

    public String getFormatName() {
        return formatName;
    }

    public static TournamentFormat getTournamentFormatById(int formatId) {
        TournamentFormat format = null;
        switch (formatId) {
            case 1:
                format = TournamentFormat.ROUND_ROBBIN;
                break;
            case 2:
                format = TournamentFormat.OLYMPIC;
                break;
        }
        return format;
    }

    /**
     * Returns tournament format by specified format ID.
     */
    public static TournamentFormat fromId(int formatId) {
        if (formatId == 0)
            return null;

        for (TournamentFormat format : TournamentFormat.values()) {
            if (format.getFormatId() == formatId) {
                return format;
            }
        }
        return null;
    }

    /**
     * Returns tournament format by specified format Name.
     */
    public static TournamentFormat fromName(String formatName) {
        if (formatName == null)
            return null;

        for (TournamentFormat format : TournamentFormat.values()) {
            if (format.getFormatName().equals(formatName)) {
                return format;
            }
        }
        return null;
    }

    /**
     * Returns tournament format by specified format ID and Name.
     *
     * @param formatId
     * @param formatName
     * @return
     */
    public static TournamentFormat fromIdAnName(int formatId, String formatName) {
        if (formatId == 0 || formatName == null)
            return null;

        for (TournamentFormat format : TournamentFormat.values()) {
            if (format.getFormatId() == formatId && format.getFormatName().equals(formatName)) {
                return format;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "TournamentFormat{" +
                "formatId=" + formatId +
                ", formatName='" + formatName + '\'' +
                '}';
    }
}
