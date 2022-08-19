package com.keyin.tree;

import static com.keyin.tree.BinaryTree.createTable;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class BinaryTree {
    Node root;

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }
    //adding in the write databse mysql parts
    public static void post(String inputs,String tree_string) throws Exception{
        final String inputsdb = (inputs);
        final String treeoutputs = (tree_string);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        //System.out.println("Converted String: " + strDate);
        try {
            Connection con = getConnection();
            PreparedStatement posted = con.prepareStatement("INSERT INTO BST_Trees (strDate, inputs, treeoutputs) VALUES ('"+strDate+"','"+inputsdb+"','"+treeoutputs+"')");
            posted.executeUpdate();
        } catch(Exception e) {System.out.println(e);    }
        finally {
            System.out.println("Insert into DB Completed!");
        }
    }
    public static void createTable() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS BST_Trees(id int NOT NULL AUTO_INCREMENT, strDate varchar(255),inputs varchar(255), treeoutputs varchar(255), PRIMARY KEY(id))");
            create.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        finally {
            System.out.println("Database is Ready!");
        };
    }
    public static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/BST";
            String username = "root";
            String password = "Hunter6$6$";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("connected to MYSQL DB");
            return conn;
        } catch(Exception e) {System.out.println(e);}
        return null;
    }
}





