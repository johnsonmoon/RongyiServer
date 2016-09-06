package xuyihao.tools.utils;

import java.util.List;
import java.util.Map;

/**
 * 生成 JDBC SQL 语句的工具类
 *
 * Created by Xuyh at 2016/08/19 下午 01:13.
 */
public class SQLUtils {
	/**
	 * Getting insert sentence like "insert into Table values('A', 'B', 1, 4.0)"
	 *
	 * @param tableName name of the table
	 * @param values valueList
	 * @return
	 */
	public static String insertSentence(String tableName, List<Object> values) {
		String sql = "insert into ";
		sql += "values(";
		if (values.size() == 1) {
			sql += convertToString(values.get(0));
		} else {
			sql += convertToString(values.get(0));
			for (int i = 1; i < values.size(); i++) {
				sql += (", " + convertToString(values.get(i)));
			}
		}
		sql += ")";
		return sql;
	}

	/**
	 * Getting sentence of deleting data from table like "delete from Table"
	 *
	 * @param tableName name of the table
	 * @return
	 */
	public static String deleteSentence(String tableName) {
		String sql = "delete from " + tableName;
		return sql;
	}

	/**
	 * Getting sentence of deleting data from table like "delete from Table where
	 * A = 'a', B = 4"
	 *
	 * @param tableName name of the table
	 * @param where query sentence
	 * @return
	 */
	public static String deleteSentence(String tableName, Map<String, Object> where) {
		String sql = "delete from " + tableName + " where ";
		for (String key : where.keySet()) {
			sql += (key + " = " + convertToString(where.get(key)) + " and ");
		}
		sql = sql.substring(0, sql.length() - 5);
		return sql;
	}

	/**
	 * Getting sentence of updating like "update Table set A = 'a', B = 4"
	 *
	 * @param tableName name of the table
	 * @param update updating params
	 * @return
	 */
	public static String updateSentence(String tableName, Map<String, Object> update) {
		String sql = "update " + tableName + " set ";
		for (String updateKey : update.keySet()) {
			sql += (updateKey + " = " + convertToString(update.get(updateKey)) + ", ");
		}
		sql = sql.substring(0, sql.length() - 2);
		return sql;
	}

	/**
	 * Getting sentence of updating like "update Table set A = 'a', B = 4 where C
	 * = 'a' and D = 5"
	 *
	 * @param tableName name of the table
	 * @param update updating params
	 * @param where query params
	 * @return
	 */
	public static String updateSentence(String tableName, Map<String, Object> update, Map<String, Object> where) {
		String sql = "update " + tableName + " set ";
		for (String updateKey : update.keySet()) {
			sql += (updateKey + " = " + convertToString(update.get(updateKey)) + ", ");
		}
		sql = sql.substring(0, sql.length() - 2);
		sql += " where ";
		for (String whereKey : where.keySet()) {
			sql += (whereKey + " = " + convertToString(where.get(whereKey)) + " and ");
		}
		sql = sql.substring(0, sql.length() - 5);
		return sql;
	}

	/**
	 * Getting select sentence like "select A, B from Table where C = 'a' and D =
	 * 3"
	 *
	 * @param tableName name of the table
	 * @param selectParam params for selecting
	 * @param where params for the condition
	 * @return
	 */
	public static String selectSentence(String tableName, List<String> selectParam, Map<String, Object> where) {
		String sql = "select ";
		for (String select : selectParam) {
			sql += (select + ", ");
		}
		sql = sql.substring(0, sql.length() - 2);
		sql += (" from " + tableName + " where ");
		for (String whereKey : where.keySet()) {
			sql += (whereKey + " = " + convertToString(where.get(whereKey)) + " and ");
		}
		sql = sql.substring(0, sql.length() - 5);
		return sql;
	}

	/**
	 * Getting sentence like "select * from Table where A = 'ad', B = 4"
	 *
	 * @param tableName name of the table
	 * @param where params for the condition
	 * @return
	 */
	public static String selectSentence(String tableName, Map<String, Object> where) {
		String sql = "select *";
		sql += (" from " + tableName + " where ");
		for (String whereKey : where.keySet()) {
			sql += (whereKey + " = " + convertToString(where.get(whereKey)) + " and ");
		}
		sql = sql.substring(0, sql.length() - 5);
		return sql;
	}

	/**
	 * Getting sentence like "select * from Table"
	 *
	 * @param tableName
	 * @return
	 */
	public static String selectSentence(String tableName) {
		String sql = "select *";
		sql += (" from " + tableName);
		return sql;
	}

	private static String convertToString(Object value) {
		String returnValue;
		if (value instanceof java.lang.String) {
			returnValue = ("'" + value + "'");
		} else if (value instanceof java.util.Date) {
			returnValue = ("'" + String.valueOf(value) + "'");
		} else {
			returnValue = String.valueOf(value);
		}
		return returnValue;
	}
}
