package cn.springmvc.mybatis.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.mybatis.common.utils.UUIDUtil;
import cn.springmvc.mybatis.entity.News;
import cn.springmvc.mybatis.entity.User;
import cn.springmvc.mybatis.mapper.NewsMapper;
import cn.springmvc.mybatis.service.NewsService;

/**
 * @author Vincent.wang
 *
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public void addNews(News news, User user) {
        if (news != null) {
            news.setId(UUIDUtil.getRandom32PK());
            news.setUserId(user.getId());
            news.setCreateTime(Calendar.getInstance().getTime());
            newsMapper.insert(news);
        }
    }

    @Override
    public List<News> findNewsByKeywords(String keywords) {
        return newsMapper.findNewsByKeywords(keywords);
    }

}
