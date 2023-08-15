import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Parser {
    public static void main(String[] args) throws XPathExpressionException, FileNotFoundException {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        XPathExpression xPathExpression = xpath.compile("//book[price>10 and publish_date[number(translate(self::publish_date, '-','')) > 20051231]]/@id | //book[price>10 and publish_date[number(translate(self::publish_date, '-','')) > 20051231]]/author | //book[price>10 and publish_date[number(translate(self::publish_date, '-', '')) > 20051231]]/title | //book[price>10 and publish_date[number(translate(self::publish_date, '-','')) > 20051231]]/genre | //book[price>10 and publish_date[number(translate(self::publish_date, '-','')) > 20051231]]/price | //book[price > 10 and publish_date[number(translate(self::publish_date, '-','')) > 20051231]]/publish_date | //book[price>10 and publish_date[number(translate(self::publish_date, '-','')) > 20051231]]/description");

        File file = new File("books.xml");
        InputSource inputSource = new InputSource(new FileInputStream(file));
        Object object = xPathExpression.evaluate(inputSource, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) object;
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getNodeName() + " - " + nodeList.item(i).getFirstChild().getNodeValue());
            if (nodeList.item(i).getNodeName().equals("description"))
                System.out.println();
            }
        }
    }

