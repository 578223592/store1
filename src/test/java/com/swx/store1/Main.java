package com.swx.store1;

import java.io.*;

/*
创造一个数组维护他们之间的种族关系
animal[0]不管，说是哪个动物就用哪个下标
疑点：怎么在合并动物时更新他们的种族信息（谁吃谁的信息）：靠（路径%3）
 */
public class Main {
    static int[] fa = new int[50000 + 10];
    static int[] dis = new int[50000 + 10];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < dis.length; i++) {
            fa[i] = i;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] split = reader.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);
        int res = 0;
        for (int i = 0; i < k; i++) {
            split = reader.readLine().split(" ");
            int c = Integer.parseInt(split[0]);
            int x = Integer.parseInt(split[1]);
            int y = Integer.parseInt(split[2]);
            if (x > n || y > n) {
                res++;
                continue;
            }
            int xf = findFa(x);
            int yf = findFa(y);
            if (c == 1) {
                if (xf == yf && (dis[x] - dis[y]) % 3 != 0) {
                    res++;
                } else if (xf != yf) {
                    //真话，合并xy所在的集合
                    //把yf当成了合并集合之后的祖宗结点
                    fa[xf] = yf;
                    //满足的关系是（xf到xy的距离）+（x到xf的距离）=y到yf的距离；
                    dis[xf] = dis[y] - dis[x];
                }
            } else if (c == 2) {
                //(dis[a]-dis[b])%3==1表示可以a可以吃b
                //自己吃自己在下面判断中可以判断出来，因此不进行特判了
                if (xf == yf && (dis[x] - dis[y] - 1) % 3 != 0) {
                    //(dis[x] - dis[y]) % 3 != 1是错的，因为(a+b)%3!=a%3+b%3,可以带a，b为1，2
                    res++;
                } else if (xf != yf) {
                    //真话，合并xy所在的集合
                    //把yf当成了合并集合之后的祖宗结点
                    fa[xf] = yf;
                    //满足的关系是（xf到xy的距离）+（x到xf的距离）=y到yf的距离+1；
                    dis[xf] = 1 + dis[y] - dis[x];
                }
            } else {
                System.out.println("input error!");
            }
        }
        writer.write(res + "");
        reader.close();
        writer.flush();
        writer.close();

    }

    /*
    find函数里面由于自带路径压缩，因此路径压缩时候必须更新dis数组
     */
    public static int findFa(int x) {
        if (fa[x] != x) {
            //因为fa[x]的值会改变，因此先更新dis数组里面的值再更新fa[x]
//            dis[x] += dis[fa[x]];  注意这种写法是错的，因为findFa函数中路径压缩的思路是：如果此结点不是祖宗结点，那么就开始递归
//            fa[x] = findFa(fa[x]);   递归到最后的结点就是祖宗结点，出递归后更新的逻辑是儿子的儿子直接指向祖宗，儿子的儿子的儿子指向祖宗
//                                    是从上往下更新的，因此在这个过程中的dis数组的更新也必须遵从这个逻辑：从辈分高的开始更新，或者说是
//                                       出递归之后以递归更新后的最新父辈的dis数组来更新子辈的dis数组，因为父辈的dis数组的变化会影响子
//                                      辈，而子辈的dis数组的变化不会影响父辈；
//                                   如果直接那么写逻辑就变成了：先更新子辈的dis，然后更新父辈的，此时会造成父辈dis的变化不会传递给子辈，
//                                  自然就错误了
//
            int t=findFa(fa[x]);   //因此这种写法是对的，先出递归，再更新dis数组！！！！ y总强啊
            dis[x]+=dis[fa[x]];
            fa[x]=t;
        }
        return fa[x];
    }
}