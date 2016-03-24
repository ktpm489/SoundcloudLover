package vn.com.lonelyknight.soundcloudlover;

import java.util.concurrent.TimeUnit;

/**
 * Created by is on 3/12/2016.
 */
public class Utilities {

    /**
     * Format big number to shorten form
     * @param count
     * @return
     */
    public static String formatShortRepresent(int count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f%c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp-1));
    }

    public static String getDurationBreakdown(long millis){
        if (millis < 0){
            return "---";
        }

        final long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        millis -= TimeUnit.SECONDS.toMillis(seconds);

        final StringBuilder sb = new StringBuilder();

        if (minutes >= 0) {
            sb.append(String.format("%02d", minutes));
        }
        sb.append(":");
        if (seconds >= 0) {
            sb.append(String.format("%02d", seconds));
        }
        return sb.toString();
    }

    public static String formatPlaylistTrackCountText(int track_count) {
        StringBuilder trackCountStrBuilder = new StringBuilder();
        trackCountStrBuilder.append(track_count);
        trackCountStrBuilder.append(" ");
        if (track_count > 1) {
            trackCountStrBuilder.append("tracks");
        }else {
            trackCountStrBuilder.append("track");
        }
        return trackCountStrBuilder.toString();
    }
}
