package com.myweb.content.rest;

import com.myweb.content.dao.ArticleDAO;
import com.myweb.content.dto.ArticleDTO;
import com.myweb.content.dto.ArticleListDTO;
import com.myweb.content.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ArticleResource {

    @Autowired
    private ArticleDAO articleDAO;

    @RequestMapping(value = "/article", method = RequestMethod.POST, consumes = "application/json",
            produces = "application/json")
    @Description("create article.")
    @PreAuthorize("hasAuthority('test1')")
    public ResponseEntity<Void> addNewArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setArticleAuthor(articleDTO.getArticleAuthor());
        article.setArticleId(UUID.randomUUID().toString());
        article.setArticleType(articleDTO.getArticleType());
        article.setBody(articleDTO.getBody());
        article.setCategory(articleDTO.getCategory());
        articleDAO.insertArticle(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/article/list/{articleCategory}/{articleType}", method = RequestMethod.GET, produces = "application/json",
            consumes = "application/json")
    @Description("get articles.")
    public ResponseEntity<ArticleListDTO> getArticles(@PathVariable("articleCategory") String articleCategory,
                                                    @PathVariable("articleType") String articleType) {
        ArticleListDTO articleListDTO = new ArticleListDTO();
        if(articleType.equals("content")) {
            List<Article> list = articleDAO.getArticleListContenyByCategory(articleCategory);
            articleListDTO.setArticleList(list);
        }
        if(articleType.equals("summary")){
            List<Article> list = articleDAO.getArticleListSummaryByCategory(articleCategory);
            articleListDTO.setArticleList(list);
        }
        return new ResponseEntity<>(articleListDTO, HttpStatus.OK);
    }
//{articleType}/{articleID}
    @RequestMapping(value = "/article/single", method = RequestMethod.GET, produces = "application/json",
            consumes = "application/json")
    @Description("get article by id.")
    public ResponseEntity<ArticleDTO> getArticleByTypeAndId(@PathVariable("articleType") String articleType, @PathVariable("articleID") String articleID) {
        Article article = new Article();
        if (articleType.equals("content")) {
            article = articleDAO.getArticleContentByArticleId(articleID);
        }
        if(articleType.equals("summary")) {
            article = articleDAO.getArticleSummaryByArticleId(articleID);
        }
        ArticleDTO articleDTO = buildArticleDYO(article);
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }

    private ArticleDTO buildArticleDYO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setArticleAuthor(article.getArticleAuthor());
        articleDTO.setArticleId(article.getArticleId());
        articleDTO.setArticleName(article.getArticleName());
        articleDTO.setArticleType(article.getArticleType());
        articleDTO.setBody(article.getBody());
        articleDTO.setCategory(article.getCategory());
        articleDTO.setCreateTime(article.getCreateTime());
        return articleDTO;
    }

    @RequestMapping(value = "/article/test", method = RequestMethod.GET, produces = "application/json",
            consumes = "application/json")
    @Description("get article by id.")
    public ResponseEntity<ArticleDTO> getArticleByTypeAndId() {
        Article article = new Article();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
 }
