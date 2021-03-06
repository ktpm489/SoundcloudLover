package vn.com.lonelyknight.soundcloudlover.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

/**
 * Created by duclm on 3/24/2016.
 */
@Generated("org.jsonschema2pojo")
public class Playlist {

    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("release_day")
    @Expose
    private Object releaseDay;
    @SerializedName("permalink_url")
    @Expose
    private String permalinkUrl;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("purchase_url")
    @Expose
    private Object purchaseUrl;
    @SerializedName("release_month")
    @Expose
    private Object releaseMonth;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("label_name")
    @Expose
    private String labelName;
    @SerializedName("tag_list")
    @Expose
    private String tagList;
    @SerializedName("release_year")
    @Expose
    private Object releaseYear;
    @SerializedName("track_count")
    @Expose
    private int trackCount;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("license")
    @Expose
    private String license;
//    @SerializedName("tracks")
//    @Expose
//    private List<Track> tracks = new ArrayList<Track>();
    @SerializedName("playlist_type")
    @Expose
    private String playlistType;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("downloadable")
    @Expose
    private boolean downloadable;
    @SerializedName("sharing")
    @Expose
    private String sharing;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("release")
    @Expose
    private String release;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("purchase_title")
    @Expose
    private Object purchaseTitle;
    @SerializedName("artwork_url")
    @Expose
    private String artworkUrl;
    @SerializedName("ean")
    @Expose
    private String ean;
    @SerializedName("streamable")
    @Expose
    private boolean streamable;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("embeddable_by")
    @Expose
    private String embeddableBy;
    @SerializedName("label_id")
    @Expose
    private Object labelId;

    /**
     * @return The duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration The duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return The releaseDay
     */
    public Object getReleaseDay() {
        return releaseDay;
    }

    /**
     * @param releaseDay The release_day
     */
    public void setReleaseDay(Object releaseDay) {
        this.releaseDay = releaseDay;
    }

    /**
     * @return The permalinkUrl
     */
    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    /**
     * @param permalinkUrl The permalink_url
     */
    public void setPermalinkUrl(String permalinkUrl) {
        this.permalinkUrl = permalinkUrl;
    }

    /**
     * @return The genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre The genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return The permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * @param permalink The permalink
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     * @return The purchaseUrl
     */
    public Object getPurchaseUrl() {
        return purchaseUrl;
    }

    /**
     * @param purchaseUrl The purchase_url
     */
    public void setPurchaseUrl(Object purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    /**
     * @return The releaseMonth
     */
    public Object getReleaseMonth() {
        return releaseMonth;
    }

    /**
     * @param releaseMonth The release_month
     */
    public void setReleaseMonth(Object releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return The labelName
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * @param labelName The label_name
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * @return The tagList
     */
    public String getTagList() {
        return tagList;
    }

    /**
     * @param tagList The tag_list
     */
    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    /**
     * @return The releaseYear
     */
    public Object getReleaseYear() {
        return releaseYear;
    }

    /**
     * @param releaseYear The release_year
     */
    public void setReleaseYear(Object releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * @return The trackCount
     */
    public int getTrackCount() {
        return trackCount;
    }

    /**
     * @param trackCount The track_count
     */
    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    /**
     * @return The userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId The user_id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return The lastModified
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified The last_modified
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return The license
     */
    public String getLicense() {
        return license;
    }

    /**
     * @param license The license
     */
    public void setLicense(String license) {
        this.license = license;
    }

//    /**
//     * @return The tracks
//     */
//    public List<Track> getTracks() {
//        return tracks;
//    }
//
//    /**
//     * @param tracks The tracks
//     */
//    public void setTracks(List<Track> tracks) {
//        this.tracks = tracks;
//    }

    /**
     * @return The playlistType
     */
    public String getPlaylistType() {
        return playlistType;
    }

    /**
     * @param playlistType The playlist_type
     */
    public void setPlaylistType(String playlistType) {
        this.playlistType = playlistType;
    }

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The downloadable
     */
    public boolean isDownloadable() {
        return downloadable;
    }

    /**
     * @param downloadable The downloadable
     */
    public void setDownloadable(boolean downloadable) {
        this.downloadable = downloadable;
    }

    /**
     * @return The sharing
     */
    public String getSharing() {
        return sharing;
    }

    /**
     * @param sharing The sharing
     */
    public void setSharing(String sharing) {
        this.sharing = sharing;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The release
     */
    public String getRelease() {
        return release;
    }

    /**
     * @param release The release
     */
    public void setRelease(String release) {
        this.release = release;
    }

    /**
     * @return The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The purchaseTitle
     */
    public Object getPurchaseTitle() {
        return purchaseTitle;
    }

    /**
     * @param purchaseTitle The purchase_title
     */
    public void setPurchaseTitle(Object purchaseTitle) {
        this.purchaseTitle = purchaseTitle;
    }

    /**
     * @return The artworkUrl
     */
    public String getArtworkUrl() {
        return artworkUrl;
    }

    /**
     * @param artworkUrl The artwork_url
     */
    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    /**
     * @return The ean
     */
    public String getEan() {
        return ean;
    }

    /**
     * @param ean The ean
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    /**
     * @return The streamable
     */
    public boolean isStreamable() {
        return streamable;
    }

    /**
     * @param streamable The streamable
     */
    public void setStreamable(boolean streamable) {
        this.streamable = streamable;
    }

    /**
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return The embeddableBy
     */
    public String getEmbeddableBy() {
        return embeddableBy;
    }

    /**
     * @param embeddableBy The embeddable_by
     */
    public void setEmbeddableBy(String embeddableBy) {
        this.embeddableBy = embeddableBy;
    }

    /**
     * @return The labelId
     */
    public Object getLabelId() {
        return labelId;
    }

    /**
     * @param labelId The label_id
     */
    public void setLabelId(Object labelId) {
        this.labelId = labelId;
    }
}
