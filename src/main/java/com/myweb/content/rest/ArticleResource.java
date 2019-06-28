package com.myweb.content.rest;

import com.myweb.content.dao.ArticleDAO;
import com.myweb.content.dto.ArticleDTO;
import com.myweb.content.dto.ArticleListDTO;
import com.myweb.content.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ArticleResource {

    @Autowired
    private ArticleDAO articleDAO;

    @RequestMapping(value = "/article", method = RequestMethod.POST, consumes = "application/json",
            produces = "application/json")
    @Path("/article")
    @Description("create article.")
    @PreAuthorize("hasAuthority('test')")
    public ResponseEntity<Void> addNewArticle(@RequestBody Map<String, String> json) {

        Article article = new Article();
        article.setArticleAuthor("TANG WENYU");
        article.setArticleId(UUID.randomUUID().toString());
        article.setArticleType(json.get("articleType"));
        article.setArticleName(json.get("articleName"));
        article.setBody(json.get("body"));
        article.setCategory(json.get("category"));
        articleDAO.insertArticle(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/article/list/{articleType}", method = RequestMethod.GET, produces = "application/json",
            consumes = "application/json")
    @Description("get articles.")
    public ResponseEntity<ArticleListDTO> getArticles(@Param("articleCategory") String articleCategory,
                                                    @PathVariable("articleType") String articleType) {
        ArticleListDTO articleListDTO = new ArticleListDTO();
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
        ArticleDTO articleDTO = buildArticleDTO(article);
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }

    private ArticleDTO buildArticleDTO(Article article) {
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

