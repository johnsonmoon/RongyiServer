package com.xuyihao.tools;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

public class RespondTextTool {

	public static void PrintJSON(HttpServletResponse response, JSONObject jsonObject) throws IOException {
		response.getWriter().println(jsonObject.toString());
	}

	public static void PrintString(HttpServletResponse response, String msg) throws IOException {
		response.getWriter().println(msg);
	}

}
