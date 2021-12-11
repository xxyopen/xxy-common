package io.github.xxyopen.model.page.builder.springdata;


import io.github.xxyopen.model.page.PageBean;
import org.springframework.data.domain.Page;

/**
 * 分页数据模型构建器，根据SpringData分页后的数据构建PageBean
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/11
 */
public class PageBuilder{

    private PageBuilder(){};

    public static <T> PageBean<T> build(Page<T> page){
        return PageBean.of(page.getNumber(), page.getSize(), page.getTotalElements(), page.getContent());
    }


}
