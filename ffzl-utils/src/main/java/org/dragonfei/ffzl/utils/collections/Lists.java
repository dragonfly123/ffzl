package org.dragonfei.ffzl.utils.collections;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by longfei on 16-4-11.
 */
public abstract class Lists {
	public static<T> List<T>  newArrayList(){
		return com.google.common.collect.Lists.newArrayList();
	}

	public static <T> List<T> newArrayList(int num){
		return com.google.common.collect.Lists.newArrayListWithCapacity(num);
	}


	public static <T> List<T> addAll(List<T> result,List<T> defaultList){
		if(defaultList == null){
			defaultList = com.google.common.collect.Lists.newArrayList();
		}
		defaultList.addAll(result);
		return  result;
	}

	public static <T> List<T> nvl(List<T> result,List<T> defaultList){
		if(result == null){
			return defaultList;
		} else
			return result;
	}






}
