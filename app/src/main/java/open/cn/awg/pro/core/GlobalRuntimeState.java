/**
 ******************************************************************************
 * @file    GlobalRuntimeState.java
 * @author  Typheye
 * @brief   Shared in-process runtime state used across file and chat screens.
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

public class GlobalRuntimeState {
    //全局变量格式: 访问修饰词 static 变量类型 变量名 = 值
    public static String d1_rootpath = "%";
    public static Boolean i2_allowDisTouch = false;
}