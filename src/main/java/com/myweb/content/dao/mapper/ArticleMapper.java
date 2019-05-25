package com.myweb.content.dao.mapper;

import com.myweb.content.entity.Article;

import java.util.List;

public interface ArticleMapper {

    List<Article> getArticleContentListByCategory(String category);

    List<Article> getArticleSummaryListByCategory(String category);

    Article getArticleSummaryByArticleId(String articleId);

    Article getArticleContentByArticleId(String articleId);

    Article getArticleSummaryByArticleName(String name);

    Article getArticleContentByArticleName(String name);

    int insert(Article article);
}
