/*
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 * 
 * To synchronize access to the forks, the class uses a Lock object. 
 * This allows only one philosopher at a time to acquire or release the forks. 
 * The forks array is an array of Condition objects, with one Condition object for each fork. 
 * These Condition objects are used to allow the philosophers to wait until the forks they need become available
 *
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl  implements DiningServer
{  
	private Lock lock;
	private Condition[] forks;
	private boolean[] available;
	private int numPhil;

	public DiningServerImpl(int numPhil)
	{
		// Initialize the variables
		lock = new ReentrantLock();
		forks = new Condition[numPhil];
		available = new boolean[numPhil];
		this.numPhil = numPhil;
		
		for (int i = 0; i < numPhil; i++){
			forks[i] = lock.newCondition();
			available[i] = true;
		}
	}
	
	@Override
	public void takeForks(int philNumber)
	{
		lock.lock();
		try{
			int leftFork = philNumber;
			int rightFork = (philNumber + 1) % numPhil;

			while(!available[leftFork] || !available[rightFork]){
				forks[leftFork].await();
				// forks[rightFork].await();
			}

			available[leftFork] = false;
			System.out.println("Fork #" + leftFork + " is with " + philNumber);

			available[rightFork] = false;
			System.out.println("Fork #" + rightFork + " is with " + philNumber);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}
	
	@Override
	public void returnForks(int philNumber) {
		lock.lock();
		try{
			int leftFork = philNumber;
			int rightFork = (philNumber + 1) % numPhil;

			available[leftFork] = true;
			available[rightFork] = true;

			forks[leftFork].signal();
			forks[rightFork].signal();
		}finally {
			lock.unlock();
		}
	}
}
