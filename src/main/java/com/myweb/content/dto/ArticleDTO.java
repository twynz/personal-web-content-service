package com.myweb.content.dto;

import java.util.Date;

public class ArticleDTO {

    private String articleId;

    private String articleType;

    private String articleName;

    private String articleAuthor;

    private Date createTime;

    private String body;

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
