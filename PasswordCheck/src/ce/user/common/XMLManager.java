package ce.user.common;


	import java.io.IOException;

	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;

	import org.w3c.dom.Document;
	import org.w3c.dom.Element;
	import org.w3c.dom.Node;
	import org.xml.sax.SAXException;

	public class XMLManager {
		public static  Document getXmlDoc(String fileName){
			Document xmlDoc = null;
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();;
				xmlDoc = builder.parse(fileName);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return xmlDoc;
		}
				
		public static Node findChildNode(String childName,Node aParent){         //将节点找到
			if(aParent == null){
				return null;
			}else{
				Node child = aParent.getFirstChild();
				while (child != null)
				{
					if (childName.equals(child.getNodeName())) {
						return child;					
					}
					child = child.getNextSibling();
				}
			}
			return null;	
		}
		
		
		public static String getNodeValue(Node sNode,boolean isTrim)     //找节点下面的值
		{
			if (sNode==null) {
				return null;
			}
			
			try {

				String sv = sNode.getFirstChild().getNodeValue();
				if (isTrim) {
					sv = sv.trim();
				}
				return sv;

			} catch (Exception e) {
				return null;
			}

		}
		
		
		public static String getNodeAttribute(Node node,String strNodeAttributeName)   //找节点下面的属性值
		{  
			if (node==null) return null;

				if (node.getNodeType()==Element.ELEMENT_NODE)
				{
					Element element = (Element)node;
					return element.getAttribute(strNodeAttributeName);
				}
				return null;

		}
}
