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
	Lock key[] = new ReentrantLock[5];
	Condition cond[] = new Condition[5];

	public DiningServerImpl()
	{
		for(int i = 0; i < 5; i++)
		{
			key[i] = new ReentrantLock();
			cond[i] = key[i].newCondition();
		}
	}

	@Override
	public void takeForks(int philNumber)
	{
		int leftFork = philNumber;
		int rightFork = (philNumber + 1) % 5;

		key[leftFork].lock();
		key[rightFork].lock();

		try
		{
			while(forks[leftFork] == 1 || forks[rightFork] == 1)
			{
				cond[leftFork].await();
				cond[rightFork].await();
			}

			forks[leftFork] = 1;
			forks[rightFork] = 1;
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			key[leftFork].unlock();
			key[rightFork].unlock();
		}
	}

	@Override
	public void returnForks(int philNumber)
	{
		int leftFork = philNumber;
		int rightFork = (philNumber + 1) % 5;

		key[leftFork].lock();
		key[rightFork].lock();

		try
		{
			forks[leftFork] = 0;
			forks[rightFork] = 0;

			cond[leftFork].signal();
			cond[rightFork].signal();
		}
		finally
		{
			key[leftFork].unlock();
			key[rightFork].unlock();
		}
	}
}
