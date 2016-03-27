package cn.springmvc.mybatis.mapper.auth;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.springmvc.mybatis.entity.auth.News;
import cn.springmvc.mybatis.mapper.BaseMapper;

/**
 * @author Vincent.wang
 *
 */
public interface NewsMapper extends BaseMapper<String, News> {

    List<News> findNewsByKeywords(@Param("keywords") String keywords);

}
