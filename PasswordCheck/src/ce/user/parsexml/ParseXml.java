package ce.user.parsexml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ce.user.bean.Level;
import ce.user.bean.Rule;
import ce.user.bean.Strength;
import ce.user.common.XMLManager;

public class ParseXml {

	public static Strength getLevelInfo(){
		Strength strength = new Strength();
		String fileName = ParseXml.class.getClassLoader().getResource("password.xml").getPath();
		Document xmlDoc = XMLManager.getXmlDoc(fileName);
		NodeList levelNodes = xmlDoc.getElementsByTagName("level");
		if(levelNodes != null){
			for(int i=0;i<levelNodes.getLength();i++){
				Node ndLevel = levelNodes.item(i);
				if(ndLevel.getNodeType() != Node.ELEMENT_NODE){
					continue;
				}
				Level level = new Level();
				String levelValue = XMLManager.getNodeAttribute(ndLevel, "value");
				level.setLevelValue(levelValue);
				NodeList ruleList = ndLevel.getChildNodes();
				for(int j=0;j<ruleList.getLength();j++){
					Node ndRule = ruleList.item(j);
					if(ndRule.getNodeType() != Node.ELEMENT_NODE){
						continue;
					}
					Rule rule = new Rule();
					String ruleName = XMLManager.getNodeAttribute(ndRule, "name");
					rule.setRuleName(ruleName);
					NodeList paramList = ndRule.getChildNodes();
					for(int x = 0;x<paramList.getLength();x++){
						Node ndParam = paramList.item(x);
						if(ndParam.getNodeType() != Node.ELEMENT_NODE){
							continue;
						}
						String paramName = XMLManager.getNodeAttribute(ndParam, "name");
						rule.setParamName(paramName);
						String paramValue = XMLManager.getNodeValue(ndParam, true);
						rule.setParamValue(paramValue);
					}
					level.addRule(rule);
				}
				strength.addLevel(level);
			}	
		}
		return strength;
		
	}
	
	
	public static Map<String,String> GetRegexpLib(){
		String fileName = ParseXml.class.getClassLoader().getResource("password.xml").getPath();
		Document xmlDoc = XMLManager.getXmlDoc(fileName);
		Node root = xmlDoc.getDocumentElement();
		Node ndRegLib = XMLManager.findChildNode("regexp-lib",root);
		NodeList ndItems = ndRegLib.getChildNodes();
		Map<String,String> map = new HashMap<String,String>();
		if(ndItems !=null){
			for(int i=0;i<ndItems.getLength();i++){
				Node ndLevel = ndItems.item(i);
				if(ndLevel.getNodeType() != Node.ELEMENT_NODE){
					continue;
				}
				String name = XMLManager.getNodeAttribute(ndLevel, "name");
				String regexp = XMLManager.getNodeValue(ndLevel, true);
				map.put(name, regexp);
			}
		}
		return map;

	}
	public static void main(String[] args) {
		Strength strength = ParseXml.getLevelInfo();
		List<Level> list = strength.getList();
		for(int i=0;i<list.size();i++){
			String value = list.get(i).getLevelValue();
			System.out.println(value);
			List<Rule> listRule = list.get(i).getList();
			for(int j=0;j<listRule.size();j++){
				String ruleName = listRule.get(j).getRuleName();
				System.out.println(ruleName);
				String paramName = listRule.get(j).getParamName();
				System.out.println(paramName);
				String paramValue = listRule.get(j).getParamValue();
				System.out.println(paramValue);
			}	
		}
	}
}
