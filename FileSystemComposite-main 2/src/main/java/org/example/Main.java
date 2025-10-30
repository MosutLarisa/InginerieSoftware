package org.example;

public class Main {
    public static void main(String[] args) {


        Folder html = new Folder("html");
        Folder head = new Folder("head");
        Folder body = new Folder("body");


        File title = new File("title", "Pagina HTML");
        File h1 = new File("h1", "Bine ai venit!");
        File p = new File("p", "Aceasta este o pagină HTML.");
        File img = new File("img", "src=\"poza.jpg\" alt=\"exemplu\"", true);


        head.add(title);
        body.add(h1);
        body.add(p);
        body.add(img);
        html.add(head);
        html.add(body);


        System.out.println("<!DOCTYPE html>");
        System.out.println(html.getContent(0));
    }
}
