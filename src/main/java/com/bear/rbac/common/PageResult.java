package com.bear.rbac.common;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bear
 */
@Data
public final class PageResult<T> implements Serializable {

    private List<T> list;

    private Long total;

    public PageResult(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }
}
