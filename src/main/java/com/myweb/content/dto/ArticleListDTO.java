package com.myweb.content.dto;

import com.myweb.content.entity.Article;

import java.util.List;

public class ArticleListDTO {

    private List<Article> articleList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
