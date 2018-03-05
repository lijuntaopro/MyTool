package com.li.tools.utils.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.li.tools.utils.CommonUtils;

public class Trie {
	
	private TrieNode rootNode;
	
	private Trie(){};
	
	public static Trie Build(String[] ss){
		Trie trie = new Trie();
		trie.rootNode = TrieNode.bulid(ss);
		return trie;
	}
	
	public boolean exists(String s){
		if(CommonUtils.isEmpty(s)){
			return false;
		}
		TrieNode parent = rootNode;
		for(char c : s.toCharArray()){
			TrieNode child = parent.search(c);
			if(child == null){
				return false;
			}
			parent = child;
		}
		return parent.isLast();
	}
	
	public List<String> commonPrefix(String str){
		if(CommonUtils.isEmpty(str)){
			throw new RuntimeException("前缀不能为空");
		}
		List<String> list = new ArrayList<String>();
		TrieNode parent = rootNode;
		for(char c : str.toCharArray()){
			TrieNode child = parent.search(c);
			if(child == null){
				return list;
			}
			parent = child;
		}
		parent.childrenString(list, str.toCharArray());
		return list;
	}
	
	public int countPrefix(String str){
		if(CommonUtils.isEmpty(str)){
			throw new RuntimeException("前缀不能为空");
		}
		TrieNode parent = rootNode;
		for(char c : str.toCharArray()){
			TrieNode child = parent.search(c);
			if(child == null){
				return 0;
			}
			parent = child;
		}
		return parent.getSize();
	}
	
	public static void main(String[] args) {
		System.out.println((char)-2);
		int[] a = new int[2];
		int[] b = new int[2];
		int[][] c = new int[2][];
		c[0] = a;
		c[1] = b;
		
		System.out.println((int )'a');
		System.out.println(System.currentTimeMillis());
		int size = 10000;
		String[] ss = randomString(size, 20);
		System.out.println(System.currentTimeMillis());
//		ss = new String[]{"c1", "c1a", "c2", "c3", "ca1"};
		Trie trie = Trie.Build(ss);
		System.out.println(System.currentTimeMillis());
		List<String> prefixs = trie.commonPrefix("44");
		System.out.println(System.currentTimeMillis());
		System.out.println(Arrays.toString(prefixs.toArray(new String[prefixs.size()])));
		String[] sss = randomString(10, 4);
		for(String s : sss){
			System.out.println("------" + System.currentTimeMillis());
			System.out.println(s + ":" + trie.commonPrefix(s).size());
			System.out.println("------" + System.currentTimeMillis());
			System.out.println(s + ":" + trie.countPrefix(s));
			System.out.println("------" + System.currentTimeMillis());
		}
	}
	
	public static String[] randomString(int size, int length){
		char[] base = "1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
		String[] ss = new String[size];
		for(;size>0;size--){
			Random random = new Random();
			int randomLength = random.nextInt(length) + 1;
			char[] cs = new char[randomLength];
			for(;randomLength>0;randomLength--){
				cs[randomLength - 1] = base[random.nextInt(base.length - 1)];
			}
			ss[size - 1] = new String(cs);
		}
		return ss;
	}
}

class TrieNode{
	
	private boolean isLast;
	
	private List<TrieNode> children;
	
	private char value;
	
	private int size;
	
	public TrieNode(){
		super();
	}
	
	public TrieNode(char value){
		super();
		this.value = value;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public List<TrieNode> getChildren() {
		return children;
	}

	public void setChildren(List<TrieNode> children) {
		this.children = children;
	}
	
	public char getValue() {
		return value;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public TrieNode search(char c){
		if(children != null){
			for(TrieNode child : children){
				if(child.getValue() == c){
					return child;
				}
			}
		}
		return null;
	}
	
	public void add(TrieNode child){
		if(children == null)
			children = new ArrayList<TrieNode>();
		children.add(child);
	}
	
	public static TrieNode bulid(String[] ss){
		//Arrays.sort(ss);
		TrieNode rootNode = new TrieNode();
		for(String s : ss){
			rootNode.setSize(rootNode.getSize() + 1);
			TrieNode parent = rootNode;
			for(char c : s.toCharArray()){
				TrieNode childNode = parent.search(c);
				if(childNode == null){
					childNode = new TrieNode(c);
					parent.add(childNode);
				}
				childNode.setSize(childNode.getSize() + 1);
				parent = childNode;
			}
			parent.setLast(true);
		}
		return rootNode;
	}
	
	public List<String> childrenString(List<String> list, char[] cs){
		char[] newCs = null;
		if(this.isLast() || !CommonUtils.isEmpty(this.getChildren())){
			newCs = new char[cs.length + 1];
			System.arraycopy(cs, 0, newCs, 0, cs.length);
			newCs[cs.length] = this.getValue();
		}
		if(this.isLast()){
			list.add(new String(newCs));
		}
		if(!CommonUtils.isEmpty(this.getChildren())){
			for(TrieNode child : this.getChildren()){
				child.childrenString(list, newCs);
			}
		}
		return list;
	}

}