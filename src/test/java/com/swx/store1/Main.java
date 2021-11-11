package com.swx.store1;

import java.io.*;

/**
 * @author admin
 * @Date 2021/11/11 20:07
 * @Description 思路主要参考：    https://www.cnblogs.com/dusf/p/kmp.html    ，

 *              理解的前提为：明白如果abcdabce在e的时候匹配不成功不用从第0个a（为统一，下标从0开始）开始匹配，
 *              而是直接从重复的abc后的一个元素d开始匹配即可，因为前后有重复的abc ，
 *              假设已经知道e不匹配要跳回的元素是d的话（next数组），那么这就是一个不断匹配的过程
 *              即：不成功就跳回到前一个重复的串的后一个元素，匹配。。。成功就跳回到前一个重复的串的后一个元素，匹配。。。一直重复下去
 *              明白了这点下一步就是看跳回哪个下标，跳回的下标用next数组存放，因此next数组存放的就是：
 *              //next[k]表示的是第k个如果不匹配应该跳到哪个点，从第0个数开始----k点之前哦  ---也就是说第k点之前有多少个重复的！！！
     *         //比如a，   b，   c，    a，  b，  d 对应的next数组为
     *         //   -1，  0,    0,     0,  1,   2，
     *         //这样正好匹配当a不匹配时只能跳回第0个数a开始匹配，
     *         // 当b不能匹配时可以确保第二个a已经匹配，因此可以直接跳回第1个数（第0个b）开始匹配
 *             //也是因此第0个数不用判断（直接给-1，标识应该移动母串而不是子串了，因为第0个数都不匹配），最后一个数也是不用参与判断的（原因见上5行注释）
 *
 *             明白了上述过程后重点就是计算next数组，在计算next数组时候会发现一件事
 *              ：kmp就是：比较字母串是否匹配，               不匹配就往next数组指定的下标跳
 *     ：计算next数组就是: 比较当前字符串的后面与前面是否匹配  ， 不匹配就？
 *                  应该已经有一些感觉了，不匹配就和比较的过程一样呗，往next数组指定的下标跳呗~！！！！！！！！！！最关键的点结束了
 *                  （跳的时候不用太担心边界，是因为next数组存放的是一定是往前跳的，往后跳是不可能的）
 *                  这两个步骤就是重复的呀！！！！，而且最难理解的就在于k=next[k]，即往前一个可以跳的地方跳再重新匹配这个过程
 *                  之后的一些细节应该不太难了
 * @return
 * @param
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter BufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bufferedReader.readLine());
        String s1 = bufferedReader.readLine();
        int m = Integer.parseInt(bufferedReader.readLine());
        String s2 = bufferedReader.readLine();
        char[] p = s1.toCharArray();
        char[] s = s2.toCharArray();
        int[] next = ne(p);
        //计算完ne数组下面计算的是kmp的输出
        /*
        这里的写法和求next数组基本一模一样，但是需要注意的是第0个数和最后一个数需要参与比较，
        因此改动：1.j=-1开始，2.while (j <= s.length - 1)中的“=”；
        然后由于求的是所有匹配结果，因此在匹配后加入特判k == p.length - 1，为true时
                    表示匹配成功，此时应该输出结果，以及当成此节点匹配失败重新开始匹配值（k = next[k];）
         */
        int k = -1, j = -1;
        while (j <= s.length - 1) {
            if (k == -1 || s[j] == p[k]) {
                if (k == p.length - 1) {
                    BufferedWriter.write(j - k + " ");
                    k = next[k];
                    continue;
                }
                k++;
                j++;
            }else {
                k = next[k];
            }
        }
        bufferedReader.close();
        BufferedWriter.flush();
        BufferedWriter.close();
    }

    private static int[] ne(char[] p) {
        //next[k]表示的是k点之前有多少个重复的，从第0个数开始
        //比如a，   b，   c，    a，  b，  d 对应的next数组为
        //   -1，  0,    0,     0,  1,   2，
        //这样正好匹配当a不匹配时只能跳回第0个数a开始匹配，
        // 当b不能匹配时可以确保第二个a已经匹配，因此可以直接跳回第1个数（第0个b）开始匹配
        //也是因此第0个数不用判断（直接给-1，标识应该移动母串而不是子串了，因为第0个数都不匹配），最后一个数也是不用参与判断的（原因见上5行注释）
        int[] next = new int[p.length];
        next[0] = -1;
        int k = -1, j = 0;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}