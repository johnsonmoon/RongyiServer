package xuyihao.filerelate.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import xuyihao.common.DatabaseConnector;
import xuyihao.filerelate.dao.CoursesVedioDao;
import xuyihao.filerelate.entity.CoursesVedio;

/**
 * 
 * @Author Xuyh created at 2016年9月18日 下午5:14:36
 */
@Component("CoursesVedioDao")
public class CoursesVedioDaoImpl implements CoursesVedioDao {
	private DatabaseConnector databaseConnector = new DatabaseConnector();

	public void closeDBConnection() {
		this.databaseConnector.close();
	}

	public boolean saveCoursesVedio(CoursesVedio coursesVedio) {
		String sql = "insert into " + CoursesVedio.BASE_TABLE_NAME + " values(null, '" + coursesVedio.getCrs_ID() + "', '"
				+ coursesVedio.getVedio_ID() + "', '" + coursesVedio.getCrsVedio_addTime() + "')";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteCoursesVedio(String Crs_ID) {
		String sql = "delete from " + CoursesVedio.BASE_TABLE_NAME + " where " + CoursesVedio.BASE_COURSESVEDIO_COURSES_ID
				+ " = '" + Crs_ID + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateCoursesVedio(CoursesVedio coursesVedio) {
		String sql = "update " + CoursesVedio.BASE_TABLE_NAME + " set " + CoursesVedio.BASE_COURSESVEDIO_VEDIO_ID + " = '"
				+ coursesVedio.getVedio_ID() + "' where " + CoursesVedio.BASE_COURSESVEDIO_COURSES_ID + " = '"
				+ coursesVedio.getCrs_ID() + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateCoursesVedioBySql(String update) {
		if (this.databaseConnector.executeUpdate(update) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public CoursesVedio query(String Crs_ID) {
		String sql = "select * from " + CoursesVedio.BASE_TABLE_NAME + " where " + CoursesVedio.BASE_COURSESVEDIO_COURSES_ID
				+ " = '" + Crs_ID + "'";
		ResultSet rs = this.databaseConnector.executeQuery(sql);
		return this.getCoursesVedioByResultSet(rs);
	}

	public CoursesVedio queryBySql(String query) {
		ResultSet rs = this.databaseConnector.executeQuery(query);
		return this.getCoursesVedioByResultSet(rs);
	}

	private CoursesVedio getCoursesVedioByResultSet(ResultSet resultSet) {
		CoursesVedio coursesVedio = new CoursesVedio();
		try {
			if (resultSet.next()) {
				coursesVedio.set_id(resultSet.getLong(CoursesVedio.BASE_COURSESVEDIO_PHYSICAL_ID));
				coursesVedio.setCrs_ID(resultSet.getString(CoursesVedio.BASE_COURSESVEDIO_COURSES_ID));
				coursesVedio.setVedio_ID(resultSet.getString(CoursesVedio.BASE_COURSESVEDIO_VEDIO_ID));
				coursesVedio.setCrsVedio_addTime(resultSet.getString(CoursesVedio.BASE_COURSESVEDIO_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coursesVedio;
	}
}