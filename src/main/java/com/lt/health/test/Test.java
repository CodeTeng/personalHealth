package com.lt.health.test;

/**
 * @description: 测试copilot
 * @author: 狂小腾
 * @date: 2022/4/4 13:05
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("hello world");
        int[] array = new int[]{1, 2, 3, 55, 33, 23};
        // 冒泡排序
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        // 手机号码正则表达式
        String phone = "17702304261";
        String regex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        boolean matches = phone.matches(regex);
        System.out.println(matches);
        // 用户名不能带特殊字符
        String username = "123456T123%";
        String regex1 = "^[a-zA-Z0-9_]{6,16}$";
        boolean matches1 = username.matches(regex1);
        System.out.println(matches1);
    }
}
