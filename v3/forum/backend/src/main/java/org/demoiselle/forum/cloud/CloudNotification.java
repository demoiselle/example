package org.demoiselle.forum.cloud;

/**
 *
 * @author paulo
 */
public class CloudNotification {

    private String title;
    private String body;
    private String icon;
    private String click_action;

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     */
    public String getClick_action() {
        return click_action;
    }

    /**
     *
     * @param click_action
     */
    public void setClick_action(String click_action) {
        this.click_action = click_action;
    }

}
