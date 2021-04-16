package org.zhangxujie.konfig.common;

public interface LogUtil<T, V> {

    boolean insert(String tag, T oldData, V newData, String opUsername);


}
