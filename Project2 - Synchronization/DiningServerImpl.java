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
