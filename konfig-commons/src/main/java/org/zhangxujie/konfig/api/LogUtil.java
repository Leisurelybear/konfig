package org.zhangxujie.konfig.api;

public interface LogUtil<T, V> {

    boolean insert(String tag, T oldData, V newData, String opUsername);


}
