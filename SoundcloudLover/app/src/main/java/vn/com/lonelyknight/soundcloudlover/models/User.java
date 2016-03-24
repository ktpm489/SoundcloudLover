package vn.com.lonelyknight.soundcloudlover.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by King on 3/8/2016.
 */
public class User {

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("permalink_url")
    @Expose
    private String permalinkUrl;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;

    /**
     * @return The avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @param avatarUrl The avatar_url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
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

}
