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
	ReentrantLock key[] = new ReentrantLock[5];
	Condition cond[] = new Condition[5];
	
	public DiningServerImpl()
	{
		for (int i = 0; i < 5; i++){
			key[i] = new ReentrantLock();
			cond[i] = key[i].newCondition();
		}
	}
	
	@Override
	public void takeForks(int philNumber)
	{
		
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
