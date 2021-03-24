package com.pancm.jdk8;

import java.awt.geom.Rectangle2D;
import java.security.PublicKey;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import clojure.lang.Obj;
import com.alibaba.fastjson.JSON;

/**
 * @Title: lambdaTest
 * @Description: 拉姆达表达式
 * 
 * @Version:1.0.0
 * @author pancm
 * @date 2018年8月28日
 */
public class LambdaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		test1();
		test2();
//		test3();
//		test4();
	}
	private static void test1() {

		Map<String, String> map = new HashMap<>();
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		map.put("d", "d");

		System.out.println("map普通方式遍历 keySet:");
		for (String key : map.keySet()) {
			System.out.println("k=" + key + "，v=" + map.get(key));
		}
		System.out.println("map普通方式遍历 entrySet:");
		for (Map.Entry<String, String> item: map.entrySet()) {
			System.out.println(item.getKey()+"===="+item.getValue());
		}

		System.out.println("map拉姆达表达式遍历:");
		map.forEach((k, v) -> {
			System.out.println("k=" + k + "，v=" + v);
		});

		
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("bb");
		list.add("ccc");
		list.add("dddd");
		System.out.println("list拉姆达表达式遍历:");
		list.forEach(v -> {
			System.out.println(v);
		});
		System.out.println("list双冒号运算符遍历:");
		list.forEach(System.out::println);

	}

	private static void test2() {
		List<User> list = new ArrayList<User>();
		List<User> list2 = new ArrayList<User>();
		list.add(new User(1, "张三"));
		list.add(new User(2, "李四"));
		list.add(new User(3, "王五"));
		list.add(new User(4, "赵六"));
		System.out.println("list:" + list);
		list.forEach(v -> {
			if (v.getId() > 2) {
				list2.add(v);
			}
		});
		System.out.println("list2:" + list2);
	}

	private static void test3(){
		/*Map<String, Object> map = new HashMap<>();
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		map.put("d", "d");

		for (Map.Entry<String, Object> entry:map.entrySet()){
			System.out.println(entry.getKey()+"==="+entry.getValue());
		}
		for (String key:map.keySet()){
			System.out.println(key+"======"+map.get(key));
		}

		map.forEach((k,v)->{
			System.out.println(k+"=="+v);
		});*/

		/*List<String> list = new ArrayList<String>(4);
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		for(String i: list){
			System.out.println(i);
		}

		list.forEach((str)->{
			System.out.println(str);
		});

		list.forEach(System.out::println);*/


		List<User> list2 = new ArrayList<User>(5);
		list2.add(new User(1,"1"));
		list2.add(new User(2,"2"));
		list2.add(new User(3,"3"));
		list2.add(new User(4,"4"));
		list2.add(new User(5,"5"));

		for (int i = 0; i < list2.size(); i++) {
			System.out.println(list2.get(i).toMap());
		}

		for (User user : list2) {
			System.out.println(user.toMap());
		}

		for (int i = list2.size() - 1; i >= 0; i--) {
			System.out.println(list2.get(i).toMap());
		}

		list2.forEach((item)->{
			System.out.println(item.toMap());
		});

		list2.forEach(System.out::println);
	}

	private static void test4(){
		List<User> uList = new ArrayList<User>();
		uList.add(new User(1,"李1"));
		uList.add(new User(3,"李3"));
		uList.add(new User(2,"李2"));
		uList.add(new User(4,"李4"));
		uList.add(new User(6,"李6"));
		uList.add(new User(5,"李5"));
		uList.add(new User(7,"李7"));
		uList.add(new User(9,"李9"));
		uList.add(new User(8,"李8"));


		List<User> resultList = new ArrayList<>();
		//使用普通方式
		/*//1、使用迭代器
		for (User user : uList) {
			if(user.getId() > 3){
				resultList.add(user);
			}
		}
		//2、使用匿名类 排序
		Collections.sort(resultList, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return Integer.compare(o1.getId(),o2.getId());
			}
		});
		System.out.println("=========");
		for (User user : resultList) {
			System.out.println(user);
		}*/

		//使用stream
		/*
		resultList = uList.stream().filter(u -> u.getId() > 3).sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
		for (User user : resultList) {
			System.out.println(user);
		}*/

	}

	//线程
	private static void testThread(){
		//使用普通的方式创建
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("普通方式创建!");
			}
		};
		r1.run();

		//使用拉姆达方式创建
		Runnable r2 = () -> System.out.println("拉姆达方式创建!");
		r2.run();
	}

}

class User {

	/** 编号 */
	private int id;
	/** 姓名 */
	private String name;

	public User() {
	}

	/**
	 * 构造方法
	 * 
	 * @param id   编号
	 * @param name 姓名
	 */
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * 获取编号
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置编号
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取姓名
	 * 
	 * @return name
	 */
	public String getName() {
		System.out.println("姓名:" + name);
		return name;
	}

	/**
	 * 设置姓名
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("rawtypes")
	public Map toMap() {
		return JSON.parseObject(toString(), HashMap.class);
	}

	/** 
	 * 
	 */
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"name\":\"" + name + "\"}";
	}
	
	
}
