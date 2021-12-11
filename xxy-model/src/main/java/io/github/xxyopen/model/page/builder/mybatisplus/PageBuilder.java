package io.github.xxyopen.model.page.builder.mybatisplus;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.xxyopen.model.page.PageBean;

/**
 * 分页数据模型构建器，根据mybatis-plus分页插件分页后数据构建PageBean
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/11
 */
public class PageBuilder{

    private PageBuilder(){};

    public static <T> PageBean<T> build(IPage<T> page){
        return PageBean.of(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

}
