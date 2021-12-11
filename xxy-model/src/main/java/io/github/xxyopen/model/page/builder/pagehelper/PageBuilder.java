package io.github.xxyopen.model.page.builder.pagehelper;

import com.github.pagehelper.PageInfo;
import io.github.xxyopen.model.page.PageBean;

import java.util.List;

/**
 * 分页数据模型构建器，根据PageHelper工具分页后的数据构建PageBean
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/11
 */
public class PageBuilder{

    private PageBuilder(){};

    public static <T> PageBean<T> build(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return PageBean.of(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(),pageInfo.getList());
    }

}
