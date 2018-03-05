package com.li.tools.utils.algorithm;

import java.util.ArrayList;
import java.util.List;

public class DoubleTrie {
	
	int[] base;
	int[] check;
	int begin = 1;
	int current = 1;
	
	int initSize = 1<<15;
	
	public void init(){
		base = new int[initSize];
		check = new int[initSize];
	}
	
	public void resize(){
		int[] newBase = new int[initSize * 2];
		int[] newCheck = new int[initSize * 2];
		System.arraycopy(base, 0, newBase, 0, initSize);
		System.arraycopy(check, 0, newCheck, 0, initSize);
		initSize *=2;
	}
	
	public void build(List<String> list){
		for(String str : list){
			build(str);
		}
	}
	
	public void build(String str){
		int state = begin;
		for(char c :str.toCharArray()){
			if(check[base[state] + c] == 0){
				check[base[state] + c] = state;
			}else{
				while(true){
					
				}
			}
		}
	}
	
	public boolean reLocated(int state, int b){
		for(int i=0; i<1<<16; i++){
			if(check[base[state] + i] == state){
				if(check[b + i] != 0){
					//已分配
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("bdc");
		list.add("cad");
		list.add("adb");
		list.add("bda");
		DoubleTrie trie = new DoubleTrie();
	}
}
