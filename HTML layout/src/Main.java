import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class HtmlTag {
    private String name;
    private String text = "";
    private List<HtmlTag> children = new ArrayList<>();

    private static final List<String> VOID_TAGS = Arrays.asList(
            "br", "hr", "img", "meta", "link", "input"
    );

    public HtmlTag(String name) {
        this.name = name.toLowerCase();
    }

    public HtmlTag(String name, String text) {
        this.name = name.toLowerCase();
        this.text = text;
    }

    public void addChild(HtmlTag child) {
        if (VOID_TAGS.contains(name)) {
            throw new IllegalArgumentException("Tag-ul <" + name + "> nu poate avea copii!");
        }
        children.add(child);
    }

    public String generateHtml(int indentLevel) {
        String indent = "  ".repeat(indentLevel);
        StringBuilder sb = new StringBuilder();

        if (VOID_TAGS.contains(name)) {
            sb.append(indent).append("<").append(name).append(" />\n");
            return sb.toString();
        }

        sb.append(indent).append("<").append(name).append(">\n");

        if (!text.isEmpty()) {
            sb.append(indent).append("  ").append(text).append("\n");
        }

        for (HtmlTag child : children) {
            sb.append(child.generateHtml(indentLevel + 1));
        }

        sb.append(indent).append("</").append(name).append(">\n");
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        HtmlTag html = new HtmlTag("html");

        HtmlTag head = new HtmlTag("head");
        head.addChild(new HtmlTag("title", "Pagina  HTML"));

        HtmlTag body = new HtmlTag("body");
        body.addChild(new HtmlTag("h1", "Salut!"));
        body.addChild(new HtmlTag("p", "Aceasta este o pagina HTML."));
        body.addChild(new HtmlTag("br")); // tag void
        body.addChild(new HtmlTag("hr")); // tag void

        HtmlTag ul = new HtmlTag("ul");
        ul.addChild(new HtmlTag("li", "Primul element"));
        ul.addChild(new HtmlTag("li", "Al doilea element"));
        body.addChild(ul);

        html.addChild(head);
        html.addChild(body);

        System.out.println("<!DOCTYPE html>");
        System.out.println(html.generateHtml(0));
    }
}
