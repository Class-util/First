package models.vo;

import models.ArtInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 09
 * Time:15:20
 */
public class ArticleInfoVO extends ArtInfo {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
