package cn.sumi.utils.tree;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author rainofsilence
 * @date 2022/7/26 周二
 */
public class TreeUtil<T> {

    // Model获取Id的方法名
    private final String getIdMethod;

    // Model获取Pid的方法名
    private final String getPidMethod;

    public TreeUtil(String getIdMethod, String getPidMethod) {
        this.getIdMethod = getIdMethod;
        this.getPidMethod = getPidMethod;
    }

    /**
     * 向下获取树结构
     *
     * @param setAll 所有节点
     * @return
     * @throws Exception
     */
    public List<T> createTree(Set<T> setAll) throws Exception {
        List<T> list = new ArrayList<T>();
        Map<Long, Set<T>> listHash = new HashMap<Long, Set<T>>();
        if (setAll == null || setAll.size() == 0) {
            return null;
        }

        for (T item : setAll) {
            Long pid = getPid(item);
            if (listHash.containsKey(pid)) {
                listHash.get(pid).add(item);
            } else {
                Set<T> t = new HashSet<T>();
                t.add(item);
                listHash.put(pid, t);
            }
        }
        Iterator<T> iterator = listHash.get(0L).iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            list.add(item);
            getLow(item, listHash, list);
        }
        return list;
    }

    private void getLow(T parent, Map<Long, Set<T>> list, List<T> getlist) throws Exception {
        Long pid = getId(parent);
        if (list.containsKey(pid)) {
            Set<T> lp = list.get(pid);
            for (T item : lp) {
                getlist.add(item);
                getLow(item, list, getlist);
            }
        }
    }

    public List<TreeEntity<T>> createTreeAsTreeEntity(Set<T> setAll) throws Exception {
        Map<Long, Set<TreeEntity<T>>> listHash = new LinkedHashMap<>();
        if (setAll == null || setAll.size() < 1) {
            return null;
        }

        for (T item : setAll) {
            Long pid = getPid(item);
            if (listHash.containsKey(pid)) {
                TreeEntity<T> et = new TreeEntity<T>();
                et.setModel(item);
                listHash.get(pid).add(et);
            } else {
                Set<TreeEntity<T>> t = new LinkedHashSet<>();
                TreeEntity<T> et = new TreeEntity<T>();
                et.setModel(item);
                t.add(et);
                listHash.put(pid, t);
            }
        }
        Iterator<TreeEntity<T>> iterator = listHash.get(0L).iterator();
        while (iterator.hasNext()) {
            TreeEntity<T> entitys = iterator.next();
            getLowAsTreeEntity(entitys, listHash);
        }
        return new ArrayList<>(listHash.get(0L));
    }

    private void getLowAsTreeEntity(TreeEntity<T> parent, Map<Long, Set<TreeEntity<T>>> list) throws Exception {
        Long id = getId(parent.getModel());
        if (list.containsKey(id)) {
            if (list.get(id) != null) {
                parent.setChildren(new ArrayList<>(list.get(id)));
                for (TreeEntity<T> t : parent.getChildren()) {
                    getLowAsTreeEntity(t, list);
                }
            }
        }
    }

    /**
     * 获取父级id
     *
     * @param item
     * @return
     * @throws Exception
     */
    private Long getId(T item) throws Exception {
        Method method = item.getClass().getMethod(getIdMethod, null);
        Object invoke = method.invoke(item, null);
        long id = 0L;
        if (invoke != null) {
            id = Long.parseLong(invoke.toString());
        }
        return id;
    }

    /**
     * 获取父级id
     *
     * @param item
     * @return
     * @throws Exception
     */
    private Long getPid(T item) throws Exception {
        Method method = item.getClass().getMethod(getPidMethod, null);
        Object invoke = method.invoke(item, null);
        long pid = 0L;
        if (invoke != null) {
            pid = Long.parseLong(invoke.toString());
        }
        return pid;
    }

}
