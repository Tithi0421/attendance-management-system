package multi;

public class EntryCount {

/*	public static void main(String args[]) {
	

	}*/
	static int count=0;
	
	public void incCount(){
		count++;
		System.out.println(count);
	}
	
	public void decCount(){
		count--;
		System.out.println(count);
	}

	public int getCount(){
		
		return count;
	}
}
