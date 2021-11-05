package com.swx.store1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Author: Admin
 * Date: 2021/11/4 8:28
 * FileName: Math
 * Description:数组模拟静态链表，链表中的下一个快的地址用数组下标来表示。
 * 因此需要区分：数组中相邻的元素在实现的静态链表中不一定相邻，链表中相邻的元素在数组中也不一定相邻
 */
public class Main {


    public static void main(String[] args) throws IOException {
        int idx = 0;
        int head = -1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(reader.readLine());
        int[] value = new int[2000000];
        int[] next = new int[2000000];
        //next数组中存放-1表示这个元素就是尾元素了
        for (int i = 0; i < 2000000; i++) {
            next[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            String[] s = reader.readLine().split(" ");
            if ("H".equals(s[0])) {
                head = addToHead(Integer.parseInt(s[1]), value, next, idx, head);
                idx++;
            } else if ("D".equals(s[0])) {
                int newHead = delete(Integer.parseInt(s[1]), value, next, idx, head);
                head = (newHead ==-2?head:newHead);

            } else if ("I".equals(s[0])) {
                insert(Integer.parseInt(s[1]), Integer.parseInt(s[2]), value, next, idx);
                idx++;
            } else {
                System.out.println("error!");
            }
        }
        int i = head;
        while (i!= -1) {
            System.out.print(value[i] + " ");
            i = next[i];
        }
    }

    private static void insert(int k, int x, int[] value, int[] next, int idx) {
        //先将插入的块填入值，然后连接起来，最后再将上一个块指向这个块
        //注意下标的转化，插入的第k个元素表示为value[k-1]
        value[idx] = x;
        next[idx] = next[k - 1];
        next[k - 1] = idx;

    }

    private static int delete(int k, int[] value, int[] next, int idx,int head) {
        //首先读取到下个元素的下个元素的next存放的值，然后修改这个元素的next存放的值即可
        //需要注意下个元素的下个元素不是k++，而是next[next[k-1]],原因在上方：Description中
        if (k == 0) {
            return next[head];
        }
        if (next[k - 1] == -1) {
            return -2;
        }

        next[k - 1] = next[next[k - 1]];
        return -2;
    }

    private static int addToHead(int x, int[] value, int[] next, int idx, int head) {
//先将插入的块填入值，然后连接起来，最后再将head指向这个块
        value[idx] = x;
        next[idx] = head;
        head = idx;
        return head;
    }
}
