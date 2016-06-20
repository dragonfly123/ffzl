package org.dragonfei.ffzl.utils.collections;


import java.util.List;

/**
 * Created by longfei on 16-4-11.
 */
public abstract class Lists {
	public static<T> List<T>  newArrayList(){
		return (List<T>) com.google.common.collect.Lists.newArrayList();
	}

	public static<T> List<T>  newArrayList(Class<T> tClass){
		return (List<T>) com.google.common.collect.Lists.newArrayList();
	}

	public static <T> List<T> newArrayList(int num){
		return (List<T>)com.google.common.collect.Lists.newArrayListWithCapacity(num);
	}


	public static <T> List<T> addAll(List<T> result,List<T> defaultList){
		if(defaultList == null){
			defaultList = com.google.common.collect.Lists.newArrayList();
		}
		defaultList.addAll(result);
		return   result;
	}


}
