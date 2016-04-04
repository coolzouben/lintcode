package com.ben;

public class Print {
	public static void print(int[] array){
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}
	Print(ListNode head){
		if(head==null) System.out.println("");
		while(head!=null){
			System.out.print(head.val+' ');
			head=head.next;
		}
	}
}
