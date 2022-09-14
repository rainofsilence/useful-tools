package cn.sumi.utils.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rainofsilence
 * @date 2022/7/26 周二
 */
public class TreeEntity<T> implements Serializable {
    private static final long serialVersionUID = -1L;

    private T model;

    private List<TreeEntity<T>> children;

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public List<TreeEntity<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeEntity<T>> children) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children = children;
    }

}
