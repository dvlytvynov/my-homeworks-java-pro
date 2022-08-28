package reflection.apps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ReflectionMapUtils {
    private ReflectionMapUtils() {
    }

    public static <K, V> Map<K, V> createMap() throws ReflectionExceptions {
        Object[] args = new Object[]{};
        Class<?>[] paramTypes = new Class[]{};
        return constructMap(args, paramTypes);
    }

    public static <K, V> Map<K, V> createMap(int initCapacity) throws ReflectionExceptions {
        Object[] args = new Object[]{initCapacity};
        Class<?>[] paramTypes = new Class[]{int.class};
        return constructMap(args, paramTypes);
    }

    public static <K, V> Map<K, V> createMap(int initCapacity, float loadFactor)
            throws ReflectionExceptions {
        Object[] args = new Object[]{initCapacity, loadFactor};
        Class<?>[] paramTypes = new Class[]{int.class, float.class};
        return constructMap(args, paramTypes);
    }

    public static <K, V> Map<K, V> createMap(Map<? extends K, ? extends V> m)
            throws ReflectionExceptions {
        Object[] args = new Object[]{m};
        Class<?>[] paramTypes = new Class[]{Map.class};
        return constructMap(args, paramTypes);
    }

    private static <K, V> Map<K, V> constructMap(Object[] args, Class<?>[] paramTypes)
            throws ReflectionExceptions {
        Map map;
        try {
            map = HashMap.class.getConstructor(paramTypes).newInstance(args);
        } catch (InstantiationException |
                NoSuchMethodException |
                InvocationTargetException |
                IllegalAccessException e) {
            throw new ReflectionExceptions(e);
        }
        return map;
    }

    public static <K, V> V put(Map<K, V> map, K key, V value) throws ReflectionExceptions {
        Object[] args = new Object[]{key, value};
        Class<?>[] paramTypes = new Class[]{Object.class, Object.class};
        return invokeMethod(map, args, "put", paramTypes);
    }

    public static <K, V> V remove(Map<K, V> map, K key) throws ReflectionExceptions {
        Object[] args = new Object[]{key};
        Class<?>[] paramTypes = new Class[]{Object.class};
        return invokeMethod(map, args, "remove", paramTypes);
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) throws ReflectionExceptions {
        Object[] args = new Object[]{};
        Class<?>[] paramTypes = new Class[]{};
        return invokeMethod(map, args, "isEmpty", paramTypes);
    }

    public static <K, V> Set<K> keySet(Map<K, V> map) throws ReflectionExceptions {
        Object[] args = new Object[]{};
        Class<?>[] paramTypes = new Class[]{};
        return invokeMethod(map, args, "keySet", paramTypes);
    }

    public static <K, V> V get(Map<K, V> map, K key) throws ReflectionExceptions {
        Object[] args = new Object[]{key};
        Class<?>[] paramTypes = new Class[]{Object.class};
        return invokeMethod(map, args, "get", paramTypes);
    }

    private static <K, V, R> R invokeMethod(
            Map<K, V> map, Object[] args, String methodName, Class<?>[] paramTypes)
            throws ReflectionExceptions {
        R result;
        Class<? extends Map> classMap = map.getClass();
        try {
            Method method = classMap.getMethod(methodName, paramTypes);
            result = (R) method.invoke(map, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new ReflectionExceptions(e);
        }
        return result;
    }
}
