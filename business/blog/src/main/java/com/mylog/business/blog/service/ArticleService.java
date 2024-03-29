package com.mylog.business.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mylog.business.blog.entity.Article;
import com.mylog.tools.model.model.result.DataResult;

import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author Dylan
 * @since 2020-06-14 20:24:19
 */
public interface ArticleService extends IService<Article> {

    /**
     * 获取符合条件的文章
     * @param article
     * @return
     */
    DataResult queryRight(Article article);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Article queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Article> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    Article insert(Article article);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    DataResult update(Article article);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    DataResult deleteById(Integer id);

}