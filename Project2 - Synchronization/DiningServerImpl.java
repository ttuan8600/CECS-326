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
	private Lock lock;
	private Condition[] cond;
	private int[] forks;
	private int numPhil;
	
	public DiningServerImpl(int numPhil)
	{
		// Initialize the variables
		this.numPhil = numPhil;
		lock = new ReentrantLock();
		cond = new Condition[numPhil];
		forks = new int[numPhil];

		for (int i = 0; i < numPhil; i++){
			cond[i] = lock.newCondition();
			forks[i] = 2;
		}
	}
	
	@Override
	public void takeForks(int philNumber)
	{
		lock.lock();
		try{
			while (forks[philNumber] < 2 || forks[(philNumber+1)%numPhil] < 2){
				cond[philNumber].await();
			}

			forks[philNumber] -= 2;
			forks[(philNumber + 1) % numPhil] -= 2;

			// Print out to the console 
			System.out.println("Fork #" + philNumber + " is with " + forks[philNumber]);
			System.out.println("Fork #" + ((philNumber + 1) % numPhil) + " is with " + forks[(philNumber + 1) % numPhil]);
		}catch (InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	@Override
	public void returnForks(int philNumber)
	{
		lock.lock();
		try{
			forks[philNumber] += 2;
			forks[(philNumber + 1) % numPhil] += 2;

			// Print out to the console
			System.out.println("Fork #" + philNumber + " is with " + forks[philNumber]);
			System.out.println("Fork #" + ((philNumber + 1) % numPhil) + " is with " + forks[(philNumber + 1) % numPhil]);

			cond[(philNumber - 1 + numPhil) % numPhil].signal();
			cond[(philNumber + 1) % numPhil].signal();
		}finally{
			lock.unlock();
		}
	}
}
