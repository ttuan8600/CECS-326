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
	ReentrantLock rtLock = new ReentrantLock[5];
	Lock key = new ReentrantLock();
	Condition[] cond = new Condition[5];

	@Override
	public void takeForks(int philNumber){
		int leftFork = philNumber;
		int rightFork = (philNumber + 1) % 5;

		synchronized(this)
		{
			while(forks[leftFork] == 1 || forks[rightFork] == 1)
			{
				try
				{
					wait();
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			forks[leftFork] = 1;
			forks[rightFork] = 1;
		}
	}

	@Override
	public void returnForks(int philNumber)
	{
		int leftFork = philNumber;
		int rightFork = (philNumber + 1) % 5;

		synchronized(this)
		{
			forks[leftFork] = 0;
			forks[rightFork] = 0;
			notifyAll();
		}
	}

}
