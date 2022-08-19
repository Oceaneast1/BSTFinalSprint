package com.keyin.tree;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static com.keyin.tree.BinaryTree.createTable;
import static com.keyin.tree.BinaryTree.post;

public class Main {



    public static void main(String[] args) throws Exception {


        String inputs = "30 11 67 4 88 29 90";
        Gson gson = new Gson();

        BinaryTree bt = new BinaryTree();
        bt.add(30);
        bt.add(11);
        bt.add(67);
        bt.add(4);
        bt.add(88);
        bt.add(29);
        bt.add(90);

        createTable();
        String tree_string = gson.toJson(bt);
        post(inputs, tree_string);

        System.out.println("Print in order->");
        bt.traverseInOrder(bt.root);

        System.out.println("\n\nPrint Tree Structure");
        bt.root.print();
    }

}
