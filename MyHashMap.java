package hashtable;


import linkedlist.MyLinkedList;

import java.util.ArrayList;

public class MyHashMap<K, V> {
	private final int numBuckets;
	ArrayList<MyLinkedList<K>> myBucketArray;
	
	public MyHashMap() {
		this.numBuckets = 10;
		this.myBucketArray = new ArrayList<>(numBuckets);
		
		for (int i = 0; i < numBuckets; i++) {
			this.myBucketArray.add(null);
		}
	}
	
	private int getBucketIndex(K key) {
		int hashCode = Math.abs(key.hashCode());
		int index = hashCode % numBuckets;
		return index;
	}

	public V get(K key) {
		int index = this.getBucketIndex(key);
		MyLinkedList<K> myLinkedList= this.myBucketArray.get(index);
		if (myLinkedList == null) {
			return null;
		}
		MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) myLinkedList.search(key);
		return (myMapNode == null) ? null : myMapNode.getValue();
	}

	public void add(K key, V value) {
		int index = this.getBucketIndex(key);
		MyLinkedList<K> myLinkedListTest = this.myBucketArray.get(index);
		if (myLinkedListTest == null) {
			myLinkedListTest = new MyLinkedList<>();
			this.myBucketArray.set(index, myLinkedListTest);
		}
		MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) myLinkedListTest.search(key);// TODO Auto-generated method stub
		if (myMapNode == null) {
			myMapNode = new MyMapNode<>(key, value);
			myLinkedListTest.append(myMapNode);
		} else {
			myMapNode.setValue(value);
		}
	}
	
	public String toString() {
		return "MyHashNodes List{" + myBucketArray + "}";
	}
}