package com.myweb.content.dao;

import com.myweb.content.entity.Article;

import java.util.List;

public interface ArticleDAO {

    List<Article> getArticleListContenyByCategory(String category);

    List<Article> getArticleListSummaryByCategory(String category);

    Article getArticleSummaryByArticleId(String articleId);

    Article getArticleContentByArticleId(String articleId);

    Article getArticleSummaryByArticleName(String name);

    Article getArticleContentByArticleName(String name);

    int insertArticle(Article article);
}

