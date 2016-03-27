package cn.springmvc.mybatis.service.auth;

import java.util.List;

import cn.springmvc.mybatis.entity.auth.News;
import cn.springmvc.mybatis.entity.auth.User;

/**
 * @author Vincent.wang
 *
 */
public interface NewsService {

    public void addNews(News news, User user);

    public List<News> findNewsByKeywords(String keywords);

}