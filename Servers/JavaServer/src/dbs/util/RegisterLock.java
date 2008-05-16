/**
 * @author sekhri
 */


package dbs.util;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;


public class RegisterLock {
	private ReadWriteLock globalFlagLock = new ReentrantReadWriteLock();
	private Lock readFlagLock = globalFlagLock.readLock();
	private Lock writeFlagLock = globalFlagLock.writeLock();
	private static boolean doneOnce;


	
	private static RegisterLock instance = null;
	private static Boolean mutex = new Boolean(true);
	public static RegisterLock getRegisterLockInstance() throws Exception {
		if( instance != null) return instance;
		synchronized(mutex) {
			if( instance != null ) return instance;
			instance = new RegisterLock();
		}
		return instance;
	}
	

	private RegisterLock() throws Exception {
		setDoneOnce(false);
	}

	
	public boolean isDoneOnce() throws Exception {
		boolean toReturn = false;
		readFlagLock.lock(); 
		try { 
			toReturn = doneOnce; 
		} finally { 
			readFlagLock.unlock();
		}
		return toReturn;
	}
	
	public void setDoneOnce(boolean in) throws Exception {
		writeFlagLock.lock();
		try {
			doneOnce = in;
		} finally { 
			writeFlagLock.unlock();
		}
	}
	

}
