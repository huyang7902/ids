package com.huyang.common.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Map工具类
 *
 * @Project ids
 * @package com.huyang.common.utils
 * @author HuYang 790247179@qq.com
 * @date 2017年5月10日 下午5:41:35
 * @version V1.0 Copyright (c) 2017
 */
public class MapUtils {

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	/**
     * 获取map中第一个entry
     *
     * @param <K> Key的类型
     * @param <V> Value的类型
     * @param map 数据源
     * @return 返回的值
     */
    public static <K, V> Entry<K, V> getFirstOrNull(Map<K, V> map) {
        K obj = null;
        Entry<K, V> entry = null;
        for (Entry<K, V> entry1 : map.entrySet()) {
            obj = entry1.getKey();
            if (obj != null) {
            	entry = entry1;
                break;
            }
        }
        return entry;
    }

}
