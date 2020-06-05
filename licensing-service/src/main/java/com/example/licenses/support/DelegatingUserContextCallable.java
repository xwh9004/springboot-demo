package com.example.licenses.support;

import com.example.common.util.UserContext;
import com.example.common.util.UserContextHolder;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<V> implements Callable<V> {

    private final Callable<V> delegate;
    private UserContext originalUserContext;
    public DelegatingUserContextCallable(Callable<V> delegate ,UserContext userContext){
        this.delegate =delegate;
        this.originalUserContext =userContext;
    }

    @Override
    public V call() throws Exception {
        UserContextHolder.setUserContext(originalUserContext);
        try {
            return  delegate.call();
        }finally {
            this.originalUserContext =null;
        }
    }
    public static <V> Callable<V> create(Callable<V> delegate,UserContext userContext){
        return new DelegatingUserContextCallable<>(delegate,userContext);
    }


}
