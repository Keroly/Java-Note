package juc.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public interface Lock {
    void lock();

    void unlock();

}
