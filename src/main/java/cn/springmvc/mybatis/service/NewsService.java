package cn.springmvc.mybatis.service;

import java.util.List;

import cn.springmvc.mybatis.entity.News;
import cn.springmvc.mybatis.entity.User;

/**
 * @author Vincent.wang
 *
 */
public interface NewsService {

    public void addNews(News news, User user);

    public List<News> findNewsByKeywords(String keywords);

}