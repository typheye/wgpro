/**
 ******************************************************************************
 * @file    IappCompat.java
 * @author  Typheye
 * @brief   Source-level compatibility helpers for legacy iApp runtime classes.
 ******************************************************************************
 * @attention
 *
 * Copyright (c) 2021-2026 Typheye. All rights reserved.
 *
 * This software is licensed under terms that can be found in the LICENSE file
 * in the root directory of this software component.
 * If no LICENSE file comes with this software, it is provided AS-IS.
 *
 ******************************************************************************
 */
package open.cn.awg.pro.core;

import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class IappCompat {

    private IappCompat() {
    }

    public static i.runlibrary.app.sj$zh zh(Object value) {
        return (i.runlibrary.app.sj$zh) (Object) value;
    }

    public static int sdk(Object deviceInfo) {
        if (deviceInfo != null) {
            try {
                Field sdkField = deviceInfo.getClass().getField("sdk");
                Object value = sdkField.get(deviceInfo);
                if (value instanceof Number) {
                    return ((Number) value).intValue();
                }
                if (value != null) {
                    return Integer.parseInt(String.valueOf(value));
                }
            } catch (Exception ignored) {
            }
        }
        return Build.VERSION.SDK_INT;
    }

    public interface V7lbViewBinder {

        void bind(Object ua, int pn, View vw);
    }

    public static i.runlibrary.app.v.v7lb$UserAdapter v7lbAdapter(
            Object list,
            Object itemClass,
            Object itemLayout) {
        return (i.runlibrary.app.v.v7lb$UserAdapter) (Object)
                invokeV7lbspq(list, itemClass, itemLayout);
    }

    public static i.runlibrary.app.v.v7lb$UserAdapter v7lbAdapter(
            Object list,
            Object itemClass,
            Object itemLayout,
            V7lbViewBinder binder) {
        return (i.runlibrary.app.v.v7lb$UserAdapter) (Object)
                invokeV7lbspq(list, itemClass, itemLayout, createV7lbViewCallback(binder));
    }

    private static Object invokeV7lbspq(Object list, Object... args) {
        if (list == null) {
            return null;
        }
        try {
            for (Method method : list.getClass().getMethods()) {
                if ("v7lbspq".equals(method.getName())
                        && method.getParameterTypes().length == args.length) {
                    return method.invoke(list, args);
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private static Object createV7lbViewCallback(final V7lbViewBinder binder) {
        try {
            final Class<?> callbackClass = Class.forName("i.runlibrary.app.v.v7lb$OnUserAdapterView");
            return Proxy.newProxyInstance(
                    callbackClass.getClassLoader(),
                    new Class[]{callbackClass},
                    new InvocationHandler() {

                        public Object invoke(Object proxy, Method method, Object[] args) {
                            if (method.getDeclaringClass() == Object.class) {
                                return handleObjectMethod(proxy, method, args);
                            }
                            if ("getView".equals(method.getName()) && args != null && args.length >= 3) {
                                binder.bind(args[0], ((Integer) args[1]).intValue(), (View) args[2]);
                            }
                            return null;
                        }
                    });
        } catch (Exception ignored) {
            return null;
        }
    }

    private static Object handleObjectMethod(Object proxy, Method method, Object[] args) {
        String name = method.getName();
        if ("toString".equals(name)) {
            return "IappCompat.V7lbViewBinder";
        }
        if ("hashCode".equals(name)) {
            return System.identityHashCode(proxy);
        }
        if ("equals".equals(name)) {
            return args != null && args.length > 0 && proxy == args[0];
        }
        return null;
    }
}
