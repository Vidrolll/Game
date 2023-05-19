package com.test.main.util.handlers;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class ClassInstantiator {
	private HashMap<String,String> classes = new HashMap<String,String>();
	
	public Object instantiate(String key, Object...args) throws Exception {
		String path = classes.get(key);
		if(path==null) path = key;
		Class<?> clazz = Class.forName(path);
		for(Constructor<?> ctor : clazz.getConstructors()) {
			Class<?>[] paramTypes = ctor.getParameterTypes();
			if(args.length == paramTypes.length) {
				return ctor.newInstance(args);
			}
		}
		throw new IllegalArgumentException("Cannot instantiate class " + classes.get(key));
	}
	
	public void addClass(String key, Class<?> clazz) {
		classes.put(key, clazz.getCanonicalName());
	}
	public void removeClass(String clazz) {
		classes.remove(clazz);
	}
}
