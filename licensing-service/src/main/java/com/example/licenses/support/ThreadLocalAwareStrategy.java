package com.example.licenses.support;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public  class ThreadLocalAwareStrategy extends HystrixConcurrencyStrategy {

    private HystrixConcurrencyStrategy exsitingConcurrencyStrategy;

    public ThreadLocalAwareStrategy(HystrixConcurrencyStrategy exsitingConcurrencyStrategy){
        this.exsitingConcurrencyStrategy =exsitingConcurrencyStrategy;
    }

    @Override
    public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
        return exsitingConcurrencyStrategy!=null?exsitingConcurrencyStrategy.getBlockingQueue(maxQueueSize):
                super.getBlockingQueue(maxQueueSize);
    }

    @Override
    public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
        return exsitingConcurrencyStrategy!=null?
                exsitingConcurrencyStrategy.getRequestVariable(rv): super.getRequestVariable(rv);
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixThreadPoolProperties threadPoolProperties) {
        return exsitingConcurrencyStrategy!=null?
                exsitingConcurrencyStrategy.getThreadPool(threadPoolKey,threadPoolProperties)
                : super.getThreadPool(threadPoolKey, threadPoolProperties);
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey,
                                            HystrixProperty<Integer> corePoolSize,
                                            HystrixProperty<Integer> maximumPoolSize,
                                            HystrixProperty<Integer> keepAliveTime,
                                            TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue) {
        return exsitingConcurrencyStrategy!=null?
                exsitingConcurrencyStrategy.getThreadPool(threadPoolKey,corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue)
                :super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return exsitingConcurrencyStrategy!=null?
                exsitingConcurrencyStrategy.wrapCallable(new DelegatingUserContextCallable<>(callable,UserContextHolder.getContext())):
                super.wrapCallable(new DelegatingUserContextCallable<>(callable,UserContextHolder.getContext()));
    }
}
