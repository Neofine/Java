public class Main {
    public static void main(String[] args) {
        BST bst = new BST<Integer>();
        bst.dodaj(5);
        bst.dodaj(1);
        bst.dodaj(7);
        System.out.println(bst.szukaj(2));
        System.out.println(bst.szukaj(1));
        System.out.println(bst.szukaj(3));
        System.out.println(bst.szukaj(5));
        System.out.println(bst.szukaj(7));

        BST bstS = new BST<String>();
        bstS.dodaj("abc");
        bstS.dodaj("po");
        bstS.dodaj("2");
        System.out.println(bstS.szukaj("bst"));
        System.out.println(bstS.szukaj("main"));
        System.out.println(bstS.szukaj("abc"));
        System.out.println(bstS.szukaj("po"));
        System.out.println(bstS.szukaj("2"));
    }
}
