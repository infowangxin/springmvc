package cn.springmvc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.springmvc.mybatis.entity.News;

/**
 * @author Vincent.wang
 *
 */
public interface NewsMapper extends BaseMapper<String, News> {

    List<News> findNewsByKeywords(@Param("keywords") String keywords);

}
