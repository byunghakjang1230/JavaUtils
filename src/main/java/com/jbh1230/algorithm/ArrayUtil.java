package com.jbh1230.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

	public static int[] convertListToArray(List<Integer> list) {
		return Arrays.stream(list.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray();
	}
	public static List<Integer> convertArrayToList(int[] arr) {
		return new ArrayList<>(Arrays.asList(Arrays.stream(arr).boxed().toArray(Integer[]::new)));
	}
	
//	private final static int ASC = 1;
//	private final static int ASC = 1;
	/*
	 * 정렬된 int 배열에 대해서 이진트리를 이용해서 특정 int 값의 INDEX값을 찾는 함수.(array 전체를 대상으로 시작)
	 * - Input
	 * arr 		: 대상배열
	 * val 		: 찾으려는 값.
	 * 
	 * - Output
	 * return	: 값을 찾을 경우 해당 Index, 값이 없을 경우 -1을 반환.
	 */
	public static int getIndexFromBinaryTreeByValue(int[] arr, int val) {
    	return getIndexFromBinaryTreeByValue(arr, val, 0, arr.length - 1);
    }
	/*
	 * 정렬된 int 배열에 대해서 이진트리를 이용해서 특정 int 값의 INDEX값을 찾는 함수.
	 * - Input
	 * arr 		: 대상배열
	 * val 		: 찾으려는 값.
	 * startIdx	: 시작 Index
	 * endIdx	: 끝 Index
	 * 
	 * - Output
	 * return	: 값을 찾을 경우 해당 Index, 값이 없을 경우 -1을 반환.
	 */
	public static int getIndexFromBinaryTreeByValue(int[] arr, int val, int startIdx, int endIdx) {
    	if(startIdx > endIdx) {
    		return -1;
    	}
    	int result = 0;
    	int centerIdx = (startIdx + endIdx) / 2;
    	if(arr[centerIdx] == val) {
    		result = centerIdx;
    	}
    	else if(arr[centerIdx] < val) {
    		result = getIndexFromBinaryTreeByValue(arr, val, centerIdx + 1, endIdx);
    	}
    	else if(arr[centerIdx] > val) {
    		result = getIndexFromBinaryTreeByValue(arr, val, startIdx, centerIdx - 1);
    	}
    	return result;
    }
	
	public static int getMinValueByMinHeap(int[] arr) {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		for(int val : arr) {
			list.add(val);
			sortMinHeapBottomUp(list);
		}
		return list.get(1);
	}
	
	public static int getMaxValueByMaxHeap(int[] arr) {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		for(int val : arr) {
			list.add(val);
			sortMaxHeapBottomUp(list);
		}
		System.out.println(list.toString());
		popMaxValueFromMaxHeap(list);
		return list.get(1);
	}
	
	public static int getMinValueByMinHeap(int[] arr, int startIdx, int endIdx) {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		for(int i = startIdx; i <= endIdx; i++) {
			list.add(arr[i]);
			sortMinHeapBottomUp(list);
		}
		return list.get(1);
	}
	
	public static int getMaxValueByMaxHeap(int[] arr, int startIdx, int endIdx) {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		for(int i = startIdx; i <= endIdx; i++) {
			list.add(arr[i]);
			sortMaxHeapBottomUp(list);
		}
		return list.get(1);
	}
	
	/**********************************************************************
	 * 
	 * Heap List 얻기
	 * Array -> List
	 * int[] -> Integer[]
	 **********************************************************************/
	//
	public static List<Integer> getMaxHeapListFromArrayToList(int[] arr){
		return getMaxHeapListFromListToList(convertArrayToList(arr));
	}
	public static int[] getMaxHeapListFromArrayToArray(int[] arr){
		List<Integer> list = getMaxHeapListFromListToList(convertArrayToList(arr));
		int[] result = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result; 
	}
	public static List<Integer> getMaxHeapListFromListToList(List<Integer> iList){
		List<Integer> list = new ArrayList<>();
		list.add(0);
		for(int i = 0; i < iList.size(); i++) {
			list.add(iList.get(i));
			sortMaxHeapBottomUp(list);
		}
		return list;
	}
	
	/*
	 * 최소값을 리턴하고 해당 항목 삭제 후 힙 정렬.
	 */
	private static int popMinValueFromMinHeap(List<Integer> list) {
		int result = list.get(1);
		list.set(1, list.get(list.size()-1));
		list.remove(list.size()-1);
		sortMinHeapTopDown(list, 1);
		return result;
	}
	private static void sortMinHeapBottomUp(List<Integer> list) {
		int idx = list.size() - 1;
		int parent = idx / 2;
		int temp = 0;
		while(parent > 0) {
			if(list.get(parent) > list.get(idx)) {
				temp = list.get(parent);
				list.set(parent, list.get(idx));
				list.set(idx, temp);
				idx = parent;
				parent = idx / 2;
			}
			else
				break;
		}
	}
	
	/*
	 * 최대값을 리턴하고 해당 항목 삭제 후 힙 정렬.
	 */
	private static int popMaxValueFromMaxHeap(List<Integer> list) {
		int result = list.get(1);
		list.set(1, list.get(list.size()-1));
		list.remove(list.size()-1);
		System.out.println(list.toString());
		sortMaxHeapTopDown(list, 1);
		return result;
	}
	private static void sortMaxHeapBottomUp(List<Integer> list) {
		int idx = list.size() - 1;
		int parent = idx / 2;
		int temp = 0;
		while(parent > 0) {
			if(list.get(parent) < list.get(idx)) {
				temp = list.get(parent);
				list.set(parent, list.get(idx));
				list.set(idx, temp);
				idx = parent;
				parent = idx / 2;
			}
			else
				break;
		}
	}
	
	/*
	 * 탑 다운방식으로 힙 정렬.(최소 힙.)
	 */
	private static void sortMinHeapTopDown(List<Integer> list, int cIdx) {
		sortMinHeapTopDown(convertListToArray(list), cIdx);
	}
	private static void sortMinHeapTopDown(int[] arr, int cIdx) {
		int lIdx = cIdx * 2;
		int rIdx = cIdx * 2 + 1;
		int tempVal = 0;
		if(arr.length - 1 < lIdx) {
			return;
		}
		else if(arr[lIdx] < arr[cIdx]){
			tempVal = arr[cIdx];
			arr[cIdx] = arr[lIdx];
			arr[lIdx] = tempVal;
			sortMinHeapTopDown(arr, lIdx);
		}
		else if(arr.length - 1 < rIdx) {
			return;
		}
		else if(arr[rIdx] < arr[cIdx]){
			tempVal = arr[cIdx];
			arr[cIdx] = arr[rIdx];
			arr[rIdx] = tempVal;
			sortMinHeapTopDown(arr, rIdx);
		}
		else if(arr[lIdx] >= arr[cIdx] && arr[rIdx] >= arr[cIdx]) {
			return;
		}
	}
	
	/*
	 * 탑 다운방식으로 힙 정렬.(최대 힙.)
	 */
	private static void sortMaxHeapTopDown(List<Integer> list, int cIdx) {
//		sortMaxHeapTopDown(convertListToArray(list), cIdx);
		sortMaxHeapTopDownWhile(convertListToArray(list), cIdx);
	}
	private static void sortMaxHeapTopDown(int[] arr, int cIdx) {
		int lIdx = cIdx * 2;
		int rIdx = cIdx * 2 + 1;
		int tempVal = 0;
		if(arr.length - 1 < lIdx) {
			return;
		}
		else if(arr[lIdx] > arr[cIdx]){
			tempVal = arr[cIdx];
			arr[cIdx] = arr[lIdx];
			arr[lIdx] = tempVal;
			sortMaxHeapTopDown(arr, lIdx);
		}
		else if(arr.length - 1 < rIdx) {
			return;
		}
		else if(arr[rIdx] > arr[cIdx]){
			tempVal = arr[cIdx];
			arr[cIdx] = arr[rIdx];
			arr[rIdx] = tempVal;
			sortMaxHeapTopDown(arr, rIdx);
		}
		else if(arr[lIdx] <= arr[cIdx] && arr[rIdx] <= arr[cIdx]) {
			return;
		}
	}
	
	public static void sortMaxHeapTopDownWhile(int[] arr, int cIdx) {
		int rootIdx = cIdx;
		int lIdx = 0;
		int rIdx = 0;
		int tempVal = 0;
		while(true) {
			lIdx = rootIdx * 2;
			rIdx = rootIdx * 2 + 1;
			if(arr.length - 1 < lIdx) {
				break;
			}
			else if(arr[lIdx] > arr[rootIdx]){
				tempVal = arr[rootIdx];
				arr[rootIdx] = arr[lIdx];
				arr[lIdx] = tempVal;
				rootIdx = lIdx;
			}
			else if(arr.length - 1 < rIdx) {
				break;
			}
			else if(arr[rIdx] > arr[rootIdx]){
				tempVal = arr[rootIdx];
				arr[rootIdx] = arr[rIdx];
				arr[rIdx] = tempVal;
				rootIdx = rIdx;
			}
			else if(arr[lIdx] <= arr[rootIdx] && arr[rIdx] <= arr[rootIdx]) {
				break;
			}
		}
	}
}
