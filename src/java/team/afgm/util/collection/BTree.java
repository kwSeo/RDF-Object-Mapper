package team.afgm.util.collection;

import java.util.ArrayList;
import java.util.List;

public class BTree<T extends Comparable<T>> {
	private int count = 0;
	private Node<T> root = null;
	
	public void add(T newValue){
		//root가 null인 경우
		if(root == null){
			root = new Node<T>(newValue);
			return;
		}
		//아닌 경우
		insert(new Node<T>(newValue), root);
		
		count++;
	}
	
	public T get(T value){
		Node<T> node = find(new Node<T>(value), root);
		if(node != null)
			return node.getValue();
		else
			return null;
	}
	
	protected Node<T> find(Node<T> node, Node<T> nowNode){
		if(nowNode == null){
			return null;
		}
		
		int compareValue = nowNode.getValue().compareTo(node.getValue());
		if(compareValue == 0){
			return nowNode;
		}else if(compareValue > 0){
			return find(node, nowNode.getLeft());
		}else{
			return find(node, nowNode.getRight());
		}
	}
	
	protected void insert(Node<T> node, Node<T> nowNode){
		if(nowNode == null){
			Node<T> parent = node.getParent();
			T parentValue = parent.getValue();
			if(parentValue.compareTo(node.getValue()) > 0){
				parent.setLeft(node);
			}else{
				parent.setRight(node);
			}
			return;
			
		}
		
		node.setParent(nowNode);
		T value = nowNode.getValue();
		T newValue = node.getValue();
		
		//parent가 더 큰경우, 왼쪽으로
		if(value.compareTo(newValue) > 0){
			insert(node, nowNode.getLeft());
		}else{
			insert(node, nowNode.getRight());
		}
	}
	
	public List<T> getSortedValues(){
		List<T> list = new ArrayList<>();
		inOrder(root, list);
		return list;
	}
	
	public void inOrder(Node<T> nowNode, List<T> list){
		if(nowNode == null){
			return;
		}
		inOrder(nowNode.getLeft(), list);
		list.add(nowNode.getValue());
		inOrder(nowNode.getRight(), list);
	}
	
}

class Node<T> {
	private T value;
	private Node<T> parent;
	private Node<T> left, right;
	
	public Node(T value){
		//parent는 null로
		this(value, null);
	}
	
	public Node(T value, Node<T> parent){
		this.value = value;
		this.parent = parent;
		this.left = this.right = null;
	}
	
	public T getValue(){
		return value;
	}
	
	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	@Override
	public String toString(){
		return value.toString();
	}
	
}

