package cn.springmvc.mybatis.mapper.news;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.springmvc.mybatis.common.base.model.Page;
import cn.springmvc.mybatis.entity.news.News;
import cn.springmvc.mybatis.mapper.BaseMapper;

/**
 * @author Vincent.wang
 *
 */
public interface NewsMapper extends BaseMapper<String, News> {

    List<News> findNewsByKeywords(@Param("keywords") String keywords);

    List<News> findNewsByPage(Page<News> page);

}
