import java.util.ArrayList;
import java.util.List;

class HtmlTag {
    private final String name;
    private final String content;
    private final List<HtmlTag> children;

    public HtmlTag(String name) {
        this.name = name;
        this.content = "";
        this.children = new ArrayList<>();
    }

    public HtmlTag(String name, String content) {
        this.name = name;
        this.content = content;
        this.children = new ArrayList<>();
    }

    public void addChild(HtmlTag child) {
        children.add(child);
    }

    public String generateHtml(int indentLevel) {
        StringBuilder html = new StringBuilder();
        String indent = "  ".repeat(indentLevel);

        html.append(indent).append("<").append(name).append(">\n");

        if (!content.isEmpty()) {
            html.append(indent).append("  ").append(content).append("\n");
        }

        for (HtmlTag child : children) {
            html.append(child.generateHtml(indentLevel + 1));
        }

        html.append(indent).append("</").append(name).append(">\n");
        return html.toString();
    }
}

public class Main {
    public static void main(String[] args) {

        HtmlTag html = new HtmlTag("html");
        HtmlTag head = new HtmlTag("head");
        HtmlTag title = new HtmlTag("title", "Pagina HTML ");
        HtmlTag body = new HtmlTag("body");
        HtmlTag h1 = new HtmlTag("h1", "Bun venit!");
        HtmlTag p = new HtmlTag("p", "Aceasta este o pagină HTML.");
        HtmlTag footer = new HtmlTag("footer", "© footer");


        head.addChild(title);
        body.addChild(h1);
        body.addChild(p);
        body.addChild(footer);

        html.addChild(head);
        html.addChild(body);


        System.out.println(html.generateHtml(0));
    }
}
