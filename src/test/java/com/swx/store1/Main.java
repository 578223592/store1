package com.swx.store1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        详细思路见：https://www.acwing.com/solution/content/40978/
         */
        char[] charArray = new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();
        Stack numStack = new Stack<Integer>();
        Stack commandStack = new Stack<Character>();
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('(',0);
        //括号的优先级为顶级，因此可以不放入map中
        for (int i = 0; i < charArray.length; i++) {
            int num = 0;
            //charArray[i]是数字则开始读取数字
            if (isNum(charArray[i])) {
                while (i < charArray.length && isNum(charArray[i])) {
                    //读取数字
                    num = num * 10 + charArray[i] - '0';
                    i++;
                }
                //isNum(charArray[i])检测不通过之后应该送去其他if里面检测，但是由于这个循环结束会自动i++，会跳过这个i 因此这里手动i--
                i--;
                numStack.push(num);
            } else if (charArray[i] == '(') {
                commandStack.push(charArray[i]);
            } else if (charArray[i] == ')') {
                //遇见右括号直接计算，直到遇到左括号
                while ((Character) commandStack.peek() != '(') {
                    eval(numStack, commandStack);
                }
                commandStack.pop();
            } else {
                //如果外部运算符等级高就先入栈，如果外部运算符等级小于等于内部运算符等级就先计算内部运算符
                // 直到外部运算符等级更高
                //(的运算优先级为0；
                while (!commandStack.empty()&& map.get(charArray[i]) <= map.get((Character) commandStack.peek()) ) {
                    eval(numStack, commandStack);
                }
                commandStack.push(charArray[i]);
            }


        }
        //此时stack里面还有一些元素需要处理
        while (!commandStack.empty()) {
            eval(numStack, commandStack);
        }
        System.out.println(numStack.peek());
    }

    private static void eval(Stack numStack, Stack commandStack) {
//       由于有-，/的存在，因此a，b需要区分前后的
        int b = (Integer) numStack.peek();
        numStack.pop();
        int a = (Integer) numStack.peek();
        numStack.pop();

        char p = (Character) commandStack.peek();
        commandStack.pop();
        int result;
        if (p == '+') {
            result = a + b;
            numStack.push(result);
        }
        if (p == '-') {
            result = a - b;
            numStack.push(result);
        }
        if (p == '*') {
            result = a * b;
            numStack.push(result);
        }
        if (p == '/') {
            result = a / b;
            numStack.push(result);
        }
    }

    private static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

}
