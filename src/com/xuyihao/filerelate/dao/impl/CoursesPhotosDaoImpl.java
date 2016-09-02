package com.xuyihao.filerelate.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.filerelate.dao.CoursesPhotosDao;
import com.xuyihao.filerelate.entity.CoursesPhotos;

/**
 * 
 * @author Xuyh at 2016年9月2日 下午10:23:27.
 *
 */
@Component("CoursesPhotosDao")
public class CoursesPhotosDaoImpl implements CoursesPhotosDao {
	private DatabaseConnector databaseConnector = new DatabaseConnector();

	public boolean saveCoursesPhotos(CoursesPhotos coursesPhotos) {
		String sql = "insert into " + CoursesPhotos.BASE_TABLE_NAME + " values(null, '" + coursesPhotos.getCrs_ID() + "', '"
				+ coursesPhotos.getHeadPhoto_ID() + "', '" + coursesPhotos.getPhoto_ID_Combine() + "', '"
				+ coursesPhotos.getCrsPhoto_addTime() + "')";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteCoursesPhotos(String Crs_ID) {
		String sql = "delete from " + CoursesPhotos.BASE_TABLE_NAME + " where " + CoursesPhotos.BASE_COURSESPHOTOS_COURSE_ID
				+ " = '" + Crs_ID + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateCoursesPhotos(CoursesPhotos coursesPhotos) {
		String sql = "update " + CoursesPhotos.BASE_TABLE_NAME + " set " + CoursesPhotos.BASE_COURSESPHOTOS_HEADPHOTO_ID
				+ " = '" + coursesPhotos.getHeadPhoto_ID() + "', " + CoursesPhotos.BASE_COURSESPHOTOS_PHOTO_ID_COMBINE + " = '"
				+ coursesPhotos.getPhoto_ID_Combine() + "' where " + CoursesPhotos.BASE_COURSESPHOTOS_COURSE_ID + " = '"
				+ coursesPhotos.getCrs_ID() + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateCoursesPhotosBySql(String update) {
		if (this.databaseConnector.executeUpdate(update) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public CoursesPhotos query(String Crs_ID) {
		String sql = "select * from " + CoursesPhotos.BASE_TABLE_NAME + " where "
				+ CoursesPhotos.BASE_COURSESPHOTOS_COURSE_ID + " = '" + Crs_ID + "'";
		ResultSet rs = this.databaseConnector.executeQuery(sql);
		return this.getCoursesPhotoByResultSet(rs);
	}

	public CoursesPhotos queryBySql(String query) {
		ResultSet rs = this.databaseConnector.executeQuery(query);
		return this.getCoursesPhotoByResultSet(rs);
	}

	private CoursesPhotos getCoursesPhotoByResultSet(ResultSet resultSet) {
		CoursesPhotos photo = new CoursesPhotos();
		try {
			if (resultSet.next()) {
				photo.set_id(resultSet.getLong(CoursesPhotos.BASE_PHYSICAL_ID));
				photo.setCrs_ID(resultSet.getString(CoursesPhotos.BASE_COURSESPHOTOS_COURSE_ID));
				photo.setCrsPhoto_addTime(resultSet.getString(CoursesPhotos.BASE_COURSESPHOTOS_ADD_TIME));
				photo.setHeadPhoto_ID(resultSet.getString(CoursesPhotos.BASE_COURSESPHOTOS_HEADPHOTO_ID));
				photo.setPhoto_ID_Combine(resultSet.getString(CoursesPhotos.BASE_COURSESPHOTOS_PHOTO_ID_COMBINE));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photo;
	}

	public void closeDBConnection() {
		this.databaseConnector.close();
	}
}