package com.blankj.module0;

import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.LogUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2018/10/08
 *     desc  :
 * </pre>
 */
@BusUtils.Bus
public class Module0Bus {

    @BusUtils.Subscribe(name = "module0")
    public static String module0Bus(String name) {
        LogUtils.e(name);
        return "module0Bus";
    }
}
