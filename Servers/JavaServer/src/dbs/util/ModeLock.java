/**
 * @author sekhri
 */


package dbs.util;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;


public class ModeLock {
	private ReadWriteLock globalFlagLock = new ReentrantReadWriteLock();
	private Lock readFlagLock = globalFlagLock.readLock();
	private Lock writeFlagLock = globalFlagLock.writeLock();
	private static boolean modeSet;


	
	private static ModeLock instance = null;
	private static Boolean mutex = new Boolean(true);
	public static ModeLock getModeLockInstance() throws Exception {
		if( instance != null) return instance;
		synchronized(mutex) {
			if( instance != null ) return instance;
			instance = new ModeLock();
		}
		return instance;
	}
	

	private ModeLock() throws Exception {
		setMode(false);
	}

	
	public boolean isMode() throws Exception {
		boolean toReturn = false;
		readFlagLock.lock(); 
		try { 
			toReturn = modeSet; 
		} finally { 
			readFlagLock.unlock();
		}
		return toReturn;
	}
	
	public void setMode(boolean in) throws Exception {
		writeFlagLock.lock();
		try {
			modeSet = in;
		} finally { 
			writeFlagLock.unlock();
		}
	}
	

}
