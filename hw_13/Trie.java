package hw_13;

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

class Trie {

    public static final int ABC_SIZE = 128;
    Node root;

    public static void main(String[] args) {
        final var trie = new Trie();
        trie.insert("apple");
        if (!trie.search("apple")) {
            throw new RuntimeException("Wrong result: search");
        }
        if (trie.search("app")) {
            throw new RuntimeException("Wrong result: search");
        }
        if (!trie.startsWith("app")) {
            throw new RuntimeException("Wrong result: search");
        }
        trie.insert("app");
        if (!trie.search("app")) {
            throw new RuntimeException("Wrong result: search");
        }
        System.out.println("All is correct");
    }

    public Trie() {
        this.root = new Node();
    }

    public void insert(String word) {
        var node = root;
        for (var c : word.toCharArray()) {
            node = node.next(c);
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        var node = root;
        for (var c : word.toCharArray()) {
            if (node.hasSymbol(c)) {
                node = node.next(c);
            } else {
                return false;
            }
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        var node = root;
        for (var c : prefix.toCharArray()) {
            if (node.hasSymbol(c)) {
                node = node.next(c);
            } else {
                return false;
            }
        }
        return true;
    }

    static class Node {
        public Node[] child;
        public boolean isEnd;

        public Node() {
            this.child = new Node[ABC_SIZE];
            this.isEnd = false;
        }

        public boolean hasSymbol(char c) {
            return child[c] != null;
        }

        public Node next(char c) {
            if (child[c] == null) {
                child[c] = new Node();
            }
            return child[c];
        }
    }
}