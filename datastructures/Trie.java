/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author Jugal Kalal
 */
public class Trie {

    static class TrieNode {

        TrieNode[] children = new TrieNode[128];
        boolean leaf;
    }

    public static void insertString(TrieNode root, String s) {
        TrieNode cur = root;
        for (char ch : s.toCharArray()) {
            if (cur.children[ch] == null) {
                cur.children[ch] = new TrieNode();
            }
            cur = cur.children[ch];
        }
        cur.leaf = true;
    }

    public static void printSorted(TrieNode node, String s) {
        for (char ch = 0; ch < node.children.length; ch++) {
            TrieNode child = node.children[ch];
            if (child != null) {
                printSorted(child, s + ch);
            }
        }
        if (node.leaf) {
            System.out.println(s);
        }
    }

    // Usage example
    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        insertString(root, "hello");
        insertString(root, "world");
        insertString(root, "hi");
        printSorted(root, "");
    }
}
