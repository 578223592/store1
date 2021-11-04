package com.swx.store1;


import java.util.*;

/**
 * Author: Admin
 * Date: 2021/11/4 8:28
 * FileName: Math
 * Description:
 */
public class Math {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        int m=scan.nextInt();
        int[] a=new int[n];
        int[] b=new int[m];
        for (int i = 0; i < n; i++) {
            a[i]=scan.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i]=scan.nextInt();
        }
        int i=0,j=0;
        while (i<n&&j<m){
            while (a[i]!=b[j]){
                j++;
            }
            i++;
            j++;
        }
        if (i==n){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    }
}
