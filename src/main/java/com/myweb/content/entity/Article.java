package com.myweb.content.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * article
 * @author
 */
public class Article implements Serializable {
    private String articleId;

    private String articleType;

    private String articleName;

    private String articleAuthor;

    private Date createTime;

    private String body;

    private String category;

    private static final long serialVersionUID = 1L;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Article other = (Article) that;
        return (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
                && (this.getArticleType() == null ? other.getArticleType() == null : this.getArticleType().equals(other.getArticleType()))
                && (this.getArticleName() == null ? other.getArticleName() == null : this.getArticleName().equals(other.getArticleName()))
                && (this.getArticleAuthor() == null ? other.getArticleAuthor() == null : this.getArticleAuthor().equals(other.getArticleAuthor()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getBody() == null ? other.getBody() == null : this.getBody().equals(other.getBody()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getArticleType() == null) ? 0 : getArticleType().hashCode());
        result = prime * result + ((getArticleName() == null) ? 0 : getArticleName().hashCode());
        result = prime * result + ((getArticleAuthor() == null) ? 0 : getArticleAuthor().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getBody() == null) ? 0 : getBody().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", articleType=").append(articleType);
        sb.append(", articleName=").append(articleName);
        sb.append(", articleAuthor=").append(articleAuthor);
        sb.append(", createTime=").append(createTime);
        sb.append(", body=").append(body);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
