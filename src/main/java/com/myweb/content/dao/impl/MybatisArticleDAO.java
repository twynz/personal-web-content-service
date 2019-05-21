package com.myweb.content.dao.impl;

import com.myweb.content.dao.ArticleDAO;
import com.myweb.content.dao.mapper.ArticleMapper;
import com.myweb.content.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MybatisArticleDAO implements ArticleDAO {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getArticleListContenyByCategory(String category) {
        return articleMapper.getArticleContentListByCategory(category);
    }

    @Override
    public List<Article> getArticleListSummaryByCategory(String category) {
        return articleMapper.getArticleSummaryListByCategory(category);
    }

    @Override
    public Article getArticleSummaryByArticleId(String articleId) {
        return articleMapper.getArticleSummaryByArticleId(articleId);
    }

    @Override
    public Article getArticleContentByArticleId(String articleId) {
        return articleMapper.getArticleContentByArticleId(articleId);
    }

    @Override
    public Article getArticleSummaryByArticleName(String name) {
        return articleMapper.getArticleSummaryByArticleName(name);
    }

    @Override
    public Article getArticleContentByArticleName(String name) {
        return articleMapper.getArticleContentByArticleName(name);
    }

    @Override
    public int insertArticle(Article article) {
        return articleMapper.insert(article);
    }
}
