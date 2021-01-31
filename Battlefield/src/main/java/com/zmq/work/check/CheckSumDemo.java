package com.zmq.work.check;

public class CheckSumDemo {
    public static void main(String[] args) {
        /**
         * 源IP
         * 目的IP
         * 协议类型
         * UDP数据长度
         * 源端口
         * 目的端口
         * UDP数据
         * UDP数据
         */
        int[] udp = {
                0x0aaa, 0x3bbf,
                0xd20e, 0x960d,
                0x0011,
                0x001c,
                0xd123,
                0x2742,
                0x001c,
                0x0000,0x6c41,0x5661,0x0000,0x0e00,0xf8b6,0xd401,0x9313,0x0000,0x0000,0x0000
        };
        int checkSum = 0x285c;

        String s = udpCheckSum(udp);
        System.out.println(s);
        System.out.println(Integer.toHexString(checkSum).equals(s));
    }

    public static String udpCheckSum(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res += num;
            if (res >>> 16 != 0) {
                res = (res >>> 16) + (res & 0xffff);
            }
        }
        return Integer.toHexString(~res).substring(4);
    }
}