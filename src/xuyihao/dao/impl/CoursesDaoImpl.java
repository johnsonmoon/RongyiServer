package xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import xuyihao.common.DatabaseConnector;
import xuyihao.dao.CoursesDao;
import xuyihao.entity.Courses;

/**
 * created by xuyihao on 2016/4/25
 * 
 * @describe 视频(视频教学)相关数据库操作封装类
 */
@Component("CoursesDao")
public class CoursesDaoImpl implements CoursesDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public CoursesDaoImpl() {
	}

	public void setConn(DatabaseConnector conn) {
		this.conn = conn;
	}

	public boolean saveCourses(Courses course) {
		String sql = "insert into " + Courses.BASE_TABLE_NAME + " values(null, '" + course.getCrs_ID() + "', '"
				+ course.getCrs_name() + "','" + course.getCrs_route() + "', '" + course.getAcc_ID() + "', '"
				+ course.getAuthor_ID() + "', " + course.getCrs_rep() + ", " + course.getCrs_comm() + ", "
				+ course.getCrs_like() + ", DATE_FORMAT('" + course.getCrs_addTime() + "', '%Y-%m-%d %H:%i:%s'))";
		boolean flag = false;
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean deleteCourses(String Crs_ID) {
		boolean flag = false;
		String sql = "delete from " + Courses.BASE_TABLE_NAME + " where " + Courses.BASE_COURSES_ID + " = '" + Crs_ID + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateCourses(Courses course) {
		boolean flag = false;
		String sql = "update " + Courses.BASE_TABLE_NAME + " set " + Courses.BASE_COURSES_NAME + " = '"
				+ course.getCrs_name() + "', " + Courses.BASE_COURSES_ROUTE + " = '" + course.getCrs_route() + "', "
				+ Courses.BASE_COURSES_ACCOUNT_ID + " = '" + course.getAcc_ID() + "', " + Courses.BASE_COURSES_AUTHOR_ACCOUNT_ID
				+ " = '" + course.getAuthor_ID() + "', " + Courses.BASE_COURSES_REPOST_COUNT + " = " + course.getCrs_rep()
				+ ", " + Courses.BASE_COURSES_COMMON_COUNT + " = " + course.getCrs_comm() + ", "
				+ Courses.BASE_COURSES_LIKE_COUNT + " = " + course.getCrs_like() + ", " + Courses.BASE_COURSES_ADD_TIME + " = '"
				+ course.getCrs_addTime() + "' where " + Courses.BASE_COURSES_ID + " = '" + course.getCrs_ID() + "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateCourses(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public Courses queryByName(String Crs_name) {
		String sql = "select * from " + Courses.BASE_TABLE_NAME + " where " + Courses.BASE_COURSES_NAME + " = '" + Crs_name
				+ "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		Courses courses = getCourseFromResultSet(resultSet);
		this.conn.close();
		return courses;
	}

	public Courses queryById(String Crs_ID) {
		String sql = "select * from " + Courses.BASE_TABLE_NAME + " where " + Courses.BASE_COURSES_ID + " = '" + Crs_ID
				+ "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		Courses courses = getCourseFromResultSet(resultSet);
		this.conn.close();
		return courses;
	}

	public List<Courses> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + Courses.BASE_TABLE_NAME + " where " + Courses.BASE_COURSES_ACCOUNT_ID + " = '"
				+ Acc_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Courses> coursesList = getCoursesListFromResultSet(resultSet);
		this.conn.close();
		return coursesList;
	}

	public List<Courses> queryByAuthorId(String Author_ID) {
		String sql = "select * from " + Courses.BASE_TABLE_NAME + " where " + Courses.BASE_COURSES_AUTHOR_ACCOUNT_ID
				+ " = '" + Author_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Courses> coursesList = getCoursesListFromResultSet(resultSet);
		this.conn.close();
		return coursesList;
	}

	public List<Courses> queryByLimitOrdered(String OrderedBy, int ascOrDesc, int page, int size) {
		int offset = page * size;
		String sql = "select * from " + Courses.BASE_TABLE_NAME + " order by " + OrderedBy;
		if (ascOrDesc == 1) {
			sql += " asc ";
		} else if (ascOrDesc == -1) {
			sql += " desc ";
		} else {
			sql += " asc ";
		}
		sql += "limit " + offset + ", " + size + "";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Courses> coursesList = getCoursesListFromResultSet(resultSet);
		this.conn.close();
		return coursesList;
	}

	public ResultSet queryBySql(String query) {
		ResultSet resultSet = this.conn.executeQuery(query);
		return resultSet;
	}

	/**
	 * 通过ResultSet获取对象
	 *
	 *
	 */
	private Courses getCourseFromResultSet(ResultSet resultSet) {
		Courses courses = new Courses();
		try {
			if (resultSet.next()) {
				courses.set_id(resultSet.getLong(Courses.BASE_COURSES_PHYSICAL_ID));
				courses.setCrs_ID(resultSet.getString(Courses.BASE_COURSES_ID));
				courses.setCrs_name(resultSet.getString(Courses.BASE_COURSES_NAME));
				courses.setCrs_route(resultSet.getString(Courses.BASE_COURSES_ROUTE));
				courses.setAcc_ID(resultSet.getString(Courses.BASE_COURSES_ACCOUNT_ID));
				courses.setAuthor_ID(resultSet.getString(Courses.BASE_COURSES_AUTHOR_ACCOUNT_ID));
				courses.setCrs_rep(resultSet.getInt(Courses.BASE_COURSES_REPOST_COUNT));
				courses.setCrs_comm(resultSet.getInt(Courses.BASE_COURSES_COMMON_COUNT));
				courses.setCrs_like(resultSet.getInt(Courses.BASE_COURSES_LIKE_COUNT));
				courses.setCrs_addTime(resultSet.getString(Courses.BASE_COURSES_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	private List<Courses> getCoursesListFromResultSet(ResultSet resultSet) {
		List<Courses> coursesList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Courses courses = new Courses();
				courses.set_id(resultSet.getLong(Courses.BASE_COURSES_PHYSICAL_ID));
				courses.setCrs_ID(resultSet.getString(Courses.BASE_COURSES_ID));
				courses.setCrs_name(resultSet.getString(Courses.BASE_COURSES_NAME));
				courses.setCrs_route(resultSet.getString(Courses.BASE_COURSES_ROUTE));
				courses.setAcc_ID(resultSet.getString(Courses.BASE_COURSES_ACCOUNT_ID));
				courses.setAuthor_ID(resultSet.getString(Courses.BASE_COURSES_AUTHOR_ACCOUNT_ID));
				courses.setCrs_rep(resultSet.getInt(Courses.BASE_COURSES_REPOST_COUNT));
				courses.setCrs_comm(resultSet.getInt(Courses.BASE_COURSES_COMMON_COUNT));
				courses.setCrs_like(resultSet.getInt(Courses.BASE_COURSES_LIKE_COUNT));
				courses.setCrs_addTime(resultSet.getString(Courses.BASE_COURSES_ADD_TIME));
				coursesList.add(courses);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coursesList;
	}

	public void closeDBConnection() {
		this.conn.close();
	}
}