package org.dragonfei.ffzl.utils.collections;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by longfei on 16-4-11.
 */
public abstract class Lists {
		public static  <T> List<T>  newArrayList(){
				return com.google.common.collect.Lists.newArrayList();
		}

		public static <T> List<T> newArrayList(int num){
				return com.google.common.collect.Lists.newArrayListWithCapacity(num);
		}
}
