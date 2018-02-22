package com.example.miodragmilosevic.roomtest;

/**
 * Created by miodrag.milosevic on 2/5/2018.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ServiceLocator {

    /**
     * All Services provided by the Service Locator have to implement this interface.
     * This is just a "marker interface pattern", it can be replaced by an annotation later.
     */
    public interface IService {
    }

    private static final Map<String, Object> sServicesInstances = new HashMap<>();
    private static final Map<String, Class> sServicesImplementationsMapping = new HashMap<>();
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static final Object sServicesInstancesLock = new Object();

    public static void init(@NonNull Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * Return instance of desired class or object that implement desired interface.
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T get(@NonNull Class<T> clazz) {
        @SuppressWarnings("ResourceType") T instance = (T) getService(clazz.getName(), mContext);
        return instance;
    }

    /**
     * This method allows to bind a custom service implementation to an interface.
     *
     * @param interfaceClass      interface
     * @param implementationClass class which implement interface specified in first param
     */
    public static void bindCustomServiceImplementation(@NonNull Class interfaceClass, @NonNull Class implementationClass) {
        synchronized (sServicesInstancesLock) {
            sServicesImplementationsMapping.put(interfaceClass.getName(), implementationClass);
        }
    }

    @NonNull
    private static Object getService(@NonNull String name, @Nullable Context applicationContext) {
        synchronized (sServicesInstancesLock) {
            Object o = sServicesInstances.get(name);
            if (o != null) {
                return o;
            } else {
                try {
                    Object serviceInstance;
                    final Class<?> clazz;
                    if (sServicesImplementationsMapping.containsKey(name)) {
                        clazz = sServicesImplementationsMapping.get(name);
                    } else {
                        clazz = Class.forName(name);
                    }

                    try {
                        Constructor e1 = clazz.getConstructor(Context.class);
                        serviceInstance = e1.newInstance(applicationContext);
                    } catch (NoSuchMethodException var6) {
                        Constructor constructor = clazz.getConstructor();
                        serviceInstance = constructor.newInstance();
                    }

                    if (!(serviceInstance instanceof ServiceLocator.IService)) {
                        throw new IllegalArgumentException("Requested service must implement IService interface");
                    }
                    sServicesInstances.put(name, serviceInstance);
                    return serviceInstance;
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException("Requested service class was not found: " + name, e);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Cannot initialize requested service: " + name, e);
                }
            }
        }
    }

    public interface Creator<T> {
        T newInstance(@NonNull Context context);
    }
}

