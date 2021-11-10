package com.swx.store1;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter BufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        int[] nums = new int[n];
        s=bufferedReader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }
        //为了方便是否滑出窗口，因此队列q存放的是nums的元素下标
        int[] q = new int[n + 1];
        int hh = 0;//hh=0，一是方便程序操作取消特判，二是最开始无论对队列或者栈第一步一定是先入
        int tt = -1;
        for (int i = 0; i < n; i++) {
            //如果栈头已经出窗口，那么即弹出
            if (tt >= hh && q[hh]<i-k+1){
                hh++;
            }
            //去除逆序对，保证队列的元素是单增排列
            while (tt>=hh&&nums[q[tt]]>=nums[i]){
                tt--;
            }
            //此时还剩两个操作:将当前元素入栈，输出栈头的元素
            //必须要先入栈，因为此时栈可能已经没有元素了
            q[++tt]=i;
            if (i>=k-1){
                BufferedWriter.write(nums[q[hh]]+" ");
            }

        }
        BufferedWriter.write("\n");
        //下面是输出最大的元素，注意没必要将最小和最大写一起，因为重复一倍对大O表示法的时间复杂度是一样的
        hh=0;tt=-1;
        for (int i = 0; i < n; i++) {
            //如果栈头已经出窗口，那么即弹出
            if (tt >= hh && q[hh]<i-k+1){
                hh++;
            }
            //去除逆序对，保证队列的元素是单减排列
            while (tt>=hh&&nums[q[tt]]<=nums[i]){
                tt--;
            }
            //此时还剩两个操作:将当前元素入栈，输出栈头的元素
            //必须要先入栈，因为此时栈可能已经没有元素了
            q[++tt]=i;
            if (i>=k-1){
                BufferedWriter.write(nums[q[hh]]+" ");
            }

        }
        BufferedWriter.write("\n");
        bufferedReader.close();
        BufferedWriter.flush();
        BufferedWriter.close();
    }
}