/*
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 *
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl  implements DiningServer
{  
	private Lock lock = new ReentrantLock();
	private Condition[] condition = new Condition[5];
	private int[] state = new int[5];
	private int[] left = new int[5];
	private int[] right = new int[5];
	
	public DiningServerImpl()
	{
		for (int i = 0; i < 5; i++){
			condition[i] = lock.newCondition();
			state[i] = 0;
			left[i] = (i + 4) % 5;
			right[i] = (i + 1) % 5;
		}
	}
	
	public void takeForks(int i)
	{
		lock.lock();
		try{
			state[i] = 1;
			test(i);
			while (state[i] != 2){
				condition[i].await();
			}
		}catch (InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void returnForks(int i)
	{
		lock.lock();
		try{
			state[i] = 0;
			test(left[i]);
			test(right[i]);
		}finally{
			lock.unlock();
		}
	}
	
	private void test(int i)
	{
		if (state[i] == 1 && state[left[i]] != 2 && state[right[i]] != 2){
			state[i] = 2;
			condition[i].signal();
		}
	}
}
