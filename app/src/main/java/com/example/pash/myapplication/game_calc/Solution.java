package com.example.pash.myapplication.game_calc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PASH on 2016/6/14.
 */
public class Solution {
    private  final int MAX_STATE = 362880;
    private  final int dx[] = {-1, 1, 0, 0};
    private  final int dy[] = {0, 0, -1, 1};
    private  boolean[] vst = new boolean[MAX_STATE];
    private  List<Integer> sol = new ArrayList<>();
    private  int[][] st = new int[MAX_STATE][9];

    private  Node[] pre = new Node[MAX_STATE];
    private  int[] fact = new int[9];
    private  int[] dist = new int[MAX_STATE];

    class Node {
        int x, dir;
    }

    private  void init_lookup_table() {

        fact[0] = 1;
        for( int i = 1; i < 9; i++ ) {
            fact[i] = fact[i-1] * i;
        }
    }


    private  int try_to_insert(int s) {

        int code = 0;
        for( int i = 0; i < 9; i++ ) {
            int cnt = 0;
            for( int j = i + 1; j < 9; j++ ) {
                if( st[s][j] < st[s][i] )
                    cnt++;
            }
            code += fact[8-i]*cnt;
        }

        if( vst[code] )
            return 0;
        vst[code] = true;
        return 1;
    }
    private  int bfs() {
        init_lookup_table();
        int front = 1, rear = 2;
        pre[front] = new Node();
        pre[front].x = 0;
        while( front < rear ) {
            int[] s = st[front];
            boolean flag = true;

            for (int i = 0; i < s.length; i++) {
                if (s[i] != i+1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                printPath(front);
                return front;
            }
            int z;
            for( z = 0; z < 9; z++ )
                if( s[z] == 9 )
                    break;
            int x = z / 3, y = z % 3;
            for( int d = 0; d < 4; d++ ) {
                int newx = x + dx[d];
                int newy = y + dy[d];
                int newz = newx * 3 + newy;
                if( newx >= 0 && newx < 3 && newy >= 0 && newy < 3 ) {
                    int[] t = st[rear];
                    for (int i = 0; i < t.length; i++) {
                        t[i] = s[i];
                    }
                    t[newz] = s[z];
                    t[z] = s[newz];
                    dist[rear] = dist[front] + 1;
                    pre[rear] = new Node();
                    pre[rear].x = front;
                    pre[rear].dir = d;
                    if( try_to_insert(rear) != 0 ) {
                        rear++;
                    }
                }
            }
            front++;
        }
        return 0;
    }

    private  void printPath(int x) {
        if( pre[x].x != 0 ) {
            printPath(pre[x].x);
            sol.add(pre[x].dir);
        }
    }

    public  List<Integer> getSolution(int[] cur) {
        st[1] = cur;
        bfs();
        return sol;
    }
}
