//package com.pekingoj;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Scanner;
//
///**
// * Created on 2020/1/26
// *
// * @author WuYi
// */
//public class Main2 {
//    /**
//     * b = (XtX)-1XtY
//     *
//     * @param args
//     */
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String l1 = br.readLine();
//        int xNum = Integer.parseInt(l1.split(" ")[0]);
//        int lineNum = Integer.parseInt(l1.split(" ")[1]);
//        Matrix xMatrix = new Matrix(xNum, lineNum);
//        Matrix yMatrix = new Matrix(lineNum,1);
//        for (int i = 0; i < lineNum; i++) {
//            String dataLine = br.readLine();
//            String[] data = dataLine.split(" ");
//            for(int j=0;j<xNum;j++){
//                xMatrix.set(i,j,Double.parseDouble(data[j]));
//            }
//            yMatrix.set(i,1,Double.parseDouble(data[xNum]));
//        }
//        /**
//         * training the raw data
//         */
//        Matrix xTransMulX = xMatrix.transpose().times(xMatrix);
//        Matrix bMatrix = xTransMulX.inverse().times(xMatrix.transpose()).times(yMatrix);
//
//
//        /**
//         * reading the test data
//         */
//        int testNum =Integer.parseInt(br.readLine());
//
//        Matrix testMatrix =new Matrix(testNum,xNum);
//        for (int i=0;i<testNum;i++){
//            String[] split = br.readLine().split(" ");
//            for(int j=0;j<split.length;j++){
//                testMatrix.set(i,j,Double.parseDouble(split[j]));
//            }
//        }
//        Matrix res = testMatrix.times(bMatrix);
//        for (int i=0;i<res.getRowDimension();i++){
//            res.print(i,1);
//        }
//    }
//}
