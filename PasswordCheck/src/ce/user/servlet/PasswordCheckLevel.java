package ce.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ce.user.bean.Level;
import ce.user.bean.Rule;
import ce.user.bean.Strength;
import ce.user.common.PasswordHybridRegExpValidation;
import ce.user.parsexml.ParseXml;


public class PasswordCheckLevel extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PasswordCheckLevel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuffer levelItem = new StringBuffer();
		String password = request.getParameter("password");
		boolean flag = PasswordHybridRegExpValidation.passwordMatchLength(password);
		if(flag == false){
			out.print("0");
		}else{
			Map<String,String> map = ParseXml.GetRegexpLib();
			Iterator<Entry<String, String>> it = map.entrySet().iterator();	 
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				Pattern p = Pattern.compile(entry.getValue());
				Matcher m = p.matcher(password);
				if(m.find() == true){
					levelItem.append(entry.getKey()).append(",");
				}else{
					continue;
				}
			}
			String level = PasswordCheckLevel.GetLevelMath(levelItem.toString());
			if(level.contains("3")){
				out.print("3");
			}else if(level.contains("2")){
				out.print("2");
			}else if(level.contains("1")){
				out.print("1");
			}
		}
		 out.close();	 
	}
	public static String GetLevelMath(String levelItem){
		Strength strength = ParseXml.getLevelInfo();
		List<Level> list = strength.getList();
		StringBuffer levelString = new StringBuffer();
		for(int i=0;i<list.size();i++){
			String levelName = list.get(i).getLevelValue();
			List<Rule> ruleList = list.get(i).getList();
			for(int j=0;j<ruleList.size();j++){
				String ruleName = ruleList.get(j).getRuleName();
				String paramName = ruleList.get(j).getParamName();
				String paramValue = ruleList.get(j).getParamValue();
				String[] levelValues = paramValue.split(",");
				if("or".equals(paramName)){
					for(int k=0;k<levelValues.length;k++){
						if(levelItem.contains(levelValues[k])){
							levelString.append(levelName);
							break;
						}
					}
				}else if("and".equals(paramName)){
					int index = 0 ;
					for(int k=0;k<levelValues.length;k++){
						if(levelItem.contains(levelValues[k])){
							index = index+1;
						}
					}
					if(index == levelValues.length){
						levelString.append(levelName);
					}else{
						break;
					}
				}
			}
		}
		return levelString.toString();
	} 
}
