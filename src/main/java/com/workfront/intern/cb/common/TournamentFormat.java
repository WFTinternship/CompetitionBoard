package com.workfront.intern.cb.common;

public enum TournamentFormat {
    ROUND_ROBBIN(1), OLYMPIC(2);

    private final int formatId;

    TournamentFormat(int formatId) {
        this.formatId = formatId;
    }

    public int getFormatId() {
        return formatId;
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
     * Returns tournament format name(string) by specific tournament id
     */
    public static String parseTournamentFormatIdToString(int formatId) {
        String formatStr;
        switch (formatId) {
            case 1:
                formatStr = "ROUND_ROBBIN";
                break;
            case 2:
                formatStr = "OLYMPIC";
                break;
            default:
                formatStr = "UNKNOWN_FORMAT";
        }
        return formatStr;
    }


    @Override
    public String toString() {
        return "TournamentFormat{" +
                "formatId=" + formatId +
                '}';
    }
}
