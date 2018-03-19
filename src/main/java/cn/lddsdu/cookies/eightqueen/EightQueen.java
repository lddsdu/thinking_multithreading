package cn.lddsdu.cookies.eightquen;

import java.util.Arrays;

/**
 * Created by jack on 18/3/18.
 */


public class EightQueen {
    private final int MAX_NUM = 8;
    int [][] board = new int[MAX_NUM][MAX_NUM];

    /**
     * 检查(x,y)的位置是否可以放置一位皇后
     * @param x
     * @param y
     * @return
     */
    public boolean check(int x,int y){
        for (int i = 0 ; i < y; i++){
            //检查竖直位置
            if(board[x][i] == 1){
                return false;
            }
            //检查左斜边
            if(x-1-i >= 0 && board[x-1-i][y-1-i] == 1){
                return false;
            }
            //检查右斜边
            if(x+1+i < MAX_NUM && board[x+1+i][y-1-i] == 1){
                return false;
            }
        }
        return true;
    }

    /**
     * 放置皇后，递归程序
     * 先假设这里的settlequeen为可以解决问题的方法
     *
     * 回归程序：需要指定何时退出
     *          需要指定
     *
     * @param y
     */
    public boolean settleQueen(int y){
        if(y == MAX_NUM){
            return true;
        }

        // 尝试各种放置方法
        for(int i = 0; i < MAX_NUM; i++){

            //将当前行清零，以免回溯的时候出现脏数据
            for(int x = 0 ; x < MAX_NUM; x++){
                board[x][y] = 0;
            }

            //检查是否符合规则
            if(check(i,y)){
                board[i][y] = 1;
                if(settleQueen(y + 1)){
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < MAX_NUM; i++){
            for(int j =0 ; j < MAX_NUM; j++){
                builder.append((board[i][j]));
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        EightQueen queen = new EightQueen();
        queen.settleQueen(0);
        System.out.println(queen);
    }

}
