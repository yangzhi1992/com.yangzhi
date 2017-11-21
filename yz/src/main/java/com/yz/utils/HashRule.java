package com.yz.utils;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.stereotype.Component;

import com.tuniu.adapter.domain.Weight;
import org.springframework.util.CollectionUtils;

@Component
public class HashRule {

    /**
     * 按比例扩大，均匀分配到环中，然后取环的第一个
     * @param cfgList cfgList
     * @param <T> T extends Weight
     */
    public <T extends Weight> void process(List<T> cfgList) {
        if(CollectionUtils.isEmpty(cfgList)){
            return;
        }
        for (T cfg : cfgList) {
            cfg.setIdentifier(UUID.randomUUID().toString() + cfg.getId());
        }
        HashCircle<T> circle = new HashCircle<T>(cfgList);
        // 哈希环随机取一个，放入list首部
        T cfg = circle.get(UUID.randomUUID().toString());
        cfgList.remove(cfg);// 去除
        cfgList.add(0, cfg);// 放到对首
    }

    /**
     * 一致性Hash环容器
     * 
     */
    public static class HashCircle<T extends Weight> {
        /** Hash计算对象，用于自定义hash算法 */
        HashFunc hashFunc;

        /** 复制的节点个数 */
        private final int replicas;

        private final static int defaultReplicas = 3;

        private final static int defaultCapacity = 16;

        /** 一致性Hash环 */
        private final ConcurrentSkipListMap<Integer, T> circle = new ConcurrentSkipListMap<Integer, T>();

        /**
         * 构造，使用Java默认的Hash算法
         */
        public HashCircle() {
            this(new ArrayList<T>(defaultCapacity), defaultReplicas);
        }

        /**
         * 构造，使用Java默认的Hash算法
         * 
         * @param nodes
         *            节点对象
         */
        public HashCircle(Collection<T> nodes) {
            this(nodes, defaultReplicas);
        }

        /**
         * 构造
         * 
         * @param replicas
         *            复制的节点个数，增加每个节点的复制节点有利于负载均衡
         * @param nodes
         *            节点对象
         */
        public HashCircle(Collection<T> nodes, int replicas) {
            this(new DefaultHashFunc(), nodes, replicas);
        }

        /**
         * 构造
         * 
         * @param hashFunc
         *            hash算法对象
         * @param replicas
         *            复制的节点个数，增加每个节点的复制节点有利于负载均衡
         * @param nodes
         *            节点对象
         */
        public HashCircle(HashFunc hashFunc, Collection<T> nodes, int replicas) {
            this.replicas = replicas;
            this.hashFunc = hashFunc;
            // 初始化节点
            for (T node : nodes) {
                add(node);
            }
        }

        /**
         * 增加节点<br>
         * 每增加一个节点，就会在闭环上增加（复制节点数*权重）个节点<br>
         * 例如复制节点数是2，则每调用此方法一次，增加两个虚拟节点，这两个节点指向同一Node
         * 由于hash算法会调用node的toString方法，故按照toString去重
         * 
         * @param node
         *            节点对象
         */
        public void add(T node) {
            int virtualNumber = node.getWeight() * replicas;
            for (int i = 0; i < virtualNumber; i++) {
                circle.putIfAbsent(hashFunc.hash(node.getIdentifier() + i), node);
            }
        }

        /**
         * 移除节点的同时移除相应的虚拟节点
         * 
         * @param node
         *            节点对象
         */
        public void remove(T node) {
            for (int i = 0; i < node.getWeight() * replicas; i++) {
                circle.remove(hashFunc.hash(node.getIdentifier() + i));
            }
        }

        /**
         * 获得一个最近的顺时针节点
         * 
         * @param key
         *            为给定键取Hash，取得顺时针方向上最近的一个虚拟节点对应的实际节点
         * @return 节点对象
         */
        public T get(Object key) {
            if (circle.isEmpty()) {
                return null;
            }
            int hash = hashFunc.hash(key);
            if (!circle.containsKey(hash)) {
                SortedMap<Integer, T> tailMap = circle.tailMap(hash); // 返回此映射的部分视图，其键大于等于
                                                                      // hash
                hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
            }
            // 正好命中
            return circle.get(hash);
        }

        /**
         * Hash算法对象，用于自定义hash算法
         * 
         */
        public interface HashFunc {
            Integer hash(Object key);
        }

        static class DefaultHashFunc implements HashFunc {
            @Override
            public Integer hash(Object key) {
                String data = key.toString();
                // 默认使用FNV1hash算法
                final int p = 16777619;
                int hash = (int) 2166136261L;
                for (int i = 0; i < data.length(); i++)
                    hash = (hash ^ data.charAt(i)) * p;
                hash += hash << 13;
                hash ^= hash >> 7;
                hash += hash << 3;
                hash ^= hash >> 17;
                hash += hash << 5;
                return hash;
            }
        }

    }

}
